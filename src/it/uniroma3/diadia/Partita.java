package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

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
	public Partita(){
		this.labirinto = new Labirinto();
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
	//--------- Metodi ---------
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}
	/**
	 * Metodo delegato: Partita chiede al Giocatore quanti CFU ha
	 */
	public int getCfu() {
		return this.giocatore.getCfu();
	}

	/**
	 * Metodo delegato: Partita dice al Giocatore di aggiornare i suoi CFU
	 * @param cfu
	 */
	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}

	public boolean giocatoreIsVivo() {
		if(this.giocatore.getCfu() == 0)
			return false;
		return true;
	}


}
