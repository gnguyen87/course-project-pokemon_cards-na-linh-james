import java.awt.Color;
import java.awt.TextArea;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.TextField;

public class GameManager {
    private CanvasWindow canvas;
    private List<PokemonCard> cards = new ArrayList<>();
    private PokemonCard firstFlipped;

    private final double DISTANCE = 20;
    private int remainingTimeInSeconds = 5 * 60; // 5 minutes in seconds
    private GraphicsGroup timerGroup;

    public GameManager() {
        canvas = new CanvasWindow("Pokemon Card Puzzle", 1200, 1000);
        // addSecretPokemon();
        startTimer();
        // addTimerPokemon();
    }

    private void addSecretPokemon() {
        Image backgroundImage = new Image("rare_pokemon/poke_lover.jpg");
        backgroundImage.setMaxWidth(0.68*canvas.getWidth());
        backgroundImage.setMaxHeight(0.68*canvas.getHeight());
        canvas.add(backgroundImage);
        backgroundImage.setCenter(canvas.getWidth()/2, canvas.getHeight()/2);
    }

    // trying to make a cute pokemon near the timer
    // private void addTimerPokemon() {
    //     GraphicsGroup timeDecoration = new GraphicsGroup();
    //     Image timerPokemon = new Image("pokemon_images/evie.png");
    //     timerPokemon.setMaxHeight(120);
    //     timerPokemon.setMaxWidth(120);
    //     timerPokemon.setPosition(50, 100);

    //     GraphicsText timeSign = new GraphicsText("Timer");
    //     timeSign.setFontSize(40);
    //     timerPokemon.setPosition(50, 150);
    //     timeDecoration.add(timeSign);

    //     timeDecoration.add(timerPokemon);
    //     canvas.add(timeDecoration);
    // }


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

    private void startTimer() {
        timerGroup = new GraphicsGroup();

        canvas.add(timerGroup);
        
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
            System.out.println("Game Over!");
        });

        timerThread.start();
    }

    private void updateTimerDisplay() {
        int minutes = remainingTimeInSeconds / 60;
        int seconds = remainingTimeInSeconds % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);

        // Clear previous timer display
        timerGroup.removeAll();

        // Add the updated timer display to the canvas
        GraphicsText timerText = new GraphicsText(timeString);
        // timerText.setFillColor(Color.PINK);
        timerText.setFontSize(40);
        timerGroup.add(timerText);
        timerGroup.setCenter(70, 50);
    }
}
