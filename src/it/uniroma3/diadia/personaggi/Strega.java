package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class Strega  extends AbstractPersonaggio{

	private static final String MESSAGGIO_STREGA = "MWEHEHEHEHEHEHE, sciocco viagguatore! Non otterrai nulla da me!!";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);

	}
	
	@Override
	public String agisci(Partita partita) {
		return MESSAGGIO_STREGA;
	}

}
