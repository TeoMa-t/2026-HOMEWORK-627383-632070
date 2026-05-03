package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/** Classe ComandoFine che si occupa di
 *   terminare la partita
 */
public class ComandoFine implements Comando{
	
	public void setParametro(String parametro) {}
	
	public void esegui(Partita partita) {
		partita.setFinita();
		System.out.println("Grazie per aver giocato");
	}
}
