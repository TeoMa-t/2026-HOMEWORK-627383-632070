package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	
	private Stanza stanza;
	private Attrezzo attrezzo;
	private ComandoPosa comandoPosa;
	private Partita partita;
	private IO io;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.io = new IOConsole(); 
		this.partita = new Partita();
		this.comandoPosa = new ComandoPosa();
		this.stanza = new Stanza("Aula N10");
		this.attrezzo = new Attrezzo("spada", 1);
		
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		this.partita.setStanzaCorrente(stanza);
	}

	@Test
	public void testPosaAttrezzoStanzaVuota() {
		this.comandoPosa.setParametro("spada");
		this.comandoPosa.esegui(this.partita, this.io);
		
		assertTrue(this.stanza.hasAttrezzo("spada"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test public void testPosaAttrezzoStanzaPiena() {
		for(int i = 0; i < 10 ; i++) {
			this.stanza.addAttrezzo(new Attrezzo("oggetto" + i, 1));
		}
		this.comandoPosa.setParametro("spada");
		this.comandoPosa.esegui(this.partita, this.io);
		
		assertFalse(this.stanza.hasAttrezzo("spada"));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test
	public void testPosaAttrezzoInesistente() {
		this.comandoPosa.setParametro("chiave");
		this.comandoPosa.esegui(this.partita, this.io);
		
		assertFalse(this.stanza.hasAttrezzo("chiave"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
	}
	
	@Test
	public void testPosaSenzaParametro() {
		this.comandoPosa.setParametro(null);
		this.comandoPosa.esegui(this.partita, this.io);
		
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
}
