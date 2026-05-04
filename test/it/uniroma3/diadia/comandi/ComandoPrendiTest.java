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

class ComandoPrendiTest {
	
	private Partita partita;
	private Stanza stanza;
	private Attrezzo attrezzo;
	private ComandoPrendi comandoPrendi;
	private IO io;
	
	@BeforeEach
	void setUp() throws Exception {
		this.partita = new Partita();
		this.io = new IOConsole();
		this.comandoPrendi = new ComandoPrendi();
		this.stanza = new Stanza("Aula N10");
		this.attrezzo = new Attrezzo("spada", 5);
		
		this.stanza.addAttrezzo(attrezzo);
		this.partita.setStanzaCorrente(stanza);
	}

	@Test
	public void testAttrezzoPresoCorrettamente() {
		this.comandoPrendi.setParametro("spada");
		this.comandoPrendi.esegui(this.partita, this.io);
		
		assertFalse(this.stanza.hasAttrezzo("spada"));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test
	public void testAttrezzoInesistente() {
		this.comandoPrendi.setParametro("osso");
		this.comandoPrendi.esegui(this.partita, this.io);
		
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}
	
	@Test
	public void testAttrezzoTroppoPesante() {
		Attrezzo martello = new Attrezzo("martello", 11);
		this.stanza.addAttrezzo(martello);
		
		this.comandoPrendi.setParametro("martello");
		this.comandoPrendi.esegui(this.partita, this.io);
		
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(this.stanza.hasAttrezzo("martello"));
	}
	
	@Test
	public void testPrendiAttrezzoBorsaPiena() {
		for(int i = 0; i < this.partita.getGiocatore().getBorsa().getPesoMax() ; i++) {
			this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("oggetto" + i, 1));
		}
		this.comandoPrendi.setParametro("spada");
		this.comandoPrendi.esegui(this.partita, this.io);
		
		assertTrue(this.stanza.hasAttrezzo("spada"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
}
