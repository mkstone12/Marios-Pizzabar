import java.text.DateFormat;
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

    public Statistics(UserInterface ui) {
        this.ui = ui;
    }

    public void reviewStats(ArrayList<Order> orderList) {
        this.orderList = orderList;
        System.out.println("Statistics"); // test
        int choice = ui.printStatsMenu();
        switch (choice) {
            case 1 -> salesStats();

            case 2 -> pizzaStats();

            case 3 -> otherStats();
        }
    }

    public void salesStats() {
        System.out.println("sales"); // test
        // try date format
        Order order = orderList.get(0);
        Date orderDate = order.getCreationDate();
        System.out.println(DateFormat.getDateInstance().format(orderDate)); //test date format
    }

    public void pizzaStats() {
        System.out.println("pizza"); // test
    }

    public void otherStats() {
        System.out.println("other"); // test
    }
}
