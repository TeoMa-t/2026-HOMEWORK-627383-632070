package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


class ComandoVaiTest {
	
	private Partita partita;
	private ComandoVai comandoVai;
	private Stanza partenza;
	private Stanza destinazione;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.partita = new Partita();
		this.comandoVai = new ComandoVai();
		this.partenza = new Stanza("Aula N10");
		this.destinazione = new Stanza("Laboratorio");
		
		this.partenza.impostaStanzaAdiacente("nord", destinazione);
		this.partita.setStanzaCorrente(partenza);
	}

	@Test
	public void testSpostamentoInStanzaEsistente() {
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(this.partita);
		assertEquals("Laboratorio", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testSpostamentoInStanzaNonEsistente() {
		this.comandoVai.setParametro("Ovest");
		this.comandoVai.esegui(this.partita);
		assertEquals("Aula N10", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testSpostamentoSenzaDirezione() {
		this.comandoVai.setParametro(null);
		this.comandoVai.esegui(this.partita);
		assertEquals("Aula N10", this.partita.getStanzaCorrente().getNome());
	}
}
