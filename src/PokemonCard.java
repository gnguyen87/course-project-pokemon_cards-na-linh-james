import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class PokemonCard extends GraphicsGroup {
    public final double CARD_WIDTH = 120;
    public final double CARD_HEIGHT = 180;

    private GraphicsGroup group;
    private double centerX;
    private double centerY;
    private Rectangle cardShape;
    private Pokemon pokemon;

    private boolean flipped = false;
    
    public PokemonCard (double centerX, double centerY, boolean flipped, Pokemon pokemon) {
        super(centerX, centerY);


        this.cardShape = new Rectangle(this.centerX, this.centerY, CARD_WIDTH, CARD_HEIGHT);
        add(cardShape);
        
        pokemon.setCenter(centerX + CARD_WIDTH/2, centerY + CARD_HEIGHT/2);
        pokemon.setMaxWidth(CARD_WIDTH*0.85);
        pokemon.setMaxHeight(CARD_HEIGHT*0.85);
        updatePokemonPosition();

        add(pokemon);
        
    }
    
    public void flipCard(Point userPoint) {
        boolean userClicked = group.testHitInLocalCoordinates(userPoint.getX(), userPoint.getY());
        if (userClicked == true) {
           if (flipped) {
                remove(pokemon);
                flipped = false;
           } else {
                add(pokemon);
                flipped = true;
           }
        }
    }
 
    private void updatePokemonPosition() {
        pokemon.setCenter(centerX + CARD_WIDTH/2, centerY + CARD_HEIGHT/2);
    }

    public void setCardStatus(boolean newStatus) {
        this.flipped = newStatus;
    }

    public boolean isFlipped() {
        return this.flipped;
    }

    public GraphicsGroup getGroup(){
        return group;
    }

    public Image getPokemon() {
        return pokemon;
    }

    




    
 
}
