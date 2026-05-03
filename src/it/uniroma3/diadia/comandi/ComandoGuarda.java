package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/** Classe ComandoGuarda che si occupa di stampare
 *  a schermo le informazioni inerenti alla stanza
 *  corrente, lo stato della partita e gli oggetti
 *  contenuti nella borsa
 */
public class ComandoGuarda implements Comando{
	
	public void setParametro(String parametro) {}
	
	public void esegui(Partita partita) {
		System.out.println(partita.getStanzaCorrente().getDescrizione());
		
		System.out.println("CFU rimanenti: " + partita.getGiocatore().getCfu());
		
		System.out.println(partita.getGiocatore().getContenutoBorsa());	
	}
}
