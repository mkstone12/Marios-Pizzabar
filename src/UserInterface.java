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



    public void printMenu(String menu){
        System.out.println("Menu:");
        System.out.println(menu);
        System.out.println();
    }


    public void printActiveOrders(){
    }

    public String nameOfOrder(){
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast navnet på bestillingen");
        return input.nextLine();
    }

    public int addToOrder(){
        Scanner input = new Scanner(System.in);
        System.out.println("Hvad vil du gerne tilføje til ordre");
        return input.nextInt();
    }

    public int whoMany(){
        Scanner input = new Scanner(System.in);
        System.out.println("Hvor mange af denne type pizzaer");
        return input.nextInt();
    }

    public int toEndOrder(){
        Scanner input = new Scanner(System.in);
        System.out.println("1. Vil du tilføje til order\n2. Vil du afslutte order");

        //TO DO check om det er 1 eller 2

        return input.nextInt();

    }

    public void printFinalOrder(String order, double price,int ETA){
        System.out.println("\n\nOrderen er oprettet\nDer er bestilt:");
        System.out.println(order);
        System.out.println("Prisen for denne order er " + price + " Kr\nDen skal være klar om " + ETA + " minutter\n\n");

    }
}


