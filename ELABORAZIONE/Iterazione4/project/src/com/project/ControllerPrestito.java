package com.project;

import java.util.ArrayList;
import java.util.List;

public class ControllerPrestito {
    private static ControllerPrestito controllerPrestito;
    private final RegistroPrestito registroPrestito;
    private Prestito prestitoCorrente;

    public ControllerPrestito() {
        this.registroPrestito = RegistroPrestito.getInstance();
    }

    public static ControllerPrestito getInstance() {
        if (controllerPrestito == null)
            controllerPrestito = new ControllerPrestito();
        else
            System.out.println("Istanza già creata");
        return controllerPrestito;
    }

    public Prestito getPrestitoCorrente() {
        return prestitoCorrente;
    }

    public void getCondizioni(ContoCorrente conto, TipoPrestito tipoPrestito, double ammontare, double stipendioCliente) throws Exception {
        double tassoInt = conto.getTassoInt();
        calcolaCondizioni(tipoPrestito, ammontare,stipendioCliente,tassoInt);


    }

    private void calcolaCondizioni(TipoPrestito tipoPrestito, double ammontare, double stipendio, double tassoInt) throws Exception {
        if(tipoPrestito == TipoPrestito.ANNI10){
            if(ammontare <= 3*stipendio){
                System.out.println("Il prestito può essere concesso per 10 anni per un ammontare di: "+ ammontare +" euro");
                System.out.println("Il tasso di interesse sarà del: " + tassoInt+"%");
                double interessi = ammontare*(tassoInt/100)*10;
                System.out.println("Interessi dopo 10 anni: "+ interessi+ "euro");
                this.prestitoCorrente = new Prestito(10,tassoInt,ammontare);
            }
            else{
                throw new Exception("Non è possibile concedere il prestito, bisogna diminuire l'ammontare, al massimo possiamo concedere: " + 3*stipendio + " euro");
            }
        }
        if(tipoPrestito == TipoPrestito.ANNI20){
            if(ammontare <= 6*stipendio){
                System.out.println("Il prestito può essere concesso per 20 anni per un ammontare di: "+ ammontare +" euro");
                System.out.println("Il tasso di interesse sarà del: " + tassoInt+"%");
                double interessi = ammontare*(tassoInt/100)*20;
                System.out.println("Interessi dopo 20 anni: "+ interessi + "euro");
                this.prestitoCorrente = new Prestito(20,tassoInt,ammontare);
            }
            else{
                throw new Exception("Non è possibile concedere il prestito, bisogna diminuire l'ammontare, al massimo possiamo concedere: " + 6*stipendio + " euro");
            }
        }
    }
    public void confermaPrestito(ContoCorrente conto, Cliente cliente) throws Exception {
        if(conto != null && cliente != null){
           prestitoCorrente.setCliente(cliente);
           prestitoCorrente.setConto(conto);
           Prestito p = registroPrestito.getPrestito(cliente.getTelefono());
           if(p != null){
                throw new Exception("Impossibile concedere un nuovo prestito perchè risulta che il cliente abbia già un prestito attivo");
           }
           registroPrestito.add(prestitoCorrente);
           conto.setSaldo(prestitoCorrente.getAmmontare()+conto.getSaldo()); //aggiornamento del saldo dopo il prestito confermato
        }
        else{
            throw new Exception("Impossibile completare l'operazione di conferma del Prestiti");
        }
    }

    public void stampaPrestiti() throws Exception {
        List<Prestito> lista_prestiti = registroPrestito.getListaPrestiti();
        if(lista_prestiti.size() == 0){
            throw new Exception("La banca non ha ancora concesso nessun prestito");
        }
        else{
            for(Prestito p: lista_prestiti){
                System.out.println(p.toString());
            }
        }
    }
}
