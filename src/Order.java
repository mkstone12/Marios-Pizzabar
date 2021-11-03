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
    private ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
    private int ETA = 0;
    private double price;
    private String name;

    public Order(String name) {
        this.orderID = UUID.randomUUID();
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getOrderedPizzas() {
        StringBuilder returnString = new StringBuilder();
        for(int i = 0; i < orderLines.size(); i++){
            returnString.append(orderLines.get(i).getPizza().getPizzaNr()).append(". ").append(orderLines.get(i).getPizza().getName()).append(" - Antal ").append(orderLines.get(i).getAmount()).append("\n");
        }
        return returnString.toString();
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void addOrderLine(Pizza pizza, int amount) {
        OrderLine orderLine = new OrderLine(pizza, amount);
        this.orderLines.add(orderLine);
        ETA += 5 * amount;
        price +=  pizza.getPrice() * amount;
    }

    public int getETA(){
        return ETA;
    }

    public double getPrice(){
        return price;
    }


  public void removePizza(Pizza pizza) {
       orderLines.remove(pizza);
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
