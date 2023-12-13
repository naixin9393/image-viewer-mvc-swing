package software.imageviewer.mock;

import software.imageviewer.FileImageLoader;
import software.imageviewer.gui.swing.SwingMainFrame;

import java.awt.*;
import java.io.File;
import java.util.List;

public class MockMain {
    public static void main(String[] args) {
        SwingMainFrame mainFrame = new SwingMainFrame();
        List<Image> images = new FileImageLoader(new File("src/main/resources")).load();
        mainFrame.imagePanel().define(images);
        mainFrame.setVisible(true);
    }
}
