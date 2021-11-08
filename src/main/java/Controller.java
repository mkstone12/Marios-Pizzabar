import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private final FileHandler FILE_HANDLER = new FileHandler();
    private final Menu MENU = new Menu(FILE_HANDLER.getMenuFromFile());
    private final UserInterface UI = new UserInterface();
    private final Statistics STATISTICS = new Statistics(UI);
    private ArrayList<Order> allActiveOrders;

    public void run() {
        // get allActiveOrders
        try {
            allActiveOrders = FILE_HANDLER.getStoredActiveOrders();
        } catch (JsonProcessingException e) {
            System.out.println("Warning: the active orders from previous session could not be loaded!");
        }
        boolean keepGoing = true;

        // to generate test orders
        /*for (int i = 0; i < 10; i++) {
            allActiveOrders.add(new Order("name " + i));
            allActiveOrders.get(i).addOrderLine(menu.getPizzaFromListNumber(i+1), 2);
        }*/

        // to populate archivedOrders
        /*for (Order order : allActiveOrders) {
            try {
                fileHandler.storeArchivedOrder(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        while (keepGoing) {

            int choice = UI.printMainMenu();

            switch (choice) {
                case 1 -> UI.printMenu(MENU.getListofPizzas());

                case 2 -> UI.printMessage(getActiveOrders());

                case 3 -> createOrder(MENU);

                case 4 -> editOrder();

                case 5 -> completeOrder();

                case 6 -> seeStats();

                case 0 -> keepGoing = saveAndQuit();
            }
        }
    }

    private boolean saveAndQuit() {
        try {
            FILE_HANDLER.storeActiveOrders(allActiveOrders);
            return false;
        } catch (IOException e) {
            UI.errorPrint("Warning: Storing of active orders failed. Quit aborted");
            return true;
        }
    }

    private void createOrder(Menu menu) {
        String name = UI.nameOfOrder();
        Order order = new Order(name);
        UI.printMenu(menu.getListofPizzas());
        while (true) {
            int toEndOrder = UI.toEndOrder();
            if (toEndOrder == 1) {

                Pizza pizzaNr = getValidPizza();
                int amount = UI.howMany();
                order.addOrderLine(pizzaNr, amount);
            } else {
                UI.printFinalOrder(order.stringOfOrderedPizzas(), order.getPrice(), order.getOrderDueTime());
                allActiveOrders.add(order);
                break;
            }
        }
    }

    private void editOrder() {

        // List active orders
        UI.printMessage(getActiveOrders());

        // Choose order to edit and what to edit
        boolean keepEditing = true;
        int orderToEdit = UI.orderToEdit(allActiveOrders.size());

        while (keepEditing) {
            int choice = UI.editMenu();

            //Delete order
            if (choice == 3) {
                allActiveOrders.remove(orderToEdit);
                keepEditing = false;
            }
            if (choice == 0) {
                keepEditing = false;
            }

            //Add to order
            else if (choice == 1) {

                //Pizza to add and amount of it
                UI.printMenu(MENU.getListofPizzas());
                Pizza pizzaNr = getValidPizza();
                int amount = UI.howMany();

                ArrayList<OrderLine> activeOrderLines = allActiveOrders.get(orderToEdit).getOrderLines();

                //Loops over orderlines in order to check if there is orderline for pizza
                for (int i = 0; i < activeOrderLines.size(); i++) {
                    if (pizzaNr.getPizzaNr() == activeOrderLines.get(i).getPizza().getPizzaNr()) {
                        //If orderline with pizza exist, add amount to orderline
                        allActiveOrders.get(orderToEdit).editOrderLine(i, amount);
                        break;
                    } else if (i == activeOrderLines.size() - 1) {
                        //else create new orderline
                        allActiveOrders.get(orderToEdit).addOrderLine(pizzaNr, amount);
                        break;
                    }
                }
            }

            //remove from order
            else if (choice == 2) {
                //Get pizza to remove and amount

                UI.printOrderLinesInOrder(allActiveOrders.get(orderToEdit).getOrderLines());
                Pizza pizzaNr = getValidPizza();

                int amount = UI.howMany();

                ArrayList<OrderLine> activeOrderLines = allActiveOrders.get(orderToEdit).getOrderLines();

                //Loop to find orderline with pizza
                for (int i = 0; i < activeOrderLines.size(); i++) {
                    if (pizzaNr.getPizzaNr() == activeOrderLines.get(i).getPizza().getPizzaNr()) {
                        //remove amount of pizza from orderline
                        allActiveOrders.get(orderToEdit).editOrderLine(i, -amount);

                    //If amount is now 0 or less, remove orderline
                    if (activeOrderLines.get(i).getAmount() <= 0) {
                        allActiveOrders.get(orderToEdit).removeOrderLine(i);
                    }}}

        }

        }
    }

    private String getActiveOrders() {
        ArrayList<String> activeOrdersList = new ArrayList<>();
        String tekst = " aktive ordre:\n";
        int size = allActiveOrders.size();
        if (size == 1) {
            tekst = " aktiv order:\n";
        }
        StringBuilder activeOrder = new StringBuilder("Der er " + size + tekst);
        int id = 1;
        for (Order order : allActiveOrders) {
            activeOrder.append(id).append(" ").append(order).append("\n");
            id++;
        }
        activeOrdersList.add(activeOrder.toString());
        StringBuilder activeOrders = new StringBuilder();
        for (String string : activeOrdersList)
            activeOrders.append(string).append("\n");
        return activeOrders.toString();
    }

    private void completeOrder() {
        UI.printMessage(getActiveOrders());

        // get which order to finish
        int choice = UI.whichOrderToComplete() - 1;
        if (choice != -1) {
            try {
                // store order
                FILE_HANDLER.storeArchivedOrder(allActiveOrders.get(choice));
                // remove order from allActiveOrders
                allActiveOrders.remove(choice);
                UI.printMessage("Ordren er arkivered");

            } catch (IOException e) {
                UI.errorPrint("Warning: Failed to complete order, cannot store!");

            } catch (IndexOutOfBoundsException e) {
                UI.errorPrint("There was no order by that number");
            }
        }
    }

    private void seeStats() {
        STATISTICS.reviewStats(FILE_HANDLER.getArchivedOrders());
    }

    private Pizza getValidPizza() {
        Pizza pizzaNr = MENU.getPizzaFromListNumber(UI.addToOrder());
        while (pizzaNr == null) {
            pizzaNr = MENU.getPizzaFromListNumber(UI.addToOrder(true));
        }
        return pizzaNr;
    }

}
