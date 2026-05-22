package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziPerPeso implements Comparator<Attrezzo> {
	
	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		int risultato = a1.getPeso() - a2.getPeso();
		
		if(risultato == 0)
			return a1.compareTo(a2);
		
		return risultato;
	}
}
