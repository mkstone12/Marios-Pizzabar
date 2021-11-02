import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private final String ARCHIVED_ORDERS = "data/archived_orders.txt";
    private final String MENU = "data/menu.csv";
    private final String ACTIVE_ORDERS = "data/active_orders.txt";



    public List<String> getMenuFromFile() {
        return getLinesFromFile(MENU);
    }

    public void storeArchivdOrder(Order order){

    }

    public List<String> getArchivedOrders(){
        return getLinesFromFile(ARCHIVED_ORDERS);
    }

    public void storeActiveOrders(){

    }

    public List<String> getStoredActiveOrders(){
        return getLinesFromFile(ACTIVE_ORDERS);
    }

    private ArrayList<String> getLinesFromFile(String filePath){
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner load = new Scanner(new File(filePath));
            while(load.hasNextLine()){
                lines.add(load.nextLine());
            }
            return lines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
