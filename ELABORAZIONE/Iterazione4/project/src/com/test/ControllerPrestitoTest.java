package com.test;

import com.project.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPrestitoTest {
    static ControllerPrestito controllerPrestito;
    static ContoCorrente conto;
    static Cliente cliente;
    static RegistroPrestito registroPrestito;
    @BeforeAll
    static void initTest() {
        controllerPrestito = ControllerPrestito.getInstance();
        conto = new ContoSilver(100);
        cliente = new Cliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464998");
        registroPrestito = RegistroPrestito.getInstance();
    }

    @Test
    void getCondizioni() {
        try {
            controllerPrestito.getCondizioni(conto, TipoPrestito.ANNI10, 50000, 20000);
            assertNotNull(controllerPrestito.getPrestitoCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            controllerPrestito.getCondizioni(conto, TipoPrestito.ANNI10, 10000, 3000);
            fail("Unexpected exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            controllerPrestito.getCondizioni(conto, TipoPrestito.ANNI20, 100000, 30000);
            assertNotNull(controllerPrestito.getPrestitoCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            controllerPrestito.getCondizioni(conto, TipoPrestito.ANNI20, 100000, 3000);
            fail("Unexpected exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void confermaPrestito() {
        try {
            controllerPrestito.getCondizioni(conto, TipoPrestito.ANNI10, 50000, 20000);
            controllerPrestito.confermaPrestito(conto, cliente);
            assertEquals(controllerPrestito.getPrestitoCorrente().getCliente(), cliente);
            assertEquals(controllerPrestito.getPrestitoCorrente().getConto(), conto);
            assertEquals(1, registroPrestito.getElencoPrestiti().size());
            assertEquals(controllerPrestito.getPrestitoCorrente(), registroPrestito.getElencoPrestiti().get(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Unexpected exception");
        }
        try {
            controllerPrestito.getCondizioni(conto, TipoPrestito.ANNI10, 50000, 20000);
            controllerPrestito.confermaPrestito(conto, cliente);
            fail("Unexpected exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}