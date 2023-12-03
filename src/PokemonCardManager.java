import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokemonCardManager {

    private List<PokemonCard> cards;
    private GraphicsGroup cardGroup;

    public PokemonCardManager() {
        this.cards = new ArrayList<>();
        this.cardGroup = new GraphicsGroup();
    }

    public void cardGenerator(CanvasWindow canvas, int rows, int columns) {
        List<String> pokemonImages = generatePokemonImages();

        // for (int i = 0; i < rows * columns; i++) {
        //     PokemonCard card = new PokemonCard(i, i, false);
        //     card.setPokemonImage(pokemonImages.get(i % (pokemonImages.size())));
        //     card.addToCanvas(cardGroup);
        //     cards.add(card);
        // }

        Collections.shuffle(cards);
        canvas.add(cardGroup);
    }

    private List<String> generatePokemonImages() {
        List<String> pokemonImages = new ArrayList<>();
        String[] pokemonNames = {
            "bulbasaur", "charmander", "chikorita", "cyndaquil",
            "girafarig", "jigglypuff", "pikachu", "seaking",
            "sudowoodo", "togepi", "wooper"
        };
    
        for (String name : pokemonNames) {
            String imagePath = "res/" + name + ".png";
            pokemonImages.add(imagePath);
        }
    
        return pokemonImages;
    }

}
