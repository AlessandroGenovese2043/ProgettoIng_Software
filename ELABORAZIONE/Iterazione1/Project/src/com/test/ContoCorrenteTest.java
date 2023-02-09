package com.test;

import com.project.ContoCorrente;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class ContoCorrenteTest {

    @Test
    void associaIBANeCarta(){
        ContoCorrente conto = new ContoCorrente(10);
        assertNotNull(conto.getIBAN());
        assertNotNull(conto.getCartaAssociata());

    }

}