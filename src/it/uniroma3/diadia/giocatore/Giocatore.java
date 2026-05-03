package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/*
 * Classe Giocatore - è il giocatore che si muove nelle stanze 
 * della partita. Tiene conto delle proprie vite, e può 
 * prendere o posare oggetti nelle stanze. Può verificare 
 * il contenuto della propria borsa
 * 
 * @author 627383 - 632070
 * @versiion base
 */
public class Giocatore {

	private int cfu;
	private Borsa borsa;
	static final private int CFU_INIZIALI = 20;

	//---------- Costruttori -----------
	public Giocatore() {
		this(CFU_INIZIALI);
	}
	public Giocatore(int cfu) {
		this.cfu= cfu;
		this.borsa = new Borsa();
	}

	//----- Getter e Setter Cfu ---------

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public Borsa getBorsa() {
	    return this.borsa;
	}

	//------ Metodi per accedere alla Borsa --------

	/**
	 * Delega l'aggiunta di un oggetto alla borsa
	 * @param attrezzo
	 */

	public boolean mettiAttrezzonellaBorsa(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}

	/**
	 * Delega la rimozione di un oggetto dalla borsa
	 * @param nomeAttrezzo
	 */

	public Attrezzo prendiAttrezzoDallaBorsa(String nomeAttrezzo) {
		return this.borsa.removeAttrezzo(nomeAttrezzo);
	}

	/**
	 * Delega l'output dell'elenco degli oggetti nella borsa
	 */

	public String getContenutoBorsa() {
		return this.borsa.toString();
	}
}
