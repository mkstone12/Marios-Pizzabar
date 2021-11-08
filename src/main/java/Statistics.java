import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Statistics {

    private final UserInterface ui;
    private ArrayList<Order> orderList, relevantOrders;
    private LocalDate startDate; // included in stats
    private LocalDate endDate; // excluded from stats


    public Statistics(UserInterface ui) {
        this.ui = ui;
    }

    public void reviewStats(ArrayList<Order> orderList) {
        this.orderList = orderList;
        getRequestedDates();
        setRelevantOrders();
        boolean stats = true;
        while (stats) {
            int choice = ui.printStatsMenu();
            switch (choice) {
                case 0 -> stats = false;

                case 1 -> salesStats();

                case 2 -> pizzaStats();

                case 3 -> getRequestedDates();
            }
        }
    }

    public void setRelevantOrders(){
        relevantOrders = new ArrayList<>();
        for (Order order : orderList) {
            LocalDate orderDate = order.getCreationDate();
            if (!orderDate.isBefore(startDate) && !orderDate.isAfter(endDate)) {
                relevantOrders.add(order);
            }
        }
    }

    public void salesStats() {
        //a.compareTo(d) * d.compareTo(b) > 0
        double totalSales = 0;
        double orderPrice;
        for (Order order : relevantOrders) {
            orderPrice = order.getPrice();
            totalSales += orderPrice;
        }
        String salg = "Total salg fra " + startDate
                + " til " + endDate + " = " + totalSales + "kr."; // test
        ui.printTotalSales(salg);
    }

    public void pizzaStats() {
        // make new HashMap
        Map<String, Integer>  pizzaMap = new HashMap<>();

        // populate Map with the string of a pizza, and the amount of times the pizza has been sold
        for (Order order : relevantOrders) {
            for (OrderLine line : order.getOrderLines()) {
                String pizza = line.getPizza().toString();
                if (pizzaMap.containsKey(pizza)){
                    pizzaMap.put(pizza, line.getAmount() + pizzaMap.get(pizza));
                } else {
                    pizzaMap.put(pizza, line.getAmount());
                }
            }
        }

        // put the entries from the map into a list
        List<Map.Entry<String, Integer>> listOfPizzas = new LinkedList<>(pizzaMap.entrySet());

        // sort the list, by the amount of times the pizza has been sold, in reverse order(largest to smallest)
        listOfPizzas.sort(((o1, o2) -> o2.getValue().compareTo(o1.getValue())));

        // make the string of the sorted pizzas for print
        StringBuilder sb = new StringBuilder("Her er pizzaerne rangeret efter mest solget:\n");
        for (Map.Entry<String, Integer> entry : listOfPizzas){
            sb.append(entry.getValue()).append(" stk.: ").append(entry.getKey()).append('\n');
        }

        // print the sorted list of pizzas
        ui.printTotalSales(sb.toString()); // TODO: 05/11/2021 Find better way to print
    }

    public void getRequestedDates() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            startDate = LocalDate.parse(ui.getDate("fra"), formatter);
            endDate = LocalDate.parse(ui.getDate("til"), formatter);

            if (startDate.isAfter(endDate)){
                ui.errorPrint("Warning: the start date you selected was after the end date.\n" +
                        "Your start date has been set to one day before as your end date.");
                startDate = endDate.minusDays(1);
            }
        } catch (DateTimeParseException e) { // if the user types an input the parser does not understand
            ui.errorPrint("Warning: Invalid Input. Please check formating and try again:");
            getRequestedDates();
        }
    }
}
