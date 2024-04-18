package gui.testing.commo;

import java.awt.Component;
import java.awt.image.BufferedImage;

public class FrameScreenshotTaker implements IScreenshotTaker {
    private Component component;

    @Override
    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public BufferedImage takeScreenshot() {
        // Specific implementation for frames
        return new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); // Placeholder
    }

    @Override
    public String getComponentIdentifier() {
        return component.getName(); // Assuming name is set as identifier
    }
}
