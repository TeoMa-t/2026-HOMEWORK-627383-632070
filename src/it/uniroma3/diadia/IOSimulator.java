 package it.uniroma3.diadia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {

	private List<String> righeDaLeggere;
	private int indiceRigheDaLeggere;
	
	private List<String> messaggiProdotti;
	private int indiceMessaggiMostrati;
	private int indiceMessaggiLetti;
	
	private Map<String, List<String>> logMessaggi;
	private String comandoAttuale;

	/**
	 * Costruttore: riceve la lista dei comandi che il "finto utente" digiterà.
	 */
	public IOSimulator(List<String> righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRigheDaLeggere = 0;
		this.messaggiProdotti = new ArrayList<>();
		this.indiceMessaggiMostrati = 0;
		this.indiceMessaggiLetti = 0;
		this.logMessaggi = new HashMap<>();
		this.comandoAttuale = "Inizio Partita";
	}

	@Override
	public String leggiRiga() {
		// Se abbiamo ancora comandi finti da leggere, li restituiamo uno alla volta
		if (this.indiceRigheDaLeggere < this.righeDaLeggere.size()) {
			String riga = this.righeDaLeggere.get(this.indiceRigheDaLeggere);
			this.indiceRigheDaLeggere++;
			this.comandoAttuale = riga;
			return riga;
		} else {
			return null;
		}
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		// Invece di fare System.out.println, salviamo il messaggio nella lista
		this.messaggiProdotti.add(messaggio);
		this.indiceMessaggiMostrati++;
		
		if(!this.logMessaggi.containsKey(this.comandoAttuale)) 
			this.logMessaggi.put(this.comandoAttuale, new ArrayList<>());
		this.logMessaggi.get(this.comandoAttuale).add(messaggio);
		
	}

	// --- METODI EXTRA PER I TEST ---
	
	/**
	 * Restituisce il prossimo messaggio prodotto dal gioco (utile per le Assertions nei test)
	 */
	public String nextMessaggio() {
		if (this.indiceMessaggiLetti < this.indiceMessaggiMostrati) {
			String next = this.messaggiProdotti.get(this.indiceMessaggiLetti);
			this.indiceMessaggiLetti++;
			return next;
		} else {
			return null;
		}
	}
	
	

	public boolean hasNextMessaggio() {
		return this.indiceMessaggiLetti < this.indiceMessaggiMostrati;
	}
	
	public Map<String, List<String>> getLogMessaggi(){
		return this.logMessaggi;
	}

	public List<String> getMessaggiProdotti() {
		return messaggiProdotti;
	}
}