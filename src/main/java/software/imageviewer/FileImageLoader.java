package software.imageviewer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileImageLoader implements ImageLoader {
    private final File directory;
    private final List<String> imageExtensions = List.of("jpeg", "jpg", "png");

    public FileImageLoader(File directory) {
        this.directory = directory;
    }

    @Override
    public List<Image> load() {
        File[] files = directory.listFiles(withImageExtension());
        if (files == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(files)
                .map(this::toImage)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Image toImage(File imageFile) {
        try {
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            return null;
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
