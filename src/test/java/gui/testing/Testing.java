package gui.testing;

import gui.testing.commo.AppTestCase;
import gui.testing.commo.GlobalVars;
import gui.testing.commo.ScreenshotComparer;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.assertj.swing.timing.Pause;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static gui.testing.common.ScreenshotHelper.takeScreenshot;

public class Testing extends AppTestCase {

    @Test
    public void testSinGraphGeneration() throws IOException, AWTException {
        window.button("buttonSin").click();

        window.button("buttonSX").click();

        JOptionPaneFixture inputDialog = JOptionPaneFinder.findOptionPane()
                .withTimeout(GlobalVars.TIMEOUT).using(robot());

        inputDialog.textBox().enterText("10");
        inputDialog.buttonWithText("OK").click();

        Pause.pause(5000);

        // Taking a screenshot of the relevant component
        // Replace 'graphComponent' with the actual component that displays the graph
        Component graphComponent = window.panel("chartPanel").target();
        File screenshot = takeScreenshot(graphComponent,
                "sinGraphTest", "C:\\Users\\vislavdis\\IdeaProjects\\test");

        // Comparing the screenshot with a reference image
        String etalonPath = "C:\\Users\\vislavdis\\IdeaProjects\\test\\etalon\\";
        String resultPath = "C:\\Users\\vislavdis\\IdeaProjects\\test\\result\\";
        int delta = 0; // Adjust delta as needed for acceptable color variance

        ScreenshotComparer comparer = new ScreenshotComparer() {
            @Override
            public void highlightDifferencesAndSave(BufferedImage currentScreenshot,
                                                    BufferedImage referenceImage, String diffImagePath) {
            }
        };
        comparer.checkCorrectsLighting(screenshot, "sinGraphTest", delta, etalonPath, resultPath);
    }
}
