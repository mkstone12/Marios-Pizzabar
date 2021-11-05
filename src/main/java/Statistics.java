import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Statistics {

    // todo: use to sort by date: .sort(Comparator.comparing(Order::getCreationDate));

    // Total sales for a period (startDate - endDate)

    // Most popular pizza sold in a period (startDate - endDate)

    private final UserInterface ui;
    private ArrayList<Order> orderList;
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

    public void salesStats() {
        //a.compareTo(d) * d.compareTo(b) > 0
        double totalSales = 0;
        double orderPrice;
        for (Order order : orderList) {
            LocalDate orderDate = order.getCreationDate();
            if (!orderDate.isBefore(startDate) && !orderDate.isAfter(endDate)) {
                orderPrice = order.getPrice();
                totalSales += orderPrice;
            }
        }
        String salg = "Total salg fra " + startDate
                + " til " + endDate + " = " + totalSales + "kr."; // test
        ui.printTotalSales(salg);
    }

    public void pizzaStats() {
        System.out.println("most popular pizza"); // test
        int pizzaNumber, pizzaQuantity, pizzaTotal;
        Pizza pizza;
        for (Order order : orderList) {
            LocalDate orderDate = order.getCreationDate();
            if (!orderDate.isBefore(startDate) && !orderDate.isAfter(endDate)) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uu");
        startDate = LocalDate.parse(ui.getDate("fra"), formatter);
        endDate = LocalDate.parse(ui.getDate("til"), formatter);

        if (startDate.isAfter(endDate)){
            ui.errorPrint("Warning: the start date you selected was after the end date.\n" +
                    "You start date has been set to be the same as your end date.");
            startDate = endDate;
        }
        resetPizzaArrays();
    }
}
