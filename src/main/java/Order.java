import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.UUID;

/*public class Order {
    private UUID id;
    private ArrayList<OrderLine> orderLines;
    private int ETA;
}*/

public class Order {
    private UUID orderID;
    private static int count;
    private ArrayList<OrderLine> orderLines = new ArrayList<>();
    private double price;
    LocalTime orderDueTime = LocalTime.now();
    private String name;

    public Order(String name) {
        this.orderID = UUID.randomUUID();
        this.name = name;
    }

    public void setOrderDueTime(LocalTime orderDueTime) {
        this.orderDueTime = orderDueTime;
    }

    public Order(){
    }

    public LocalTime getOrderDueTime(){
        return orderDueTime;
    }

    public String stringOfOrderedPizzas() { // TODO: 03/11/2021 Reduce coupling
        StringBuilder returnString = new StringBuilder();
        for (OrderLine orderLine : orderLines) {
            returnString.append(orderLine.getPizza().getPizzaNr()).append(". ").
                    append(orderLine.getPizza().getName()).append(" - Antal ").append(orderLine.getAmount()).append("\n");
        }
        return returnString.toString();
    }

    public void addOrderLine(Pizza pizza, int amount) {
        OrderLine orderLine = new OrderLine(pizza, amount);
        this.orderLines.add(orderLine);
        orderDueTime = orderDueTime.truncatedTo(ChronoUnit.MINUTES).plusMinutes(5 * amount);
        price +=  pizza.getPrice() * amount;
    }

    public void removePizza(Pizza pizza) { // TODO: 03/11/2021 ?fix 
        orderLines.remove(pizza);
    }

    public void setOrderID(UUID orderID) {
        this.orderID = orderID;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public UUID getOrderID() {
        return orderID;
    }


    public double getPrice(){
        return price;
    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(name + ": ");
        for (OrderLine orderLine : orderLines) {
            output.append(orderLine.getAmount()).append(" ").append(orderLine.getPizza().getName()).append(", ");
        }
        return output.toString();
    }

  /*  @Override
    public String toString() {
        return "Ordre= " +
                "ordreID: " + orderID +
                ", bestilte pizzaer: " + orderedPizzas;
    }*/
}
