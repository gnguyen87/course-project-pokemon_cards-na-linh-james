import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokemonCardManager {

    private List<PokemonCard> cards;
    public GraphicsGroup cardGroup;
    private final double DISTANCE = 20;

    public PokemonCardManager() {
        this.cards = new ArrayList<>();
        this.cardGroup = new GraphicsGroup();
    }




    public void cardGenerator(CanvasWindow canvas, int rows, int columns) {
        File files = new File("res/pokemon_images");
    
        List<String> pokemonImages = pokemonImageStrings(files.listFiles());

        double centerX = 0;
        double centerY = 0;

        for (String poke_name : pokemonImages){
            PokemonCard card = new PokemonCard(centerX, centerY, false);
            card.makePokemonCard(poke_name);
            cards.add(card);
            cardGroup.add(card.getGroup());
            centerX = centerX + card.CARD_WIDTH + DISTANCE;

            for (int i = 0; i < pokemonImages.size(); i++) {
                if ((i + 1) % 5 == 0) {
                    centerY = centerY + card.CARD_HEIGHT + DISTANCE;
                }
            }
        }


        Collections.shuffle(cards);
        canvas.add(cardGroup);
    }


    private List<String> pokemonImageStrings(File[] files) {
        List<String> pokemonImages = new ArrayList<>();
        for (File imageFile: files) {
            String imageFileName = imageFile.getAbsolutePath();
            pokemonImages.add(imageFileName);
        }
        return pokemonImages;
    }

}
