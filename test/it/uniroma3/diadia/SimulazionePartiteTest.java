package it.uniroma3.diadia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.ambienti.Labirinto;

public class SimulazionePartiteTest {

	private Map<String,List<String>> log;

	private IOSimulator io;
	private Labirinto labirinto;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaFinale = "Uscita";
	private DiaDia gioco;


	private Map<String,List<String>> eseguiSimulazionePartita(Labirinto labirinto, List<String> comandiDaEseguire) {

		io = new IOSimulator(comandiDaEseguire);
		gioco = new DiaDia(labirinto, io);
		try {
			gioco.gioca(); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return io.getLogMessaggi();
	}

	@Test
	public void PartitaVintaPrimaMossaTest(){

		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaFinale)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaFinale, "nord")
				.getLabirinto();
		
		log = eseguiSimulazionePartita(labirinto, Arrays.asList("vai nord"));
		assertTrue(log.get("vai nord").toString().contains("Hai vinto!"));
	}

	@Test
	public void PartitaVintaTreStanzeTest() {

		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("Soggiorno")
				.addAdiacenza(nomeStanzaIniziale, "Soggiorno", "nord")
				.addAdiacenza("Soggiorno", nomeStanzaIniziale, "sud")
				.addStanzaVincente(nomeStanzaFinale)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaFinale, "est")
				.getLabirinto();

		log = eseguiSimulazionePartita(labirinto, Arrays.asList("vai nord","vai sud","vai est"));
		assertTrue(log.get("vai nord").toString().contains("Soggiorno"));
		assertTrue(log.get("vai sud").toString().contains(nomeStanzaIniziale));
		assertTrue(log.get("vai est").toString().contains(nomeStanzaFinale));
	}

	@Test
	public void PartitaConOggettoLuminosoVinta() {

		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("Magazzino")
				.addAttrezzo("Lanterna", 1)
				.addAdiacenza(nomeStanzaIniziale, "Magazzino", "est")
				.addAdiacenza("Magazzino", nomeStanzaIniziale, "ovest")
				.addStanzaBuia("Scantinato", "Lanterna")
				.addAttrezzo("Strumento", 1)
				.addAdiacenza(nomeStanzaIniziale, "Scantinato", "nord")
				.addAdiacenza("Scantinato", nomeStanzaIniziale, "sud")
				.addStanzaVincente(nomeStanzaFinale)
				.addAdiacenza("Scantinato", nomeStanzaFinale, "nord")
				.getLabirinto();

		log = eseguiSimulazionePartita(labirinto, Arrays.asList("vai nord","guarda","vai sud","vai est","prendi",
				"prendi Lanterna","vai ovest", "vai nord","posa Lanterna","guarda","prendi Strumento","vai nord"));
		
		assertTrue(log.get("vai nord").toString().contains("Scantinato"));
		assertTrue(log.get("guarda").toString().contains("qui c'è un buio pesto"));
		assertTrue(log.get("vai sud").toString().contains(nomeStanzaIniziale));
		assertTrue(log.get("vai est").toString().contains("Magazzino"));
		assertTrue(log.get("prendi").toString().contains("Che oggetto vuoi prendere?"));
		assertTrue(log.get("prendi Lanterna").toString().contains("Attrezzo messo nella borsa!"));
		assertTrue(log.get("vai ovest").toString().contains(nomeStanzaIniziale));
		assertTrue(log.get("posa Lanterna").toString().contains("Oggetto posato"));
		assertTrue(log.get("guarda").toString().contains(labirinto.getStanza("Scantinato").getDescrizione()));
		assertTrue(log.get("prendi Strumento").toString().contains("Attrezzo messo nella borsa!"));
		assertTrue(log.get("vai nord").toString().contains("Hai vinto!"));
		
	}
}