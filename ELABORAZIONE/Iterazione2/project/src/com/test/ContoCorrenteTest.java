package com.test;

import com.project.ContoCorrente;
import com.project.Deposito;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class ContoCorrenteTest {

    @Test
    void associaIBANeCarta(){
        ContoCorrente conto = new ContoCorrente(10);
        assertNotNull(conto.getIBAN());
        assertNotNull(conto.getCartaAssociata());

    }

    @Test
    void aggiornaSaldo() {
        ContoCorrente conto = new ContoCorrente(10);
        conto.aggiornaSaldo(5, "deposito");
        assertEquals(15 - Deposito.getCommissione(), conto.getSaldo());
        double c = conto.getSaldo();
        conto.aggiornaSaldo(5, "prelievo");
        assertEquals(c-5, conto.getSaldo());
    }
}