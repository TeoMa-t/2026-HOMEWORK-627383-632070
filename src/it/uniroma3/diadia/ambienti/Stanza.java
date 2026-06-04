package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * * @author docente di POO 
 * @see Attrezzo
 * @version base
 */
public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;

	private Map<String, Attrezzo> attrezzi;

	// Sostituita la chiave String con l'enum Direzione
	private Map<Direzione, Stanza> stanzeAdiacenti;
	
	private AbstractPersonaggio personaggio;
	
	//--------- Costruttore ----------
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new HashMap<>();
	}

	//------------- Getter e Setter --------------
	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.toString();
	}

	public List<Attrezzo> getAttrezzi() {
	    return new ArrayList<>(this.attrezzi.values());
	}

	public List<Direzione> getDirezioni() {
		return new ArrayList<>(this.stanzeAdiacenti.keySet());
	}
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
	    this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
	    return this.personaggio;
	}

	//--------- Metodi -------------

	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if(this.stanzeAdiacenti.size() < NUMERO_MASSIMO_DIREZIONI || this.stanzeAdiacenti.containsKey(direzione))
			this.stanzeAdiacenti.put(direzione, stanza);
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo != null && this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;
	}

	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("Nome stanza: " + this.nome);
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.stanzeAdiacenti.keySet())
			if (direzione != null)
				risultato.append(" " + direzione.name());
		
		risultato.append("\nAttrezzi nella stanza: ");
		
		for(Attrezzo attrezzo : this.attrezzi.values()) 
			risultato.append(attrezzo.toString() + " | ");

		return risultato.toString();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo != null && this.attrezzi.containsKey(attrezzo.getNome())) {
			this.attrezzi.remove(attrezzo.getNome());
			return true;
		}
		return false;
	}
	
	public boolean hasAdiacente(Direzione direzione) {
	    return this.getStanzaAdiacente(direzione) != null;
	}

	public boolean isMagica() {
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    
	    if (!(obj instanceof Stanza)) return false; 
	    
	    Stanza other = (Stanza) obj;
	    
	    if (this.nome == null) {
	        return other.getNome() == null;
	    } else {
	        return this.nome.equals(other.getNome());
	    }
	}

	@Override
	public int hashCode() {
	    return this.nome != null ? this.nome.hashCode() : 0;
	}
}