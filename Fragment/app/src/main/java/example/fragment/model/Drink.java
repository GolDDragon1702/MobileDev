package example.fragment.model;

public class Drink {
    private final int id;
    private final String name;
    private final int price;
    private final int imageResId;
    private final String origin;
    private final String manufacturer;
    private final String volume;
    private final String country;

    public Drink(int id, String name, int price, int imageResId, String origin,
                String manufacturer, String volume, String country) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.origin = origin;
        this.manufacturer = manufacturer;
        this.volume = volume;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getOrigin() {
        return origin;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getVolume() {
        return volume;
    }

    public String getCountry() {
        return country;
    }
} 