import java.util.Date;

public class Statistics {

    // Total sales for a period (day, week, month, year, ever)

    // Most popular pizza sold in a period (day, week, month, year, ever)

    private final UserInterface ui;
    private Date startDate;
    private Date endDate;

    public Statistics(UserInterface ui) {
        this.ui = ui;
    }

    public void reviewStats() {
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
    }

    public void pizzaStats() {
        System.out.println("pizza"); // test
    }

    public void otherStats() {
        System.out.println("other"); // test
    }
}
