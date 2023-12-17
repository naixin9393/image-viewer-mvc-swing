package software.imageviewer;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import software.imageviewer.gui.command.ImageCommandManager;
import software.imageviewer.gui.command.NextImageCommand;
import software.imageviewer.gui.command.ChooseImageCommand;
import software.imageviewer.gui.command.PreviousImageCommand;
import software.imageviewer.gui.swing.SwingMainFrame;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        setTheme();
        SwingMainFrame imageViewer = new SwingMainFrame();
        setInitialImage(imageViewer);
        setCommands(imageViewer);
        imageViewer.setVisible(true);
    }

    private static void setCommands(SwingMainFrame imageViewer) {
        ImageCommandManager.getInstance()
                .add(ChooseImageCommand.class, new ChooseImageCommand(imageViewer.imageChooser(), imageViewer.imagePanel()))
                .add(NextImageCommand.class, new NextImageCommand(imageViewer.imagePanel()))
                .add(PreviousImageCommand.class, new PreviousImageCommand(imageViewer.imagePanel()));
    }

    private static void setInitialImage(SwingMainFrame imageViewer) {
        LinkedImage linkedImage = new FileImageLoader(new File("src/main/resources")).load();
        imageViewer.imagePanel().image(linkedImage);
    }

    private static void setTheme() {
        try {
            FlatCarbonIJTheme.setup();
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
    }
}
