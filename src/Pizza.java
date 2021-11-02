public class Pizza {

    private String navn;
    private String desc;
    private double pris;
    private int pizzaNr;

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
