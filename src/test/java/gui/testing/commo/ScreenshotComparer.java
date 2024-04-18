package gui.testing.commo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.assertj.swing.assertions.Assertions.assertThat;

public abstract class ScreenshotComparer {

    public ScreenshotComparer() {
    }

    public void checkCorrectsLighting(File tempScreenshot, String nameScreenShot, int delta, String etalonPath, String resultPath) throws IOException {
        BufferedImage expectedImg = ImageIO.read(tempScreenshot);
        BufferedImage etalonImg = ImageIO.read(new File(etalonPath + "etalon_" + nameScreenShot + ".png"));

        int width = expectedImg.getWidth();
        int height = expectedImg.getHeight();

        BufferedImage highlightedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = highlightedImg.createGraphics();
        g.drawImage(etalonImg, 0, 0, null);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int expectedRgb = expectedImg.getRGB(x, y);
                int etalonRgb = etalonImg.getRGB(x, y);

                if (Math.abs((expectedRgb & 0xFF) - (etalonRgb & 0xFF)) > delta
                        || Math.abs(((expectedRgb >> 8) & 0xFF) - ((etalonRgb >> 8) & 0xFF)) > delta
                        || Math.abs(((expectedRgb >> 16) & 0xFF) - ((etalonRgb >> 16) & 0xFF)) > delta) {
                    highlightedImg.setRGB(x, y, Color.RED.getRGB());
                }
            }
        }
        g.dispose();

        File folder = new File(resultPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File imageFile = new File(resultPath + "check_" + nameScreenShot + ".png");
        ImageIO.write(highlightedImg, "PNG", imageFile);

        assertThat(expectedImg).as("Скриншоты не совпадают. Различия можно посмотреть по пути " + resultPath).isEqualTo(etalonImg);
    }

    public boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        return false;
    }

    public abstract void highlightDifferencesAndSave(BufferedImage currentScreenshot, BufferedImage referenceImage, String diffImagePath);
}
