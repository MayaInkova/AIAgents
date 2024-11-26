package Agents;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImageGeneratorApp {
    public static void main(String[] args) {

        // Създаваме списък с агенти
        List<AIImageAgent> agents = new ArrayList<>();

        // Добавяне на поне 1 агент за тест
        agents.add(new AIImageAgent("Agent 1"));

        // Тук потребителят може да въведе  от клавиатурата колко агента да има
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of agents to create: ");
        int numberOfAgents = scanner.nextInt();

        // Създаваме необходимия брой агенти
        for (int i = 0; i < numberOfAgents; i++) {
            agents.add(new AIImageAgent("Agent " + (i + 1)));
        }

        // Създаваме главния прозорец на приложението
        JFrame frame = new JFrame("AI Agents Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Създаваме Canvas и го добавяме към прозореца
        Canvas canvas = new Canvas(agents);
        frame.add(canvas);

        // Показваме прозореца
        frame.setVisible(true);
    }
}