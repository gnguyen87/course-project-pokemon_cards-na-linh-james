
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;


public class GameManager {
    private CanvasWindow canvas;

    private List<PokemonCard> cards = new ArrayList<>();
    private PokemonCard firstFlipped;

    private Thread timeThread;
    private final double DISTANCE = 20;
    private int remainingTimeInSeconds = 5 * 60; // 5 minutes in seconds
    private int currentnumPairs;
    private GraphicsGroup timerGroup = new GraphicsGroup();

    private int attemptsCount;

    private GraphicsText attemptsText;
    private GraphicsText gameOverText;

    private Image gameOverImage;
    private Image winningImage;

    private Rectangle timerBarOutline;
    private Rectangle timerBar;

    private Image timerPokemon;
    private Image pokeball;
    private Image redoButton;
    private Image pauseButton;
    private Image homeButton;


    private boolean isPaused = false;
    private long startPauseTime; // Variable to track start time of pause


    public void startGame() {
        createGameCanvas();
    }

    /**
     * Set number of attempts based on difficulty level
     * 
     * @param numAttempts
     * @return attempts' count that corresponds to the chosen game level.
     */
    public int setAttempts(int numAttempts) {
        return this.attemptsCount = numAttempts;
    }

    /**
     * Create the Game Canvas for each level
     */
    public void createGameCanvas() {
        canvas = new CanvasWindow("Pokemon Card Puzzle", 1200, 1000);
        attemptsText = new GraphicsText("Attempts: " + attemptsCount);
        attemptsText.setFontSize(20);
        canvas.add(attemptsText);

        attemptsText.setCenter(canvas.getWidth() - 120, 50);
        gameOverText = new GraphicsText("Game Over!");
        gameOverText.setFontSize(40);
        gameOverText.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 2);

