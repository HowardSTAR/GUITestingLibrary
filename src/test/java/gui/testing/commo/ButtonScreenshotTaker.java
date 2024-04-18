package gui.testing.commo;

import java.awt.Component;
import java.awt.image.BufferedImage;

public class ButtonScreenshotTaker implements IScreenshotTaker {
    private Component component;

    @Override
    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public BufferedImage takeScreenshot() {
        // Implementation to take screenshot of the button
        return new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); // Placeholder implementation
    }

    @Override
    public String getComponentIdentifier() {
        return component.getName(); // Assuming name is set as identifier
    }
}