package software.imageviewer.gui;

import java.awt.*;
import java.util.List;

public interface ImagePanel {
    Image next();
    Image previous();
    ImagePanel define(List<Image> images);
}
