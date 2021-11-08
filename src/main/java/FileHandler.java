import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private final String ARCHIVED_ORDERS = "data/archived_orders.json";
    private final String MENU = "data/menu.csv";
    private final String ACTIVE_ORDERS = "data/active_orders.json";


    public List<String> getMenuFromFile() {
        try {
            return getLinesFromFile(MENU);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // takes an order, reads the already store orders, adds the order to the list of orders,
    // and then saves the new list of orders
    public void storeArchivedOrder(Order order) throws IOException {
        ArrayList<Order> storedOrders = getStoredFromFile(ARCHIVED_ORDERS);
        storedOrders.add(order);
        saveToFile(convertOrdersToJson(storedOrders), ARCHIVED_ORDERS);
    }

    public ArrayList<Order> getArchivedOrders() {
        return getStoredFromFile(ARCHIVED_ORDERS);
    }

    public void storeActiveOrders(List<Order> orders) throws IOException {
        saveToFile(convertOrdersToJson(orders), ACTIVE_ORDERS);
    }

    // will return a string with the json information
    private String convertOrdersToJson(List<Order> orders) throws JsonProcessingException {
        JsonNode node = Json.toJson(orders);

        return Json.prettyPrint(node);
    }

    // will save the given string to the given file
    private void saveToFile(String stringToSave, String filePath) throws IOException {
        File file = new File(filePath);

        PrintStream ps = new PrintStream(file, StandardCharsets.UTF_8);
        ps.println(stringToSave);
    }

    public ArrayList<Order> getStoredActiveOrders() throws JsonProcessingException {
        return getStoredFromFile(ACTIVE_ORDERS);
    }

    // gets the orders from a file, if it fails it will return an empty arraylist
    private ArrayList<Order> getStoredFromFile(String file) {
        List<String> lines;
        try {
            lines = getLinesFromFile(file);
            if (lines.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String line : lines) {
                    stringBuilder.append(line).append('\n');
                }

                List<Order> orders = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<>() {
                });
                return new ArrayList<>(orders);
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    // will take a file and return the lines in the file
    private ArrayList<String> getLinesFromFile(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        File file = new File(filePath);

        Scanner load = new Scanner(file);
        while (load.hasNextLine()) {
            lines.add(load.nextLine());
        }
        return lines;
    }
}
