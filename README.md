#Pokemon Card Game

Pokemon Card Game is a multi-level memory card game developed in Java with [kilt-graphics API](<https://mac-comp127.github.io/kilt-graphics/>) to enhance concentration and critical thinking skills in 5-8 youth.

<img width="888" alt="Screenshot 2023-12-10 at 5 05 32 PM" src="https://github.com/Mac-COMP-127-Fall-2023/course-project-pokemon_cards-na-linh-james/assets/134335069/639800cc-11f8-4d18-837a-f673b2d543c0">
<img width="888" alt="Screenshot 2023-12-10 at 4 59 06 PM" src="https://github.com/Mac-COMP-127-Fall-2023/course-project-pokemon_cards-na-linh-james/assets/134335069/ee0072e4-d26a-4eee-a908-fbb74e3874e0">
<img width="888" alt="Screenshot 2023-12-10 at 5 05 42 PM" src="https://github.com/Mac-COMP-127-Fall-2023/course-project-pokemon_cards-na-linh-james/assets/134335069/2a9f9a88-50e5-4482-826b-0f9e973d1a6d">

The game begins with 30 cards flipped upside down. Upon clicking one of the cards, it flips face-side-up to reveal the pokemon it pertains to. The goal of the each round is to find the matching card, with 15 total pairs, for the card flipped. Upon the second card being clicked, if it is not a correct match, the cards will flip back over to hide the pokemon. However, if they match, the two cards will be removed from the screen. The goal of the game is to remove all 15 pairs before the given time and under the limited number of attempts.

# UML Diagram
Below is a UML Diagram of our classes, their varialbes, methods, and their relationships.

<img width="700" alt="Screenshot 2023-12-10 at 5 15 27 PM" src="https://github.com/Mac-COMP-127-Fall-2023/course-project-pokemon_cards-na-linh-james/assets/134335069/4f6b2074-5336-4c13-91cf-4d637232feab">

# Game UI features
Use [kilt-graphics API](<https://mac-comp127.github.io/kilt-graphics/>) to generate a 5x6 grid, holding 15 pairs of pokemon graphics cards. The position of all 30 graphics images are randomly generated.
James Hernandez, Linh Nguyen, Na Nguyen

# Project description:
This is a memory-based pokemon card game where cards are flipped and matched by players. 

# Technical Guide: 
Technical Requirements: Java 17 is required due to the use of Kilt Graphics.
Running the Program: Execute the PokemonCardGame class, which serves as the main class.

# Acknowledgements: 
We got the inspiration from the Vietnamese card game __. This source-game is similar in that cards must match, but takes it a step further by requiring a clear path between cards. It is necessary to mention the very welcomed help from __ that helped us to __. 

Our pokemon card images come directly from the pokemon official website. (...link here...). We also got our pokemon rare cards from... 

# Known Issues:
A user must understand that they should be intentional and precise with their clicking- the cards will not always properly flip unless it is clear to the computer that they want that specific pokemon card to flip. It is also worth noting that the start menu is a separate canvas- it is not a part of the already game canvas.

# Societal Impact:
We tried our best to make this inclusive. Our biggest tasks for inclusivity included (a) a timer bar and (b) a difficulty level. Originally, the timer bar was a countdown that resembled a clock. However, with the understanding that small children or people who struggle with time-reading may struggle with having to play while simultaneously interpreting a countdown and understand how much time they have left. A timer bar that empties allows it to be visualized- they simply need to see how much of the bar is left. A difficulty level allows for the game to be played by people with different memory skills. A small child may struggle at first with 30 cards, and we felt as if 10 would be a better count. Originally, we had it so where the difficulty level was typed. By instead having a button, it included those who cannot type or may struggle with mobility, though is also just easier. 

We recognize that the game is not very accessible to those with memory issues, especially with the more challenging difficulties, since the nature of the game is to be a memory challenge. We also recognize that it is not friendly to other disabilities, such as low vision. We believe the central game aspects to be color-blind friendly, as the theme is consistenly pink and the shape of the pokemon images on the card are visible and clear, so their shapes can be made out regardless. Still, the images produced during victory, loss, etc., are not intentended to be colorblind friendly. 

We tried to make the aspects of the game click-based, which is very helpful for those with low mobility. 

Societal impact:
Please write about how your project abides by ethical considerations and socially responsible computing practices. Be mindful that anything created by humans, including software, can carry embedded biases and have unintended consequences. In every software project, you must consider the ways in which your program can promote justice or heighten injustices, either through unintentional effects or through malicious misuse.

This may seem like an odd question. For example, Breakout cannot possibly have negative consequences…right? It is just a game, and such a simple little game at that! But can a blind person play it? What about somebody who uses an assistive device instead of a mouse?

Consider equitable practices in software like inclusivity, accessibility, and diversity. Think about the ways in which the code you write or software you produce may or may not contribute to society. Here are more specific questions to ask yourself: 


Does your code have the potential to exclude or cause harm to vulnerable populations? 
In what ways do you anticipate your software project being used in malicious and oppressive situations?
Could there be unintended consequences of your software creation?





# Give a one-sentence description of your project, so that somebody considering pulling the code knows what to expect.

# Give a brief technical guide to running your code:
What are its technical requirements? (At a minimum, it requires that you have Java 17 installed, because that’s what Kilt Graphics requires. Anything else?)
How should somebody run the program? Where is the main class?


# Acknowledge any help, references, inspiration, or outside resources you used. If your game includes any art or sound assets, or includes the work of others in any way, credit the creator(s) and link to the source(s).

# Known issues:
Are there fundamental design limitations that users should be aware of?
Are there specific bugs, glitches, or shortcomings that users should be aware of?

# Societal impact:
Please write about how your project abides by ethical considerations and socially responsible computing practices. Be mindful that anything created by humans, including software, can carry embedded biases and have unintended consequences. In every software project, you must consider the ways in which your program can promote justice or heighten injustices, either through unintentional effects or through malicious misuse.

This may seem like an odd question. For example, Breakout cannot possibly have negative consequences…right? It is just a game, and such a simple little game at that! But can a blind person play it? What about somebody who uses an assistive device instead of a mouse?

Consider equitable practices in software like inclusivity, accessibility, and diversity. Think about the ways in which the code you write or software you produce may or may not contribute to society. Here are more specific questions to ask yourself: 


Does your code have the potential to exclude or cause harm to vulnerable populations? 
In what ways do you anticipate your software project being used in malicious and oppressive situations?
Could there be unintended consequences of your software creation?

Note that you do not need to fix every problem you identify. Our goal is for you to develop an awareness of these issues early on in your computing journey. Being able to identify and raise ethical concerns in your software work is an important skill to have. We want you to start developing that skill now.
