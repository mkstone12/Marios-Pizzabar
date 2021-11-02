import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private final String ARCHEIVED_ORDERS = "data/archived_orders.txt";
    private final String MENU = "data/menu.csv";
    private final String ACTIVE_ORDERS = "data/active_orders.txt";



    public List<String> getMenuFromFile() {
        ArrayList<String> pizzas = new ArrayList<>();
        try {
            Scanner load = new Scanner(new File(MENU));
            while(load.hasNextLine()){
                pizzas.add(load.nextLine());
            }
            return pizzas;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
