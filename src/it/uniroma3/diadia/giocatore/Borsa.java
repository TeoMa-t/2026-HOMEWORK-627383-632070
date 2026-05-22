package it.uniroma3.diadia.giocatore;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

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
	   return getPeso() >= this.getPesoMax();
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
		Attrezzo a = this.getAttrezzo(nomeAttrezzo);
	    if (a != null) {
	        this.attrezzi.remove(a);
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
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> risultato = new ArrayList<>(this.attrezzi);
		Collections.sort(risultato, new ComparatoreAttrezziPerPeso());
		return risultato;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> risultato = new TreeSet<>(this.attrezzi);
		return risultato;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> risultato = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		risultato.addAll(this.attrezzi);
		return risultato;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<>();
		
		for(Attrezzo attrezzo : this.attrezzi) {
			int peso = attrezzo.getPeso();
			
			if(!mappa.containsKey(peso))
				mappa.put(peso, new TreeSet<>());
			
			mappa.get(peso).add(attrezzo);
		}
		return mappa;
	}
}
