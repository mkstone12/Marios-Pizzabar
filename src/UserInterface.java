import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private Menu test;

    public int printMainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("""
                Velkommen til Marios Pizzabar
                Hvad vil du gerne gøre?
                
                1. Se menu
                2. Se bestillingsoversigt
                3. Oprette ordre
                4. Redigere ordre
                5. Færdiggøre ordre  
                
                0. Afslutte programmet""");
        int choice;
        while(true){
        try{
            choice = input.nextInt();
            if(choice <=5 && choice >= 0){
            break;}
            else{
                System.out.println("Dette er ikke muligt");
            }
        }
        catch (InputMismatchException e){
            System.out.println("Dette er ikke muligt");
            input.nextLine();
        }}

        return choice;
    }



    public void printMenu(Menu menu){
        for(int i = 0; i < menu.getSize(); i++){
            ArrayList<Pizza> pizzaer = menu.getPizzaer();
            System.out.println(pizzaer.get(i));
        }
    }


    public void printBestillingsOversigt(){


    }


}


