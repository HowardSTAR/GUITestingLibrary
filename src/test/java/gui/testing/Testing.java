package gui.testing;

import gui.testing.commo.AppTestCase;
import gui.testing.commo.GlobalVars;
import gui.testing.commo.ScreenshotHelper;
import gui.testing.commo.ScreenshotMaker;
import gui.testing.commo.compare.ExtendedScreenshotComparer;
import gui.testing.commo.compare.ScreenshotComparer;

import gui.testing.commo.interfaces.IScreenshotTaker;
import gui.testing.commo.taker.ButtonScreenshotTaker;
import gui.testing.commo.taker.PanelScreenshotTaker;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.assertj.swing.timing.Pause;
import org.junit.Test;

import javax.swing.*;
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

        inputDialog.textBox().enterText(String.valueOf(10));
        inputDialog.buttonWithText("OK").click();

        Pause.pause(2000);

        // Делаем скриншот соответствующего компонента
        // Замените 'graphComponent' на реальный компонент, который отображает график
         Component graphComponent = window.panel("chartPanel").target();
        // Для Mac
        File screenshot = takeScreenshot(graphComponent,
                "sinGraphTest", "/Users/vsevolodgrigorev/Desktop/диссер/workApp/result");

        compareScreenshots(screenshot,"sinGraphTest");
    }

    @Test
    public void testButtonClickEffect() throws IOException, AWTException {
        Component affectedComponent = window.button("buttonSin").target();
        Pause.pause(2000);
        File screenshot = ScreenshotHelper.takeScreenshot(affectedComponent,
                "buttonClickEffect", "/Users/vsevolodgrigorev/Desktop/диссер/workApp/result");

        compareScreenshots(screenshot,"buttonClickEffect");
    }

    @Test
    public void testDialogInput() throws IOException, AWTException {
        window.button("buttonSin").click();

        window.button("buttonSX").click();

        JOptionPaneFixture inputDialog = JOptionPaneFinder.findOptionPane()
                .withTimeout(GlobalVars.TIMEOUT).using(robot());

        Component affectedComponent = inputDialog.target();
        Pause.pause(2000);
        File screenshot = ScreenshotHelper.takeScreenshot(affectedComponent,
                "dialogInput", "/Users/vsevolodgrigorev/Desktop/диссер/workApp/result");

        compareScreenshots(screenshot,"dialogInput");
    }

    @Test
    public void testButtonAppearance() throws IOException, AWTException {
        JButton button = window.button("buttonSin").target();
        ButtonScreenshotTaker buttonTaker = new ButtonScreenshotTaker();
        buttonTaker.setComponent(button);

        File screenshot = ScreenshotHelper.takeScreenshot(buttonTaker.setComponent(button),
                "buttonAppearanceTest", "/Users/vsevolodgrigorev/Desktop/диссер/workApp/result");
        compareScreenshots(screenshot, "buttonAppearanceTest");
    }

    private void compareScreenshots(File screenshot, String testName) throws IOException {
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

        ExtendedScreenshotComparer comparer = new ExtendedScreenshotComparer();
        comparer.checkCorrectsLighting(screenshot, testName, delta, etalonPath, resultPath);
    }
}
