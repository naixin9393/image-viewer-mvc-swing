package software.imageviewer.gui.swing;

import software.imageviewer.gui.ImageDisplay;
import software.imageviewer.gui.command.Command;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingMainFrame extends JFrame {
    private SwingImagePanel imagePanel;
    private final Map<String, Command> commands = new HashMap<>();

    public SwingMainFrame() throws HeadlessException {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.add(createImagePanel());
        this.add(nextButton(), BorderLayout.EAST);
        this.add(previousButton(), BorderLayout.WEST);
    }

    private Component previousButton() {
        JButton button = new JButton("<");
        button.addActionListener(e -> commands.get("previous image").execute());
        return button;
    }

    private Component nextButton() {
        JButton button = new JButton(">");
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
