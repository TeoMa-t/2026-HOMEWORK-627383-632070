package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

	private IO io;
	private ComandoPosa comandoPosa;
	private Partita partita;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.io = new IOConsole();
		this.comandoPosa = new ComandoPosa();
		
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Aula N10")
				.getLabirinto();
		
		this.partita = new Partita(monolocale);
	}
	
	@Test
	public void testPosaAttrezzoStanzaVuota() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("Spada", 1));
		
		this.comandoPosa.setParametro("Spada");
		this.comandoPosa.esegui(this.partita, this.io);
		
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("Spada"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Spada"));
	}
	
	@Test 
	public void testPosaAttrezzoStanzaPiena() {
		LabirintoBuilder builder = new LabirintoBuilder().addStanzaIniziale("Aula N10");
		for(int i = 0; i < 10 ; i++) {
			builder.addAttrezzo("oggetto" + i, 1);
		}
		this.partita = new Partita(builder.getLabirinto()); 
		
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada", 1));
		
		this.comandoPosa.setParametro("spada");
		this.comandoPosa.esegui(this.partita, this.io);

		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}

	@Test
	public void testPosaAttrezzoInesistente() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada", 1)); 

		this.comandoPosa.setParametro("chiave"); 
		this.comandoPosa.esegui(this.partita, this.io);

		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("chiave"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
	}

	@Test
	public void testPosaSenzaParametro() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada", 1));

		this.comandoPosa.setParametro(null);
		this.comandoPosa.esegui(this.partita, this.io);

		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}	
}
