package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;

import org.junit.jupiter.api.Test;
 
public class CaricatoreLabirintoTest {

    @Test
    public void testCaricatoreMonolocale() throws Exception {
        // Aggiunti TUTTI i marker nell'ordine esatto richiesto dal metodo carica()
        String labirinto = 
            "Stanze: atrio\n" +
            "StanzeMagiche:\n" +
            "StanzeBuie:\n" +
            "StanzeBloccate:\n" +
            "Inizio: atrio\n" +
            "Vincente: atrio\n" +
            "Attrezzi: spada 5 atrio\n" +
            "Uscite:\n" +
            "Maghi:\n" +
            "Cani:\n" +
            "Streghe:\n";
        
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirinto));
        caricatore.carica();
        
        assertEquals("atrio", caricatore.getLabirinto().getStanzaIniziale().getNome());
        assertEquals("atrio", caricatore.getLabirinto().getStanzaVincente().getNome());
        assertTrue(caricatore.getLabirinto().getStanzaIniziale().hasAttrezzo("spada"));
    }
    
    @Test
    public void testCaricatoreBilocale() throws Exception {
        // Aggiunti TUTTI i marker nell'ordine esatto richiesto dal metodo carica()
        String labirinto = 
            "Stanze: atrio, biblioteca\n" +
            "StanzeMagiche:\n" +
            "StanzeBuie:\n" +
            "StanzeBloccate:\n" +
            "Inizio: atrio\n" +
            "Vincente: biblioteca\n" +
            "Attrezzi: \n" +
            "Uscite: atrio nord biblioteca, biblioteca sud atrio\n" +
            "Maghi:\n" +
            "Cani:\n" +
            "Streghe:\n";
        
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirinto));
        caricatore.carica();
        
        assertEquals("atrio", caricatore.getLabirinto().getStanzaIniziale().getNome());
        assertEquals("biblioteca", caricatore.getLabirinto().getStanzaVincente().getNome());
        
        assertEquals("biblioteca", caricatore.getLabirinto().getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
        assertEquals("atrio", caricatore.getLabirinto().getStanzaVincente().getStanzaAdiacente(Direzione.SUD).getNome());
    }
}