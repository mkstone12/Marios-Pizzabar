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
    private ArrayList<OrderLine> orderLine = new ArrayList<OrderLine>();
    private int ETA = 0;
    private double price;

    public Order() {
        this.orderID = UUID.randomUUID();
    }

    public ArrayList<OrderLine> getOrderedPizzas() {
        return orderLine;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void addOrderLine(Pizza pizza, int amount) {
        OrderLine orderLine = new OrderLine(pizza, amount);
        this.orderLine.add(orderLine);
        ETA += 5 * amount;
        price +=  pizza.getPris() * amount;
    }

    public int getETA(){
        return ETA;
    }

    public double getPrice(){
        return price;
    }


  public void removePizza(Pizza pizza) {
       orderLine.remove(pizza);
    }

  /*  @Override
    public String toString() {
        return "Ordre= " +
                "ordreID: " + orderID +
                ", bestilte pizzaer: " + orderedPizzas;
    }*/
}
