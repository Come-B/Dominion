import java.util.Collection;
import java.util.Collections;

public class Deck extends Stack{
Player owner;

Deck(Player p) {
	super();
	owner=p;
	Shop s=p.theShop;
	for(int i=0;i<7;i++) add(s.getCard("Cuivre"));
	for(int i=0;i<3;i++) add(s.getCard("Domaine"));
}

public String toString() {
	String s = "Contenu du deck "+ getClass().getName() + "@" + Integer.toHexString(hashCode()) + ":\n";
	for (int i =0; i<data.size();i++) {
		s += data.get(i).name + "  |  " ;
		if (i%7 == 0 && i !=0) {
			s+= "\n";
		}
	}
	return s+"\n";
}

public void shuffle() {
	Collections.shuffle(data);
}

///ANR : action non renouvelante = ne donne pas d'autres actions
// le but c'est de quantifier la proba de pouvoir jouer une carte action donn�e en fonction du nombre d'actions


///ATTENTION,COMPTE SEULEMENT DANS LE DECK (au sens bibliotheque),
// faut creer une liste des cartes du deck ! 
}
