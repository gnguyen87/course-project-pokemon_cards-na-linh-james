import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

public class PokemonCardsGame {
    private CanvasWindow canvas;
    private PokemonCardManager cardManager;

    public static void main(String[] args) {
        PokemonCardsGame game = new PokemonCardsGame();
        game.addPokemonCard();
    }

    public PokemonCardsGame() {
        canvas = new CanvasWindow("Pokemon Card Puzzle", 1200, 900);
        cardManager = new PokemonCardManager();
    }

    public void initializeGame() {
        cardManager.cardGenerator(canvas, 5, 6);
        canvas.onClick(event -> handleCardClick(event.getPosition()));
    }

    private void handleCardClick(Point clickPoint) {
    }

    public void addPokemonCard() {
        File files = new File("res/pokemon_images");
        List<String> allPokemonImages = pokemonImageStrings(files.listFiles());

        List<String> pokemonImages = new ArrayList<>(allPokemonImages);
        pokemonImages.addAll(allPokemonImages);
        Collections.shuffle(pokemonImages);

        double initialCardX = 100;
        double initialCardY = 65;
        double cardWidth = 60;
        double cardHeight = 68;
        double xOffset = cardWidth + 60;
        double yOffset = cardHeight + 60;
        int totalCards = 30;
        int rows = 5;
        int imageIndex = 0;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < 6; x++) {
                int cardIndex = y * 6 + x;

                if (cardIndex >= totalCards) {
                    break;
                }

                double newCardX = initialCardX + x * xOffset;
                double newCardY = initialCardY + y * yOffset;

                PokemonCard pokemonCard = new PokemonCard(newCardX, newCardY, false);
                pokemonCard.makePokemonCard(pokemonImages.get(imageIndex));

                pokemonCard.addToCanvas(canvas);
                canvas.onClick(event -> pokemonCard.cardClicked(event.getPosition()));

                imageIndex++;
            }
        }
    }

    private List<String> pokemonImageStrings(File[] files) {
        List<String> pokemonImages = new ArrayList<>();
        for (File imageFile : files) {
            String imageName = imageFile.getName(); 
            String pokemonName = imageName.substring(0, imageName.lastIndexOf('.')); // Remove file extension
            pokemonImages.add(pokemonName);
        }
        return pokemonImages;
    }
}
