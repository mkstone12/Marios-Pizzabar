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


    public void printActiveOrders(String string) {
        System.out.print(string);
    }

    // prints errorMessage in bright red and resets the text color afterwards
    public void errorPrint(String errorMessage){
        System.out.println("\033[0;91m" + errorMessage + "\u001B[0m");
    }

    public int[] editMenu(int orderSize) {
        System.out.println("Hvilken order vil du ændre? ");
        int orderChoice = 0;
        boolean goodChoice = false;
        while(!goodChoice){
            if (orderChoice  >= 1 && orderChoice <=orderSize){
                goodChoice = true;
            }
            else{
                System.out.println("Du kan kun vælge med tal mellem 1 og " + orderSize);
                try{
                    orderChoice = input.nextInt();
                }
                catch (InputMismatchException e){
                    input.nextLine();
                }}}

        System.out.println("""
                Vil du:
                1. Tilføje til order
                2. Fjerne fra order
                3. Slette order""");

        int editChoice = 0;
        goodChoice = false;
        while(!goodChoice){
            if (editChoice  >= 1 && editChoice <=3){
                goodChoice = true;
            }
            else{
                System.out.println("Du kan kun vælge med tal mellem 1 og 3");
                try{
                    editChoice = input.nextInt();
                }
                catch (InputMismatchException e){
                    input.nextLine();
                }}}

        int[] choice = {orderChoice - 1 ,editChoice};
        return choice;
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
        System.out.println("Indtast navnet og telefonnummer på bestillingen");
        input.nextLine();
        return input.nextLine();
    }

    public int addToOrder(){
        System.out.println("Vælg en pizza");
        input.nextLine();
        while (!input.hasNextInt()){
            System.out.print("Du kan kun vælge med tal:");
            input.nextLine(); // to avoid scanner bug
        }
        return input.nextInt();
    }
    public int addToOrder(boolean triedBefore){
        System.out.println("Denne pizza eksistere ikke. \nVælg en anden pizza");
        input.nextLine();
        while (!input.hasNextInt()){
            System.out.print("Du kan kun vælge med tal:");
            input.nextLine(); // to avoid scanner bug
        }
        return input.nextInt();
    }

    public int howMany(){
        System.out.println("Hvor mange af denne type pizzaer");
        int choice = 0;
        boolean goodChoice = false;
        while(!goodChoice){
            if (choice  >= 1){
                goodChoice = true;
            }
            else{
                System.out.println("Du kan kun vælge et tal større end 0");
                try{
                    choice = input.nextInt();
                }
                catch (InputMismatchException e){
                    input.nextLine();
                }
            }}

        return choice;
    }

    public int toEndOrder(){
        System.out.println("1. Vil du tilføje til order\n2. Vil du afslutte order");

        int choice = 0;
        boolean goodChoice = false;
        while(!goodChoice){

        if (choice  ==1 || choice == 2){
            goodChoice = true;
        }
        else{
            System.out.println("Du kan kun vælge tallene 1 og 2");
            try{
                choice = input.nextInt();
            }
            catch (InputMismatchException e){
                input.nextLine();
            }
        }}
        return choice;

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
                3. Skifte datoer
                
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


