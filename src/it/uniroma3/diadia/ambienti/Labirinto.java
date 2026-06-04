package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String, Stanza> stanze;
	
	/**
	 * COSTRUTTORE PRIVATO DI LABIRINTO! 
	 * Nessuno da fuori può fare "new Labirinto()".
	 */
	private Labirinto(String vuoto) {
		this.stanze = new HashMap<>();
	}

	/**
	 * FACTORY METHOD STATICO
	 * L'unico modo per iniziare a costruire un labirinto è chiamare questo metodo.
	 */
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	// --------- Getter e Setter di Labirinto ----------
	
	public Map<String, Stanza> getListaStanze() {
		return this.stanze;
	}
	
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale = stanza;
		this.addStanza(stanza); 
	}

	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente = stanza;
		this.addStanza(stanza); 
	}

	public void addStanza(Stanza stanza) {
		this.stanze.put(stanza.getNome(), stanza);
	}

	public Stanza getStanza(String nome) {
		return this.stanze.get(nome);
	}
	
	public int getNumeroStanze() {
		return this.stanze.size();
	}


	// ======================================================================
	// INIZIO CLASSE NIDIFICATA STATICA (Tutto il Builder ora vive qui dentro!)
	// ======================================================================
	public static class LabirintoBuilder {
		
		private Labirinto labirinto; // Torna ad essere di tipo Labirinto!
		private Stanza ultimaStanzaAggiunta;

		public LabirintoBuilder() {
			// Il builder può chiamare il costruttore privato perché è una classe nidificata
			this.labirinto = new Labirinto("vuoto"); 
		}

		public LabirintoBuilder addStanzaIniziale(String nome) {
			Stanza stanza = new Stanza(nome);
			this.labirinto.setStanzaIniziale(stanza);
			this.labirinto.addStanza(stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this; 
		}

		public LabirintoBuilder addStanzaVincente(String nome) {
			Stanza stanza = new Stanza(nome);
			this.labirinto.setStanzaVincente(stanza);
			this.labirinto.addStanza(stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nome, Direzione direzione, String chiave) {
			Stanza stanza = new StanzaBloccata(nome, direzione, chiave);
			this.labirinto.addStanza(stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza stanza = new StanzaMagica(nome, soglia);
			this.labirinto.addStanza(stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanza(String nome) {
			Stanza stanza = new Stanza(nome);
			this.labirinto.addStanza(stanza); 
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nome, String AttrezzoLuminoso) {
			Stanza stanza = new StanzaBuia(nome, AttrezzoLuminoso);
			this.labirinto.addStanza(stanza); 
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public Map<String , Stanza> getListaStanze() {
			return this.labirinto.getListaStanze();
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			Attrezzo attrezzo = new Attrezzo(nome, peso);
			this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaSorgente, String stanzaDestinazione, Direzione direzione) {
			Stanza s1 = this.labirinto.getStanza(stanzaSorgente);
			Stanza s2 = this.labirinto.getStanza(stanzaDestinazione);
			s1.impostaStanzaAdiacente(direzione, s2);
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione, String nomeStanza, Attrezzo attrezzo) {
			Mago mago = new Mago(nome, presentazione, attrezzo);
			this.getListaStanze().get(nomeStanza).setPersonaggio(mago);
			return this;
		}

		public LabirintoBuilder addCane(String nome, String presentazione, String nomeStanza) {
			Cane cane = new Cane(nome, presentazione);
			this.getListaStanze().get(nomeStanza).setPersonaggio(cane);
			return this;
		}

		public LabirintoBuilder addStrega(String nome, String presentazione, String nomeStanza) {
			Strega strega = new Strega(nome, presentazione);
			this.getListaStanze().get(nomeStanza).setPersonaggio(strega);
			return this;
		}
	}
	// ======================================================================
	// FINE CLASSE NIDIFICATA STATICA
	// ======================================================================
}