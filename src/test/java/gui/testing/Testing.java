package gui.testing;

import gui.testing.commo.AppTestCase;
import gui.testing.commo.GlobalVars;
import gui.testing.commo.compare.ScreenshotComparer;

import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.assertj.swing.timing.Pause;
import org.junit.Test;

import java.awt.Component;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static gui.testing.commo.ScreenshotHelper.takeScreenshot;

public class Testing extends AppTestCase {

    @Test
    public void testSinGraphGeneration() throws IOException, AWTException {
        window.button("buttonSin").click();

        window.button("buttonSX").click();

        JOptionPaneFixture inputDialog = JOptionPaneFinder.findOptionPane()
                .withTimeout(GlobalVars.TIMEOUT).using(robot());

        inputDialog.textBox().enterText("1");
        inputDialog.buttonWithText("OK").click();

        Pause.pause(5000);

        // Делаем скриншот соответствующего компонента
        // Замените 'graphComponent' на реальный компонент, который отображает график
         Component graphComponent = window.panel("chartPanel").target();
        // Для Mac
        File screenshot = takeScreenshot(graphComponent,
                "sinGraphTest", "/Users/vsevolodgrigorev/Desktop/диссер/workApp/result");

        // Comparing the screenshot with a reference image
        String etalonPath = "/Users/vsevolodgrigorev/Desktop/диссер/workApp/etalon/";
        String resultPath = "/Users/vsevolodgrigorev/Desktop/диссер/workApp/diff/";
/*
//  Для PC
        File screenshot = takeScreenshot(graphComponent,
                "sinGraphTest", "C:\\Users\\vislavdis\\IdeaProjects\\test");

        // Comparing the screenshot with a reference image
        String etalonPath = "C:\\Users\\vislavdis\\IdeaProjects\\test\\etalon\\";
        String resultPath = "C:\\Users\\vislavdis\\IdeaProjects\\test\\result\\";
*/

        // Отрегулировать дельту так, как это необходимо для получения приемлемой цветовой дисперсии
        // Желательно чтобы она была равно 0
        int delta = 0;

        ScreenshotComparer comparer = new ScreenshotComparer() {
            @Override
            public void highlightDifferencesAndSave(BufferedImage currentScreenshot,
                                                    BufferedImage referenceImage, String diffImagePath) {
            }
        };
        comparer.checkCorrectsLighting(screenshot, "sinGraphTest", delta, etalonPath, resultPath);
    }
}
