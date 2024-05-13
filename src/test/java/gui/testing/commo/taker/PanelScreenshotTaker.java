package gui.testing.commo.taker;

import gui.testing.commo.interfaces.IScreenshotTaker;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Реализация {@code IScreenshotTaker} для создания скриншотов определенного {@link Component}.
 * Эта реализация разработана специально для создания скриншотов панелеподобных компонентов.
 * Она инкапсулирует функциональность для установки компонента, создания его скриншота и получения идентификатора компонента.
 */
public class PanelScreenshotTaker implements IScreenshotTaker {
    /**
     * Компонент пользовательского интерфейса, с которого будет сделан снимок экрана. Предполагается, что этот
     * компонент представляет собой кнопку или аналогичный интерактивный элемент пользовательского интерфейса.
     */
    private Component component;

    /**
     * Устанавливает компонент, для которого будет сделан скриншот. Компонент должен быть диалоговым или
     * компонентом, связанным с диалогом. Ожидается, что у компонента будет задано его имя, так как оно будет использоваться в качестве
     * идентификатора.
     *
     * @param component компонент диалога для захвата на скриншоте.
     * @return
     */
    @Override
    public Component setComponent(Component component) {
        this.component = component;
        return component;
    }

    /**
     * Делает снимок экрана текущего установленного компонента. Этот метод предоставляет специальную реализацию
     * для захвата визуального состояния компонентов диалога. Фактическая реализация может отличаться и должна
     * быть предоставлена в соответствии с конкретными требованиями к скриншотам диалогов.
     *
     * @return {@code BufferedImage} представляющий собой снимок экрана компонента.
     */
    @Override
    public BufferedImage takeScreenshot() {
        return new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); // Placeholder
    }

    /**
     * Получает идентификатор компонента. Предполагается, что идентификатор - это имя компонента,
     * которое должно быть установлено до вызова этого метода. Он используется для уникальной идентификации компонента внутри
     * приложения.
     *
     * @return идентификатор компонента, обычно его имя.
     */
    @Override
    public String getComponentIdentifier() {
        return component.getName();
    }
}
