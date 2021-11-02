public class Pizza {

    private String navn;
    private String desc;
    private double pris;
    private int pizzaNr;

    public Pizza(String csv) {
        //checks if the string that has been passed is not empty
        if (csv == null) throw new IllegalArgumentException("Cannot create a new Pizza without proper information");

        // split the csv string and save in array
        String[] components = csv.split(";");

        // save the info to the respective attributes
        this.navn = components[0];
        this.desc = components[1];
        this.pris = Integer.parseInt(components[2]);
        this.pizzaNr = Integer.parseInt(components[3]);
    }

    @Override
    public String toString() {
        return pizzaNr + ". " +
                navn + ": " +
                desc + "......." +
                pris + ",-";
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public int getPizzaNr() {
        return pizzaNr;
    }

    public void setPizzaNr(int pizzaNr) {
        this.pizzaNr = pizzaNr;
    }

    public Pizza(String navn, String desc, double pris, int pizzaNr) {
        this.navn = navn;
        this.desc = desc;
        this.pris = pris;
        this.pizzaNr = pizzaNr;
    }
}
