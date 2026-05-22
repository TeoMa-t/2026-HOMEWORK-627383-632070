package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

class BorsaTest {

	Borsa borsa;
	Borsa borsaPiena;
	Borsa borsaOrdinamento;
	
	Attrezzo attrezzoTest, attrezzoTestMax;
	Attrezzo piuma, libro, ps4, piombo;

	@BeforeEach
	void setUp() throws Exception {
		// Borsa base
		this.borsa = new Borsa();
		this.attrezzoTest = new Attrezzo("attrezzoTest", 1);
		this.attrezzoTestMax = new Attrezzo("attrezzoTestMax", this.borsa.getPesoMax() + 1);

		// Borsa piena per i casi limite
		this.borsaPiena = new Borsa();
		int i = 0;
		while(!this.borsaPiena.isFull()) {
			this.borsaPiena.addAttrezzo(new Attrezzo("oggetto" + i, 1));
			i++;
		}

		// Borsa per ordinamento
		this.borsaOrdinamento = new Borsa(30); 
		this.piuma = new Attrezzo("piuma", 1);
		this.libro = new Attrezzo("libro", 5);
		this.ps4 = new Attrezzo("ps4", 5);
		this.piombo = new Attrezzo("piombo", 10);
		
		this.borsaOrdinamento.addAttrezzo(piombo);
		this.borsaOrdinamento.addAttrezzo(ps4);
		this.borsaOrdinamento.addAttrezzo(libro);
		this.borsaOrdinamento.addAttrezzo(piuma);
	}

	//-------- Test Metodo AddAttrezzo ----------
	@Test
	void testAddAttrezzo_BorsaPiena_False() {
		assertFalse(this.borsaPiena.addAttrezzo(attrezzoTest));
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
		assertTrue(this.borsa.addAttrezzo(new Attrezzo("altro", 1))); // Evitiamo lo stesso riferimento
	}

	//---------- Test Metodo IsEmpty e IsFull -------------
	@Test
	void testIsEmpty_False() {
		assertFalse(this.borsaPiena.isEmpty());
	}
	
	@Test
	void testIsEmpty_True() {
		assertTrue(this.borsa.isEmpty());
	}

	//----------- Test Metodo hasAttrezzo --------------
	@Test
	void testHasAttrezzo_BorsaVuota_False() {
		assertFalse(this.borsa.hasAttrezzo("attrezzoTest"));
	}
	
	@Test
	void testHasAttrezzo_BorsaPiena_False() {
		assertFalse(this.borsaPiena.hasAttrezzo("attrezzoTest"));
	}

	@Test
	void testHasAttrezzo_Borsa_True() {
		this.borsa.addAttrezzo(attrezzoTest);
		assertTrue(this.borsa.hasAttrezzo("attrezzoTest"));
	}

	//--------- Test Metodo removeAttrezzo ----------
	@Test
	void testRemoveAttrezzo_BorsaVuota_Null() {
		assertNull(this.borsa.removeAttrezzo("attrezzoTest"));
	}

	@Test
	void testRemoveAttrezzo_BorsaPiena_Null() {
		assertNull(this.borsaPiena.removeAttrezzo("attrezzoTest"));
	}

	@Test
	void testRemoveAttrezzo_BorsaConOggetto_Oggetto() {
		this.borsa.addAttrezzo(attrezzoTest);
		assertEquals(attrezzoTest, this.borsa.removeAttrezzo("attrezzoTest"));
	}

	//-------- Test Metodi di Ordinamento ----------
	@Test
	void testGetContenutoOrdinatoPerPeso() {
		List<Attrezzo> ordinata = this.borsaOrdinamento.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it = ordinata.iterator();
		
		assertEquals(this.piuma, it.next());
		assertEquals(this.libro, it.next()); 
		assertEquals(this.ps4, it.next());
		assertEquals(this.piombo, it.next());
	}

	@Test
	void testGetContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> ordinata = this.borsaOrdinamento.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = ordinata.iterator();
		
		assertEquals(this.libro, it.next());
		assertEquals(this.piombo, it.next());
		assertEquals(this.piuma, it.next());
		assertEquals(this.ps4, it.next());
	}

	@Test
	void testGetContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = this.borsaOrdinamento.getContenutoRaggruppatoPerPeso();
		
		assertEquals(1, mappa.get(1).size());
		assertEquals(2, mappa.get(5).size());
		assertEquals(1, mappa.get(10).size());
		
		assertTrue(mappa.get(5).contains(this.libro));
		assertTrue(mappa.get(5).contains(this.ps4));
	}
}