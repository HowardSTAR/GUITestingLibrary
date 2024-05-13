package gui.testing.commo;

import gui.testing.commo.compare.ScreenshotComparer;
import gui.testing.commo.interfaces.IScreenshotTaker;
import gui.testing.commo.taker.ButtonScreenshotTaker;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static gui.testing.commo.ScreenshotHelper.generateDiffImagePath;
import static gui.testing.commo.ScreenshotHelper.loadImage;

/**
 * Класс {@code ScreenshotMaker} предоставляет функциональность для создания скриншотов компонентов
 * пользовательского интерфейса, сравнения этих скриншотов с эталонными изображениями и выделения различий,
 * если таковые имеются.
 * Этот класс полагается на реализации {@code IScreenshotTaker} для создания скриншотов для
 * определенных типов компонентов и использует {@code ScreenshotComparer} для сравнения и выделения различий.
 */
public class ScreenshotMaker {
    /**
     * Компаратор, используемый для сравнения скриншотов и выделения различий.
     */
    private ScreenshotComparer screenshotComparer = new ScreenshotComparer() {
        @Override
        public void highlightDifferencesAndSave(BufferedImage currentScreenshot,
                                                BufferedImage referenceImage, String diffImagePath) {
        }
    };

    /**
     * Map, содержащая ассоциации между классами компонентов и их соответствующими создателями скриншотов.
     */
    private Map<Class<? extends Component>, IScreenshotTaker> screenshotTakers = new HashMap<>();

    /**
     * Конструирует новый {@code ScreenshotMaker} и инициализирует его с устройством
     * для создания скриншотов по умолчанию для
     * {@code Component} class.
     */
    public ScreenshotMaker() {
        screenshotTakers.put(Component.class, new ButtonScreenshotTaker());
    }

    /**
     * Делает скриншот указанного компонента, сравнивает его с эталонным изображением,
     * и выделяет различия, если таковые обнаружены. Различия сохраняются в отдельном изображении.
     *
     * @param component Компонент пользовательского интерфейса, для которого нужно сделать скриншот.
     * @param referenceImagePath путь к эталонному изображению для сравнения.
     * @throws IOException, если во время процесса произошла ошибка.
     * @throws UnsupportedOperationException, если для класса компонента не найден {@code IScreenshotTaker}.
     */
    public void takeAndCompareScreenshot(Component component, String referenceImagePath) throws IOException {
        IScreenshotTaker taker = screenshotTakers.get(component.getClass());
        if (taker == null) {
            throw new UnsupportedOperationException("No taker for " + component.getClass());
        }
        taker.setComponent(component);
        BufferedImage currentScreenshot = taker.takeScreenshot();
        BufferedImage referenceImage = loadImage(referenceImagePath);

        if (!screenshotComparer.compareImages(currentScreenshot, referenceImage)) {
            String diffImagePath = generateDiffImagePath(component);
            screenshotComparer.highlightDifferencesAndSave(currentScreenshot, referenceImage, diffImagePath);
            System.out.println("Различия сохраняются до: " + diffImagePath);
        } else {
            System.out.println("Различий не обнаружено.");
        }
    }
}