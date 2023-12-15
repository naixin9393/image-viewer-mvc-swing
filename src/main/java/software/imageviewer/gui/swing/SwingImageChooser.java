package software.imageviewer.gui.swing;

import software.imageviewer.FileImageLoader;
import software.imageviewer.LinkedImage;
import software.imageviewer.gui.ImageChooser;

import javax.swing.*;
import java.io.File;

public class SwingImageChooser implements ImageChooser {
    @Override
    public LinkedImage choose() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("src/main/resources"));
        fileChooser.showOpenDialog(null);
        String path = fileChooser.getSelectedFile().getPath();
        return new FileImageLoader(new File(path)).load();
    }
}
