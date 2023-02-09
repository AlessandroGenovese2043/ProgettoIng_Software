public class ContoSilver extends ContoCorrente{
    private int costoannuo;
    public ContoSilver(double saldo, int costoannuo) {
        super(saldo);
        this.costoannuo = costoannuo;
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
