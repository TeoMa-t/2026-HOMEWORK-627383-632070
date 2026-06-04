package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione; // IMPORT FONDAMENTALE!
import it.uniroma3.diadia.ambienti.Stanza;

/** * Classe ComandoVai che si occupa di far
 * spostare il giocatore in una stanza
 * adiacente
 */
public class ComandoVai extends AbstractComando {
	
	public ComandoVai() {}
	
	@Override
	public String getNome() {
		return "vai";
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if(getParametro() == null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione.");
			return;
		}
		
		Direzione direzione = null;
		try {
			// Convertiamo la stringa digitata nell'enum corrispondente
			direzione = Direzione.valueOf(getParametro().toUpperCase());
		} catch (IllegalArgumentException e) {
			io.mostraMessaggio("Direzione inesistente! (Usa nord, sud, est, ovest)");
			return;
		}
		
		// Ora passiamo l'enum corretto al metodo
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		
		if(prossimaStanza == null) {
			io.mostraMessaggio("In quella direzione non c'è una porta!");
			return;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}
}