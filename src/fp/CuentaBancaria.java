package fp;


public abstract class CuentaBancaria {
	//ATRIBUTOS
	private Double saldo;
	private String numeroCuenta;
	
	//CONSTRUCTOR QUE RECIBE COMO PARAMETROS LOS DOS ATRIBUTOS
	public CuentaBancaria(String numeroCuenta,Double saldo) {
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		
	}
	//GETTERS DE LOS ATRIBUTOS
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	
	public Double getSaldo() {
		return saldo;
	}

	
	//DEPOSITAR DINERO
	
	public void depositar(double cantidad) throws CantidadInvalidaException{
		if(cantidad<=0) {
			throw new CantidadInvalidaException("La cantidad a depositar debe ser mayor a 0.");
		}
		saldo += cantidad;
	}
	public void retirar(double cantidad) throws SaldoInsuficienteException, CantidadInvalidaException{
		if(cantidad<=0) {
			throw new CantidadInvalidaException("La cantidad a retirar debe ser mayor a 0.");
		}
		if(saldo<cantidad) {
			throw new SaldoInsuficienteException("El saldo debe ser mayo que la cantidad a retirar.");
		}
		saldo -=cantidad;
	}
	public abstract void mostrarDetalles();
	
	
}
