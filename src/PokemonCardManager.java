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

        Collections.shuffle(pokemonImages);


        double centerX = 0;
        double centerY = 0;

        int imageIndex = 0;

        for (int y = 0; y < rows; y++){
            for (int x = 0; x < columns; x++) {
                PokemonCard card = new PokemonCard(centerX, centerY, false);

                card.makePokemonCard(pokemonImages.get(imageIndex));

                cards.add(card);
                cardGroup.add(card.getGroup());
    
                centerX += card.CARD_WIDTH + DISTANCE;
    
                imageIndex++;
    
                if (imageIndex >= pokemonImages.size()) {
                    break;
                }
            }

        centerX = 0;
        centerY += cards.get(0).CARD_HEIGHT + DISTANCE;
    }

    Collections.shuffle(cards);
    canvas.add(cardGroup);


    }
    public List<PokemonCard> getCards() {
        return cards;
    }
    


    //     for (String poke_name : pokemonImages){
    //         PokemonCard card = new PokemonCard(centerX, centerY, false);
    //         card.makePokemonCard(poke_name);
    //         cards.add(card);
    //         cardGroup.add(card.getGroup());
    //         centerX += centerX + card.CARD_WIDTH + DISTANCE;

    //         for (int i = 0; i < pokemonImages.size(); i++) {
    //             if ((i + 1) % 5 == 0) {
    //                 centerY += centerY + card.CARD_HEIGHT + DISTANCE;
    //             }
    //         }
    //     }


    //     Collections.shuffle(cards);
    //     canvas.add(cardGroup);
    // }


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
