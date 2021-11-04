import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Statistics {

    // todo: use to sort by date: .sort(Comparator.comparing(Order::getCreationDate));

    // Total sales for a period (startDate - endDate)

    // Most popular pizza sold in a period (startDate - endDate)

    private final UserInterface ui;
    private ArrayList<Order> orderList;
    private Date startDate; // included in stats
    private Date endDate; // excluded from stats


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
            Date orderDate = order.getCreationDate();
            if (orderDate.after(startDate) && orderDate.before(endDate)) {
                orderPrice = order.getPrice();
                totalSales += orderPrice;
            }
        }
        String salg = "Total salg fra " + DateFormat.getDateInstance().format(startDate)
                + " til " + DateFormat.getDateInstance().format(endDate) + " = " + totalSales + "kr."; // test
        ui.printTotalSales(salg);
    }

    public void pizzaStats() {
        System.out.println("pizza"); // test
    }

    public void getRequestedDates() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String startStats = ui.getDate("fra"); // simple date format
        String endStats = ui.getDate("til"); // simple date format
        try {
            startDate = formatter.parse(startStats);
            endDate = formatter.parse(endStats); // endDate has to be after startDate - todo check
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
