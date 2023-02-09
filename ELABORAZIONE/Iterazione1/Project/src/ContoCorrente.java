import java.time.LocalDate;

public class ContoCorrente {
    private static int numberIban= 100000;
    private final String firstLetter = "IT";
    private String IBAN;
    private double saldo;

    private CartadiCredito cartaAssociata;
    public ContoCorrente(double saldo) {
        this.saldo = saldo;
        associaCartaCredito();
        associaIBAN();
    }

    private void associaIBAN() {
        String number = ("" + numberIban);
        numberIban++;
        IBAN = firstLetter.concat(number);
    }

    private void associaCartaCredito(){
        LocalDate today = LocalDate.now();
        today = today.plusYears(5);
        cartaAssociata = new CartadiCredito(today);
    }

    public String getIBAN() {
        return IBAN;
    }

    public double getSaldo() {
        return saldo;
    }
    public CartadiCredito getCartaAssociata() {
        return cartaAssociata;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "ContoCorrente{" +
                "IBAN='" + IBAN + '\'' +
                ", saldo=" + saldo +
                ", cartaAssociata=" + cartaAssociata +
                '}';
    }
}
