package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class ComandoVaiTest {
	
	private ComandoVai comandoVai;
	private IO io;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.io = new IOConsole();
		this.comandoVai = new ComandoVai();
	}

	@Test
	public void testSpostamentoInStanzaEsistente_Bilocale() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("Aula N10")
				.addStanza("Laboratorio")
				.addAdiacenza("Aula N10", "Laboratorio", "nord")
				.getLabirinto();
		
		Partita partita = new Partita(bilocale);
		
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita, this.io);
		
		assertEquals("Laboratorio", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testSpostamentoInStanzaNonEsistente_Monolocale() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Aula N10")
				.getLabirinto();
		
		Partita partita = new Partita(monolocale);
		
		this.comandoVai.setParametro("ovest");
		this.comandoVai.esegui(partita, this.io);
		
		assertEquals("Aula N10", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testSpostamentoSenzaDirezione() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Aula N10")
				.getLabirinto();
		
		Partita partita = new Partita(monolocale);
		
		this.comandoVai.setParametro(null);
		this.comandoVai.esegui(partita, this.io);
		
		assertEquals("Aula N10", partita.getStanzaCorrente().getNome());
	}
}
