package software.imageviewer.mock;

import software.imageviewer.FileImageLoader;
import software.imageviewer.Image;
import software.imageviewer.gui.command.NextImageCommand;
import software.imageviewer.gui.command.PreviousImageCommand;
import software.imageviewer.gui.swing.SwingMainFrame;
import java.io.File;

public class MockMain {
    public static void main(String[] args) {

        SwingMainFrame mainFrame = new SwingMainFrame();
        Image image = new FileImageLoader(new File("src/main/resources")).load();
        mainFrame.imagePanel().image(image);
        mainFrame.add("next image", new NextImageCommand(mainFrame.imagePanel()));
        mainFrame.add("previous image", new PreviousImageCommand(mainFrame.imagePanel()));
        mainFrame.setVisible(true);
    }
}
