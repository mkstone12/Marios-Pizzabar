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

    public void storeArchivedOrder(Order order) throws IOException {
        ArrayList<Order> storedOrders = getStoredFromFile(ARCHIVED_ORDERS);
        storedOrders.add(order);
        saveToFile(convertOrdersToJson(storedOrders), ARCHIVED_ORDERS);
    }

    public ArrayList<Order> getArchivedOrders() throws JsonProcessingException {
        return getStoredFromFile(ARCHIVED_ORDERS);
    }

    public void storeActiveOrders(List<Order> orders) throws IOException {
        saveToFile(convertOrdersToJson(orders), ARCHIVED_ORDERS);
    }

    private String convertOrdersToJson(List<Order> orders) throws JsonProcessingException {
        JsonNode node =  Json.toJson(orders);

        return Json.prettyPrint(node);
    }

    private void saveToFile(String stringToSave, String filePath) throws IOException {
        File file = new File(filePath);

        PrintStream ps = new PrintStream(file, StandardCharsets.UTF_8);
        ps.println(stringToSave);
    }

    public ArrayList<Order> getStoredActiveOrders() throws JsonProcessingException {
        return getStoredFromFile(ACTIVE_ORDERS);
    }

    private ArrayList<Order> getStoredFromFile(String file) throws JsonProcessingException {
        List<String> lines = getLinesFromFile(file);

        if (lines.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : lines) {
                stringBuilder.append(line).append('\n');
            }

            List<Order> orders = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<>() {});
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
