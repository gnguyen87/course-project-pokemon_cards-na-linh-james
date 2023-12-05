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

    public void cardGenerator(CanvasWindow canvas) {
        File files = new File("res/pokemon_images");
    
        List<String> pokemonImages = pokemonImageStrings(files.listFiles());

        List<String> pokemonImages2 = pokemonImageStrings(files.listFiles());
        pokemonImages.addAll(pokemonImages2);

        Collections.shuffle(pokemonImages);

        System.out.println(pokemonImages);
        double centerX = canvas.getWidth() * 0.1;
        double centerY = canvas.getWidth() * 0.1;


        for (int i =0; i < 30; i++) {
            Pokemon pokemon = new Pokemon("pokemon_images/" + pokemonImages.get(i) + ".png");
            PokemonCard card = new PokemonCard(centerX, centerY, false, pokemon);

            cards.add(card);
            cardGroup.add(card);
            centerX += card.CARD_WIDTH + DISTANCE;

            if (i % 6 == 5) {
                centerX = canvas.getWidth() * 0.1;
                centerY += card.CARD_HEIGHT + DISTANCE * 0.5;
            }
        }

        canvas.add(cardGroup);
        cardGroup.setCenter(canvas.getWidth() * 0.5, canvas.getHeight() * 0.5);

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
        boolean card1_status = card1.isFlipped();
        boolean card2_status = card2.isFlipped();
        if (card1_status == true && card2_status == true) {
            if (card1.getPokemon().equals(card2.getPokemon())) {
                cards.remove(card1);
                cards.remove(card2);
                cardGroup.remove(card1);
                cardGroup.remove(card2);
                return true;
            } else {
                card1.remove(card1.getPokemon());
                card2.remove(card2.getPokemon());
                return false;
            }
        }
        return false;
    }
    
    



    public List<PokemonCard> getCards() {
        return cards;
    }

   



    

    



}
