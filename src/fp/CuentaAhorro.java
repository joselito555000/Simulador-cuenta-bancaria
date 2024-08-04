package fp;


public class CuentaAhorro extends CuentaBancaria {
	private double tasaIntereses;
	
	public CuentaAhorro(double tasaIntereses,double saldoInicial,String numeroCuenta) {
		super(numeroCuenta,saldoInicial);
		this.tasaIntereses = tasaIntereses;
	}
		
		public void aplicarInteres() {
	        double interes = getSaldo() * tasaIntereses / 100;
	        try {
	            depositar(interes);
	        } catch (CantidadInvalidaException e) {
	            e.printStackTrace(); // Manejo básico de excepciones
	        
	        }
		}
		public void mostrarDetalles() {
	        System.out.println("Cuenta Ahorro: " + getNumeroCuenta());
	        System.out.println("Saldo: " + getSaldo());
	        System.out.println("Tasa de Interés: " + tasaIntereses + "%");
	    }
	

}
