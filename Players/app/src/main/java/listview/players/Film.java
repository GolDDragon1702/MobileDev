package listview.players;

public class Film {
    public String title;
    public String description;
    public int imageResId;
    public int rating; // từ 0 đến 5

    public Film(String title, String description, int imageResId, int rating) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
