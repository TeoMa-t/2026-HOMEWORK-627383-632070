package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ambienti.Direzione;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Partita {

	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;

	//--------- Costruttore ----------
	public Partita() {
		// AL POSTO DI "new Labirinto()", usiamo il builder per un livello minimale
		this(Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto());
	}
	
	public Partita(Labirinto labirinto){
		this.labirinto = labirinto;
		this.finita = false;
		this.giocatore= new Giocatore();
		this.setStanzaCorrente(labirinto.getStanzaIniziale());
	}

	//---------- Getter e Setter ----------
	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	//--------- Metodi ---------
	public boolean vinta() {
		return this.getStanzaCorrente() == labirinto.getStanzaVincente();
	}

	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	public void setFinita() {
		this.finita = true;
	}
	
	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}

	public boolean giocatoreIsVivo() {
		if(this.giocatore.getCfu() == 0)
			return false;
		return true;
	}
}