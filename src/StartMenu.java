import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;
import java.awt.GraphicsEnvironment;

public class StartMenu {
    public static void displayStartMenu(GameManager gameManager) {
        // Get the screen dimensions
        int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();

        // Create the menuCanvas with screen dimensions
        CanvasWindow menuCanvas = new CanvasWindow("Pokemon Card Game - Start Menu", screenWidth, screenHeight);

        // Title
        GraphicsText titleText = new GraphicsText("Pokemon Card Game");
        titleText.setFontSize(screenHeight / 20); // Adjust the font size proportionally
        titleText.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() / 4);
        menuCanvas.add(titleText);

        // Instruction
        GraphicsText instructionText = new GraphicsText("Choose difficulty:");
        instructionText.setFontSize(screenHeight / 25); // Adjust the font size proportionally
        instructionText.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() / 2);
        menuCanvas.add(instructionText);

        // Buttons
        addDifficultyButton(menuCanvas, "Easy (5 Pairs)", menuCanvas.getWidth() / 2, menuCanvas.getHeight() * 3 / 4, 5, gameManager);
        addDifficultyButton(menuCanvas, "Medium (10 Pairs)", menuCanvas.getWidth() / 2, menuCanvas.getHeight() * 4 / 5, 10, gameManager);
        addDifficultyButton(menuCanvas, "Hard (15 Pairs)", menuCanvas.getWidth() / 2, menuCanvas.getHeight() - screenHeight / 40, 15, gameManager);
    }

    private static void addDifficultyButton(CanvasWindow startCanvas, String text, double x, double y, int numPairs, GameManager gameManager) {
        GraphicsText buttonText = new GraphicsText(text);
        buttonText.setFontSize(startCanvas.getHeight() / 25); // Adjust the font size proportionally
        double textWidth = buttonText.getBounds().getWidth();
        double textHeight = buttonText.getBounds().getHeight();

        Rectangle buttonShape = new Rectangle(x - textWidth / 2, y - textHeight / 2, textWidth, textHeight);
        buttonShape.setFillColor(Color.LIGHT_GRAY);
        buttonShape.setStrokeColor(Color.BLACK);

        GraphicsText label = new GraphicsText(text);
        label.setCenter(x, y - textHeight - startCanvas.getHeight() / 200); // Adjust the vertical position proportionally
        label.setFontSize(startCanvas.getHeight() / 25); // Adjust the font size proportionally

        startCanvas.add(buttonShape);
        startCanvas.add(buttonText);
        startCanvas.add(label);

        startCanvas.onClick(event -> {
            double clickX = event.getPosition().getX();
            double clickY = event.getPosition().getY();

            if (clickX >= buttonShape.getX() && clickX <= buttonShape.getX() + buttonShape.getWidth() &&
                    clickY >= buttonShape.getY() && clickY <= buttonShape.getY() + buttonShape.getHeight()) {
                // Click was inside the button, perform the action
                gameManager.startGame(numPairs);

                // Remove the difficulty selection canvas
                startCanvas.closeWindow();
            }
        });
    }
}
