import java.util.Arrays;
import java.util.List;

public class Controller {

    Pizza testPizza = new Pizza("Test", "Description", 55.0, 1);

    public void run(){
        UserInterface ui = new UserInterface();

        List<String> test = Arrays.asList("one", "two", "three");
        Menu menu = new Menu(test);

        boolean keepGoing = true;

        while(keepGoing) {

            int choice = ui.printMainMenu();

            switch (choice){
                case 1 ->
                    ui.printMenu(menu);

                case 2 ->
                    ui.printBestillingsOversigt();

                case 3 ->
                    opretOrdre();

                case 4 ->
                    redigerOrdre();

                case 5 ->
                    færdiggørOrdre();

                case 0 ->
                    keepGoing = false;


           }
        }
    }


    public void opretOrdre(){

    }

    public void redigerOrdre(){

    }

    public void færdiggørOrdre(){

    }
}
