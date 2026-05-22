package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;

	public LabirintoBuilder() {
	    this.labirinto = new Labirinto("vuoto"); 
	}

	public LabirintoBuilder addStanzaIniziale(String nome) {
		Stanza stanza = new Stanza(nome);
		this.labirinto.setStanzaIniziale(stanza);
		this.labirinto.addStanza(stanza);
		this.ultimaStanzaAggiunta = stanza;
		return this; // Ritorna se stesso per permettere il concatenamento
	}

	public LabirintoBuilder addStanzaVincente(String nome) {
		Stanza stanza = new Stanza(nome);
		this.labirinto.setStanzaVincente(stanza);
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

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
	    Attrezzo attrezzo = new Attrezzo(nome, peso);
	    this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
	    return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanzaSorgente, String stanzaDestinazione, String direzione) {
	    Stanza s1 = this.labirinto.getStanza(stanzaSorgente);
	    Stanza s2 = this.labirinto.getStanza(stanzaDestinazione);
	    
	    s1.impostaStanzaAdiacente(direzione, s2);
	    return this;
	}
}
