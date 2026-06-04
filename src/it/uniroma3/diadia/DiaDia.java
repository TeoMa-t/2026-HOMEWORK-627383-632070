package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * @author  docente di POO 
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

	private Partita partita;
	private IO io;

	public DiaDia(Labirinto labirinto, IO io) {
		this.io = io;
		this.partita = new Partita(labirinto);
	}

	public void gioca() throws Exception {
		String istruzione; 
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = io.leggiRiga(); 
		while (!processaIstruzione(istruzione));
	}   

	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita, this.io);

		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");

		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();
	}	

	public static void main(String[] argc) throws Exception {
		IO io = new IOConsole();
		
		// Sfruttiamo il Builder nidificato per ricreare il livello classico!
		// Nota: addAttrezzo aggiunge l'oggetto all'ultima stanza dichiarata
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N11")
				.addStanza("Aula N10")
				.addAttrezzo("lanterna", 3)
				.addStanza("Laboratorio Campus")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.addAdiacenza("Atrio", "Aula N11", Direzione.EST)
				.addAdiacenza("Atrio", "Aula N10", Direzione.SUD)
				.addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
				.addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST)
				.addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
				.addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
				.addAdiacenza("Aula N10", "Aula N11", Direzione.EST)
				.addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST)
				.addAdiacenza("Laboratorio Campus", "Atrio", Direzione.EST)
				.addAdiacenza("Laboratorio Campus", "Aula N11", Direzione.OVEST)
				.addAdiacenza("Biblioteca", "Atrio", Direzione.SUD)
				.getLabirinto();
		
		/* N.B. In alternativa, per usare i file .txt, ti basterà fare:
		 * CaricatoreLabirinto c = new CaricatoreLabirinto("labirinto.txt");
		 * c.carica();
		 * Labirinto labirinto = c.getLabirinto();
		 */

		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
	}
}