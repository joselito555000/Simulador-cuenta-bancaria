package fp;


public class CuentaCorriente extends CuentaBancaria {
	
	private double limiteCredito;

    public CuentaCorriente(String numeroCuenta, double saldoInicial, double limiteCredito) {
        super(numeroCuenta, saldoInicial);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void retirar(double cantidad) throws SaldoInsuficienteException, CantidadInvalidaException {
        if (cantidad <= 0) {
            throw new CantidadInvalidaException("La cantidad a retirar debe ser mayor a cero.");
        }
        if ((getSaldo() + limiteCredito) < cantidad) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro, incluso con el límite de crédito.");
        }
        double saldoRestante = getSaldo() - cantidad;
        super.retirar(cantidad);
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Cuenta Corriente: " + getNumeroCuenta());
        System.out.println("Saldo: " + getSaldo());
        System.out.println("Límite de Crédito: " + limiteCredito);
    }
}


