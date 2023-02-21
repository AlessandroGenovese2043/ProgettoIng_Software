package com.project;

public enum TipoSettore {

    AZIONITECH,
    AZIONI,
    CRIPTOVALUTE,
    MATERIEPRIME,
    VALUTE,
    ETF,
    NTF;

    TipoSettore() {
    }

    @Override
    public String toString() {
        return "TipoSettore{}";
    }
}
