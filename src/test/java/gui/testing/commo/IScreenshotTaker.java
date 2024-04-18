package gui.testing.commo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface IScreenshotTaker {
    void setComponent(Component component); // Sets the target Swing component for the screenshot.
    BufferedImage takeScreenshot(); // Takes and returns a screenshot of the set component.
    String getComponentIdentifier(); // Returns a unique identifier for the component, useful for naming and debugging.
}



