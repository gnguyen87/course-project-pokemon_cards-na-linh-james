import java.util.ArrayList;

import edu.macalester.graphics.GraphicsGroup;

public class PokemonCardManager {
    private ArrayList Cards;
    private GraphicsGroup cardGroup;
    private ArrayList cardStatus;




/*
 * +PokemonCardManager: 
 * a constructor that  initializes a pokemoncardmanager, 
 * each constructor has a PokemonCardList and PokemonCards
 */
    public PokemonCardManager() {
    }


/*
 * +CardGenerator(): 
 * generate a list of cards in a 5 by 6 grid (method to add the cards twice”)            
 * for (int i = 0; i < numberOfImage - numberOfImage % 2; i++) {  v.add(i % (numberOfImage / 2)) };
 */
    public void CardGenerator(){
        
    }





/* 
 * +(boolean) isValidMatch(pokemoncard card1, pokemoncard card2): 
 * checks if two cards are flipped and a match, 
 * returns true if yes and remove both cards from cardGroup, reset card if false 
 * */
    public boolean isValidMatch(PokemonCard card1, PokemonCard card2){

        if(card1.isFlipped() && card2.isFlipped()){

            if(card1.isFlipped() == card2.isFlipped()){
                return true;
                canvas.remove(card1);
                canvas.remove(card2);
            }

            else{
                 card1.resetCard();
                 card2.resetCard();
            }
        } 

        else{
            card1.resetCard();
            card2.resetCard();
            }

    }



/*
 * +resetCard(): 
 * fill the card with color to flip the card backwards
 */









}







