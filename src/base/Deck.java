package base;
import java.util.Collections;

public class Deck extends Stack{
Player owner;

Deck(Player p) {
	super();
	owner=p;
	Shop s=p.partie.theShop;
	for(int i=0;i<7;i++) add(s.getCard("Copper"));
	for(int i=0;i<3;i++) add(s.getCard("Estate"));
}

public String toString() {
	String s = "Contenu du deck "+ getClass().getName() + "@" + Integer.toHexString(hashCode()) + ":\n";
	for(int i = 0; i < data.size();i++) {
		s += data.get(data.size()-i-1).getName() + "  <  " ;
		if (i%8 == 0 && i !=0) {
			s+= "\n";
		}
	}
	return s;
}

public void shuffle() {
	Collections.shuffle(data);
}

}
