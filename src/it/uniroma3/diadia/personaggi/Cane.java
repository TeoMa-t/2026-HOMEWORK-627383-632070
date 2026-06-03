package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_CANE = "ARGHHH,GRRR... ti ho morso!!";
	
	public Cane(String nome,String presentazione) {
		super(nome,presentazione);
		
	}

	@Override
	public String agisci(Partita partita) {
		
		int cfuAttuali = partita.getGiocatore().getCfu();
	    partita.getGiocatore().setCfu(cfuAttuali - 1);
	    
		return MESSAGGIO_CANE;
	}
}
