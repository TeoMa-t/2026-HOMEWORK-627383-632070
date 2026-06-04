package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.*;
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

			// IL SUPER LABIRINTO!
			Labirinto labirinto = Labirinto.newBuilder()
					.addStanzaIniziale("Atrio")
					.addAttrezzo("osso", 1) // Lo useremo per il cane

					.addStanzaVincente("Biblioteca")

					.addStanza("Aula N11")
					.addAttrezzo("chiave", 1) // Serve per la stanza bloccata

					.addStanza("Aula N10")
					.addAttrezzo("lanterna", 3) // Serve per la stanza buia

					.addStanzaMagica("Laboratorio Campus", 2) // Stanza magica con soglia 2

					.addStanzaBuia("Ripostiglio", "lanterna")

					.addStanzaBloccata("Archivio Segreto", "nord", "chiave")

					// Adiacenze standard
					.addAdiacenza("Atrio", "Biblioteca", "nord")
					.addAdiacenza("Biblioteca", "Atrio", "sud")

					.addAdiacenza("Atrio", "Aula N11", "est")
					.addAdiacenza("Aula N11", "Atrio", "ovest")

					.addAdiacenza("Atrio", "Aula N10", "sud")
					.addAdiacenza("Aula N10", "Atrio", "nord")

					.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
					.addAdiacenza("Laboratorio Campus", "Atrio", "est")

					// Adiacenza Stanza Buia
					.addAdiacenza("Aula N10", "Ripostiglio", "est")
					.addAdiacenza("Ripostiglio", "Aula N10", "ovest")

					// Adiacenza Stanza Bloccata
					.addAdiacenza("Aula N11", "Archivio Segreto", "nord")
					.addAdiacenza("Archivio Segreto", "Aula N11", "sud")
					.addAdiacenza("Archivio Segreto", "Biblioteca", "nord") // L'uscita a nord dell'Archivio è BLOCCATA!

					// Aggiungiamo i Personaggi
					.addCane("Fuffi", "Grrr... bau bau!", "Aula N11")
					.addStrega("Morgana", "Ihihihih! Sei venuto a farti bocciare?", "Aula N10")
					.addMago("Merlino", "Saluti, giovane studente. Ho un dono per te.", "Laboratorio Campus", new Attrezzo("bacchetta", 1))

					.getLabirinto();

			DiaDia gioco = new DiaDia(labirinto, io);
			gioco.gioca();
		} // <--- Fine del try-with-resources. Lo Scanner si chiude da solo qui senza memory leak
	}
}