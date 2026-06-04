package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

class ComandoPrendiTest {
	
	private IO io;
	private ComandoPrendi comandoPrendi;
	
	@BeforeEach
	public void setUp() throws Exception {
		// IOConsole rimane con le tonde vuote!
		this.io = new IOConsole();
		this.comandoPrendi = new ComandoPrendi();
	}
	
	@Test
	public void testPrendiAttrezzoEsistente() {
		// FIX: Usiamo Labirinto.newBuilder()
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("Aula N10")
				.addAttrezzo("Osso", 1)
				.getLabirinto();
		
		Partita partita = new Partita(monolocale);
		
		this.comandoPrendi.setParametro("Osso");
		this.comandoPrendi.esegui(partita, this.io);
		
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("Osso"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("Osso"));
	}
	
	@Test
	public void testPrendiAttrezzoNonEsistente() {
		// FIX: Usiamo Labirinto.newBuilder()
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("Aula N10")
				.getLabirinto();
		
		Partita partita = new Partita(monolocale);
		
		this.comandoPrendi.setParametro("Osso");
		this.comandoPrendi.esegui(partita, this.io);
		
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testPrendiAttrezzoSenzaParametro() {
		// FIX: Usiamo Labirinto.newBuilder()
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("Aula N10")
				.addAttrezzo("Osso", 1)
				.getLabirinto();

		Partita partita = new Partita(monolocale);
		
		this.comandoPrendi.setParametro(null);
		this.comandoPrendi.esegui(partita, this.io);

		assertTrue(partita.getStanzaCorrente().hasAttrezzo("Osso"));
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
}