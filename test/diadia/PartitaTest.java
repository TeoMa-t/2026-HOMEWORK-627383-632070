package diadia;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {

	private Partita partita;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.partita = new Partita();
	}
	
	//---------- Test Metodo vinta() ---------------

	@Test
	public void testVinta_InizioPartita_False() {
		/*
		 * Test Vinta
		 * Il test verifica che il metodo Vinta
		 * a partita appena iniziata
		 * restituisca False
		 */
		assertFalse(this.partita.vinta());
	}

	@Test
	public void testVinta_StanzaSbagliata_False() {
		/* 
		 * Test Vinta
		 * Il test verifica che il metodo vinta
		 * a seguito di uno spostamento in una stanza non vincente
		 * restituisca False
		 */
		Stanza stanzaSbagliata = new Stanza("Aula N8");
		this.partita.setStanzaCorrente(stanzaSbagliata);
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_StanzaVincente_true() {
		/*
		 * Test Vinta
		 * Il test verifica che il metodo vinta
		 * a seguito dello spostamento nella stanza vincente
		 * restituisce True
		 */
	 this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
	 assertTrue(this.partita.vinta());
	}
	
	
	//------------ Test metodo IsFinita() --------------
	@Test
	public void testIsFinita_StanzaVincente_True() {
		/*
		 * Test IsFinita
		 * Il test verifica che il metodo isFinita
		 * a seguito dello spostamento nella stanza vincente
		 * restituisca True
		 */
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		 assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_FineVite_True() {
		/*
		 *Test IsFinita
		 *Il test verifica che il metodo isFinita
		 *a seguito della fine delle vite
		 *restituisa True 
		 */
		this.partita.setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_UtenteTerminaLaPartita_True() {
		/*
		 * Test IsFinita
		 * Il test verifica che il metodo isFinita
		 * a seguito della fine della partita
		 * restituisca True
		 */
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}

}
