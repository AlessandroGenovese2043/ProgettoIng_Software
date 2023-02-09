public class ContoGold extends ContoCorrente{
    private int costoannuo;

    public ContoGold(double saldo, int costoannuo) {
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
        return "ContoGold{" + super.toString() +
                "costoannuo=" + costoannuo +
                '}';
    }
}
