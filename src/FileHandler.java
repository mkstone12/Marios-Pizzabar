import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private final String ARCHEIVED_ORDERS = "data/archived_orders.txt";
    private final String MENU = "data/menu.txt";
    private final String ACTIVE_ORDERS = "data/active_orders.txt";



    public ArrayList<Pizza> getPizzasFromFile() {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        try {
            Scanner load = new Scanner(new File(MENU));
            while(load.hasNextLine()){
                pizzas.add(new Pizza(load.nextLine()));
            }
            return pizzas;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
