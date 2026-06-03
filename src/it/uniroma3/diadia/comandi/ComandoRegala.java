package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {
	
	public ComandoRegala() {}

	@Override
	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		
		if (personaggio == null) {
			io.mostraMessaggio("Non c'è nessuno a cui regalare qualcosa qui!");
			return;
		}
		
		String nomeAttrezzoDaRegalare = this.getParametro();
		if (nomeAttrezzoDaRegalare == null) {
			io.mostraMessaggio("Cosa vuoi regalare? Specifica un attrezzo dalla tua borsa.");
			return;
		}
		
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzoDaRegalare);
		if (attrezzo == null) {
			io.mostraMessaggio("Non hai un attrezzo chiamato " + nomeAttrezzoDaRegalare + " nella borsa!");
			return;
		}
		
		partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzoDaRegalare);
		String risposta = personaggio.riceviRegalo(attrezzo, partita);
		
		io.mostraMessaggio(risposta);
	}

	@Override
	public String getNome() {
		return "regala";
	}
}
