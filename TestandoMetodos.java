package view;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;

import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

class TestandoMetodos {
	
	
	@Test
	void test() {
		String data = "14/02/1960";
		CadPacientes c = new CadPacientes();
		assertEquals("63", c.atualizarDivisao(data));
	}

}
