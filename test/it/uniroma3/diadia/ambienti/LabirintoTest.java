package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {

	private Labirinto labirinto;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	@BeforeEach
	public void setUp() {
		// Creiamo un labirinto vuoto usando il Builder (unico modo permesso ora!)
		this.labirinto = Labirinto.newBuilder().getLabirinto();
		
		// Prepariamo un paio di stanze per fare i test
		this.stanzaIniziale = new Stanza("Atrio");
		this.stanzaVincente = new Stanza("Biblioteca");
	}

	@Test
	public void testGetStanzaIniziale() {
		this.labirinto.setStanzaIniziale(this.stanzaIniziale);
		assertEquals(this.stanzaIniziale, this.labirinto.getStanzaIniziale());
	}

	@Test
	public void testGetStanzaVincente() {
		this.labirinto.setStanzaVincente(this.stanzaVincente);
		assertEquals(this.stanzaVincente, this.labirinto.getStanzaVincente());
	}

	@Test
	public void testAddStanzaEGetStanza() {
		Stanza salotto = new Stanza("Salotto");
		this.labirinto.addStanza(salotto);
		
		assertEquals(salotto, this.labirinto.getStanza("Salotto"));
	}
	
	@Test
	public void testGetStanzaInesistente() {
		// Se cerco una stanza che non ho mai aggiunto, deve restituire null
		assertNull(this.labirinto.getStanza("StanzaSegreta"));
	}
}