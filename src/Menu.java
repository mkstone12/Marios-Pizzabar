import java.util.ArrayList;
import java.util.List;

public class Menu {

    private ArrayList<Pizza> pizzasInMenu;

    public Menu(List<String> menuList){
        this.pizzasInMenu = new ArrayList<>();
        for(String item : menuList){
            pizzasInMenu.add(new Pizza(item));// TODO: 02/11/2021 Add constructor that takes a csv string
        }
    }

    public Pizza getPizzaFromListNumber(int number){
        for (Pizza pizza : pizzasInMenu) {
            if (pizza.getPizzaNr = number) return pizza; // TODO: 02/11/2021 add getPizzaNr to Pizza
        }
        return null;
    }
}
