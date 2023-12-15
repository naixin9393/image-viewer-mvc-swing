package software.imageviewer.gui.swing;

import software.imageviewer.gui.ImageDisplay;
import software.imageviewer.gui.command.Command;
import software.imageviewer.gui.ImageChooser;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingMainFrame extends JFrame {
    private SwingImagePanel imagePanel;
    private final Map<String, Command> commands = new HashMap<>();
    private ImageChooser imageChooser;

    public SwingMainFrame() throws HeadlessException {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.add(createImagePanel());
        this.add(createNextButton(), BorderLayout.EAST);
        this.add(createPreviousButton(), BorderLayout.WEST);
        this.add(createMenuBar(), BorderLayout.NORTH);
        createImageChooser();
    }

    private void createImageChooser() {
        this.imageChooser = new SwingImageChooser();
    }

    private Component createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        return menuBar;
    }

    private Component createFileMenu() {
        JMenu menu = new JMenu("File");
        menu.add(createOpenMenuItem());
        return menu;
    }

    private Component createOpenMenuItem() {
        JMenuItem menuItem = new JMenuItem("Open");
        menuItem.addActionListener(e -> commands.get("open").execute());
        return menuItem;
    }

    private Component createPreviousButton() {
        JButton button = new JButton("<");
        button.addActionListener(e -> commands.get("previous image").execute());
        return button;
    }

    private Component createNextButton() {
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

    public ImageChooser imageChooser() {
        return this.imageChooser;
    }
}
