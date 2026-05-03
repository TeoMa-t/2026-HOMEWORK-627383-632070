package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaTest {

	private Stanza stanza1;
	private Stanza stanza2;
	private Stanza stanza3;
	private Attrezzo lanterna;
	private Attrezzo osso;
	
	
	
	
	
	
	@BeforeEach
	public void setUp() throws Exception {
		
	     this.stanza1 = new Stanza("Stanza 1");
		 this.stanza2 = new Stanza("Stanza 2");
		 this.stanza3 = new Stanza("Stanza 3");
		 this.stanza1.impostaStanzaAdiacente("nord", stanza2);
		 this.stanza1.impostaStanzaAdiacente("ovest", stanza3);
		 lanterna = new Attrezzo("lanterna", 1);
		 osso = new Attrezzo("osso",1);
	}

	//---------- Test GetStanzaAdiacente ------------
	
	@Test
	public void testGetStanzaAdiacente_null() {
		
		assertNull(this.stanza1.getStanzaAdiacente("est"));
		
	}
	
	
	@Test
	public void testGetStanzaAdiacente_direzione() {
		
		assertEquals(stanza2,this.stanza1.getStanzaAdiacente("nord"));
		
	}
	
	
	@Test
	public void testGetStanzaAdiacente_sovrascrittura() {
		
		stanza1.impostaStanzaAdiacente("nord", stanza3);
		assertEquals(stanza3,this.stanza1.getStanzaAdiacente("nord"));
		
	}
	
	//------------- Test AddAttrezzo --------------

	@Test
	public void testAddAttrezzo_overload_False() {
		
		for(int i = 0; i < 10; i++) {
			
			Attrezzo attrezzoFittizio = new Attrezzo("oggetto" + i, 1);
			this.stanza1.addAttrezzo(attrezzoFittizio);
		}
		
		
		assertFalse(this.stanza1.addAttrezzo(lanterna));
		
	}
	
	
	@Test
	public void testAddAttrezzo_StanzaVuota_True() {
		
		assertTrue(this.stanza1.addAttrezzo(lanterna));
		
	}
	
	
	@Test
	public void testAddAttrezzo_StanzaConAltroOggetto_True() {
		
		Attrezzo NuovoOggetto = new Attrezzo("NuovoOggetto",1);
		this.stanza1.addAttrezzo(NuovoOggetto);
		assertTrue(this.stanza1.addAttrezzo(lanterna));
		
	}

	//------------- Test HasAttrezzo ------------------

	@Test
	public void testHasAttrezzo_ArrayVuoto_False() {
		
		assertFalse(this.stanza1.hasAttrezzo("lanterna"));
	     
	}
	
	@Test
	public void testHasAttrezzo_ArrayConOggettoDiverso_False() {
		
		this.stanza1.addAttrezzo(osso);
		assertFalse(this.stanza1.hasAttrezzo("lanterna"));
	     
	}
	
	@Test
	public void testHasAttrezzo_ArrayConOggetto_True() {
		this.stanza1.addAttrezzo(lanterna);
		assertTrue(this.stanza1.hasAttrezzo("lanterna"));
	     
	}

}
