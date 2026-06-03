package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class CaricatoreLabirintoTest {

    @Test
    public void testCaricatoreMonolocale() throws Exception {
        // Fixture monolocale (1 stanza, 1 attrezzo, 0 uscite)
        String labirinto = 
            "Stanze: atrio\n" +
            "Inizio: atrio\n" +
            "Vincente: atrio\n" +
            "Attrezzi: spada 5 atrio\n" +
            "Uscite:\n"; // Vuoto ma la riga deve esserci
        
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirinto));
        caricatore.carica();
        
        assertEquals("atrio", caricatore.getLabirinto().getStanzaIniziale().getNome());
        assertEquals("atrio", caricatore.getLabirinto().getStanzaVincente().getNome());
        assertTrue(caricatore.getLabirinto().getStanzaIniziale().hasAttrezzo("spada"));
    }
    
    @Test
    public void testCaricatoreBilocale() throws Exception {
        // Fixture bilocale (2 stanze, 0 attrezzi, 2 uscite incrociate)
        String labirinto = 
            "Stanze: atrio, biblioteca\n" +
            "Inizio: atrio\n" +
            "Vincente: biblioteca\n" +
            "Attrezzi: \n" +
            "Uscite: atrio nord biblioteca, biblioteca sud atrio\n";
        
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirinto));
        caricatore.carica();
        
        assertEquals("atrio", caricatore.getLabirinto().getStanzaIniziale().getNome());
        assertEquals("biblioteca", caricatore.getLabirinto().getStanzaVincente().getNome());
        
        // Verifica dei collegamenti
        assertEquals("biblioteca", caricatore.getLabirinto().getStanzaIniziale().getStanzaAdiacente("nord").getNome());
        assertEquals("atrio", caricatore.getLabirinto().getStanzaVincente().getStanzaAdiacente("sud").getNome());
    }
}