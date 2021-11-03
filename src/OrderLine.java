public class OrderLine {
    private Pizza pizza;
    private int amount;

    public OrderLine(Pizza pizza, int amount){
        this.pizza = pizza;
        this.amount = amount;
    }

    public Pizza getPizza(){
        return pizza;
    }
}
