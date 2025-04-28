package listview.players;

public class Country {
    private String name;
    private int rate;
    private int flagResId;

    public Country(String name, int rate, int flagResId){
        this.name = name;
        this.rate = rate;
        this.flagResId = flagResId;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public int getFlagResId() {
        return flagResId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setFlagResId(int flagResId) {
        this.flagResId = flagResId;
    }
}
