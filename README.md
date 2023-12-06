# Pokemon Card Game

We are creating a card-based memory game that is based on pokemon. 

The game begins with 30 cards flipped upside down. Upon clicking one of the cards, it flips face-side-up to reveal the pokemon it pertains to. The goal of the each round is to find the matching card, with 15 total pairs, for the card flipped. Upon the second card being clicked, if it is not a correct match, the cards will flip back over to hide the pokemon. However, if they match, the two cards will be removed from the screen. The goal of the game is to remove all 15 pairs, revealing a hidden image underneath. 

Our first step was to create a UML diagram, found here: [https://docs.google.com/presentation/d/1vqWdAX5gFALG_R6A3m-a0ksv9IdK18p1FbsHWslaJzo/edit#slide=id.g2a09f9b6f1b_0_34]. Later, we decided to deviate from this class-layout as we realized it was causing a bit of bugs. Still, we found value in that it provided us with a framework for how to begin to tackle this project. 

Our first coding task was to create a singular card flipped face up. We decided to break this down into two pieces: Each card is to consist of a simple rectangle that appears, serving as both the background of the front-facing side as well as the card cover. Each card also consists of an image, being the actual pokemon. 

As such, we had to create a method that allows for the user to simply click on the card, removing the pokemon. Now, the showing of the actual pokemon image could be dependent on if it had or hadn't been clicked by the user. However, the actual pink background should stay regardless. By this point, in order for a card to appear, the pokemon image file had to be called directly- the program could not find the images on its own. 

Our next step was to fix this, allowing for the 30 cards to be made in a way that allows for random positioning and so that the images in the res folder could be directly accessed and looped throughh by the program. To do so, we created a method that creates a list with each of the 15 pokemon image files as elements of a list, and assigned each of these a position within the list. Then, we created a loop that generates 30 cards in a 6x5 grid layout with each card used twice, and randomized their positioning. We tied this into the already established clicking, and had to modify the class layout to do so. 

Next step: isValidMatch
     step: 
     step: