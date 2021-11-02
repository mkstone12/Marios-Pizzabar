public class Controller {

    public void run(){
        UserInterface ui = new UserInterface();
        Menu menu = new Menu();
        while(true) {
            int choice = ui.printMainMenu();
            switch (choice){
                case 1 -> {
                    ui.printMenu(menu);
                }
                case 2 -> {
                    ui.printBestillingsOversigt();
                }
                case 3 -> {
                    opretOrdre();

                }
                case 4 -> {
                    færdiggørBestilling();
                }

           }
        }
    }
    public void opretOrdre(){

    }

    public void færdiggørBestilling(){

    }
}
