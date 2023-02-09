public class ContoPlatinum extends ContoCorrente{
    private int costoannuo;

    public ContoPlatinum(double saldo, int costoannuo) {
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
        return "ContoPlatinum{" + super.toString() +
                "costoannuo=" + costoannuo +
                '}';
    }
}
