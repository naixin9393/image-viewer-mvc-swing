package software.imageviewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileImageLoader implements ImageLoader {
    private final File originalFile;
    private final List<String> imageExtensions = List.of("jpeg", "jpg", "png");
    private final String[] images;

    public FileImageLoader(File file) {
        this.originalFile = file;
        if (file.isDirectory())
            this.images = toString(file.listFiles(withImageExtension()));
        else
            this.images = toString(new File(file.getParent()).listFiles(withImageExtension()));
    }

    private String[] toString(File[] files) {
        return Arrays.stream(files)
                .map(File::getAbsolutePath)
                .toList()
                .toArray(new String[0]);
    }

    @Override
    public LinkedImage load() {
        if (originalFile.isDirectory())
            return imageAt(0);
        else
            return searchFile(originalFile);
    }

    private LinkedImage searchFile(File originalFile) {
        for (int i = 0; i < images.length; i++) {
            if (images[i].endsWith(originalFile.getName()))
                return imageAt(i);
        }
        throw new RuntimeException("File not found");
    }

    private LinkedImage imageAt(int i) {
        return new LinkedImage(){

            @Override
            public String url() {
                return images[i];
            }

            @Override
            public Drawable drawable() {
                BufferedImage image = readImage();
                return new Drawable(image.getWidth(), image.getHeight());
            }

            private BufferedImage readImage() {
                try {
                    return ImageIO.read(new File(images[i]));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public LinkedImage next() {
                return i + 1 == images.length ? null : imageAt(i + 1);
            }

            @Override
            public LinkedImage previous() {
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
