public class OrderLine {
    private Pizza pizza;
    private int amount;

    public OrderLine(Pizza pizza, int amount){
        this.pizza = pizza;
        this.amount = amount;
    }
    
    public OrderLine(){

    }

    public Pizza getPizza(){
        return pizza;
    }

    public int getAmount(){
        return amount;
    }
    public void addAmount(int amount){
        this.amount += amount;
    }

    @Override
    public String toString() {
        return amount + " stk. " + pizza.getPizzaNr() + ". " + pizza.getName();
    }
}
