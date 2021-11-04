import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Statistics {

    // todo: use to sort by date: .sort(Comparator.comparing(Order::getCreationDate));

    // Total sales for a period (day, week, month, year, ever)

    // Most popular pizza sold in a period (day, week, month, year, ever)

    private final UserInterface ui;
    private ArrayList<Order> orderList;
    private Date startDate;
    private Date endDate;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

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

                case 3 -> changeDates();
            }
        }
    }

    public void getRequestedDates() {
        String startStats = ui.getDate("fra"); // simple date format
        String endStats = ui.getDate("til"); // simple date format
        try {
            startDate =formatter.parse(startStats);
            endDate =formatter.parse(endStats);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void salesStats() {
        double totalSales = 0;
        double orderPrice;
        for (Order order : orderList) {
            orderPrice = order.getPrice();
            totalSales += orderPrice;
        }
        String salg = "Total salg fra " + DateFormat.getDateInstance().format(startDate)
                + " til " + DateFormat.getDateInstance().format(endDate) + " = " + totalSales + "kr."; // test
        ui.printTotalSales(salg);
    }

    public void pizzaStats() {
        System.out.println("pizza"); // test
    }

    public void changeDates() {
        System.out.println("dates"); // test
    }
}
