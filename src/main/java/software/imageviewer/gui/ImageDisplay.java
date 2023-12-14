package software.imageviewer.gui;

import software.imageviewer.Image;

public interface ImageDisplay {
    Image image();
    void image(Image image);
    void display();
}
