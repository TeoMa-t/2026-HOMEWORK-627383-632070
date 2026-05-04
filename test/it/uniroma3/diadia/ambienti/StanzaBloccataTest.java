package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaAdiacente;
	private Attrezzo passepartout;

	@BeforeEach
	public void setUp() throws Exception {
		this.stanzaBloccata = new StanzaBloccata("Cella", "nord", "chiave");
		this.stanzaAdiacente = new Stanza("Corridoio");
		this.passepartout = new Attrezzo("chiave", 1);
		
		// Impostiamo la stanza adiacente a nord
		this.stanzaBloccata.impostaStanzaAdiacente("nord", stanzaAdiacente);
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneBloccata() {
		// Senza chiave, se vado a nord devo rimanere bloccato nella Cella
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneSbloccata() {
		// Aggiungo la chiave nella stanza
		this.stanzaBloccata.addAttrezzo(passepartout);
		// Ora se vado a nord devo trovarmi nel Corridoio
		assertEquals(stanzaAdiacente, stanzaBloccata.getStanzaAdiacente("nord"));
	}
    
    @Test
	public void testGetStanzaAdiacenteDirezioneLibera() {
        // Se vado in una direzione non bloccata (es. sud), non devo avere la stanza corrente
        // (ritornerà null perché non c'è nulla a sud, ma l'importante è che non sia la stanza bloccata)
        assertEquals(null, stanzaBloccata.getStanzaAdiacente("sud"));
    }
}