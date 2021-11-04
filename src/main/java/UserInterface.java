import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Scanner input = new Scanner(System.in);

    public int printMainMenu() {
        System.out.println("""
                Velkommen til Marios Pizzabar
                Hvad vil du gerne gøre?
                
                1. Se menu
                2. Se bestillingsoversigt
                3. Oprette ordre
                4. Redigere ordre
                5. Færdiggøre ordre
                6. Se statistik
                
                0. Afslutte programmet""");

        int choice;
        while(true){
        try{
            choice = input.nextInt();
            if(choice <=6 && choice >= 0){
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


    public void printActiveOrders(ArrayList<String> list) {
        for (String string : list)
        System.out.print(string);
    }

    // prints errorMessage in bright red and resets the text color afterwards
    public void errorPrint(String errorMessage){
        System.out.println("\033[0;91m" + errorMessage + "\u001B[0m");
    }

    public int[] editMenu() {
        System.out.println("Hvilken order vil du ændre? ");
        int orderChoice = input.nextInt();

        System.out.println("""
                Vil du:
                1. Tilføje til order
                2. Fjerne fra order
                3. Slette order""");

        int editChoice = input.nextInt();
        int[] choice = {orderChoice - 1 ,editChoice};
        return choice; // todo menu to choose order
    }

    public int removeFromOrder(){
        System.out.println("Hvad skal fjernes fra order");
        return 1;
    }

    public int whichOrderToComplete() {
        Scanner input = new Scanner(System.in);
        System.out.print("Hvilken order vil du færdigøre? ");
        while (!input.hasNextInt()){
            System.out.print("Du kan kun vælge med tal:");
            input.nextLine(); // to avoid scanner bug
        }
        return input.nextInt();
    }

    public String nameOfOrder(){
        System.out.println("Indtast navnet på bestillingen");
        input.nextLine();
        return input.nextLine();
    }

    public int addToOrder(){
        System.out.println("Vælg en pizza");
        return input.nextInt();
    }

    public int howMany(){
        System.out.println("Hvor mange af denne type pizzaer");
        return input.nextInt();
    }

    public int toEndOrder(){
        System.out.println("1. Vil du tilføje til order\n2. Vil du afslutte order");
        //TO DO check om det er 1 eller 2
        return input.nextInt();

    }

    public void printFinalOrder(String order, double price, LocalTime ETA){
        System.out.println("\n\nOrderen er oprettet\nDer er bestilt:");
        System.out.println(order);
        System.out.println("Prisen for denne order er " + price + " Kr\nDen skal være klar klokken " + ETA + "\n\n");
    }

    public int printStatsMenu() {
        System.out.println("""
                Vil du:
                1. Se total salg
                2. Se den meste populære pizza
                3. Skift datoer
                
                0. Forlade statistik""");
        return input.nextInt();
    }

    public String getDate(String type) {
        Scanner inputLine = new Scanner(System.in);
        System.out.print("Indtast datoen du vil søge " + type + " (dd/mm/åå) "); // check valid throws exception
        return inputLine.nextLine();
    }

    public void printTotalSales(String sales) {
        System.out.println(sales);
    }
}


