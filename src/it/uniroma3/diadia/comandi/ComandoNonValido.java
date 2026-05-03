package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;

/** Classe ComandoNonValido che si occupa di
 *  stampare a schermo "Comando sconosciuto"
 *  quando un comando inserito dall'utente 
 *  non è valido
 */
public class ComandoNonValido implements Comando{
	
	public void esegui(Partita partita) {
		System.out.println("Comando sconosciuto");
	}
	
	public void setParametro(String parametro) {}

}
