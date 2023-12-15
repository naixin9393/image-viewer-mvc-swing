package software.imageviewer.gui;

import software.imageviewer.LinkedImage;

public interface ImageDisplay {
    LinkedImage image();
    void image(LinkedImage image);
    void display();
}
