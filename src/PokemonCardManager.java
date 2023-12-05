import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;


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
    private int cardsClicked = 0;


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
                Pokemon pokemon = new Pokemon(pokemonImages.get(imageIndex));
                PokemonCard card = new PokemonCard(centerX, centerY, false, pokemon);

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
  

    private List<String> pokemonImageStrings(File[] files) {
        List<String> pokemonImages = new ArrayList<>();
        for (File imageFile: files) {
            String imageName = imageFile.getName(); // Get the file name
            String pokemonName = imageName.substring(0, imageName.lastIndexOf('.')); // Remove file extension
            pokemonImages.add(pokemonName);
        }
        return pokemonImages;
    }

    public boolean isValidMatch(PokemonCard card1, PokemonCard card2) {
        boolean card1_status = card1.getCardStatus();
        boolean card2_status = card2.getCardStatus();
        if (card1_status == true && card2_status == true) {
            if (card1.getPokemon().equals(card2.getPokemon())) {
                cards.remove(card1);
                cards.remove(card2);
                cardGroup.remove(card1.getGroup());
                cardGroup.remove(card2.getGroup());
                return true;
            } else {
                card1.getGroup().remove(card1.getPokemon());
                card2.getGroup().remove(card2.getPokemon());
                return false;
            }
        }
        return false;
    }
    
     // check status of card if it's flipped or not
     private boolean isFlipped(Point clickPoint){
        GraphicsObject object = cardGroup.getElementAt(clickPoint);
        if (object != null && object instanceof PokemonCard) {
            object = (PokemonCard) object;
            if (object instanceof Pokemon) {
                
                return true;
            }
        }
        return false;
    }
    
        
    public void flipCard(Point userPoint) {
        GraphicsObject object = cardGroup.getCanvas().getElementAt(userPoint);
        if (object instanceof PokemonCard) {
            if (object instanceof Pokemon) {
                
            }
        }
        if (userClicked == true) {
            if (isFlipped(userPoint) == true) {
                group.remove(pokemon);
            } else {
                group.add(pokemon);
            }
        }
    }

    public List<PokemonCard> getCards() {
        return cards;
    }

   



    

    



}
