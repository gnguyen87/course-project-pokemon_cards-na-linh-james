import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

public class PokemonCard {
    private final double CARD_WIDTH = 120;
    private final double CARD_HEIGHT = 180;
    // private final double POKEMON_SIZE;

    private GraphicsGroup group;
    private double centerX;
    private double centerY;
    private Rectangle cardShape;
    private Image pokemon;
    
    public PokemonCard(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;

        group = new GraphicsGroup();
        
        this.cardShape = new Rectangle(this.centerX, this.centerY, CARD_WIDTH, CARD_HEIGHT);
        group.add(cardShape);

        pokemon = new Image(this.centerX + CARD_WIDTH/2, this.centerY + CARD_HEIGHT/2);
        pokemon.setMaxWidth(CARD_WIDTH*0.85);
        pokemon.setMaxHeight(CARD_HEIGHT*0.85);
        group.add(pokemon);
    }

    public void makePokemonCard(){
        pokemon.setImagePath("pokemon_images/starly.png");
        updatePokemonPosition();
    }

    private void updatePokemonPosition() {
        pokemon.setCenter(centerX + CARD_WIDTH/2, centerY + CARD_HEIGHT/2);
    }

    public void addToCanvas(CanvasWindow canvas) {
        cardShape.setFillColor(Color.PINK);
        canvas.add(group);
    }

    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(group);
    }
    
}
