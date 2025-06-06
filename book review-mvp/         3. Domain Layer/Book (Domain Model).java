
public class Book {

    private String id;
    private String title;
    private String author;
    private String description;
    private String thumbnailUrl;
    private float rating;

    public Book(String id, String title, String author, String description, String thumbnailUrl, float rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.rating = rating;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
