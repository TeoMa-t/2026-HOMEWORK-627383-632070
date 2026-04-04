package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/*
 * Classe Borsa - è la classe cheviene generata assieme
 * al giocatore, tiene conto degli oggetti che prendiamo e del loro peso.
 * 
 *  
 * @author 627383 - 632070
 * @version base
 */

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;


	//-------- Costruttori ---------
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino ...
		this.numeroAttrezzi = 0;
	}

	//-------- Metodi -----------

	/**
	 * Aggiunge l'attrezzo in borsa se c'è spazio
	 * @param attrezzo
	 * @return true se inserito, false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi == 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	//Peso massimo borsa
	public int getPesoMax() {
		return pesoMax;

	}

	/**
	 * Restituisce attrezzo se presente, altrimenti null
	 * @param nomeAttrezzo
	 * @return attrezzo
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++) 
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];


		return a;

	}

	/**
	 * Restituisce peso attuale borsa
	 * @return peso
	 */
	public int getPeso() {
		int peso = 0;

		for (int i= 0; i<this.numeroAttrezzi; i++) {
			if(attrezzi[i] != null)
				peso += this.attrezzi[i].getPeso();
		}
		return peso;
	}

	/**
	 * verifica se la borsa è vuota
	 */
	public boolean isEmpty( ) {
		return this.numeroAttrezzi == 0;
	}
	/**
	 * Verifica se la borsa è piena
	 * @return
	 */
	public boolean isFull( ) {
		return this.numeroAttrezzi == 10;
	}

	/**
	 * verifica se è presente l'attrezzo in borsa
	 * @param nomeAttrezzo
	 * 
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * rimuove l'attrezzo in borsa se presente, altrimenti restituisce null
	 * @param nomeAttrezzo
	 * @return
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++) 
			if(attrezzi[i] != null) {
				if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					a = attrezzi[i];
					this.attrezzi[i] = this.attrezzi[this.numeroAttrezzi - 1];
					this.attrezzi[this.numeroAttrezzi - 1] = null;


				}
			}
		return a;
	}

	//ci restituisce una descrizione degli oggetti in borsa
	public String toString() {
		StringBuilder s = new StringBuilder ();
		if (!this. isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++) {
				if(attrezzi[i] != null)
					s.append(attrezzi[i].toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
