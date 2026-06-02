package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/** Classe ComandoAiuto che si occupa di stampare la 
 *  lista di comandi che può usare l'utente
 */

public class ComandoAiuto extends AbstractComando{
	
	public ComandoAiuto() {}
	
	
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("\nComandi disponibili:");
		
		for(String comando : AbstractComando.getNomiComandi()) {
			io.mostraMessaggio("- " + comando);
		}
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	
		
	
}
