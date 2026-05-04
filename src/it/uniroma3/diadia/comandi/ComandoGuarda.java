package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/** Classe ComandoGuarda che si occupa di stampare
 *  a schermo le informazioni inerenti alla stanza
 *  corrente, lo stato della partita e gli oggetti
 *  contenuti nella borsa
 */
public class ComandoGuarda implements Comando{
	
	public void setParametro(String parametro) {}
	
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		
		io.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
		
		io.mostraMessaggio(partita.getGiocatore().getContenutoBorsa());	
	}
	
	public String getNome() {
		return "guarda";
	}
	
	public String getParametro() {
		return null;
	}
}
