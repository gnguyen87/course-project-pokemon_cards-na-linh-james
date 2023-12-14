import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;

import java.awt.Color;
import java.awt.GraphicsEnvironment;

public class StartMenu {

    public static void displayStartMenu(GameManager gameManager) {
        CanvasWindow menuCanvas = new CanvasWindow("Pokemon Card Game - Start Menu", 1200, 1000);

        // Title
        GraphicsText titleText = new GraphicsText("Pokemon Card Game");
        titleText.setFontSize(menuCanvas.getHeight() / 20); // Adjust the font size proportionally
        titleText.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() / 4);
        menuCanvas.add(titleText);

        // Instruction
        GraphicsText instructionText = new GraphicsText("Choose difficulty:");
        instructionText.setFontSize(menuCanvas.getHeight()  / 25); // Adjust the font size proportionally
        instructionText.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() /4 + 100);
        menuCanvas.add(instructionText);

        // Easy
        GraphicsText easy = new GraphicsText("Easy");
        easy.setFontSize(menuCanvas.getHeight()  / 30); // Adjust the font size proportionally
        easy.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() * 1/4 + 170);
        menuCanvas.add(easy);

         // Medium
        GraphicsText medium = new GraphicsText("Medium");
        medium.setFontSize(menuCanvas.getHeight()  / 30); // Adjust the font size proportionally
        medium.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() * 1/2 + 50);
        menuCanvas.add(medium);

        // Difficult
        GraphicsText difficult = new GraphicsText("Difficult");
        difficult.setFontSize(menuCanvas.getHeight()  / 30); // Adjust the font size proportionally
        difficult.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() * 1/2 + 150);
        menuCanvas.add(difficult);


        // Buttons
        addDifficultyButton(menuCanvas, "5 Pairs", menuCanvas.getWidth() / 2 , menuCanvas.getHeight() * 1/4 + 200, 5, 10, gameManager);
        addDifficultyButton(menuCanvas, "10 Pairs", menuCanvas.getWidth() / 2, menuCanvas.getHeight() * 1/2 + 90, 10, 20, gameManager);
        addDifficultyButton(menuCanvas, "15 Pairs", menuCanvas.getWidth() / 2, menuCanvas.getHeight() * 1/2 + 180, 15, 30, gameManager);
    }

    private static void addDifficultyButton(CanvasWindow startCanvas, String text, double x, double y, int numPairs, int numAttempts, GameManager gameManager) {
        Button button = new Button(text);
        button.setCenter(x, y);
        startCanvas.add(button);


        button.onClick(() -> {
                startCanvas.closeWindow();
                gameManager.setAttempts(numAttempts);
                gameManager.createGameCanvas();
                gameManager.cardGenerator(numPairs);
                startCanvas.draw();
            });
    }
}
