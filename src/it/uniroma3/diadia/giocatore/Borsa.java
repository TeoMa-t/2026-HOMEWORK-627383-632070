package it.uniroma3.diadia.giocatore;


import java.util.LinkedList;
import java.util.List;

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
	private List<Attrezzo> attrezzi;
	private int pesoMax;


	//-------- Costruttori ---------
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new LinkedList<>(); // speriamo bastino ...
		
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
		
		this.attrezzi.add(attrezzo);
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
		
		for(Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo.getNome().equals(nomeAttrezzo))
				return attrezzo;
		}
		
		return null;

	}

	/**
	 * Restituisce peso attuale borsa
	 * @return peso
	 */
	public int getPeso() {
		int peso = 0;
		
		for(Attrezzo attrezzo : this.attrezzi)
			peso += attrezzo.getPeso();
		
		return peso;
	}

	/**
	 * verifica se la borsa è vuota
	 */
	public boolean isEmpty( ) {
		return this.attrezzi.isEmpty();
	}
	/**
	 * Verifica se la borsa è piena
	 * @return
	 */
	public boolean isFull( ) {
	   return getPeso() >= 10;
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
		
		for(Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo.getNome().equals(nomeAttrezzo)) {
				a = attrezzo;
				this.attrezzi.remove(attrezzo);
				break;
			}
		}
		
		return a;
	}

	//ci restituisce una descrizione degli oggetti in borsa
	public String toString() {
		StringBuilder s = new StringBuilder ();
		if (!this. isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for(Attrezzo attrezzo : this.attrezzi) {
				s.append(attrezzo.toString() + " | ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
