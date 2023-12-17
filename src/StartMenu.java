
import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;

/**
 * Authors: Na Nguyen, James Hernandez, Linh Nguyen
 * Setting up the starter Menu canvas after the game is run. Menu offers 3 different levels of difficulty for player to choose.
 * This is also the home menu that user can choose to come back to after playing.
 */
public class StartMenu {
    public static CanvasWindow menuCanvas;

    public static void displayStartMenu(GameManager gameManager) {
        menuCanvas = new CanvasWindow("Pokemon Card Game - Start Menu", 1200, 1000);

        // Title
        GraphicsText titleText = new GraphicsText("Pokemon Card Game");
        titleText.setFontSize(menuCanvas.getHeight() / 14); // Adjust the font size proportionally
        titleText.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() / 4);
        menuCanvas.add(titleText);

        // Instruction
        GraphicsText instructionText = new GraphicsText("Choose difficulty:");
        instructionText.setFontSize(menuCanvas.getHeight() / 25); // Adjust the font size proportionally
        instructionText.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() / 4 + 100);
        menuCanvas.add(instructionText);

        // Easy
        GraphicsText easy = new GraphicsText("Easy");
        easy.setFontSize(menuCanvas.getHeight() / 30); // Adjust the font size proportionally
        easy.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() / 4 + 170);
        menuCanvas.add(easy);

        // Medium
        GraphicsText medium = new GraphicsText("Medium");
        medium.setFontSize(menuCanvas.getHeight() / 30); // Adjust the font size proportionally
        medium.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() / 4 + 270);
        menuCanvas.add(medium);

        // Difficult
        GraphicsText difficult = new GraphicsText("Difficult");
        difficult.setFontSize(menuCanvas.getHeight() / 30); // Adjust the font size proportionally
        difficult.setCenter(menuCanvas.getWidth() / 2, menuCanvas.getHeight() / 4 + 370);
        menuCanvas.add(difficult);

        // Decorative pokemons for menu screen
        Pokemon pokemon1 = new Pokemon("pokemon_images/wooper.png");
        pokemon1.setMaxHeight(350);
        pokemon1.setMaxWidth(350);
        pokemon1.setCenter(menuCanvas.getWidth()*0.2, menuCanvas.getHeight()/1.8);
        menuCanvas.add(pokemon1);

        Pokemon pokemon2 = new Pokemon("pokemon_images/squirtle.png");
        pokemon2.setMaxHeight(350);
        pokemon2.setMaxWidth(350);
        pokemon2.setCenter(menuCanvas.getWidth()*0.8, menuCanvas.getHeight()/1.8);
        menuCanvas.add(pokemon2);


        // Buttons
        addDifficultyButton(menuCanvas, "5 Pairs", menuCanvas.getWidth() / 2, menuCanvas.getHeight() / 4 + 200, 5,
            10, 2, gameManager);
        addDifficultyButton(menuCanvas, "10 Pairs", menuCanvas.getWidth() / 2, menuCanvas.getHeight() /4 + 300, 10,
            20, 3, gameManager);
        addDifficultyButton(menuCanvas, "15 Pairs", menuCanvas.getWidth() / 2, menuCanvas.getHeight() /4 + 400, 15,
            30, 5, gameManager);   
    }

   
    /**
     * Generate a button for different levels If button is clicked, a game will be generated with the
     * appropriate numbers of cards & attempts according to the chosen level
     */
    private static void addDifficultyButton(CanvasWindow startCanvas,
                                        String text,
                                        double x,
                                        double y,
                                        int numPairs,
                                        int numAttempts,
                                        int timeInMinutes,
                                        GameManager gameManager) {
        Button button = new Button(text);
        button.setCenter(x, y);
        startCanvas.add(button);
        button.onClick(() -> {
            startCanvas.closeWindow();
            gameManager.setAttempts(numAttempts);
            gameManager.createGameCanvas();
            gameManager.cardGenerator(numPairs);
            gameManager.setCardNum(numPairs);
            gameManager.startTimer(timeInMinutes);
            gameManager.updateCanvas();
        });
    }

}
