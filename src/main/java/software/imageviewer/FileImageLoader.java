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
    public LinkedImage load() {
        return new MyLinkedImage(0);
    }

    class MyLinkedImage implements LinkedImage {
        private final String url;
        private final MyLinkedImage previous;
        private final MyLinkedImage next;
        private final Drawable drawable;

        public MyLinkedImage(int i) {
            this.previous = null;
            this.next = new MyLinkedImage(this, i + 1);
            File imageFile = imageFiles[i];
            BufferedImage bimage = loadImage(i);
            this.drawable = new Drawable(bimage.getWidth(), bimage.getHeight());
            this.url = imageFile.getPath();
        }

        private MyLinkedImage(MyLinkedImage previous, int i) {
            this.previous = previous;
            this.next = i + 1 == imageFiles.length ? null : new MyLinkedImage(this, i + 1);
            File imageFile = imageFiles[i];
            BufferedImage bimage = loadImage(i);
            this.drawable = new Drawable(bimage.getWidth(), bimage.getHeight());
            this.url = imageFile.getPath();
        }

        private BufferedImage loadImage(int i) {
            try {
                return ImageIO.read(new File(imageFiles[i].getPath()));
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        public String url() {
            return this.url;
        }

        @Override
        public Drawable drawable() {
            return this.drawable;
        }

        @Override
        public LinkedImage next() {
            return this.next;
        }

        @Override
        public LinkedImage previous() {
            return this.previous;
        }
    }

    private FileFilter withImageExtension() {
        return f -> isImageFile(f.getName());
    }

    private boolean isImageFile(String name) {
        return imageExtensions.stream()
                .anyMatch(name::endsWith);
    }
}
