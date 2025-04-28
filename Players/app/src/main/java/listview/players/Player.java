package listview.players;

public class Player {
    private String name;
    private String birthday;
    private int age;
    private int imageResId;
    private int flagResId;

    public Player(String name, String birthday, int age, int imageResId, int flagResId) {
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.imageResId = imageResId;
        this.flagResId = flagResId;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getAge() {
        return age;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getFlagResId() {
        return flagResId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setFlagResId(int flagResId) {
        this.flagResId = flagResId;
    }
}
