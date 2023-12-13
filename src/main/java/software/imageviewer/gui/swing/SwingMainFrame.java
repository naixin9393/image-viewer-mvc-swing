package software.imageviewer.gui.swing;

import software.imageviewer.gui.ImagePanel;
import javax.swing.*;
import java.awt.*;

public class SwingMainFrame extends JFrame {
    private SwingImagePanel imagePanel;

    public SwingMainFrame() throws HeadlessException {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.add(createImagePanel());
    }

    public SwingImagePanel createImagePanel() {
        SwingImagePanel panel = new SwingImagePanel();
        this.imagePanel = panel;
        return panel;
    }

    public ImagePanel imagePanel() {
        return this.imagePanel;
    }
}
