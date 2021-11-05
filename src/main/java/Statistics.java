import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Statistics {

    // todo: use to sort by date: .sort(Comparator.comparing(Order::getCreationDate));

    // Total sales for a period (startDate - endDate)

    // Most popular pizza sold in a period (startDate - endDate)

    private final UserInterface ui;
    private ArrayList<Order> orderList;
    private LocalDate startDate; // included in stats
    private LocalDate endDate; // excluded from stats


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
        System.out.println("pizza"); // test
    }

    public void getRequestedDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        startDate = LocalDate.parse(ui.getDate("fra"), formatter);
        endDate = LocalDate.parse(ui.getDate("til"), formatter);

        // TODO: 05/11/2021 check that startDate cannot be after endDate
    }
}
