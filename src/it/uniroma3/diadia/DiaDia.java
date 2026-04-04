package it.uniroma3.diadia;





import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {



	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "prendi","posa","aiuto", "fine"};

	private Partita partita;
	private IOConsole scanner;




	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		this.scanner = new IOConsole();

		System.out.println(MESSAGGIO_BENVENUTO);

		do		
			istruzione = scanner.leggiRiga(); 
		while (!processaIstruzione(istruzione));

	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if(comandoDaEseguire.getNome() == null) {
			scanner.mostraMessaggio("Devi inserire un comando");
		}
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			scanner.mostraMessaggio("Comando sconosciuto");


		if (this.partita.isFinita()) {
			if(this.partita.vinta()) {
				scanner.mostraMessaggio("Hai vinto!");
				return true;
			}
			else if(this.partita.getCfu() == 0) {
				scanner.mostraMessaggio("Hai perso!");
				return true;
			}

			this.partita.setFinita();
			return true;
		}
		else
			return false;
	} 



	// implementazioni dei comandi dell'utente:
	/**
	 * Posa oggetti a terra
	 * 
	 * @return se l'utente non inserisce nessun parametro,
	 * altrimenti verifiica se è presente, se si lo posa.
	 */
	public void posa(String oggetto) {
		Giocatore giocatore = this.partita.getGiocatore();

		if(oggetto == null) {
			scanner.mostraMessaggio("Che oggetto vuoi posare?");
			scanner.mostraMessaggio(giocatore.getContenutoBorsa());
			scanner.mostraMessaggio("\n");
			return;
		}
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Attrezzo attrezzoDaPosare = giocatore.prendiAttrezzoDallaBorsa(oggetto);

		if( attrezzoDaPosare != null) {

			if(stanzaCorrente.addAttrezzo(attrezzoDaPosare)) {
				scanner.mostraMessaggio("Attrezzo posato!");
			}
			else {
				scanner.mostraMessaggio("Stanza piena!");
				giocatore.mettiAttrezzonellaBorsa(attrezzoDaPosare);
			}


		}
		else
			scanner.mostraMessaggio("Non possiedi l'attrezzo!");

	}
	/**
	 * Prendi oggetti da terra
	 * 
	 * @return se l'utente non inserisce nessun parametro,
	 * altrmenti verifica se la stanza contiene ll'oggetto, 
	 * se si lo mette in borsa.
	 */
	public void prendi(String oggetto) {
		if(oggetto == null) {
			scanner.mostraMessaggio("Che oggetto vuoi prendere?");

			Attrezzo[] attrezziDisponibili = this.partita.getStanzaCorrente().getAttrezzi();

			boolean stanzaVuota = true;

			for(Attrezzo a : attrezziDisponibili) {
				if(a != null) {
					scanner.mostraMessaggio(a.toString() + " | ");
					stanzaVuota = false;
				}
			}
			if(stanzaVuota)
				scanner.mostraMessaggio("La stanza non contiene oggetti!");

			scanner.mostraMessaggio("\n");
			return;
		}

		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Giocatore giocatore = this.partita.getGiocatore();
		if(stanzaCorrente.hasAttrezzo(oggetto)) {

			Attrezzo attrezzoPreso = stanzaCorrente.getAttrezzo(oggetto);
			stanzaCorrente.removeAttrezzo(attrezzoPreso);
			if(giocatore.mettiAttrezzonellaBorsa(attrezzoPreso))
				scanner.mostraMessaggio("Attrezzo messo nella borsa!");
			else {
				scanner.mostraMessaggio("Borsa piena!");
				stanzaCorrente.addAttrezzo(attrezzoPreso);
			}
		}
		else
			scanner.mostraMessaggio("Attrezzo non presente nella stanza.");

	}



	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			scanner.mostraMessaggio(elencoComandi[i]+" ");

		scanner.mostraMessaggio("\n");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 * @param direzione
	 */ 
	private void vai(String direzione) {
		if(direzione==null) {
			scanner.mostraMessaggio("Dove vuoi andare ? Direzioni disponibili: ");

			//modifica fatta per visualizzare le direzioni disponibili
			String[] direzioniDisponibili = this.partita.getStanzaCorrente().getDirezioni();

			for (String direzioni : direzioniDisponibili) {
				if (direzioni != null) { // Evitiamo il NullPointerException se l'array ha buchi
					scanner.mostraMessaggio(direzioni + " ");
				}

			}
			scanner.mostraMessaggio("\n");
			return;
		}

		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			scanner.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu-1);

		}
		scanner.mostraMessaggio("\nVite rimaste: "+ this.partita.getCfu() + "\n");
		scanner.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.partita.setFinita();
		scanner.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}