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
                    ui.printBestillingsOversigt();

                case 3 ->
                    opretOrdre(ui);

                case 4 ->
                    redigerOrdre();

                case 5 ->
                    færdiggørOrdre();

                case 0 ->
                    keepGoing = false;


           }
        }
    }


    public void opretOrdre(UserInterface ui){
        ui.navnPåOrdre();
        ui.tilføjTilOrdre();

    }

    public void redigerOrdre(){

    }

    public void færdiggørOrdre(){ // TODO: 02/11/2021 translate to english

    }
}
