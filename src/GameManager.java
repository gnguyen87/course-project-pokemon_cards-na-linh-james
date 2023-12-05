import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;

public class GameManager {
    private CanvasWindow canvas;
    private final double DISTANCE = 20;

    public GameManager() {
        canvas = new CanvasWindow("Pokemon Card Puzzle", 1200, 1000);
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


        for (int i =0; i < 30; i++) {
            Pokemon pokemon = new Pokemon("pokemon_images/" + pokemonImages.get(i) + ".png");
            PokemonCard card = new PokemonCard(centerX, centerY, false, pokemon);

            card.addToCanvas(canvas);
            canvas.onClick(event -> card.flipCard(event.getPosition()));

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

  
}
