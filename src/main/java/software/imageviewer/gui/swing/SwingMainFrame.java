package software.imageviewer.gui.swing;

import software.imageviewer.gui.ImageDisplay;
import software.imageviewer.gui.command.Command;
import software.imageviewer.gui.command.NextImageCommand;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class SwingMainFrame extends JFrame {
    private SwingImagePanel imagePanel;
    private Map<String, Command> commands = new HashMap<>();

    public SwingMainFrame() throws HeadlessException {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.add(createImagePanel());
        this.add(nextButton(), BorderLayout.EAST);
    }

    private Component nextButton() {
        JButton button = new JButton("-->");
        button.addActionListener(e -> commands.get("next image").execute());
        return button;
    }

    public SwingImagePanel createImagePanel() {
        SwingImagePanel panel = new SwingImagePanel();
        this.imagePanel = panel;
        return panel;
    }

    public ImageDisplay imagePanel() {
        return this.imagePanel;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }
}
