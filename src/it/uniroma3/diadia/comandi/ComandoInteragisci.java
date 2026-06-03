package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando{
	
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?";

	public ComandoInteragisci() {}

	@Override
	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			String messaggio = personaggio.agisci(partita);
			io.mostraMessaggio(messaggio);
		} else {
			io.mostraMessaggio(MESSAGGIO_CON_CHI);
		}
	}

	@Override
	public String getNome() {
		return "interagisci";
	}
}
