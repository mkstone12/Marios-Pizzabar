import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private UUID orderID;
    private LocalDate creationDate;
    private static int count;
    private ArrayList<OrderLine> orderLines = new ArrayList<>();
    private double totalOrderPrice;
    LocalTime orderDueTime = LocalTime.now();
    private String name;

    public Order(String name) {
        this.orderID = UUID.randomUUID();
        this.creationDate = LocalDate.now();
        this.name = name;
    }

    public String stringOfOrderedPizzas() {
        StringBuilder returnString = new StringBuilder();
        for (OrderLine orderLine : orderLines) {
            returnString.append(orderLine).append("\n");
        }
        return returnString.toString();
    }

    public void addOrderLine(Pizza pizza, int amount) {
        OrderLine orderLine = new OrderLine(pizza, amount);
        this.orderLines.add(orderLine);
        orderDueTime = orderDueTime.truncatedTo(ChronoUnit.MINUTES).plusMinutes(5 * amount);
        updatePrice();
    }

    public double getPrice(){
        return totalOrderPrice;
    }

    public void editOrderLine(int orderLineToEdit, int amount){
        orderLines.get(orderLineToEdit).addAmount(amount);
        updatePrice();
    }

    public void removeOrderLine(int orderLineToRemove){
        orderLines.remove(orderLineToRemove);
        updatePrice();
    }

    public void updatePrice(){
        totalOrderPrice = 0;
        for(int i = 0; i <orderLines.size();i++){
            totalOrderPrice += orderLines.get(i).getPizza().getPrice() * orderLines.get(i).getAmount();
        }
    }



    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(name + ": ");
        for (OrderLine orderLine : orderLines) {
            output.append(orderLine).append(", ");
        }
        output.append("Pris ").append(getPrice()).append(" kr.");
        return output.toString();
    }

    //*********************************************
    //
    // DO NOT DELETE, IS USED TO CREATE FROM JSON
    //
    //*********************************************

    public Order(){

    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void setOrderID(UUID orderID) {
        this.orderID = orderID;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void setPrice(double price) {
        this.totalOrderPrice = price;
    }

    public LocalTime getOrderDueTime() {
        return orderDueTime;
    }

    public void setOrderDueTime(LocalTime orderDueTime) {
        this.orderDueTime = orderDueTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
