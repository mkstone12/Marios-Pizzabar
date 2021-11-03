public class Controller {
    private final FileHandler fileHandler = new FileHandler();
    private Menu menu;

    Pizza testPizza = new Pizza("Test", "Description", 55.0, 1);

    public void run(){
        // initialize menu
        menu = new Menu(fileHandler.getMenuFromFile());

        // make UserInterface
        UserInterface ui = new UserInterface();

        boolean keepGoing = true;


        while(keepGoing) {

            int choice = ui.printMainMenu();

            switch (choice){
                case 1 ->
                    ui.printMenu(menu.getListofPizzas());

                case 2 ->
                    ui.printOrderOversight();

                case 3 ->
                    createOrder(ui, menu);

                case 4 ->
                    editOrder();

                case 5 ->
                    completeOrder();

                case 0 ->
                    keepGoing = false;


           }
        }
    }


    public void createOrder(UserInterface ui, Menu menu){
        ui.nameOfOrder();

        ui.printMenu(menu.getListofPizzas());
        ui.addToOrder();

    }

    public void editOrder(){

        // find order

        // what element to edit? which pizza type

        // add pizza to order
        // remove pizza from order


    }

    public void completeOrder(){

        // ?

    }
}
