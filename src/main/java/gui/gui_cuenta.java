package gui;

import entidades.CuentaBancaria;
import entidades.CuentaCorriente;
import entidades.CuentaDeAhorros;
import servicio.ServicioCuentaBancaria;

import java.util.Scanner;

public class gui_cuenta {
    private boolean running = true;
    private ServicioCuentaBancaria serviciosPersona;

    public gui_cuenta() {
        serviciosPersona = new ServicioCuentaBancaria();
    }

    public void iniciar() {
        System.out.println("Bienvenido al sistema de persistencia de Cuentas Bancarias");

        while (running) {
            System.out.println("1. Crear Cuenta");
            System.out.println("2. Buscar cuenta por numero de cuenta");
            System.out.println("3. Depositar");
            System.out.println("4. Transferir");
            System.out.println("5. Retirar");
            System.out.println("6. Salir");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            seleccionarOpcionesPrincipales(opcion);
        }

    }

    private void seleccionarOpcionesPrincipales(int seleccion) {
        switch (seleccion) {
            case 1:
                crearCuenta();
                break;
            case 2:
                buscarCuenta();
                break;
            case 3:
                depositar();
                break;
            case 4:
                transferir();
                break;
            case 5:
                retirar();
                break;
            case 6:
                salir();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public CuentaBancaria buscarCuenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el numero  de la cuenta");
        String numeroCuenta = scanner.nextLine();
        CuentaBancaria cuenta = serviciosPersona.buscarCuenta(numeroCuenta);
        if(cuenta != null){
            String data = "Tipo de cuenta: " + cuenta.getTipoCuenta() + "\n"
                    + "Numero: " + cuenta.getNumeroDeCuenta() + "\n"
                    + "Propietario: " + cuenta.getPropietario() + "\n"
                    + "Saldo: " + cuenta.getSaldo();
            System.out.println(data);
        } else {
            System.out.println("La cuenta indicada no existe");
        }
        return cuenta;
    }

    private void crearCuenta() {
        CuentaBancaria cuenta;

        System.out.println("Creación de cuenta");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escoja el tipo de cuenta \n1.CC\n2.CA");
        String tipoCuenta = scanner.nextLine();
        if(tipoCuenta.equalsIgnoreCase("cc") || tipoCuenta.equalsIgnoreCase("ca")){
            System.out.println("Ingrese el saldo de la cuenta");
            float saldo = scanner.nextFloat();
            scanner.nextLine();
            System.out.println("Ingrese el número de la cuenta");
            String numeroCuenta = scanner.nextLine();

            System.out.println("Ingrese el nombre del propietario de la cuenta");
            String propietario = scanner.nextLine();

            if(tipoCuenta.equalsIgnoreCase("cc")) {
                cuenta = new CuentaCorriente(saldo, numeroCuenta, propietario);
            } else {
                cuenta = new CuentaDeAhorros(saldo, numeroCuenta, propietario);
            }

            serviciosPersona.crearCuenta(cuenta);
        } else {
            System.out.println("No ha seleccionado una opción valida");
        }
    }

    private void depositar() {
        CuentaBancaria cuenta = buscarCuenta();

        if(cuenta != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el saldo a ingresar");
            float saldo = scanner.nextFloat();
            scanner.nextLine();
            if(cuenta.getTipoCuenta().equalsIgnoreCase("cc")) {
                CuentaCorriente cuentaCC = (CuentaCorriente) cuenta;
                cuentaCC.consignarDinero(saldo);

                serviciosPersona.actualizarSaldo(cuentaCC.getNumeroDeCuenta(), cuentaCC.getSaldo());
            } else {
                CuentaDeAhorros cuentaA = (CuentaDeAhorros) cuenta;
                cuentaA.consignarDinero(saldo);

                serviciosPersona.actualizarSaldo(cuentaA.getNumeroDeCuenta(), cuentaA.getSaldo());
            }
        }
    }

    private void transferir() {

    }

    private void retirar() {

    }

    private void salir() {
        System.out.println("Salir");
        running = false;
    }
}
