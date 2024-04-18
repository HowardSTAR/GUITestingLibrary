package gui.testing.commo.compare;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Класс {@code ExtendedScreenshotComparer} расширяет {@code ScreenshotComparer}
 * для более детального сравнения двух изображений. Он предоставляет возможность
 * не только сравнивать два изображения на предмет сходства, но и выделять различия
 * и сохранять результирующее изображение, показывающее эти различия. Этот класс полезен в
 * автоматизированных средах тестирования, где визуальная согласованность имеет решающее значение.
 */
public class ExtendedScreenshotComparer extends ScreenshotComparer {

    /**
     * Сравнивает два изображения на предмет сходства.
     *
     * Этот метод переопределяет метод {@code compareImages} в {@code ScreenshotComparer}
     * для реализации более детальной логики сравнения, специфичной для нужд этого подкласса.
     * Фактическая реализация подробной логики сравнения должна быть представлена здесь.
     *
     * @param imgA - первое изображение для сравнения.
     * @param imgB второе изображение для сравнения.
     * @return {@code true}, если изображения считаются похожими в соответствии с реализованной логикой,
     * {@code false} в противном случае.
     */
    @Override
    public boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        return super.compareImages(imgA, imgB);
    }

    /**
     * Выделяет различия между двумя изображениями и сохраняет результат.
     *
     * Этот метод предоставляет реализацию, которая просто заполняет полученное
     * изображение красным цветом, указывая на разницу. В реальном сценарии этот метод
     * должен быть переопределен, чтобы реализовать специальную логику для выделения различий
     * между {@code currentScreenshot} и {@code referenceImage}, и сохранить
     * полученное изображение в указанный {@code diffImagePath}.
     *
     * @param currentScreenshot - текущее изображение скриншота.
     * @param referenceImage эталонное изображение для сравнения.
     * @param diffImagePath путь, по которому должно быть сохранено результирующее изображение разницы.
     */
    @Override
    public void highlightDifferencesAndSave(BufferedImage currentScreenshot, BufferedImage referenceImage, String diffImagePath) {
        BufferedImage diffImage = new BufferedImage(currentScreenshot.getWidth(), currentScreenshot.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = diffImage.getGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, diffImage.getWidth(), diffImage.getHeight());
    }
}
