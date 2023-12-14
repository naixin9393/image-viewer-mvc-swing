package software.imageviewer.gui.swing;

import software.imageviewer.Drawable;
import software.imageviewer.Image;
import software.imageviewer.gui.ImageDisplay;
import javax.swing.*;
import java.awt.*;

public class SwingImagePanel extends JPanel implements ImageDisplay {
    private Image image;

    @Override
    public Image image() {
        return this.image;
    }

    @Override
    public void image(Image image) {
        if (this.image != null) {
            this.image = image;
            return;
        }
        this.image = scaled(image);
    }

    private Image scaled(Image image) {
        if (image == null) return null;
        Drawable drawable = image.drawable();
        return new Image() {
            @Override
            public String name() {
                return image.name();
            }

            @Override
            public Drawable drawable() {
                return drawable.resize(getWidth(), getHeight());
            }

            @Override
            public java.awt.Image bitmap() {
                return image.bitmap().getScaledInstance(drawable.resize(getWidth(), getHeight()).width(), drawable.resize(getWidth(), getHeight()).height(), java.awt.Image.SCALE_FAST);
            }

            @Override
            public Image next() {
                return scaled(image.next());
            }

            @Override
            public Image previous() {
                return scaled(image.previous());
            }
        };
    }

    @Override
    public void display() {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image.bitmap(),
                    image.drawable().center(getWidth(),
                    getHeight()).x(),
                image.drawable().center(getHeight(),
                    getHeight()).y(),
                null);
    }
}
