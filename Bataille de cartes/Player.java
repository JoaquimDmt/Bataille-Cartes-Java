import java.util.List;

/**
 * Player
 */

public class Player {
	
	public String name;
	public List<Card> deck;
    public int score;
	
	public Player(String name, List<Card> deck) {
		this.name = name;
		this.deck = deck;
		this.score = deck.size();
	}

	//met en jeu une carte ou plusieurs en cas de bataille (la/les première(s) du paquet)
	public Card getCard(int number) {
		return deck.get(number);
	}
	
	// ajoute la carte remporté dans le paquet du joueur
	public void addCard(Card[] win) {
		for (int i=0;i<win.length;i++) {
			deck.add(win[i]);
			score++;
		}
	}
	// retire la carte perdu du paquet du joueur
	public void removeCard(Card[] win) {
		for (int i=0;i<win.length;i++) {
			deck.remove(win[i]);
			score--;
		}
	}

}