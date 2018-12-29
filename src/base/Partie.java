package base;

import java.util.Vector;

public class Partie {
public Player [] joueurs;
public Shop theShop;
public int iCurrentPlayer;
public boolean visible;

Partie(int players){
	this(players,true);
}

Partie(int players,boolean visible){
	this.visible=visible;
	Shop s = new Shop(players,visible);
	theShop = s;
	joueurs = new Player [players];
	for (int i = 0; i<joueurs.length; i++) {
		joueurs[i] = new AIPlayer();
		joueurs[i].partie=this;
		joueurs[i].newHand();
	}
}

Partie(String[] strats){
	this(strats,true);
}

Partie(String[] strats,boolean visible){
	this.visible=visible;
	Shop s = new Shop(strats.length,visible);
	theShop = s;
	joueurs = new Player[strats.length];
	for (int i = 0; i<joueurs.length; i++) {
		joueurs[i] = new AIPlayer(strats[i]);
		joueurs[i].beginGame(this);
	}
}

Partie(Player[] players){
	this(players,true);
}

Partie(Player[] players,boolean visible){
	this.visible=visible;
	Shop s = new Shop(players.length,visible);
	theShop = s;
	joueurs = players;
	for (int i = 0; i<joueurs.length; i++) {
		joueurs[i].beginGame(this);
	}
}

void joueUnTourComplet(int first) {
	for (int i = first; i<4+first; i++) {
		joueurs[i%4].tourDeJeu();
	}
}

Player partie() {
	int first = (int) (Math.random()*this.joueurs.length);
	int turn=0;
	while(!hasEnded()) {
		joueurs[(turn+first)%this.joueurs.length].tourDeJeu();
		turn++;
	}
	if(visible) {
		System.out.println("===================== GAME ENDED ===============================");
		for (int i = 0; i<this.joueurs.length; i++) {
			System.out.println(joueurs[i].name+"'s decklist:\n"+joueurs[i].decklist);
			int points = joueurs[i].countVictoryPoints();
			System.out.println(joueurs[i].name+" has " + points + " VP");
		}
	}

	
	int maxVP = -1;
	Vector<Integer> maxPlayers = new Vector<Integer>();
	for (int i = 0; i<this.joueurs.length; i++) {
		int tempVP = joueurs[i].countVictoryPoints();
		if (tempVP>maxVP){
			maxVP = tempVP;
			maxPlayers.clear();
			maxPlayers.add(i);
			}
		else if(tempVP==maxVP)
			maxPlayers.add(i);
	}
	if(maxPlayers.size()==1)
		return joueurs[maxPlayers.get(0)];
	
	int leastTurn = 10000;
	Vector<Integer> leastTurnPlayers = new Vector<Integer>();
	for (int j = 0; j<maxPlayers.size(); j++) {
		int tempTurn = (turn-((maxPlayers.get(j)-first)%joueurs.length))/joueurs.length;
		if (tempTurn<leastTurn){
			leastTurn = tempTurn;
			leastTurnPlayers.clear();
			leastTurnPlayers.add(maxPlayers.get(j));
			}
		else if(tempTurn==leastTurn)
			leastTurnPlayers.add(maxPlayers.get(j));
	}
	
	return joueurs[leastTurnPlayers.get((int)(Math.random()*leastTurnPlayers.size()))]; //also works if only one player is in vector (rounds to 0)
}

boolean hasEnded() {
	return theShop.nombrePilesVides()>=3 || (theShop.remainingCards("Province")==0);
}

public String toString() {
	String s = "appercu de la partie : "+ "\n";
	s+= "j0 : "+ "\n" +  joueurs[0].decklist.toString();
	s+= "j1 : "+ "\n" +  joueurs[1].decklist.toString();
	s+= "j2 : "+ "\n" +  joueurs[2].decklist.toString();
	s+= "j3 : "+ "\n" +  joueurs[3].decklist.toString();
	return s;
}

public static void main(String[] args) {
	AIPlayer pedro = new AIPlayer("Test");
	AIPlayer pedro2 = new AIPlayer("Test");
	//HumanPlayer me = new HumanPlayer("Come");
	
	Partie p = new Partie(new Player[] {pedro,pedro2},false);
	p.partie();
}

}
