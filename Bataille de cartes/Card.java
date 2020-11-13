/**
 * Card
 */

public class Card {

	private static final String ERROR = "card value error";
	public String cardSymbol;
	public int cardValue;
	
	
	public Card(String cardSymbol, int cardValue) {
		this.cardSymbol=cardSymbol;
		this.cardValue=cardValue;
	}
	
	public char getSymbol() {
		switch (cardSymbol) {
			case "Trefle": return '♣';
			case "Pique": return '♠';
			case "Carreau": return '♦';
			case "Coeur": return '♥';
			default: return '?';
		}
	}
	
	public String getValue() {
		switch (cardValue) {
			case 1: return "2";
			case 2: return "3";
			case 3: return "4";
			case 4: return "5";
			case 5: return "6";
			case 6: return "7";
			case 7: return "8";
			case 8: return "9";
			case 9: return "10";
			case 10: return "Valet";
			case 11: return "Dame";
			case 12: return "Roi";
			case 13: return "As";
			default: return ERROR;
		}
	}
	
	public int compareCard(Card opponentCard) {
		int opponentValue=opponentCard.cardValue;
		int ourValue=cardValue;
		if (ourValue>opponentValue) return 0; //joueur1 gagne la manche
		else if (ourValue<opponentValue) return 1; //joueur2 gagne la manche
		else return 2; //Egalité -> bataille
	}
}
