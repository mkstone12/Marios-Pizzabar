import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private final FileHandler fileHandler = new FileHandler();
    private final Menu menu = new Menu(fileHandler.getMenuFromFile());
    private final UserInterface ui = new UserInterface();
    private final Statistics stats = new Statistics(ui);
    private ArrayList<Order> allActiveOrders;

    public void run() {
        // get allActiveOrders
        try {
            allActiveOrders = fileHandler.getStoredActiveOrders();
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

            int choice = ui.printMainMenu();

            switch (choice) {
                case 1 -> ui.printMenu(menu.getListofPizzas());

                case 2 -> ui.printActiveOrders(getActiveOrders());

                case 3 -> createOrder(menu);

                case 4 -> editOrder();

                case 5 -> completeOrder();

                case 6 -> seeStats();

                case 0 -> keepGoing = saveAndQuit();
            }
        }
    }

    private boolean saveAndQuit() {
        try {
            fileHandler.storeActiveOrders(allActiveOrders);
            return false;
        } catch (IOException e) {
            ui.errorPrint("Warning: Storing of active orders failed. Quit aborted");
            return true;
        }
    }

    public void createOrder(Menu menu) {
        String name = ui.nameOfOrder();
        Order order = new Order(name);
        ui.printMenu(menu.getListofPizzas());
        while (true) {
            int toEndOrder = ui.toEndOrder();

            if (toEndOrder == 1) {

                Pizza pizzaNr = menu.getPizzaFromListNumber(ui.addToOrder());
                int amount = ui.howMany();
                order.addOrderLine(pizzaNr, amount);
            } else {
                ui.printFinalOrder(order.stringOfOrderedPizzas(), order.getPrice(), order.getOrderDueTime());
                allActiveOrders.add(order);
                break;
            }
        }
    }

    public void editOrder() {

        // List active orders

        ui.printActiveOrders(getActiveOrders());

        // Choose order to edit and what to edit
        int choice[] = ui.editMenu();

        //Delete order
        if(choice[1] == 3 ){
            allActiveOrders.remove(choice[0]);
        }

        //Add to order
        else if (choice[1] == 1){

            //Pizza to add and amount of it
            Pizza pizzaNr = menu.getPizzaFromListNumber(ui.addToOrder());
            int amount = ui.howMany();

            ArrayList<OrderLine> activeOrderLines =  allActiveOrders.get(choice[0]).getOrderLines();

            //Loops over orderlines in order to check if there is orderline for pizza
            for(int i = 0; i < activeOrderLines.size();i++){
                if(pizzaNr.getPizzaNr() == activeOrderLines.get(i).getPizza().getPizzaNr()){
                    //If orderline with pizza exist, add to amount to orderline
                    allActiveOrders.get(choice[0]).editOrderLine(i,amount);
                    break;
                }
                else if (i == activeOrderLines.size()-1){
                    //else create new orderline
                    allActiveOrders.get(choice[0]).addOrderLine(pizzaNr, amount);
                    break;
                }
            }
        }

        //remove from order
        else if (choice[1] == 2){
            //Get pizza to remove and amount
            Pizza pizzaNr = menu.getPizzaFromListNumber(ui.addToOrder());
            int amount = ui.howMany();

            ArrayList<OrderLine> activeOrderLines =  allActiveOrders.get(choice[0]).getOrderLines();

            //Loop to find orderline with pizza
            for(int i = 0; i < activeOrderLines.size();i++){
                if(pizzaNr.getPizzaNr() == activeOrderLines.get(i).getPizza().getPizzaNr()) {
                    //remove amount of pizza from orderline
                    allActiveOrders.get(choice[0]).editOrderLine(i,-amount);

                    //If amount is now 0 or less, remove orderline
                    if(activeOrderLines.get(i).getAmount()<= 0){
                        allActiveOrders.get(choice[0]).removeOrderLine(i);
                    }
                }}
        }

    }

    public ArrayList<String> getActiveOrders() {
        ArrayList<String> activeOrdersList = new ArrayList<>();
        String tekst = " aktive ordre:\n";
        int size = allActiveOrders.size();
        if (size == 1) {
            tekst = " aktiv order:\n";
        }
        StringBuilder activeOrder = new StringBuilder("Der er " + size + tekst);
        int id = 1;
        for (Order order : allActiveOrders) {
            activeOrder.append(id).append(" ").append(order).append("Pris "+order.getPrice() + " Kr").append("\n");
            id++;
        }
        activeOrdersList.add(activeOrder.toString());
        return activeOrdersList;
    }

    public void completeOrder() {
        ui.printActiveOrders(getActiveOrders());

        // get which order to finish
        int choice =  ui.whichOrderToComplete() - 1;

        try {
            // store order
            fileHandler.storeArchivedOrder(allActiveOrders.get(choice));
            // remove order from allActiveOrders
            allActiveOrders.remove(choice);
        } catch (IOException e) {
            ui.errorPrint("Warning: Failed to complete order, cannot store!");
        } catch (IndexOutOfBoundsException e){
            ui.errorPrint("There was no order by that number");
        }
    }

    public void seeStats() {
        ArrayList<Order> orders = fileHandler.getArchivedOrders();
        stats.reviewStats(orders);
    }
}
