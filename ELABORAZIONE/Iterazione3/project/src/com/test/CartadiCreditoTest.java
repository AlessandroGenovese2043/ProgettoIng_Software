package com.test;

import com.project.CartadiCredito;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CartadiCreditoTest {
    @Test
    void generaNumeroCartaePin(){
        CartadiCredito carta = new CartadiCredito(LocalDate.now().plusYears(5));
        assertNotNull(carta.getNumeroCarta());
        assertNotNull(carta.getPinCarta());
        assertNotNull(carta.getScadenza());
    }

}