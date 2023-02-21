package com.project;

public class ControllerPrestito {
    private static ControllerPrestito controllerPrestito;
    private RegistroPrestito registroPrestito;
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
                System.out.println("Interessi dopo 10 anni: "+ interessi);
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
                System.out.println("Interessi dopo 20 anni: "+ interessi);
                this.prestitoCorrente = new Prestito(20,tassoInt,ammontare);
            }
            else{
                throw new Exception("Non è possibile concedere il prestito, bisogna diminuire l'ammontare, al massimo possiamo concedere: " + 6*stipendio + " euro");
            }
        }
    }
    public void confermaOperazione(ContoCorrente conto, Cliente cliente){
        if(conto != null && cliente != null){
           prestitoCorrente.setCliente(cliente);
           registroPrestito.add(prestitoCorrente);
        }
        else{
            System.err.println("Impossibile completare l'operazione di conferma del Prestiti");
        }
    }
}
