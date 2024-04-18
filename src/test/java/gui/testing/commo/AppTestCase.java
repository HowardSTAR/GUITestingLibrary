package gui.testing.commo;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;

import javax.swing.*;

/**
 * Главный класс программы для запуска приложения
 */
public class AppTestCase extends AssertJSwingJUnitTestCase {

    protected FrameFixture window;

    @Override
    protected void onSetUp() {
        GuiActionRunner.execute(() -> gui.library.testing.diplom.MainClass.main(new String[0]));

        // Use GenericTypeMatcher to find the JFrame by its title
        GenericTypeMatcher<JFrame> matcher = new GenericTypeMatcher<JFrame>(JFrame.class) {
            @Override
            protected boolean isMatching(JFrame frame) {
                return "Sin".equals(frame.getTitle()) && frame.isShowing();
            }
        };

        window = new FrameFixture(robot(), robot().finder().find(matcher));
        window.show(); // It's important to make the frame visible for the test
    }

    @Override
    protected void onTearDown() {
        window.cleanUp();
    }
}
