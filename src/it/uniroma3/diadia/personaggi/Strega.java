package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega  extends AbstractPersonaggio{

	private static final String MESSAGGIO_STREGA = "MWEHEHEHEHEHEHE, sciocco viagguatore! Non otterrai nulla da me!!";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);

	}
	
	@Override
	public String agisci(Partita partita) {
		return MESSAGGIO_STREGA;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "AHAHAH! Grazie per avermi regalato " + attrezzo.getNome() + ", stolto viaggiatore! Ora è mio!";
	}
}
