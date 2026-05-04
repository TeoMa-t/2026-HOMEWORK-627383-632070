package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private StanzaBuia stanzaBuia;
	private Attrezzo lanterna;

	@BeforeEach
	public void setUp() throws Exception {
		this.stanzaBuia = new StanzaBuia("Cantina", "lanterna");
		this.lanterna = new Attrezzo("lanterna", 1);
	}

	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		String e = "qui c'è un buio pesto";
		assertEquals(e, stanzaBuia.getDescrizione());
	}

	@Test
	public void testGetDescrizioneConAttrezzo() {
		this.stanzaBuia.addAttrezzo(lanterna);
		String e = "qui c'è un buio pesto";
		assertNotEquals(e, stanzaBuia.getDescrizione());
	}
}