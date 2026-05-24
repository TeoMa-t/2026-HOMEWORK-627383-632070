package it.uniroma3.diadia.comandi;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FabbricaComandiRiflessivaTest {

private FabbricaDiComandiRiflessiva fabbrica;
	
	@BeforeEach
	public void setUp() {
		this.fabbrica = new FabbricaDiComandiRiflessiva();
	}
	
	@Test
	public void testComandoVai() throws Exception {
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals("vai", comando.getNome());
		assertEquals("nord", comando.getParametro());
	}
	
	@Test
	public void testComandoPrendi() throws Exception {
		Comando comando = fabbrica.costruisciComando("prendi osso");
		assertEquals("prendi", comando.getNome());
		assertEquals("osso", comando.getParametro());
	}
	
	@Test
	public void testComandoFine() throws Exception {
		Comando comando = fabbrica.costruisciComando("fine");
		assertEquals("fine", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoVuoto() throws Exception {
		Comando comando = fabbrica.costruisciComando("");
		
		assertEquals("Comando sconosciuto", comando.getNome());
	}
}
