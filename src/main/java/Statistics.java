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

                case 4 -> printArchivedOrders(relevantOrders);

                case 5 -> printArchivedOrders(orderList);
            }
        }
    }

    private void setRelevantOrders(){
        relevantOrders = new ArrayList<>();
        for (Order order : orderList) {
            LocalDate orderDate = order.getCreationDate();
            if (!orderDate.isBefore(startDate) && !orderDate.isAfter(endDate)) {
                relevantOrders.add(order);
            }
        }
    }

    private void salesStats() {
        double totalSales = 0;

        // goes through all the relevantOrders and adds the price to totalSales
        for (Order order : relevantOrders) {
            totalSales += order.getPrice();
        }
        // print the result
        ui.printTotalSales("Total salg fra " + startDate + " til " + endDate + " = " + totalSales + "kr.");
    }

    private void pizzaStats() {
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

    private void printArchivedOrders(ArrayList<Order> orders) {

        // get the length of allActiveOrders and set the right grammer
        int size = orders.size();
        String tekst;
        if (size == 1) {
            tekst = " arkiverede order:\n";
        }else{
            tekst = " arkiverede ordre:\n";
        }

        // create String builder and initial line
        StringBuilder activeOrder = new StringBuilder("Der er " + size + tekst);

        // add the archived orders to the Stringbuilder, with an id
        for (Order order : orders) {
            activeOrder.append(order.getOrderID()).append(" ").append(order).append("Pris ").append(order.getPrice()).append(" Kr").append("\n");
        }

        //return the string of the Stringbuilder
        ui.printActiveOrders(activeOrder.toString());
    }

    private void getRequestedDates() {
        try {
            // make a pattern to parse the given dates from
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // get the dates from the user and parse
            startDate = LocalDate.parse(ui.getDate("fra"), formatter);
            endDate = LocalDate.parse(ui.getDate("til"), formatter);

            // if the startDate is after the endDate, then print warning
            // and set the startdate a day before the end date
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
