import java.util.ArrayList;
import java.util.UUID;

/*public class Order {
    private UUID id;
    private ArrayList<OrderLine> orderLines;
    private int ETA;
}*/

public class Order {
    private int orderID;
    private ArrayList<Pizza> orderedPizzas;

    public Order(int ID, ArrayList orderedPizzas) {
        this.orderID = ID;
        this.orderedPizzas = orderedPizzas;
    }

    public void setOrderedPizzas(ArrayList<Pizza> orderedPizzas) {
        this.orderedPizzas = orderedPizzas;
    }

    public ArrayList<Pizza> getOrderedPizzas() {
        return orderedPizzas;
    }

    public int getOrderID() {
        return orderID;
    }

    public void addPizza(Pizza pizza) {
        orderedPizzas.add(pizza);
    }

    public void removePizza(Pizza pizza) {
        orderedPizzas.remove(pizza);
    }

    @Override
    public String toString() {
        return "Ordre= " +
                "ordreID: " + orderID +
                ", bestilte pizzaer: " + orderedPizzas;
    }
}
