package gui.testing.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {

    // Modified to include a savePath parameter for flexibility
    public static File takeScreenshot(Component component, String nameScreenShot, String savePath) throws AWTException, IOException {
        Rectangle captureRect = component.getBounds();
        Point locationOnScreen = component.getLocationOnScreen();
        captureRect.setLocation(locationOnScreen);

        Robot robot = new Robot();
        BufferedImage capturedImage = robot.createScreenCapture(captureRect);

        // Use provided savePath for saving the screenshot
        File screenshotFile = new File(savePath + File.separator + nameScreenShot + ".png");
        ImageIO.write(capturedImage, "PNG", screenshotFile);

        return screenshotFile;
    }

    // Example utility method for loading an image from a file
    public static BufferedImage loadImage(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }

    /**
     * Генерирует путь для сохранения изображения, выделяющего различия, на основе компонента.
     *
     * @param component Компонент, для которого генерируется путь к diff-изображению.
     * @return Полный путь к файлу diff-изображения.
     */    public static String generateDiffImagePath(Component component) {
        // Define a base directory for diff images. This could be configurable elsewhere.
        String diffImagesBaseDir = System.getProperty("user.home") + File.separator + "diffImages";

        // Ensure the directory exists
        File diffImagesDir = new File(diffImagesBaseDir);
        if (!diffImagesDir.exists()) {
            diffImagesDir.mkdirs();
        }

        // Construct the diff image file name, potentially using the component's class name and hashCode for uniqueness
        String imageName = component.getClass().getSimpleName() + "_" + component.hashCode() + "_diff.png";

        // Return the full path
        return diffImagesBaseDir + File.separator + imageName;
    }
}
