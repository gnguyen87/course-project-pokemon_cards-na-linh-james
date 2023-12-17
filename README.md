# Pokemon Card Game


Pokemon Card Game is a multi-level memory card game developed in Java with [kilt-graphics API](<https://mac-comp127.github.io/kilt-graphics/>) to enhance concentration and critical thinking skills in 5-8 youth. It was developed by: 
James Hernandez, Linh Nguyen, Na Nguyen

<img width="888" alt="Screenshot 2023-12-10 at 5 05 32 PM" src="https://github.com/Mac-COMP-127-Fall-2023/course-project-pokemon_cards-na-linh-james/assets/134335069/639800cc-11f8-4d18-837a-f673b2d543c0">
<img width="888" alt="Screenshot 2023-12-10 at 4 59 06 PM" src="https://github.com/Mac-COMP-127-Fall-2023/course-project-pokemon_cards-na-linh-james/assets/134335069/ee0072e4-d26a-4eee-a908-fbb74e3874e0">
<img width="888" alt="Screenshot 2023-12-10 at 5 05 42 PM" src="https://github.com/Mac-COMP-127-Fall-2023/course-project-pokemon_cards-na-linh-james/assets/134335069/2a9f9a88-50e5-4482-826b-0f9e973d1a6d">

The game begins with 30 cards flipped upside down. Upon clicking one of the cards, it flips face-side-up to reveal the pokemon it pertains to. The goal of the each round is to find the matching card, with 15 total pairs, for the card flipped. Upon the second card being clicked, if it is not a correct match, the cards will flip back over to hide the pokemon. However, if they match, the two cards will be removed from the screen. The goal of the game is to remove all pairs before the given time and under the limited number of attempts.


## UML Diagram
Below is a UML Diagram of our classes, their variables, methods, and their relationships.

<img width="700" alt="Screenshot 2023-12-10 at 5 15 27 PM" src="https://github.com/Mac-COMP-127-Fall-2023/course-project-pokemon_cards-na-linh-james/assets/134335069/4f6b2074-5336-4c13-91cf-4d637232feab">




## Game UI features
Use [kilt-graphics API](<https://mac-comp127.github.io/kilt-graphics/>) to generate a 5x6 grid, holding 15 pairs of pokemon graphics cards. The position of all 30 graphics images are randomly generated.



## Technical Guide: 
 The technical requirements of this game are minimal, only having Java 17 installed. To play the program, users should refer to the PokemonCardGame class and run the main method.

<!-- Technical Requirements: Java 17 is required due to the use of Kilt Graphics.
Running the Program: Execute the PokemonCardGame class, which serves as the main class. -->

## Acknowledgements: 
We got the inspiration from the Vietnamese card game __. This source-game is similar in that cards must match, but has different logics and rules. It is necessary to mention the very welcomed help from __ that helped us to __. 

Our pokemon card images come directly from the pokemon official pokedex website, which has images and information on all 406 pokemon. (<https://www.pokemon.com/us/pokedex>). 

Our images for winning and losing both come from Tynker, an online porgram that helps teach children to code (<https://www.tynker.com/community/projects/play/pokemon-battle/5d4e7a20cebfbd358676cd13/>). The loss image comes from a user named Stylish Lupin, and our win image comes from ___. 

With the use of a Youtube Channel "Coding with John," we were able to find information on Threads, which was very helpful for creating our timer. We did not use everything, but it gave us a bit of familiarity with the concept. (<https://www.youtube.com/watch?v=r_MbozD32eo>)

We used oracle (<https://docs.oracle.com/javase/8/docs/api/java/awt/GraphicsEnvironment.html>) to learn more about the built in java graphics, which was used for the start menu. 

We added music from the 1997 game. We donloaded this music from a CD website, khinsider. We compiled four of these songs - guide, cycling, Pewter City, and Casino - with the program Audacity to make the first music loop run for at least 5 minutes (<https://downloads.khinsider.com/game-soundtracks/album/pokemon-game-boy-pok-mon-sound-complete-set-play-cd>). To add music, we watched a youtuber Max O'Didily (<https://www.youtube.com/watch?v=wJO_cq5XeSA>). 

## Known Issues:
Using kilt.graphics as the basis for its GUI, Pokemon Memory Game has certain limitations in advanced graphical animations that can be achieved by using other packages such as Swing. There are no specific bugs, glitches, or shortcomings that users should be aware of as of today. 

<!-- A user must understand that they should be intentional and precise with their clicking- the cards will not always properly flip unless it is clear to the computer that they want that specific pokemon card to flip. It is also worth noting that the start menu is a separate canvas- it is not a part of the already game canvas. -->



## Societal Impact:
<!-- We tried our best to make this inclusive. Our biggest tasks for inclusivity included (a) a timer bar and (b) a difficulty level. Originally, the timer bar was a countdown that resembled a clock. However, with the understanding that small children or people who struggle with time-reading may struggle with having to play while simultaneously interpreting a countdown and understand how much time they have left. A timer bar that empties allows it to be visualized- they simply need to see how much of the bar is left. A difficulty level allows for the game to be played by people with different memory skills. A small child may struggle at first with 30 cards, and we felt as if 10 would be a better count. Originally, we had it so where the difficulty level was typed. By instead having a button, it included those who cannot type or may struggle with mobility, though is also just easier. 

We recognize that the game is not very accessible to those with memory issues, especially with the more challenging difficulties, since the nature of the game is to be a memory challenge. We also recognize that it is not friendly to other disabilities, such as low vision. We believe the central game aspects to not be color-blind friendly, but still playable, as the theme is consistenly pink and the shape of the pokemon images on the card are visible and clear, so their shapes can be made out regardless. The images produced during victory, loss, etc., are not colorblind nor low-vision friendly. 


We acknowledge that the game is not accessible to those with mobility and vision issues. For this to occur, we would need to allow for voice and audio description. For instance, the cards would be labeled on letters for the horizontals and numbers vertically like a chess board, and players would say the position they want to reveal. Then, the game would announce the name of the pokemon. While this would probably be more challenging to play, it would no longer be click based nor visual based. Further, to allow for more mobility access, we would try to implement a directional pad usage. However, both the audio, voice, and d-pad implementation are beyond our current scope of knowledge. 

We do understand that our game excludes vulnerable populations that do not have access to computers that can run the game.  -->

	Pokemon Memory Game relies entirely on mouse clicks for user input and screen navigation. This creates barriers for those who rely on keyboards and switches to access computers and other digital devices. 
	Furthermore, taking into consideration the different colors of pokemon characters against the card background, Pokemon Memory Card does not comply with the AAA color contrast guide which imposes strain on those with low vision and color blindness. 






