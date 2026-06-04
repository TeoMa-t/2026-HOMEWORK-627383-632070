package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoBuilderTest {

	// FIX 1: Il tipo ora è Labirinto.LabirintoBuilder
	private Labirinto.LabirintoBuilder builder;

	@BeforeEach
	public void setUp() throws Exception {
		// FIX 2: Usiamo il Factory Method statico invece della new!
		this.builder = Labirinto.newBuilder();
	}

	@Test
	public void testLabirintoCompleto() {
		Labirinto lab = builder
			.addStanzaIniziale("Bar")
			.addStanzaVincente("Mensa")
			.addStanza("Laboratorio")
			// Qui le stringhe vanno bene perché il nostro Builder sa tradurle!
			.addAdiacenza("Bar", "Mensa", "nord")        
			.addAdiacenza("Bar", "Laboratorio", "ovest") 
			.addAttrezzo("Osso", 1)
			.getLabirinto();

		assertNotNull(lab.getStanzaIniziale());
		assertEquals("Bar", lab.getStanzaIniziale().getNome());
		assertEquals("Mensa", lab.getStanzaVincente().getNome());
		
		// FIX 3: Interrogando direttamente la Stanza, dobbiamo usare gli Enum!
		assertTrue(lab.getStanza("Bar").hasAdiacente(Direzione.OVEST));
		assertTrue(lab.getStanza("Bar").hasAdiacente(Direzione.NORD));
		
		assertTrue(lab.getStanza("Laboratorio").hasAttrezzo("Osso"));
	}
}