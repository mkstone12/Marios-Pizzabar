import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private final String ARCHIVED_ORDERS = "data/archived_orders.txt";
    private final String MENU = "data/menu.csv";
    private final String ACTIVE_ORDERS = "data/active_orders.json";



    public List<String> getMenuFromFile() {
        return getLinesFromFile(MENU);
    }

    public void storeArchivdOrder(Order order){

    }

    public List<String> getArchivedOrders(){
        return getLinesFromFile(ARCHIVED_ORDERS);
    }

    public void storeActiveOrders() throws JsonProcessingException {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza("name", "desc", 1, 1));
        pizzas.add(new Pizza("name", "desc", 1, 2));
        pizzas.add(new Pizza("name", "desc", 1, 3));
        pizzas.add(new Pizza("name", "desc", 1, 4));
        pizzas.add(new Pizza("name", "desc", 1, 5));
        pizzas.add(new Pizza("name", "desc", 1, 6));
        pizzas.add(new Pizza("name", "desc", 1, 7));
        pizzas.add(new Pizza("name", "desc", 1, 8));
        Order order = new Order(1, pizzas);

        JsonNode node =  Json.toJson(order);

        String jsonString = Json.stringify(node);

        System.out.println(jsonString);
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
