package gui.testing.commo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Этот класс предоставляет методы для создания скриншотов компонентов GUI,
 * загрузки изображений из файловой системы и генерации путей к файлам для сохранения разностных изображений.
 */
public class ScreenshotHelper {

    /**
     * Делает скриншот указанного компонента и сохраняет его по указанному пути с заданным именем.
     *
     * @param component Компонент GUI для создания скриншота.
     * @param nameScreenShot Имя файла скриншота без расширения файла.
     * @param savePath Путь к каталогу, в котором будет сохранен снимок экрана.
     * @return Объект File, представляющий сохраненный файл скриншота.
     * @throws AWTException Если конфигурация платформы не позволяет низкоуровневый захват экрана.
     * @throws IOException Если произошла ошибка при записи файла скриншота на диск.
     */
    public static File takeScreenshot(Component component, String nameScreenShot, String savePath) throws AWTException, IOException {
        Rectangle captureRect = component.getBounds();
        Point locationOnScreen = component.getLocationOnScreen();
        captureRect.setLocation(locationOnScreen);

        Robot robot = new Robot();
        BufferedImage capturedImage = robot.createScreenCapture(captureRect);

        File screenshotFile = new File(savePath + File.separator + nameScreenShot + ".png");
        ImageIO.write(capturedImage, "PNG", screenshotFile);

        return screenshotFile;
    }

    /**
     * Загружает изображение из указанного пути к файлу в память в виде BufferedImage.
     *
     * @param filePath Путь к файлу изображения для загрузки.
     * @return Объект BufferedImage, представляющий загруженное изображение.
     * @throws IOException Если во время чтения файла произошла ошибка.
     */
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
