package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DiaDiaTest {

	@Test
	public void testPartitaConComandoFine() {
		// 1. Prepariamo i comandi da dare in pasto al gioco
		List<String> comandiDaEseguire = new ArrayList<>();
		comandiDaEseguire.add("fine");
		
		// 2. Creiamo il finto I/O passandogli la lista di comandi
		IOSimulator io = new IOSimulator(comandiDaEseguire);
		
		// 3. Facciamo partire il gioco passandogli il finto I/O
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		// 4. Verifichiamo l'output!
		// Sappiamo che quando si digita "fine", il gioco stampa "Grazie di aver giocato!"
		// Usiamo il metodo contains per vedere se in TUTTI i messaggi salvati c'è quello di fine.
		boolean messaggioFineTrovato = false;
		for(String messaggio : io.getMessaggiProdotti()) {
			if(messaggio.contains("Grazie per aver giocato")) {
				messaggioFineTrovato = true;
				break;
			}
		}
		
		assertTrue(messaggioFineTrovato);
	}
}