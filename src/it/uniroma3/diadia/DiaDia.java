package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
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
		
		// Esercizio 20: Costrutto try-with-resources per chiudere lo Scanner in sicurezza
		try (Scanner scanner = new Scanner(System.in)) {
			
			// FIX ESERCIZIO 20: Passiamo lo scanner appena creato dentro le parentesi
			IO io = new IOConsole(scanner);
			
			Labirinto labirinto = Labirinto.newBuilder()
					.addStanzaIniziale("Atrio")
					.addAttrezzo("osso", 1)
					.addStanzaVincente("Biblioteca")
					.addStanza("Aula N11")
					.addStanza("Aula N10")
					.addAttrezzo("lanterna", 3)
					.addStanza("Laboratorio Campus")
					.addAdiacenza("Atrio", "Biblioteca", "nord")
					.addAdiacenza("Atrio", "Aula N11", "est")
					.addAdiacenza("Atrio", "Aula N10", "sud")
					.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
					.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
					.addAdiacenza("Aula N11", "Atrio", "ovest")
					.addAdiacenza("Aula N10", "Atrio", "nord")
					.addAdiacenza("Aula N10", "Aula N11", "est")
					.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
					.addAdiacenza("Laboratorio Campus", "Atrio", "est")
					.addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
					.addAdiacenza("Biblioteca", "Atrio", "sud")
					.getLabirinto();
			
			DiaDia gioco = new DiaDia(labirinto, io);
			gioco.gioca();
		} // <--- Fine del try-with-resources. Lo Scanner si chiude da solo qui senza memory leak
	}
}