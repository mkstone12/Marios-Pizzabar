import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Statistics {

    // todo: use to sort by date: .sort(Comparator.comparing(Order::getCreationDate));

    // Total sales for a period (startDate - endDate)

    // Most popular pizza sold in a period (startDate - endDate)

    private final UserInterface ui;
    private ArrayList<Order> orderList, relevantOrders;
    private LocalDate startDate; // included in stats
    private LocalDate endDate; // excluded from stats
    private final int MENU_SIZE = 14;
    private int[] orderedPizzaTotals = new int[MENU_SIZE];
    private String[] orderedPizzaNames = new String[MENU_SIZE];


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

                case 2 -> pizzaStats2();

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

    public void pizzaStats2() {
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

    public void pizzaStats() {
        System.out.println("most popular pizza"); // test
        int pizzaNumber, pizzaQuantity, pizzaTotal;
        Pizza pizza;
        for (Order order : relevantOrders) {
                ArrayList<OrderLine> orderLines = order.getOrderLines();
                for (OrderLine orderLine : orderLines) {
                    pizza = orderLine.getPizza();
                    pizzaNumber = pizza.getPizzaNr() - 1;
                    orderedPizzaNames[pizzaNumber] = pizza.getName();
                    pizzaQuantity = orderLine.getAmount();
                    pizzaTotal = orderedPizzaTotals[pizzaNumber] + pizzaQuantity;
                    orderedPizzaTotals[pizzaNumber] = pizzaTotal;
                }
        }
        sortPizzaArrays();
        System.out.println(Arrays.toString(orderedPizzaTotals)); //test
        System.out.println(Arrays.toString(orderedPizzaNames)); //test
    }

    public void sortPizzaArrays() {
        // sort the array totals
        int tempI;
        String tempS;
        for (int i = 0; i < MENU_SIZE - 1; i++) {
            for (int j = i + 1; j < MENU_SIZE; j++) {
                if (orderedPizzaTotals[j] > orderedPizzaTotals[i]) {
                    tempI = orderedPizzaTotals[j];
                    orderedPizzaTotals[j] = orderedPizzaTotals[i];
                    orderedPizzaTotals[i] = tempI;
                    tempS = orderedPizzaNames[j];
                    orderedPizzaNames[j] = orderedPizzaNames[i];
                    orderedPizzaNames[i] = tempS;
                }
            }
        }
    }

    public void resetPizzaArrays() {
        for (int i = 0; i < MENU_SIZE; i++) {
            orderedPizzaNames[i] = "";
            orderedPizzaTotals[i] = 0;
        }
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
            resetPizzaArrays();

        } catch (DateTimeParseException e) { // if the user types an input the parser does not understand
            ui.errorPrint("Warning: Invalid Input. Please check formating and try again:");
            getRequestedDates();
        }
    }
}
