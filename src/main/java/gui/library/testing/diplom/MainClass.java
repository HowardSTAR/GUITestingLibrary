package gui.library.testing.diplom;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {

    // Главное окно приложения
    static JFrame frame = getFrame();
    // Основная панель для размещения элементов
    static JPanel panel = new JPanel();

    public static void main(String[] args) {
        // Добавляем основную панель к фрейму
        frame.add(panel);
        // Создаем и добавляем кнопку для построения графика sin
        JButton buttonSin = getButton("SIN");
        panel.add(buttonSin);
        buttonSin.setName("buttonSin");

        // Добавляем слушателя на кнопку, который будет строить график при нажатии
        buttonSin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                constructGraphSin(1.0f, 1.0F);
            }
        });

        // Делаем окно видимым
        frame.setVisible(true);
    }

    // Метод для создания и настройки главного окна
    private static JFrame getFrame() {
        JFrame frame = new JFrame("Sin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        // Располагаем окно по центру экрана
        frame.setBounds(dimension.width / 2 - 400, dimension.height / 2 - 300, 800, 600);
        return frame;
    }

    // Фабричный метод для создания кнопок с заданным названием
    private static JButton getButton(String title) {
        return new JButton(title);
    }

    // Метод для запроса у пользователя смещения и обновления графика
    private static void offsetConstructGraphSin(int axis) {
        try {
            String m = JOptionPane.showInputDialog("Введите число для смещения графика:");
            // Проверяем, не нажал ли пользователь отмену
            if (m != null) {
                float offset = Float.parseFloat(m);
                // Обновляем график с учетом смещения
                if (axis == 1) {
                    constructGraphSin(offset, 1);
                } else if (axis == 2) {
                    constructGraphSin(1, offset);
                }
            }
        } catch (NumberFormatException e) {
            // Выводим сообщение об ошибке, если введено не число
            JOptionPane.showMessageDialog(null, "Некорректный ввод! Пожалуйста, введите число.");
        }
    }

    // Метод для построения графика функции sin с заданными параметрами
    private static void constructGraphSin(float ay, float bx) {
        // Очищаем панель перед добавлением нового содержимого
        panel.removeAll();

        // Создаем серию данных для графика
        XYSeries series = new XYSeries("sin(a)");
        for (float i = (float) -(2 * Math.PI); i < 2 * Math.PI; i += 0.1) {
            series.add(i, Math.sin(i * bx) * ay);
        }

        // Создаем набор данных для графика
        XYDataset xyDataset = new XYSeriesCollection(series);
        // Получаем заголовок графика в зависимости от параметров
        String titleGraph = getTitleGraphSin(ay, bx);
        // Создаем график
        JFreeChart chart = ChartFactory.createXYLineChart(titleGraph, "x", "y", xyDataset, PlotOrientation.VERTICAL, true, true, true);

        // Панель для отображения графика
        ChartPanel chartPanel = new ChartPanel(chart);
        // Устанавливаем менеджер компоновки и добавляем график
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Кнопки для ввода смещения графика
        JButton buttonSX = getButton("Ввести смещение графика - x");
        JButton buttonSY = getButton("Ввести смещение графика - y");

        // Слушатели для кнопок, вызывающие смещение графика
        buttonSX.addActionListener(e -> offsetConstructGraphSin(2));
        buttonSY.addActionListener(e -> offsetConstructGraphSin(1));

        // Добавляем кнопки на панель кнопок
        buttonPanel.add(buttonSX);
        buttonPanel.add(buttonSY);
        // Добавляем панель кнопок в верхнюю часть основной панели
        panel.add(buttonPanel, BorderLayout.NORTH);


        buttonSX.setName("buttonSX");
        buttonSY.setName("buttonSY");
        chartPanel.setName("chartPanel");

        // Обновляем фрейм для отображения изменений
        frame.pack();
        frame.repaint();
        frame.setVisible(true);
    }

    // Метод для получения заголовка графика в зависимости от параметров
    private static String getTitleGraphSin(float a, float b) {
        if (b == 1 && a == 1) {
            return "y = sin(x)";
        } else if (b != 1) {
            return "y = sin(" + b + "x)";
        } else {
            return "y = " + a + "sin(x)";
        }
    }
}
