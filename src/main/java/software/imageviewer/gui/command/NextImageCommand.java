package software.imageviewer.gui.command;

import software.imageviewer.gui.ImageDisplay;

public class NextImageCommand implements Command {
    private final ImageDisplay imageDisplay;

    public NextImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        if (imageDisplay.image().next() == null)
            return;
        imageDisplay.image(imageDisplay.image().next());
        imageDisplay.display();
    }
}
