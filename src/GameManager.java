import java.awt.Color;
import java.awt.TextArea;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.TextField;

public class GameManager {
    private CanvasWindow canvas;
    private List<PokemonCard> cards = new ArrayList<>();
    private PokemonCard firstFlipped;

    private final double DISTANCE = 20;
    private int remainingTimeInSeconds = 5 * 60; // 5 minutes in seconds
    private GraphicsGroup timerGroup;

    private int attemptsCount = 10;
    // private final int MAX_ATTEMPTS = 50; // Update the maximum attempts
    // private int current_attempts = 50; // Update the maximum attempts

    private GraphicsText attemptsText;
    private GraphicsText gameOverText;
    private Image gameOverImage;
    private Rectangle timerBarOutline;
    private Rectangle timerBar;
    private Image timerPokemon;
    private Image pokeball;

    public GameManager() {
        canvas = new CanvasWindow("Pokemon Card Puzzle", 1200, 1000);
   
        startTimer();

        attemptsText = new GraphicsText("Attempts: " + attemptsCount);
        attemptsText.setFontSize(20);
        canvas.add(attemptsText);

        attemptsText.setCenter(canvas.getWidth() - 100, 50);
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
        pokeball.setCenter(canvas.getWidth() - 100, 150);
        canvas.add(pokeball);
    }



    public void cardGenerator() {
        File files = new File("res/pokemon_images");
    
        List<String> pokemonImages = pokemonImageStrings(files.listFiles());
        List<String> pokemonImages2 = pokemonImageStrings(files.listFiles());
        pokemonImages.addAll(pokemonImages2);

        Collections.shuffle(pokemonImages);

        System.out.println(pokemonImages);
        double centerX = canvas.getWidth() * 0.15;
        double centerY = canvas.getHeight() * 0.03;

        for (int i = 0; i < 30; i++) {
            Pokemon pokemon = new Pokemon("pokemon_images/" + pokemonImages.get(i) + ".png");
            PokemonCard card = new PokemonCard(centerX, centerY, false, pokemon);
            cards.add(card);

            card.addToCanvas(canvas);
            canvas.onClick(event -> card.flipCard(event.getPosition(), this));

            centerX += card.CARD_WIDTH + DISTANCE;

            if (i % 6 == 5) {
                centerX = canvas.getWidth() * 0.15;
                centerY += card.CARD_HEIGHT + DISTANCE * 0.5;
            }
        }
    }


    private List<String> pokemonImageStrings(File[] files) {
        List<String> pokemonImages = new ArrayList<>();
        for (File imageFile: files) {
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
    
    private void drawTimerBar(){
        timerBarOutline = new Rectangle(70, 170, 40, 550);
        timerBarOutline.setStrokeWidth(2.5);
        timerBarOutline.setStrokeColor(Color.BLACK);
        canvas.add(timerBarOutline);

        timerBar = new Rectangle(72.5, 172.5, 35, 545);
        timerBar.setFillColor(Color.PINK);
        timerBar.setStrokeColor(Color.PINK);
        canvas.add(timerBar);
    }

    // // trying to make a cute pokemon near the timer
    private void addTimerPokemon() {
        timerPokemon = new Image("pokemon_images/evie.png");
        timerPokemon.setMaxHeight(120);
        timerPokemon.setMaxWidth(120);
        timerPokemon.setPosition(42, 60);
        canvas.add(timerPokemon);
    }


    private void startTimer() {
        timerGroup = new GraphicsGroup();
        canvas.add(timerGroup);
        drawTimerBar();
        addTimerPokemon();
        
        Thread timerThread = new Thread(() -> {
            while (remainingTimeInSeconds > 0) {
                updateTimerDisplay();
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                remainingTimeInSeconds--;        
            } 
            // Game over logic can be added here
            if (remainingTimeInSeconds <= 0) {
                    canvas.removeAll();
                    canvas.add(gameOverImage);
            } 
            else {
                    attemptsText.setText("Attempts: " + attemptsCount);
                    updateCanvas();
            }
        }
        );
            

        timerThread.start();
    }

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

    private void updateGameOverDisplay() {
        canvas.removeAll();
        canvas.add(gameOverImage);
    }

    public void ringPokeBall() {
        double initialY = pokeball.getCenter().getY();
        double distance = 5; // Adjust the distance the Pokeball will move
        double duration = 1000; // Duration of the animation in milliseconds
        long startTime = System.currentTimeMillis();
    
        Thread pokeballAnimation = new Thread(() -> {
            while (System.currentTimeMillis() - startTime < duration) {
                double timeElapsed = System.currentTimeMillis() - startTime;
                double newY = initialY + distance * Math.sin((timeElapsed / duration) * Math.PI * 2);
                
                // Update the Pokeball position
                pokeball.setCenter(pokeball.getCenter().getX(), newY);
    
                // Redraw the canvas
                updateCanvas();
            }

            // Reset the Pokeball position to the initial position after animation completes
            pokeball.setCenter(pokeball.getCenter().getX(), initialY);
            updateCanvas();
        });
    
        pokeballAnimation.start();
    }
    
}

