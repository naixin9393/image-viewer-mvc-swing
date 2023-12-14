package software.imageviewer;

public interface Image {
    String location();
    Drawable drawable();
    Image next();
    Image prev();
}
