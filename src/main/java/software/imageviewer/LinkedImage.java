package software.imageviewer;

public interface LinkedImage {
    String url();
    Drawable drawable();
    LinkedImage next();
    LinkedImage previous();
}
