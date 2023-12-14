package software.imageviewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

public class FileImageLoader implements ImageLoader {
    private final List<String> imageExtensions = List.of("jpeg", "jpg", "png");
    private final File[] imageFiles;

    public FileImageLoader(File directory) {
        this.imageFiles = directory.listFiles(withImageExtension());
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        if (imageFiles == null || imageFiles.length == 0) return null;
        return new Image() {
            @Override
            public String name() {
                return imageFiles[i].getName();
            }

            @Override
            public Drawable drawable() {
                BufferedImage image = readImage();
                if (image == null) return null;
                return new Drawable(image.getWidth(), image.getHeight());
            }

            @Override
            public java.awt.Image bitmap() {
                return readImage();
            }

            private BufferedImage readImage() {
                try {
                    return ImageIO.read(imageFiles[i]);
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            public Image next() {
                return i + 1 == imageFiles.length ? null : imageAt(i + 1);
            }

            @Override
            public Image previous() {
                return i == 0 ? null : imageAt(i - 1);
            }
        };
    }

    private FileFilter withImageExtension() {
        return f -> isImageFile(f.getName());
    }

    private boolean isImageFile(String name) {
        return imageExtensions.stream()
                .anyMatch(name::endsWith);
    }
}
