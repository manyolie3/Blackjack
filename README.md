# Blackjack JavaFX GUI Application
I created a Blackjack application using JavaFX with [@acrumbley7](https://github.com/acrumbley7)! This game functions like a normal blackjack game except it does not have some features of blackjack such as splitting your hand and doubling your bet. 

<img width="550" alt="Screenshot 2023-07-10 at 2 30 24 PM" src="https://github.com/manyolie3/blackjack/assets/127877144/d5a37065-92ed-4d10-8421-f6faceb2d35f">
<img width="550" alt="Screenshot 2023-07-10 at 2 30 38 PM" src="https://github.com/manyolie3/blackjack/assets/127877144/c5b2ba88-e9e7-4d6f-997e-2fdd00bf2ded">

# How to Play!
### Goal
Tthe player wants the total value of their hand to be of greater value than the dealer’s hand without going over 21.
Scoring: cards with face value of 2-10 are worth the value of the card. Face cards Jack, Queen, & King are values at 10. Aces can serve as either 1 or 11, depending on what is best for the hand.
### Steps
- The player places a bet for the round and the dealer matches.
- The dealer alternately places one card face-up in front of the player & himself.
- The player is given one more card face-up.
- The dealer deals himself one more card face-down.
- Dealer asks if the player wants to add another card to
their hand from the top of the deck.
  - If yes, player chooses “hit.”
  - If not, player chooses “stay.”
- Any number of cards are allowed if total value of
the hand is less than 21.
- The dealer has to keep hitting until their hand
reaches a value of at least 17.
- After the player chooses, the dealer reveals their
second card.
### Winning
- If first two cards given to either the player or the dealer have a value equal to 21, this is called a blackjack and is an automatic win. “Casino” pays 1.5x the value of the player’s initial bet.
- If the player or the dealer's hand go over the value of 21, their hand is a "bust" and results in a loss. The player that does not bust wins the bet and the other loses.
