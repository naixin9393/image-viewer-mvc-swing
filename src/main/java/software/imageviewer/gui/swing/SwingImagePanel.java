package software.imageviewer.gui.swing;

import software.imageviewer.gui.ImagePanel;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class SwingImagePanel extends JPanel implements ImagePanel {
    private Image image;

    @Override
    public Image next() {
        return null;
    }

    @Override
    public Image previous() {
        return null;
    }

    @Override
    public ImagePanel define(List<Image> images) {
        this.setSize(1000, 1000);
        this.image = images.get(0);
        return this;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, centerXAxis(image), centerYAxis(image), null);
    }

    private int centerYAxis(Image image) {
        return (getHeight() - image.getHeight(null)) >> 1;
    }

    private int centerXAxis(Image image) {
        return (getWidth() - image.getWidth(null)) >> 1;
    }
}
