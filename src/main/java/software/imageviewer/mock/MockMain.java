package software.imageviewer.mock;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import software.imageviewer.FileImageLoader;
import software.imageviewer.LinkedImage;
import software.imageviewer.gui.command.NextImageCommand;
import software.imageviewer.gui.command.PreviousImageCommand;
import software.imageviewer.gui.swing.SwingMainFrame;
import java.io.File;

public class MockMain {
    public static void main(String[] args) {
        try {
            FlatCarbonIJTheme.setup();
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        SwingMainFrame mainFrame = new SwingMainFrame();
        LinkedImage linkedImage = new FileImageLoader(new File("src/main/resources")).load();
        mainFrame.imagePanel().image(linkedImage);
        mainFrame.add("next image", new NextImageCommand(mainFrame.imagePanel()));
        mainFrame.add("previous image", new PreviousImageCommand(mainFrame.imagePanel()));
        mainFrame.setVisible(true);
    }
}
