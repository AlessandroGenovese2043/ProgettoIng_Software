public class ContoSilver extends ContoCorrente{
    private int costoannuo=20;
    public ContoSilver(double saldo) {
        super(saldo);

    }

    public int getCostoannuo() {
        return costoannuo;
    }

    public void setCostoannuo(int costoannuo) {
        this.costoannuo = costoannuo;
    }

    @Override
    public String toString() {
        return "ContoSilver{" + super.toString() +
                "costoannuo=" + costoannuo +
                '}';
    }
}
