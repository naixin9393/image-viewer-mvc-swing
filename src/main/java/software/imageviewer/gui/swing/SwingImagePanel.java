package software.imageviewer.gui.swing;

import software.imageviewer.Drawable;
import software.imageviewer.LinkedImage;
import software.imageviewer.gui.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SwingImagePanel extends JPanel implements ImageDisplay {
    private LinkedImage linkedImage;
    private final Map<String, BufferedImage> bitmaps = new HashMap<>();

    @Override
    public LinkedImage image() {
        return this.linkedImage;
    }

    @Override
    public void image(LinkedImage linkedImage) {
        this.linkedImage = linkedImage;
    }

    @Override
    public void display() {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        BufferedImage bitmap = bitmaps.computeIfAbsent(linkedImage.url(), n -> {
            try {
                return ImageIO.read(new File(linkedImage.url()));
            } catch (IOException e) {
                return null;
            }
        });
        Drawable scaledDrawable = linkedImage.drawable().resize(getWidth(), getHeight());
        Image scaledBitmap = bitmap.getScaledInstance(scaledDrawable.width(), scaledDrawable.height(), BufferedImage.TYPE_INT_RGB);
        g.drawImage(scaledBitmap,
                    scaledDrawable.center(getWidth(),
                    getHeight()).x(),
                scaledDrawable.center(getHeight(),
                    getHeight()).y(),
                null);
    }
}
