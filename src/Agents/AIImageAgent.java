package Agents;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class AIImageAgent {
    private String name;
    private int x, y; // Позиция на агента
    private int dx, dy; // Скорост на движение
    private Random random;

    public AIImageAgent(String name) {
        this.name = name;
        this.random = new Random();
        // Начална позиция на агента
        this.x = random.nextInt(500);
        this.y = random.nextInt(500);
        // Скорост на движение (положителна или отрицателна)
        this.dx = random.nextInt(5) + 1;
        this.dy = random.nextInt(5) + 1;
    }

    // Метод за движение на агента
    public void move(int canvasWidth, int canvasHeight) {
        x += dx;
        y += dy;

        // Проверка за граници и отскачане
        if (x < 0 || x > canvasWidth) dx = -dx;
        if (y < 0 || y > canvasHeight) dy = -dy;
    }

    // Генериране на случайна форма (кръг, правоъгълник или линия)
    public Shape generateShape(int canvasWidth, int canvasHeight) {
        int size = random.nextInt(50) + 10; // Размер на формата

        switch (random.nextInt(3)) {
            case 0: // Кръг
                return new Ellipse2D.Double(x, y, size, size);
            case 1: // Правоъгълник
                return new Rectangle2D.Double(x, y, size, size);
            case 2: // Линия
                return new Line2D.Double(x, y, x + size, y + size);
            default:
                return new Ellipse2D.Double(x, y, size, size); // По подразбиране
        }
    }

    // Генериране на случайна цветова  за агента
    public Color generateColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    // Гетериране на X координата на агента
    public int getX() {
        return x;
    }

    // Гетериране на Y координата на агента
    public int getY() {
        return y;
    }
}