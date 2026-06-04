package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull; // Aggiunto per l'ultimo test

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaAdiacente;
	private Attrezzo passepartout;

	@BeforeEach
	public void setUp() throws Exception {
		// FIX: Sostituisco "nord" con Direzione.NORD
		this.stanzaBloccata = new StanzaBloccata("Cella", Direzione.NORD, "chiave");
		this.stanzaAdiacente = new Stanza("Corridoio");
		this.passepartout = new Attrezzo("chiave", 1);
		
		// FIX: Impostiamo la stanza adiacente con l'enum
		this.stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneBloccata() {
		// FIX: Uso Direzione.NORD
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneSbloccata() {
		this.stanzaBloccata.addAttrezzo(passepartout);
		// FIX: Uso Direzione.NORD
		assertEquals(stanzaAdiacente, stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
	}
    
    @Test
	public void testGetStanzaAdiacenteDirezioneLibera() {
        // FIX: Uso Direzione.SUD al posto di "sud"
        assertNull(stanzaBloccata.getStanzaAdiacente(Direzione.SUD));
    }
}