        gameOverImage = new Image("game_over/poke_game_over.png");
        gameOverImage.setMaxWidth(canvas.getWidth());
        gameOverImage.setMaxHeight(canvas.getHeight());
        gameOverImage.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 2);

        pokeball = new Image("pokeball.png");
        pokeball.setMaxHeight(100);
        pokeball.setMaxWidth(100);
        pokeball.setCenter(canvas.getWidth() - 120, 150);
        canvas.add(pokeball);

        winningImage = new Image("game_over/poke_game_over.png");
        winningImage.setMaxWidth(canvas.getWidth());
        winningImage.setMaxHeight(canvas.getHeight());
        winningImage.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 2);

        redoButton = new Image("buttons/redo_button.png");
        redoButton.setMaxHeight(150);
        redoButton.setMaxWidth(150);
        redoButton.setCenter(canvas.getWidth() - 120, 450);
        canvas.add(redoButton);

        canvas.onClick(event -> redoGame(event.getPosition()));

        pauseButton = new Image("buttons/pause_button.png");
        pauseButton.setMaxHeight(150);
        pauseButton.setMaxWidth(150);
        pauseButton.setCenter(canvas.getWidth() - 120, 600);
        canvas.add(pauseButton);

        canvas.onClick(event -> pauseGame(event.getPosition()));

        homeButton = new Image("buttons/home_button.png");
        homeButton.setMaxHeight(130);
        homeButton.setMaxWidth(130);
        homeButton.setCenter(canvas.getWidth() - 120, 750);
        canvas.add(homeButton);
        
        canvas.onClick(event -> returnHome(event.getPosition()));


    }

    /**
     * Generate the pokemon cards depending on the difficulty level
     * 
     * @param int numPairs: number of Pairs
     */
    public void cardGenerator(int numPairs) {
        int numberOfCards = numPairs * 2;

        File files = new File("res/pokemon_images");

        List<String> pokemonImages = pokemonImageStrings(files.listFiles());

        // Shuffle and select a subset of unique Pokemon images
        List<String> selectedImages = new ArrayList<>(pokemonImages.subList(0, numPairs));
        selectedImages.addAll(selectedImages); // Duplicate to create pairs
        Collections.shuffle(selectedImages);

        double centerX = canvas.getWidth() * 0.15;
        double centerY = canvas.getHeight() * 0.03;

        int numRows, numCols;

        // Set the grid layout based on the difficulty level
        if (numPairs == 5) { // Easy
            numRows = 2;
            numCols = 5;
        } else if (numPairs == 10) { // Medium
            numRows = 4;
            numCols = 5;
        } else if (numPairs == 15) { // Hard
            numRows = 5;
            numCols = 6;
        } else {
            // Default to a 4x4 grid if the difficulty level is unknown
            numRows = 4;
            numCols = 4;
        }

        for (int i = 0; i < numberOfCards; i++) {
            Pokemon pokemon = new Pokemon("pokemon_images/" + selectedImages.get(i) + ".png");
            PokemonCard card = new PokemonCard(centerX, centerY, false, pokemon);
            cards.add(card);

            card.addToCanvas(canvas);
            canvas.onClick(event -> card.flipCard(event.getPosition(), this));

            centerX += card.CARD_WIDTH + DISTANCE;

            if (i % numCols == numCols - 1) {
                centerX = canvas.getWidth() * 0.15;
                centerY += card.CARD_HEIGHT + DISTANCE * 0.5;
            }
        }

        if (cards.isEmpty()) {
            displayWinningImage();
        }
    }


    /**
     * Display an image when the user wins
     */
    private void displayWinningImage() {
        canvas.removeAll();
        canvas.add(winningImage);
    }

    /**
     * Display an image when the user loses
     */
    private void updateGameOverDisplay() {
        canvas.removeAll();
        canvas.add(gameOverImage);
    }


    /**
     * Read in all pokemon images file and add it to a list of strings Source:
     * https://intellipaat.com/community/38545/how-do-i-iterate-through-the-files-in-a-directory-in-java#:~:text=1%20Answer&text=You%20can%20use%20File%23isDirectory,This%20is%20called%20recursion.
     * 
     * @param File[] files: List of image files (the pokemon images folder)
     */
    private List<String> pokemonImageStrings(File[] files) {
        List<String> pokemonImages = new ArrayList<>();
        for (File imageFile : files) {
            String imageName = imageFile.getName(); // Get the file name
            String pokemonName = imageName.substring(0, imageName.lastIndexOf('.')); // Remove file extension
            pokemonImages.add(pokemonName);
        }
        return pokemonImages;
    }

    public void removeCard(PokemonCard card) {
        canvas.remove(card.getGraphicsGroup());
        cards.remove(card);
    }

    public PokemonCard getFirstFlipped() {
        return firstFlipped;
    }

    public void setFirstFlipped(PokemonCard card) {
        firstFlipped = card;
    }

    public void pauseCanvas() {
        canvas.pause(500);
    }

    public void updateCanvas() {
        canvas.draw();
    }

    public CanvasWindow getCanvas() {
        return canvas;
    }

    private int getCardNum() {
        return currentnumPairs;
    }

    public void setCardNum(int numPairs) {
        currentnumPairs = numPairs;
    }

    /**
     * Draw the time bar
     */
    private void drawTimerBar() {
        timerBarOutline = new Rectangle(70, 170, 40, 550);
        timerBarOutline.setStrokeWidth(2.5);
        timerBarOutline.setStrokeColor(Color.BLACK);
        canvas.add(timerBarOutline);

        timerBar = new Rectangle(72.5, 172.5, 35, 545);
        timerBar.setFillColor(Color.PINK);
        timerBar.setStrokeColor(Color.PINK);
        canvas.add(timerBar);
    }

    /**
     * Add pokemon icon to guide how much time is left
     */
    private void addTimerPokemon() {
        timerPokemon = new Image("pokemon_images/evie.png");
        timerPokemon.setMaxHeight(120);
        timerPokemon.setMaxWidth(120);
        timerPokemon.setPosition(42, 60);
        canvas.add(timerPokemon);
    }

    /**
     * Start timer for the game and add all timer graphics Source: https://chat.openai.com (Prompt: "how
     * to add a timer that counts down 5 minutes on the screen the moment the game runs" )
     */
    public void startTimer() {
        cancelTimer();
        canvas.add(timerGroup);
        drawTimerBar();
        addTimerPokemon();

        timeThread = new Thread(() -> {
            while (remainingTimeInSeconds > 0 && !Thread.currentThread().isInterrupted()) {
                if (!isPaused) {
                    updateTimerDisplay();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                remainingTimeInSeconds--;
            }
            if (remainingTimeInSeconds <= 0) {
                canvas.removeAll();
                canvas.add(gameOverImage);
            } else {
                attemptsText.setText("Attempts: " + attemptsCount);
                updateCanvas();
            }
        });
        timeThread.start();
    }

    /**
     * Cancel the current timer thread
     */
    private void cancelTimer() {
        if (timeThread != null && timeThread.isAlive()) {
            timeThread.interrupt(); // Interrupt the current timer thread
            timeThread = null; // Set the thread to null
        }
    }

    public void setRemainingTime() {
        remainingTimeInSeconds = 5 * 60;
    }


    /**
     * Start timer by adding in all graphic components and utilizing the Thread class
     */
    private void updateTimerDisplay() {
        int minutes = remainingTimeInSeconds / 60;
        int seconds = remainingTimeInSeconds % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);

        // Calculate the ratio of remaining time to the initial time
        double ratio = (double) remainingTimeInSeconds / (5 * 60); // Assuming initial time is 5 minutes

        // Calculate the new height of the timerBar
        double newHeight = ratio * 545; // 545 is the initial height of the timerBar
        double newY = 717.5 - newHeight; // 717.5 is the initial bottom y-position of the timerBar
        double newYPokemon = 60 + 545 - newHeight;

        // Update the timerBar height
        timerBar.setSize(35, newHeight);
        timerBar.setPosition(72.5, newY);

        // Update the position of timerPokemon
        timerPokemon.setPosition(42, newYPokemon);

        // Clear previous timer display
        timerGroup.removeAll();

        // Add the updated timer display to the canvas
        GraphicsText timerText = new GraphicsText(timeString);
        timerText.setFontSize(40);
        timerGroup.add(timerText);
        timerGroup.setCenter(70, 50);
    }

    /**
     * Update the number of attempts
     */
    public void AttemptsCount() {
        attemptsCount--;

        if (attemptsCount <= 0) {
            canvas.removeAll();
            updateGameOverDisplay();
        } else {
            attemptsText.setText("Attempts: " + attemptsCount);
            updateCanvas();
        }
    }

    /**
     * Ring the pokeball when there is a valid match Source: https://chat.openai.com (Prompt: "How to
     * move an image from side to side for a period of time?")
     */
    public void ringPokeBall() {
        double initialY = pokeball.getCenter().getY();
        double distance = 5;
        double duration = 1000;
        long startTime = System.currentTimeMillis();

        Thread pokeballAnimation = new Thread(() -> {
            while (System.currentTimeMillis() - startTime < duration) {
                double timeElapsed = System.currentTimeMillis() - startTime;
                double newY = initialY + distance * Math.sin((timeElapsed / duration) * Math.PI * 2);

                // Update the Pokeball position
                pokeball.setCenter(pokeball.getCenter().getX(), newY);

                canvas.draw();
            }

            // Reset the Pokeball position to the initial position after animation completes
            pokeball.setCenter(pokeball.getCenter().getX(), initialY);
            canvas.draw();
        });

        pokeballAnimation.start();
    }

    /**
     * Carry out the tasks when user clicks on the redo button: 
     * 1) Pause timer 
     * 2) Pop up a screen to ask user confirmation
     */

    private void redoGame(Point userPoint) {
        GraphicsObject obj = canvas.getElementAt(userPoint);
        if (obj == redoButton) {
            pauseTimer();
            promptUserForRedoConfirmation();

        }
    }


    /**
     * Carry out the tasks when user clicks on the pause button: 
     * 1) Pause timer 
     * 2) Pop up a screen to ask user confirmation
     */
    private void pauseGame(Point userPoint) {
        GraphicsObject obj = canvas.getElementAt(userPoint);
        if (obj == pauseButton) {
            pauseTimer();
            promptUserForResumeConfirmation();

        }
    }

    /**
     * Carry out the tasks when user clicks on the home button: 
     * 1) Pause timer 
     * 2) Pop up a screen to ask user confirmation
     */
    private void returnHome(Point userPoint) {
        GraphicsObject obj = canvas.getElementAt(userPoint);
        if (obj == homeButton) {
            pauseTimer();
            promptUserForReturnHomeConfirmation();

        }
    }

    /**
     * Generate a pop up screen with 2 buttons (Yes/No) to confirm whether user wants to redo the game
     * If 'Yes' button is clicked, the game restarts to its current level If 'No' button is clicked, the
     * game resumes to its current status
     */
    private void promptUserForRedoConfirmation() {
        CanvasWindow cfScreen = new CanvasWindow("ARE YOU SURE YOU WANT TO START OVER?", canvas.getWidth() / 3,
            canvas.getHeight() / 12);

        Button yes = new Button("START OVER");
        cfScreen.add(yes);
        yes.setCenter(cfScreen.getWidth() * 0.3, cfScreen.getHeight() * 2 / 3);

        Button no = new Button("RESUME GAME");
        cfScreen.add(no);
        no.setCenter(cfScreen.getWidth() * 0.7, cfScreen.getHeight() * 2 / 3);

        yes.onClick(() -> {
            cfScreen.closeWindow();
            restartCurrentGame();
        });

        no.onClick(() -> {
            cfScreen.closeWindow();
            resumeTimer();
        });
    }

    /**
     * Generate a pop up screen with 1 button to confirm whether user wants to resume the game
     */
    private void promptUserForResumeConfirmation() {
        CanvasWindow cfScreen = new CanvasWindow("GAME IS PAUSED", canvas.getWidth() / 3, canvas.getHeight() / 12);

        Button resume = new Button("RESUME GAME");
        cfScreen.add(resume);
        resume.setCenter(cfScreen.getWidth() * 0.5, cfScreen.getHeight() * 0.5);

        resume.onClick(() -> {
            cfScreen.closeWindow();
            resumeTimer();
        });

    }

     /**
     * Generate a pop up screen with 1 button to confirm whether user wants to return to Home Screen
     */
    private void promptUserForReturnHomeConfirmation() {
        CanvasWindow cfScreen = new CanvasWindow("DO YOU WANT TO GO TO HOME SCREEN?", canvas.getWidth() / 3, canvas.getHeight() / 12);
        GraphicsText message = new GraphicsText("*Your current game will be deleted!*");
        cfScreen.add(message);
        message.setCenter(cfScreen.getWidth() * 0.5, cfScreen.getHeight() * 0.2);
        
        Button resume = new Button("RESUME GAME");
        cfScreen.add(resume);
        resume.setCenter(cfScreen.getWidth() * 0.3, cfScreen.getHeight() * 0.6);

        Button home = new Button("HOME SCREEN");
        cfScreen.add(home);
        home.setCenter(cfScreen.getWidth() * 0.7, cfScreen.getHeight() * 0.6);

        resume.onClick(() -> {
            cfScreen.closeWindow();
            resumeTimer();
        });

        home.onClick(() -> {
            cfScreen.closeWindow();
            canvas.removeAll();
            canvas.closeWindow();
            cancelTimer();
            setRemainingTime();
            isPaused = false;
            StartMenu.displayStartMenu(this);
        });

    }


    /**
     * Restart the game in its current level
     */
    private void restartCurrentGame() {
        canvas.removeAll();
        canvas.closeWindow();
        cancelTimer();
        setRemainingTime();
        createGameCanvas();
        cardGenerator(getCardNum());
        isPaused = false;
        startTimer();
    }


    /**
     * Pause the timer & start a "clock" on the pause time
     */
    private void pauseTimer() {
        isPaused = true;
        startPauseTime = System.currentTimeMillis();
    }


    /**
     * Resume the timer to its previous state by updating remainingTimeInSeconds with how much time has
     * passed since the game was paused
     */
    private void resumeTimer() {
        isPaused = false;
        long pauseDuration = System.currentTimeMillis() - startPauseTime;
        remainingTimeInSeconds += (int) (pauseDuration / 1000);

    }


}

