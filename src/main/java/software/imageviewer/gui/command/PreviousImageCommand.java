package software.imageviewer.gui.command;

import software.imageviewer.gui.ImageDisplay;

public class PreviousImageCommand implements Command {
    private final ImageDisplay imageDisplay;

    public PreviousImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        if (imageDisplay.image().previous() == null)
            return;
        imageDisplay.image(imageDisplay.image().previous());
        imageDisplay.display();
    }
}
