package fp;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimuladorBanco {
	private static Map<String, CuentaBancaria> cuentas = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    crearCuenta();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarRetiro();
                    break;
                case 4:
                    consultarSaldo();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== Simulador de Banco ===");
        System.out.println("1. Crear Cuenta");
        System.out.println("2. Realizar Depósito");
        System.out.println("3. Realizar Retiro");
        System.out.println("4. Consultar Saldo");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void crearCuenta() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Ingrese el saldo inicial: ");
        double saldoInicial = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea
        System.out.println("Seleccione el tipo de cuenta (1. Ahorro, 2. Corriente): ");
        int tipoCuenta = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        switch (tipoCuenta) {
            case 1:
                System.out.print("Ingrese la tasa de interés (%): ");
                double tasaInteres = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea
                cuentas.put(numeroCuenta, new CuentaAhorro(tasaInteres, saldoInicial, numeroCuenta));
                System.out.println("Cuenta de ahorro creada exitosamente.");
                break;
            case 2:
                System.out.print("Ingrese el límite de crédito: ");
                double limiteCredito = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea
                cuentas.put(numeroCuenta, new CuentaCorriente(numeroCuenta, saldoInicial, limiteCredito));
                System.out.println("Cuenta corriente creada exitosamente.");
                break;
            default:
                System.out.println("Tipo de cuenta no válido.");
        }
    }

    private static void realizarDeposito() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        CuentaBancaria cuenta = cuentas.get(numeroCuenta);

        if (cuenta != null) {
            System.out.print("Ingrese la cantidad a depositar: ");
            double cantidad = scanner.nextDouble();
            scanner.nextLine(); // Consumir la nueva línea
            try {
                cuenta.depositar(cantidad);
                System.out.println("Depósito realizado exitosamente.");
            } catch (CantidadInvalidaException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private static void realizarRetiro() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        CuentaBancaria cuenta = cuentas.get(numeroCuenta);

        if (cuenta != null) {
            System.out.print("Ingrese la cantidad a retirar: ");
            double cantidad = scanner.nextDouble();
            scanner.nextLine(); // Consumir la nueva línea
            try {
                cuenta.retirar(cantidad);
                System.out.println("Retiro realizado exitosamente.");
            } catch (SaldoInsuficienteException | CantidadInvalidaException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private static void consultarSaldo() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        CuentaBancaria cuenta = cuentas.get(numeroCuenta);

        if (cuenta != null) {
            cuenta.mostrarDetalles();
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

}
