import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;

public class Controller {
    private final FileHandler fileHandler = new FileHandler();
    private final Menu menu = new Menu(fileHandler.getMenuFromFile());
    private ArrayList<Order> allActiveOrders;
    private UserInterface ui = new UserInterface();

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

                case 3 -> createOrder(ui, menu);

                case 4 -> editOrder(ui);

                case 5 -> completeOrder();

                case 0 -> keepGoing = false;


            }
        }
    }

    public void createOrder(UserInterface ui, Menu menu) {
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
                ui.printFinalOrder(order.stringOfOrderedPizzas(), order.getPrice(), order.getTime());
                allActiveOrders.add(order);
                break;
            }
        }
    }

    public void editOrder(UserInterface ui) {

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

        // ?

    }
}
