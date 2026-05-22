package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;

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
		
		Borsa borsa = partita.getGiocatore().getBorsa();
		
		io.mostraMessaggio("\n--- STATO DELLA BORSA ---");
		io.mostraMessaggio("Stato normale: " + borsa.toString());
		io.mostraMessaggio("Ordinata per peso: " + borsa.getContenutoOrdinatoPerPeso().toString());
		io.mostraMessaggio("Ordinata per nome: " + borsa.getContenutoOrdinatoPerNome().toString());
		io.mostraMessaggio("Raggruppata per peso: " + borsa.getContenutoRaggruppatoPerPeso().toString());
		io.mostraMessaggio("-------------------------\n");
	}
	
	public String getNome() {
		return "guarda";
	}
	
	public String getParametro() {
		return null;
	}
}
