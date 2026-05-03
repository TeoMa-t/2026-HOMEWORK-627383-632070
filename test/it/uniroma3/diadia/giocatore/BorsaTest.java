package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorsaTest {

	Borsa borsa;
	Attrezzo attrezzoTest,attrezzoTestMax;

	@BeforeEach
	void setUp() throws Exception {
		this.borsa = new Borsa();
		this.attrezzoTest = new Attrezzo("attrezzoTest", 1);
		this.attrezzoTestMax = new Attrezzo("attrezzoTestMax",this.borsa.getPesoMax() + 1);
	}

	//-------- Test Metodo AddAttrezzo ----------
	@Test
	void testAddAttrezzo_BorsaPiena_False() {
		int i = 0;
		while(!this.borsa.isFull()) {
			Attrezzo attrezzo = new Attrezzo("oggetto" + i,1);
			this.borsa.addAttrezzo(attrezzo);
			i++;
		}

		assertFalse(this.borsa.addAttrezzo(attrezzoTest));

	}

	@Test
	void testAddAttrezzo_BorsaPesante_False() {

		assertFalse(this.borsa.addAttrezzo(attrezzoTestMax));

	}

	@Test
	void testAddAttrezzo_BorsaVuota_True() {
		assertTrue(this.borsa.addAttrezzo(attrezzoTest));
	}

	@Test
	void testAddAttrezzo_BorsaConAltriOggetti_True() {
		this.borsa.addAttrezzo(attrezzoTest);
		assertTrue(this.borsa.addAttrezzo(attrezzoTest));
	}

	//---------- Test Metoodo IsEmpty -------------
	@Test
	void testIsEmpty_False() {
		this.borsa.addAttrezzo(attrezzoTest);
		assertFalse(this.borsa.isEmpty());
	}
	@Test
	void testIsEmpty_True() {
		assertTrue(this.borsa.isEmpty());
	}


	//----------- Test Metodo hasAttrezzo --------------
	@Test
	void testHasAttrezzo_Borsavuota_False() {
		assertFalse(this.borsa.hasAttrezzo("attrezzoTest"));
	}
	@Test
	void testHasAttrezzo_BorsaPiena_False() {
		int i = 0;
		while(!this.borsa.isFull()) {
			Attrezzo attrezzo = new Attrezzo("oggetto" + i,1);
			this.borsa.addAttrezzo(attrezzo);
			i++;
		}
		assertFalse(this.borsa.hasAttrezzo("attrezzoTest"));
	}

	@Test
	void testHasAttrezzo_Borsa_True() {
		this.borsa.addAttrezzo(attrezzoTest);
		assertTrue(this.borsa.hasAttrezzo("attrezzoTest"));
	}

	//--------- Test Metodoo removeAttrezzo ----------
	@Test
	void testRemoveAttrezzo_BorsaVuota_Null() {
		assertNull(this.borsa.removeAttrezzo("attrezzoTest"));
	}

	@Test
	void testRemoveAttrezzo_BorsaPiena_Null() {
		int i = 0;
		while(!this.borsa.isFull()) {
			Attrezzo attrezzo = new Attrezzo("oggetto" + i,1);
			this.borsa.addAttrezzo(attrezzo);
			i++;
		}
		assertNull(this.borsa.removeAttrezzo("attrezzoTest"));
	}

	@Test
	void testRemoveAttrezzo_BorsaConOggetto_Oggetto() {
		this.borsa.addAttrezzo(attrezzoTest);
		assertEquals(attrezzoTest,this.borsa.removeAttrezzo("attrezzoTest"));
	}

}
