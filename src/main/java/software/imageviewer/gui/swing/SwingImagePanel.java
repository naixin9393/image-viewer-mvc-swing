package software.imageviewer.gui.swing;

import software.imageviewer.Drawable;
import software.imageviewer.Image;
import software.imageviewer.gui.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SwingImagePanel extends JPanel implements ImageDisplay {
    private Image image;

    @Override
    public Image image() {
        return this.image;
    }

    @Override
    public void image(Image image) {
        this.image = image;
    }

    @Override
    public void display() {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        java.awt.Image scaledImage = scaled(image);
        Drawable scaledDrawable = scaledDrawable(image);
        g.drawImage(scaledImage,
                    scaledDrawable.center(getWidth(),
                    getHeight()).x(),
                scaledDrawable.center(getHeight(),
                    getHeight()).y(),
                null);
    }

    private Drawable scaledDrawable(Image image) {
        return image.drawable().resize(getWidth(), getHeight());
    }

    private  java.awt.Image scaled(Image image) {
        try {
            BufferedImage bimage = ImageIO.read(new File("src/main/resources/" + image.location()));
            Drawable drawable = image.drawable()
                    .resize(getWidth(), getHeight());
            return bimage.getScaledInstance(drawable.width(), drawable.height(), java.awt.Image.SCALE_SMOOTH);
        } catch (IOException e) {
            return null;
        }

    }
}
