package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class DiaDiaTest {

	@Test
	public void testPartitaConComandoFine() throws Exception {
		// 1. Prepariamo i comandi da dare in pasto al gioco
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("fine");
		
		// 2. Creiamo il finto I/O passandogli la lista di comandi
		IOSimulator io = new IOSimulator(comandiDaEseguire);
		
		// 3. LA SOLUZIONE DEFINITIVA!
		// Usiamo il Factory Method statico (newBuilder) e il Builder annidato 
		// per creare un labirinto minimo che non faccia crashare il gioco
		Labirinto labirintoMinimo = Labirinto.newBuilder()
				.addStanzaIniziale("AtrioFinto")
				.getLabirinto();
		
		// 4. Facciamo partire il gioco passandogli il labirinto appena costruito
		DiaDia gioco = new DiaDia(labirintoMinimo, io);
		gioco.gioca();
		
		// 5. Verifichiamo l'output!
		boolean messaggioFineTrovato = false;
		for(String messaggio : io.getMessaggiProdotti()) {
			// N.B: Assicurati che la frase sia ESATTAMENTE quella stampata dal tuo ComandoFine
			if(messaggio.contains("Grazie per aver giocato")) { 
				messaggioFineTrovato = true;
				break;
			}
		}
		
		assertTrue(messaggioFineTrovato);
	}
}