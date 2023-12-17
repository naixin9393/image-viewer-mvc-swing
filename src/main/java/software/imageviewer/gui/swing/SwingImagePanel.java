package software.imageviewer.gui.swing;

import software.imageviewer.Drawable;
import software.imageviewer.LinkedImage;
import software.imageviewer.gui.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImagePanel extends JPanel implements ImageDisplay {
    private LinkedImage linkedImage;

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
        BufferedImage bitmap = bitmap();
        Drawable scaledDrawable = linkedImage.drawable().resize(getWidth(), getHeight());
        Image scaledBitmap = bitmap.getScaledInstance(scaledDrawable.width(), scaledDrawable.height(), BufferedImage.TYPE_INT_RGB);
        g.drawImage(scaledBitmap,
                    scaledDrawable.center(getWidth(),
                    getHeight()).x(),
                scaledDrawable.center(getHeight(),
                    getHeight()).y(),
                null);
    }

    private BufferedImage bitmap() {
        BufferedImage bitmap;
        try {
            bitmap = ImageIO.read(new File(linkedImage.url()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bitmap;
    }
}
