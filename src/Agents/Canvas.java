package Agents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.List;

public class Canvas extends JPanel {
    private final List<AIImageAgent> agents; // Списък с агенти
    private double scaleFactor = 1.0; // Мащаб за зуумане

    public Canvas(List<AIImageAgent> agents) {
        this.agents = agents;

        // Таймер за анимация (опреснява на всеки 500 ms)
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint(); // Преиндикация на панела
            }
        });
        timer.start();

        // Добавяне на слушател за мишката (кликване добавя нов агент)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Добавяне на нов агент при кликване
                AIImageAgent newAgent = new AIImageAgent("Agent " + (agents.size() + 1));
                agents.add(newAgent);
                repaint();
            }
        });

        // Добавяне на слушател за клавиатурата (зумване)
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == KeyEvent.VK_EQUALS) {
                    zoomIn(); // Зумване при натискане на "+"
                } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                    zoomOut(); // Зумване при натискане на "-"
                }
                repaint(); // Преиндикация след зум
            }
        });

        setFocusable(true); //   Тук панелът  хваща събития от клавиатурата
        requestFocusInWindow();
    }

    // Метод за увеличаване на мащаба
    private void zoomIn() {
        scaleFactor += 0.1;
    }

    // Метод за намаляване на мащаба
    private void zoomOut() {
        scaleFactor = Math.max(0.1, scaleFactor - 0.1); // Не може да се намали под 0.1
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Рисуване на всички агенти
        for (AIImageAgent agent : agents) {
            Shape shape = agent.generateShape(getWidth(), getHeight());

            // Прилагане на трансформации (мащабиране)
            AffineTransform transform = new AffineTransform();
            transform.translate(agent.getX(), agent.getY());
            transform.scale(scaleFactor, scaleFactor); // Мащабиране на формата
            transform.translate(-agent.getX(), -agent.getY()); // Връщане на позицията на агента
            Shape scaledShape = transform.createTransformedShape(shape);

            g2d.setColor(agent.generateColor());
            g2d.fill(scaledShape); // Попълване с цветен агент
        }
    }
}