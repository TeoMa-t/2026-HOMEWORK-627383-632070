package diadia;
import it.uniroma3.diadia.giocatore.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {

	Giocatore giocatore1;
	Giocatore giocatore2;
	
	@BeforeEach
	void setUp() throws Exception {
		this.giocatore1 = new Giocatore();
		this.giocatore2 = new Giocatore(30);
	}

	
	//---------- Test Metodo SetCfu ---------
	@Test
	void testSetCfu_StaticValue_True() {
		assertEquals(20,this.giocatore1.getCfu());
	}
	
	@Test
	void testSetCfu_IntValue_True() {
		assertEquals(30,this.giocatore2.getCfu());
	}
	
	

}
