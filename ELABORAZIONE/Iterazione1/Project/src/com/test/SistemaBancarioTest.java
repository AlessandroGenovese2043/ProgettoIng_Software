package com.test;


import com.project.SistemaBancario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class SistemaBancarioTest {

    static SistemaBancario sistema;

    @BeforeAll
    static void initTest() {
        sistema = sistema.getInstance();
    }
    @Test
    void inserisciNuovoCliente() {
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        assertNotNull(sistema.getClientecorrente());
    }

    @Test
    void inserisciConto() {
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        sistema.inserisciConto(2,0);
        assertNotNull(sistema.getCorrente());
    }

    @Test
    void confermaOperazione() {
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        sistema.inserisciConto(2,0);
        sistema.confermaOperazione();
        assertEquals(1, sistema.getElenco().size());
        assertNotNull(sistema.getElenco().keySet());

    }
}