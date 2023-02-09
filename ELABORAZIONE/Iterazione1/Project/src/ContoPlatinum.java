public class ContoPlatinum extends ContoCorrente{
    private int costoannuo=60;

    public ContoPlatinum(double saldo) {
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
        return "ContoPlatinum{" + super.toString() +
                "costoannuo=" + costoannuo +
                '}';
    }
}
