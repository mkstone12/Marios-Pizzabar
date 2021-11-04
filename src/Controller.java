import java.util.ArrayList;

public class Controller {
    private final FileHandler fileHandler = new FileHandler();
    private Menu menu;
    private ArrayList<Order> allActiveOrders = new ArrayList<Order>();

    Pizza testPizza = new Pizza("Test", "Description", 55.0, 1);

    public void run() {
        // initialize menu
        menu = new Menu(fileHandler.getMenuFromFile());

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
                int antal = ui.whoMany();
                order.addOrderLine(pizzaNr, antal);
            } else {
                ui.printFinalOrder(order.getOrderedPizzas(), order.getPrice(), order.getETA());
                allActiveOrders.add(order);
                break;
            }
        }
    }

    public void editOrder(UserInterface ui) {
        Order selectedOrder;

        // List active orders
        ui.printActiveOrders(getActiveOrders());

        // Choose order to edit
        int choice = 0;
        int size = allActiveOrders.size();
        if (size > 0) {
            choice = ui.editMenu(size);
        } if (choice != 0) {
            selectedOrder = allActiveOrders.get(choice - 1);
            System.out.println(selectedOrder);

        // choose orderline to edit
        ArrayList<String> orderlinesNames = new ArrayList<>();
        ArrayList<Integer> orderlinesAmounts = new ArrayList<>();
        /*for (Pizza pizza : selectedOrder) {

        }
        ui.printSelectedOrderlines();*/
        }
        // Choose add o

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
