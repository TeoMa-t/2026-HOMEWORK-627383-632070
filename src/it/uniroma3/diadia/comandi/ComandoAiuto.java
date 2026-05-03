package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;

/** Classe ComandoAiuto che si occupa di stampare la 
 *  lista di comandi che può usare l'utente
 */

public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "prendi","posa", "guarda", "aiuto", "fine"};
	
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.println(elencoComandi[i] + " ");

		System.out.println("\n");
	}
	
	public String getNome() {
		return "aiuto";
	}
	
	public String getParametro() {
		return null;
	}
	
	public void setParametro(String parametro) {}
		
	
}
