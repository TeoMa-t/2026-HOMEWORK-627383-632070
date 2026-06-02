package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/** Classe ComandoNonValido che si occupa di
 *  stampare a schermo "Comando sconosciuto"
 *  quando un comando inserito dall'utente 
 *  non è valido
 */
public class ComandoNonValido extends AbstractComando{
	
	public ComandoNonValido() {}
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Comando sconosciuto");
	}
	
	@Override
	public String getNome() {
		return "Comando sconosciuto";
	}
	
	
}
