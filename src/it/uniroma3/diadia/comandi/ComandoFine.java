package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/** Classe ComandoFine che si occupa di
 *   terminare la partita
 */
public class ComandoFine extends AbstractComando{
	
	public ComandoFine() {}
	
	
	@Override
	public void esegui(Partita partita, IO io) {
		partita.setFinita();
		io.mostraMessaggio("Grazie per aver giocato");
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
	
	
}
