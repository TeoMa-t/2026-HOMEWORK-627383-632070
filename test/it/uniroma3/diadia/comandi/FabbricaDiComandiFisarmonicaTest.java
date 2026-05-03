package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbrica;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	public void testComandoVai() {
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals("vai", comando.getNome());
		assertEquals("nord", comando.getParametro());
	}
	
	@Test
	public void testComandoPrendi() {
		Comando comando = fabbrica.costruisciComando("prendi osso");
		assertEquals("prendi", comando.getNome());
		assertEquals("osso", comando.getParametro());
	}
	
	@Test
	public void testComandoFine() {
		Comando comando = fabbrica.costruisciComando("fine");
		assertEquals("fine", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoNonValido() {
		Comando comando = fabbrica.costruisciComando("comandoInesistente");
		assertEquals("Comando sconosciuto", comando.getNome());
	}
	
	@Test
	public void testComandoVuoto() {
		Comando comando = fabbrica.costruisciComando("");
		assertEquals("Comando sconosciuto", comando.getNome());
	}
}
