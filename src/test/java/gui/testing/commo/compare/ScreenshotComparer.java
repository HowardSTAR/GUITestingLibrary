package gui.testing.commo.compare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.assertj.swing.assertions.Assertions.assertThat;

/**
 * Этот абстрактный класс предназначен для сравнения скриншотов, в частности, для проверки и
 * выделения различий в освещении или содержимом.
 * Он предоставляет базовую основу для операций сравнения скриншотов, позволяя настраивать способ обнаружения различий и
 * как обрабатываются или сохраняются результаты.
 */
public abstract class ScreenshotComparer {

    public ScreenshotComparer() {
    }

    /**
     * Сравнивает освещение и содержимое временного скриншота с эталонным (стандартным) изображением и выделяет различия.
     * Если найдены различия, превышающие заданное значение дельты, эти области выделяются на выходном изображении.
     * Результат сохраняется в указанную директорию.
     *
     * @param tempScreenshot Временный файл скриншота для сравнения.
     * @param nameScreenShot Идентификатор имени скриншота, используемый при именовании выходного файла.
     * @param delta Уровень толерантности к различиям. Различия в значениях RGB ниже этой дельты игнорируются.
     * @param etalonPath Путь к каталогу, в котором хранятся изображения эталона, включая концевой разделитель файлов.
     * @param resultPath Путь к каталогу, в котором должны быть сохранены изображения результата, включая разделитель файлов.
     * @throws IOException Если во время чтения или записи файла произошла ошибка.
     */
    public void checkCorrectsLighting(File tempScreenshot, String nameScreenShot, int delta,
                                      String etalonPath, String resultPath) throws IOException {
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

    /**
     * Сравнивает два объекта BufferedImage на предмет равенства.
     * Конкретные критерии сравнения должны быть реализованы подклассами.
     *
     * @param imgA Первое изображение для сравнения.
     * @param imgB Второе изображение для сравнения.
     * @return true, если изображения считаются эквивалентными, false в противном случае.
     */
    public boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        return false;
    }

    /**
     * Абстрактный метод, который должен быть реализован для выделения различий между двумя изображениями и
     * сохранения полученного изображения по указанному пути.
     * Метод выделения и критерии различий должны быть определены подклассами.
     *
     * @param currentScreenshot Текущий скриншот для сравнения.
     * @param referenceImage Эталонное изображение для сравнения.
     * @param diffImagePath Путь, по которому должно быть сохранено изображение различий.
     */
    public abstract void highlightDifferencesAndSave(BufferedImage currentScreenshot, BufferedImage referenceImage, String diffImagePath);
}
