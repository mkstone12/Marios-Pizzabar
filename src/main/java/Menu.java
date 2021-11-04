import java.util.ArrayList;
import java.util.List;

public class Menu {

    private ArrayList<Pizza> pizzasInMenu;

    public Menu(List<String> menuList){
        this.pizzasInMenu = new ArrayList<>();
        for(String item : menuList){
            pizzasInMenu.add(new Pizza(item));
        }
    }

    public Pizza getPizzaFromListNumber(int number){
        for (Pizza pizza : pizzasInMenu) {
            if (pizza.getPizzaNr() == number) return pizza;
        }
        return null;
    }

    public String getListofPizzas(){
        StringBuilder returnString = new StringBuilder();
        for (Pizza pizza : pizzasInMenu){
            returnString.append(pizza).append('\n');
        }
        return returnString.toString();
    }
}
