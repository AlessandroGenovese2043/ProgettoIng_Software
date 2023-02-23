package com.test;


import com.project.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SistemaBancarioTest {

    static SistemaBancario sistema;
    static ConsulenteFinanziario consulenteFinanziario;
    @BeforeAll
    static void initTest() {
        sistema = sistema.getInstance();
    }
    @AfterEach
    void afterTest(){
        sistema.getElenco().clear();
        sistema.getElencoClienti().clear();
    }
    @BeforeEach
    void beforeTest(){
        sistema.getElenco().clear();
        sistema.getElencoClienti().clear();
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

    @Test
    void verificaCarta() {
        System.out.println("TEST"+sistema.getElencoClienti());
        System.out.println("TEST"+sistema.getElenco());
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        ContoCorrente conto =sistema.inserisciConto(2,45);
        sistema.confermaOperazione();
        sistema.stampa();
        assertEquals(conto, sistema.verificaCarta(conto.getCartaAssociata().getNumeroCarta()));
        assertNull(sistema.verificaCarta("10098989")); //Questo numero di carta non può essere creato
        System.out.println("TEST"+sistema.getElenco());
    }

    @Test
    void effetuaPrelievo() {
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        ContoCorrente conto =sistema.inserisciConto(2,45);
        sistema.confermaOperazione();
        double saldoAttuale = conto.getSaldo();
        sistema.effetuaPrelievo(conto.getCartaAssociata().getPinCarta(), 10, conto);
        //verifica se il saldo del conto è diminuito di 10
        assertEquals(saldoAttuale-10,conto.getSaldo());
    }
    @Test @DisplayName("Aggiunta Prelievo alla lista movimenti")
    void testListaMovimentiPrelievo() {
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        ContoCorrente conto =sistema.inserisciConto(2,45);
        sistema.confermaOperazione();
        sistema.effetuaPrelievo(conto.getCartaAssociata().getPinCarta(), 10, conto);
        assertEquals(1, conto.getLista_movimenti().size()); //elemento aggiunto
        assertEquals(conto.getLista_movimenti().get(0).getClass(), Prelievo.class); //Effettivamente un Prelievo
    }
    @Test
    void effetuaDeposito() {
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        ContoCorrente conto =sistema.inserisciConto(2,45);
        sistema.confermaOperazione();
        double saldoAttuale = conto.getSaldo();
        sistema.effetuaDeposito(conto.getCartaAssociata().getPinCarta(), 10, conto);
        //verifica se il saldo del conto è diminuito di 10
        assertEquals(saldoAttuale+10- Deposito.getCommissione(),conto.getSaldo());
    }
    @Test @DisplayName("Aggiunta Deposito alla lista movimenti")
    void testListaMovimentiDeposito() {
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        ContoCorrente conto =sistema.inserisciConto(2,45);
        sistema.confermaOperazione();
        sistema.effetuaDeposito(conto.getCartaAssociata().getPinCarta(), 10, conto);
        assertEquals(1, conto.getLista_movimenti().size()); //elemento aggiunto
        assertEquals(conto.getLista_movimenti().get(0).getClass(), Deposito.class); //Effettivamente un Deposito
    }

    @Test
    void richiediConsulente() {
        List<ConsulenteFinanziario> lista;
        try {
            sistema.loadConsulenti();
            lista = sistema.richiediConsulente(TipoSettore.AZIONI);
            System.out.println(lista);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
    @Test
    void confermaConsulente() {
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464998");
        sistema.inserisciConto(2,0);
        sistema.confermaOperazione();
        sistema.loadConsulenti();
        try {
            sistema.confermaConsulente(1, "3546499");
            fail("Exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            sistema.confermaConsulente(1, "35464998");
            consulenteFinanziario = sistema.getElencoConsulenti().get(1);
            assertEquals(1,consulenteFinanziario.getListaClienti().size());
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}