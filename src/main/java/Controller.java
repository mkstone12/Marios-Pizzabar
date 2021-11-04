import java.util.ArrayList;

public class Controller {
    private final FileHandler fileHandler = new FileHandler();
    private final Menu menu = new Menu(fileHandler.getMenuFromFile());
    private final ArrayList<Order> allActiveOrders = fileHandler.getStoredActiveOrders();

    Pizza testPizza = new Pizza("Test", "Description", 55.0, 1);

    public void run() {

        // make UserInterface
        UserInterface ui = new UserInterface();

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
                int antal = ui.howMany();
                order.addOrderLine(pizzaNr, antal);
            } else {
                ui.printFinalOrder(order.stringOfOrderedPizzas(), order.getPrice(), order.getETA());
                allActiveOrders.add(order);
                break;
            }
        }
    }

    public void editOrder(UserInterface ui) {

        // Try new
        // Will you add or remove a pizza from an order, or delete active order
        int choice = ui.whichEditAction();
        switch (choice) {
            case 1 -> addPizzaToActiveOrder(ui);

            case 2 -> removePizzaFromActiveOrder(ui);

            case 3 -> cancelActiveOrder(ui);
        }



        // todo remove pizza from order



        // todo Cancel order (are you sure?)


    }
    // add pizza to order
    public void addPizzaToActiveOrder(UserInterface ui) {
        int pizza = ui.whichPizza();
        Pizza pizzaToAdd = menu.getPizzaFromListNumber(pizza);
        String pizzaToAddName = pizzaToAdd.getName();
        System.out.println("Trying to add a " + pizzaToAddName);

        // List active orders
        ui.printActiveOrders(getActiveOrders());

        // Choose order to edit
        int choice = ui.editMenu();
        System.out.println("Editing this order: " + allActiveOrders.get(choice - 1));
        int newNumber = ui.howMany(pizzaToAddName);
        System.out.println(newNumber); // test
    }

    public void removePizzaFromActiveOrder(UserInterface ui) {

    }

    public void cancelActiveOrder(UserInterface ui) {

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
