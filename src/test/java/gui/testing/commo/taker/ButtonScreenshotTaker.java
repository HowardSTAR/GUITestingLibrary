package gui.testing.commo.taker;

import java.awt.*;
import java.awt.image.BufferedImage;

import gui.testing.commo.interfaces.IScreenshotTaker;


/**
 * Предоставляет функциональность для создания скриншота указанного компонента пользовательского интерфейса. Этот класс
 * реализует интерфейс {@link IScreenshotTaker}, позволяющий делать скриншоты
 * скриншотов компонентов пользовательского интерфейса, специально разработанных для кнопок или
 * подобных взаимодействующих элементов.
 * <p>
 * Эта реализация создает статическое изображение текущего состояния компонента, которое
 * может быть использовано для различных целей, таких как тестирование.
 * </p>
 */
public class ButtonScreenshotTaker implements IScreenshotTaker {
    /**
     * Компонент пользовательского интерфейса, с которого будет сделан снимок экрана. Предполагается, что этот
     * компонент представляет собой кнопку или аналогичный интерактивный элемент пользовательского интерфейса.
     */
    private Component component;

    /**
     * Устанавливает компонент, с которого будет сделан скриншот. Компонент должен быть.
     * кнопкой или аналогичным элементом пользовательского интерфейса, который может быть визуально представлен на скриншоте.
     *
     * @param component компонент пользовательского интерфейса, который нужно захватить на скриншоте.
     * @return
     */
    @Override
    public Component setComponent(Component component) {
        this.component = component;
        return component;
    }

    /**
     * Делает снимок экрана текущего установленного компонента пользовательского интерфейса. Этот метод захватывает
     * визуальное представление компонента и возвращает его в виде {@link BufferedImage}.
     * <p>
     * Примечание: Текущая реализация возвращает изображение-заполнитель. Фактическая
     * реализация должна захватывать визуальное состояние компонента.
     * </p>
     *
     * @return a {@link BufferedImage}, представляющий снимок экрана компонента.
     */
    @Override
    public BufferedImage takeScreenshot() {
        return new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Получает идентификатор для текущего установленного компонента. Этот идентификатор предназначен
     * для уникального различения компонента в пользовательском интерфейсе, что может быть
     * полезным для отладки или протоколирования.
     * <p>
     * Этот метод предполагает, что в качестве идентификатора используется свойство name компонента.
     * </p>
     *
     * @return {@link String}, представляющий уникальный идентификатор компонента.
     */
    @Override
    public String getComponentIdentifier() {
        return component.getName();
    }
}