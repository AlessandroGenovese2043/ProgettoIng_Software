

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaBancario {
    private static SistemaBancario sistemabancario;
    private ContoCorrente corrente;
    private Cliente clientecorrente;
    private Map<ContoCorrente,Cliente>elenco;

    public SistemaBancario() {
        this.elenco=new HashMap<>();
    }

    public static SistemaBancario getInstance() {
        if (sistemabancario == null)
            sistemabancario = new SistemaBancario();
        else
            System.out.println("Istanza già creata");
        return sistemabancario;
    }


    public ContoCorrente getCorrente() {
        return corrente;
    }

    public Map<ContoCorrente, Cliente> getElenco() {
        return elenco;
    }

    public List<Cliente> getElencoCliente() {
        List<Cliente> listCliente = new ArrayList<>();
        listCliente.addAll(elenco.values());
        return listCliente;
    }
    public List<ContoCorrente> getElencoConto() {
        List<ContoCorrente> listConto = new ArrayList<>();
        listConto.addAll(elenco.keySet());
        return listConto;
    }

    @Override
    public String toString() {
        return "SistemaBancario{" +
                ", elenco=" + elenco +
                '}';
    }

    public void inserisciNuovoCliente(String nome, String cognome,LocalDate datanascita,String telefono)
    {
        this.clientecorrente= new Cliente(nome,cognome,datanascita,telefono);
    }
    public void inserisciConto(int tipologia,double saldo)
    {

        switch(tipologia)
        {
            case 1:
                this .corrente= new ContoSilver(saldo);
                break;

            case 2:
                this .corrente= new ContoGold(saldo);
                break;

            case 3:
                this .corrente= new ContoPlatinum(saldo);
                break;

            default:
                System.out.println("Indicare una delle 3 tipologie indicate");


        }
    }

    public void confermaOperazione()
    {
        if(clientecorrente!= null && corrente!=null)
        {
            this.elenco.put(corrente,clientecorrente);
            System.out.println("Operazione completata con successo!!");
        }

    }

}
