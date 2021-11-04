import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private final FileHandler fileHandler = new FileHandler();
    private final Menu menu = new Menu(fileHandler.getMenuFromFile());
    private final UserInterface ui = new UserInterface();
    private ArrayList<Order> allActiveOrders;

    Pizza testPizza = new Pizza("Test", "Description", 55.0, 1);

    public void run() {
        // get allActiveOrders
        try {
            allActiveOrders = fileHandler.getStoredActiveOrders();
        } catch (JsonProcessingException e) {
            System.out.println("Warning: the active orders from previous session could not be loaded!");
        }

        boolean keepGoing = true;


        while (keepGoing) {

            int choice = ui.printMainMenu();

            switch (choice) {
                case 1 -> ui.printMenu(menu.getListofPizzas());

                case 2 -> ui.printActiveOrders(getActiveOrders());

                case 3 -> createOrder(menu);

                case 4 -> editOrder();

                case 5 -> completeOrder();

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
                int antal = ui.whoMany();
                order.addOrderLine(pizzaNr, antal);
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

        // Choose order to edit
        int choice = ui.editMenu();

        // what element to edit? pizza, other?

        // add pizza to order
        // remove pizza from order

        // add/remove other items? cola, chips?

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
            activeOrder.append(id).append(" ").append(order).append("\n");
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
        }
    }
}
