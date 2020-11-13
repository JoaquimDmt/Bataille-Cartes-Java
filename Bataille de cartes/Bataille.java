import java.util.ArrayList;
import java.util.Collections;

/**
 * Bataille (Main)
 * press F5 in Visual-Studio Code to launch the programm
 * to generate all .class files use the command "$ javac -encoding UTF8 Bataille.java", utf-8 required because i use those characters ♣ ♠ ♦ ♥
 */

public class Bataille {
	
	public static void main(String[] args) {
		//52 Cards generation + shuffle
		ArrayList<Card> cards = generatecards();

		ArrayList<Card> deck1 = new ArrayList<Card>();
		ArrayList<Card> deck2 = new ArrayList<Card>();
		
		//spliting the cards in 2 decks, each one containing half of the cards
		for (int i=0;i<26;i++) {
			deck1.add(cards.get(i)); //player 1 gets the first 26 cards
			deck2.add(cards.get(i+26)); //player 1 gets the last 26 cards
		}
		//We create 2 players who have a name and an array of cards
		Player p1 = new Player("P1", deck1);
		Player p2 = new Player("P2", deck2);

		int roundCardsNumber; //nombre de cartes mises en jeu par manche (1 par défaut, 3 en cas de bataille)

		//lancement de la partie qui s'arretera lorsque l'un des deux joueurs n'aura plus de cartes
		while (p1.deck.size() != 0 && p2.deck.size() != 0) {
			roundCardsNumber=0; //aucunes cartes sur tables de base

			play(p1, p2, roundCardsNumber);

			System.out.println(p1.name + " = " + p1.score + " | " + p2.name + " = " + p2.score + "\n");
		}
		
		//si l'on sort du while c'est que l'un des joueurs n'a plus de cartes, si c'est p2 alors p1 gagne
		if (p2.score == 0) System.out.println(p1.name + " a remporté toutes les cartes, il gagne la partie !");
		//sinon cela signifie que c'est p1 qui n'a plus de cartes et donc p2 gagne
		else System.out.println(p2.name + " a remporté toutes les cartes, il gagne la partie !");	

	} //end of main function
	
	static ArrayList<Card> generatecards(){
		//cards creation (new Array containing every Card)
		ArrayList<Card> cards = new ArrayList<Card>();
		//we create 52 cards (13 of each symbol)
		for (int j=0;j<4;j++) {
			for (int i=0;i<13;i++) {
				switch(j) {
				case 0: cards.add(new Card("Pique",i+1)); break;
				case 1: cards.add(new Card("Coeur",i+1)); break;
				case 2: cards.add(new Card("Trefle",i+1)); break;
				case 3: cards.add(new Card("Carreau",i+1)); break;
				}
			}
		}
		//Then deck shuffle before spliting in 2
		Collections.shuffle(cards);
		return cards;
	}
	
	static void play (Player p1, Player p2, int roundCardsNumber) {
		// chaque joueur met en jeu la carte (ou les cartes si roundCardsNumber>1) du haut de son paquet
		Card card1 = p1.getCard(roundCardsNumber);
		Card card2 = p2.getCard(roundCardsNumber);

		System.out.println(card1.getValue()+ " de " +card1.getSymbol() + " vs "+card2.getValue()+ " de " +card2.getSymbol());

		//on compare la carte du joueur 1 avec celle du joueur 2
		switch(card1.compareCard(card2)) {
			//p1 > p2
			case 0: {
				Card[] cardWon = new Card[roundCardsNumber+1];
				Card[] cardTakenBack = new Card[roundCardsNumber+1];
				for(int i=0;i<=roundCardsNumber;i++) {
					cardWon[i]=p2.getCard(i); //la carte gagnée est celle du joueur 2
					cardTakenBack[i]=p1.getCard(i); //la carte récupérée est celle du joueur 1
				}
				//p2 donne sa carte a p1
				p2.removeCard(cardWon);
				p1.addCard(cardWon);
				//p1 reprend sa carte
				p1.removeCard(cardTakenBack);
				p1.addCard(cardTakenBack);
				break;
				}
			//p2 > p1
			case 1: {
				Card[] cardWon = new Card[roundCardsNumber+1];
				Card[] cardTakenBack = new Card[roundCardsNumber+1];
				for(int i=0;i<=roundCardsNumber;i++) {
					cardWon[i]=p1.getCard(i); //la carte gagnée est celle du joueur 1
					cardTakenBack[i]=p2.getCard(i); //la carte récupérée est celle du joueur 2
				}
				//p1 donne sa carte a p2
				p1.removeCard(cardWon); 
				p2.addCard(cardWon); 
				//p2 reprend sa carte
				p2.removeCard(cardTakenBack);
				p2.addCard(cardTakenBack);
				break;
				}
			//p1 = p2
			case 2: {
				//En cas de bataille si le joueur n'a plus assez de cartes alors on considère qu'il a perdu et donc on fait comme si c'était un "case 0" ou "case 1".

				//if player 1 has less than 3 cards
				if (p1.deck.size() < roundCardsNumber+3) {
					System.out.println(p1.name + " n'a plus assez de cartes pour un bataille...");

					//same as case 1
					Card[] cardWon = new Card[roundCardsNumber+1];
					Card[] cardTakenBack = new Card[roundCardsNumber+1];
					for(int i=0;i<=roundCardsNumber;i++) {
						cardWon[i]=p1.getCard(i);
						cardTakenBack[i]=p2.getCard(i);
					}
					//p1 donne sa carte a p2
					p1.removeCard(cardWon); 
					p2.addCard(cardWon); 
					//p2 reprend sa carte
					p2.removeCard(cardTakenBack);
					p2.addCard(cardTakenBack);
					break;
				}
				//else if player 2 has less than 3 cards
				else if (p2.deck.size() < roundCardsNumber+3) {
					System.out.println(p2.name + " n'a plus assez de cartes pour un bataille...");

					// same as case 0
					Card[] cardWon = new Card[roundCardsNumber+1];
					Card[] cardTakenBack = new Card[roundCardsNumber+1];
					for(int i=0;i<=roundCardsNumber;i++) {
						cardWon[i]=p2.getCard(i);
						cardTakenBack[i]=p1.getCard(i);
					}
					//p2 donne sa carte a p1
					p2.removeCard(cardWon);
					p1.addCard(cardWon);
					//p1 reprend sa carte
					p1.removeCard(cardTakenBack);
					p1.addCard(cardTakenBack);
					break;
				}

				//En cas de bataille normale (roundCardsNumber+2 monte le nombre de cartes en jeu à 3 qui est le nombre de cartes requises par joueur pour faire une bataille)
				else {
					play(p1, p2, roundCardsNumber+2);
				}
			}

		} 
	}

}