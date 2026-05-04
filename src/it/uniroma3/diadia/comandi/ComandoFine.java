package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/** Classe ComandoFine che si occupa di
 *   terminare la partita
 */
public class ComandoFine implements Comando{
	
	public void setParametro(String parametro) {}
	
	public void esegui(Partita partita, IO io) {
		partita.setFinita();
		io.mostraMessaggio("Grazie per aver giocato");
	}
	
	public String getNome() {
		return "fine";
	}
	
	public String getParametro() {
		return null;
	}
}
