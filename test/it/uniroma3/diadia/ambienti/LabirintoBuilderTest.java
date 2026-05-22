package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoBuilderTest {

	private LabirintoBuilder builder;

	@BeforeEach
	public void setUp() throws Exception {
		this.builder = new LabirintoBuilder();
	}

	@Test
	public void testLabirintoCompleto() {
	    Labirinto lab = builder
	        .addStanzaIniziale("Bar")
	        .addStanzaVincente("Mensa")
	        .addStanza("Laboratorio")
	        .addAdiacenza("Bar", "Mensa", "nord")        // Collega Bar a Mensa
	        .addAdiacenza("Bar", "Laboratorio", "ovest") // Collega Bar a Laboratorio
	        .addAttrezzo("Osso", 1)
	        .getLabirinto();

	    assertNotNull(lab.getStanzaIniziale());
	    assertEquals("Bar", lab.getStanzaIniziale().getNome());
	    assertEquals("Mensa", lab.getStanzaVincente().getNome());
	    
	    assertTrue(lab.getStanza("Bar").hasAdiacente("nord"));
	    assertTrue(lab.getStanza("Bar").hasAdiacente("ovest"));
	    assertTrue(lab.getStanza("Bar").hasAttrezzo("Osso"));
	}

}
