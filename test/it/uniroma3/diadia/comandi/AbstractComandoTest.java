package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class AbstractComandoTest {

	private AbstractComando comandoAstratto;
	
	private class comandoFinto extends AbstractComando{
		
		@Override
		public void esegui(Partita partita, IO io) {
			
		}
		@Override
		public String getNome() {
			return "finto";
		}
	}
	
	@BeforeEach
	public void setUp() {
		this.comandoAstratto = new comandoFinto();
	}
	
	@Test
	public void testSetGetParametro() {
		this.comandoAstratto.setParametro("Attrezzo");
		assertEquals("Attrezzo",this.comandoAstratto.getParametro());
	}
	@Test
	public void testParametroNull() {
		assertNull(this.comandoAstratto.getParametro());
	}

}
