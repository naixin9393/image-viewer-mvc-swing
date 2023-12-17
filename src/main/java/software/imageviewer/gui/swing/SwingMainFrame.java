package software.imageviewer.gui.swing;

import software.imageviewer.gui.ImageDisplay;
import software.imageviewer.gui.command.*;
import software.imageviewer.gui.ImageChooser;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;

public class SwingMainFrame extends JFrame {
    private SwingImagePanel imagePanel;
    private SwingImageChooser imageChooser;
    private final ImageCommandManager commandManager = ImageCommandManager.getInstance();

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
        menuItem.addActionListener(e -> commandManager.execute(ChooseImageCommand.class));
        return menuItem;
    }

    private Component createPreviousButton() {
        JButton button = new JButton("<");
        button.addActionListener(e -> commandManager.execute(PreviousImageCommand.class));
        return button;
    }

    private Component createNextButton() {
        JButton button = new JButton(">");
        button.addActionListener(e -> commandManager.execute(NextImageCommand.class));
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

    public ImageChooser imageChooser() {
        return this.imageChooser;
    }
}
