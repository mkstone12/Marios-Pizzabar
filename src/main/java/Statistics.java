public class Statistics {

    // Total sales for a period (day, week, month, year, ever)

    // Most popular pizza sold in a period (day, week, month, year, ever)

    private final UserInterface ui;

    public Statistics(UserInterface ui) {
        this.ui = ui;
    }

    public void reviewStats() {
        System.out.println("Statistics"); // test
        ui.printStatsMenu();
    }
}
