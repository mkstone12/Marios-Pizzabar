import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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

    public List<Order> getArchivedOrders(){
        return null;
    }

    public void storeActiveOrders(List<Order> orders) throws JsonProcessingException {
        JsonNode node =  Json.toJson(orders);

        String jsonString = Json.prettyPrint(node);

        try {
            saveActiveOrdersToFile(jsonString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveActiveOrdersToFile(String stringToSave) throws FileNotFoundException {
        File file = new File(ACTIVE_ORDERS);
        PrintStream ps;
        try {
            ps = new PrintStream(file, StandardCharsets.UTF_8);
            ps.println(stringToSave);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> getStoredActiveOrders(){
        List<String> lines = getLinesFromFile(ACTIVE_ORDERS);


        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line).append('\n');
        }

        List<Order> orders = null;
        if (stringBuilder.length() > 0) {
            try {
                orders = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<List<Order>>() {
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ArrayList<>(orders);
        }
        return new ArrayList<>();
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
