package software.imageviewer;

public interface Image {
    String name();
    Drawable drawable();
    java.awt.Image bitmap();
    Image next();
    Image previous();
}
