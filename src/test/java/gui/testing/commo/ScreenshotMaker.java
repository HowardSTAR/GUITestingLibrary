package gui.testing.commo;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static gui.testing.common.ScreenshotHelper.generateDiffImagePath;
import static gui.testing.common.ScreenshotHelper.loadImage;

public class ScreenshotMaker {
    private ScreenshotComparer screenshotComparer = new ScreenshotComparer() {
        @Override
        public void highlightDifferencesAndSave(BufferedImage currentScreenshot, BufferedImage referenceImage, String diffImagePath) {

        }
    };
    private Map<Class<? extends Component>, IScreenshotTaker> screenshotTakers = new HashMap<>();

    public ScreenshotMaker() {
        screenshotTakers.put(Component.class, new ButtonScreenshotTaker()); // Example mapping
    }

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
            System.out.println("Differences saved to: " + diffImagePath);
        } else {
            System.out.println("No differences detected.");
        }
    }
}