public class Pizza {

    private String name;
    private String desc;
    private double price;
    private int pizzaNr;

    public Pizza(String csv) {
        //checks if the string that has been passed is not empty
        if (csv == null) throw new IllegalArgumentException("Cannot create a new Pizza without proper information");

        // split the csv string and save in array
        String[] components = csv.split(";");

        // save the info to the respective attributes
        this.pizzaNr = Integer.parseInt(components[0]);
        this.name = components[1];
        this.desc = components[2];
        this.price = Double.parseDouble(components[3]);
    }

    public Pizza(){

    }

    @Override
    public String toString() {
        return pizzaNr + ". " +
                name + ": " +
                desc + "......." +
                String.format("%.2fkr.", price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPizzaNr() {
        return pizzaNr;
    }

    public void setPizzaNr(int pizzaNr) {
        this.pizzaNr = pizzaNr;
    }

    public Pizza(String navn, String desc, double pris, int pizzaNr) {
        this.name = navn;
        this.desc = desc;
        this.price = pris;
        this.pizzaNr = pizzaNr;
    }
}
