package main;

import Dominio.Bank;
import Dominio.Cuenta;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Bank banco = new Bank();
        Cuenta cuenta;
        int menuBanco = menuBanco();
        while (menuBanco != 5) {
            switch (menuBanco) {
                case 1:
                    cuenta = crearCuenta(banco);
                    break;
                case 2:
                    System.out.println("Que número de cuenta quieres eliminar: ");
                    banco.remove(inputInt());
                    break;
                case 3:
                    System.out.println("A que número de cuenta quieres acceder");
                    int numero = inputInt();
                    cuenta = banco.get(numero - 1);
                    if (numero <= banco.size()) {
                        int menu = menu();
                        while (menu != 8) {
                            switch (menu) {
                                case 1:
                                    System.out.println(cuenta.toString());
                                    break;
                                case 2:
                                    System.out.println("IBAN: " + cuenta.getIBAN());
                                    break;
                                case 3:
                                    System.out.println("Titular: " + cuenta.getTitular());
                                    break;
                                case 4:
                                    System.out.println("Saldo: " + cuenta.getSaldo());
                                    break;
                                case 5:
                                    System.out.println("Cuanto quiere ingresar: ");
                                    cuenta.ingreso(inputDouble());
                                    break;
                                case 6:
                                    System.out.println("Cuanto quiere retirar: ");
                                    cuenta.retirada(inputDouble());
                                    break;
                                case 7:
                                    cuenta.verMovimientos();
                                    break;
                                default:
                                    System.err.println("Por favor, introduzca una opción válida");
                                    break;
                            }
                            menu = menu();
                        }
                    }    break;
                case 4:
                    banco.getBanco();
                    break;
                case 5:
                    System.out.println("Gracias por confiar en nosotros");
                    System.exit(0);
                    break;
                default:
                   System.err.println("Por favor elige una opción válida");
                    break;
            }
            menuBanco = menuBanco();
        }
    }
    /*El método crear cuenta nos pedirà los datos para rellenar los atributos que no inicializabamos en el constructor
    * tendrá que pasar por una validación*/
    static Cuenta crearCuenta(Bank banco){
        Scanner sc = new Scanner(System.in);
        Cuenta cuenta = new Cuenta();
        System.out.print("Nombre: ");
        cuenta.setNombreTitular(sc.nextLine());
        System.out.print("Apellido: ");
        cuenta.setApellidoTitular(sc.nextLine());
        System.out.print("IBAN: ");
        cuenta.setIBAN(sc.nextLine());
        while(!validacion(cuenta)){
            System.out.print("Nombre: ");
            cuenta.setNombreTitular(sc.nextLine());
            System.out.print("Apellido: ");
            cuenta.setApellidoTitular(sc.nextLine());
            System.out.print("IBAN: ");
            cuenta.setIBAN(sc.nextLine());
        }
        //He hecho un for para introducir 100 ingresos para probar que funcionaba bien el bloqueo de la cuenta
        /*for(int i = 0; i < 100; i++){
            cuenta.ingreso(1);
        }*/
        banco.add(cuenta);
        return cuenta;
    }
    /*El menu que gestiona las opciones del banco*/
    static int menuBanco(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige una opción: ");
        System.out.println("1. Crear nueva cuenta");
        System.out.println("2. Eliminar cuenta");
        System.out.println("3. Selecciona una cuenta");
        System.out.println("4. Ver cuentas");
        System.out.println("5. Salir del sistema");
        return inputInt();
    }
    /*El menu que gestiona las opciones de la cuenta*/
    static int menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige una opción: ");
        System.out.println("1. Datos de la cuenta. Mostrará el IBAN, el titular y el saldo.\n" +
                "2. IBAN. Mostrará el IBAN.\n" +
                "3. Titular. Mostrará el titular.\n" +
                "4. Saldo. Mostrará el saldo disponible.\n" +
                "5. Ingreso. Pedirá la cantidad a ingresar y realizará el ingreso si es posible.\n" +
                "6. Retirada. Pedirá la cantidad a retirar y realizará la retirada si es posible.\n" +
                "7. Movimientos. Mostrará una lista con el historial de movimientos.\n" +
                "8. Volver al menu del banco");
        return inputInt();
    }
    /*El siguiente método valida cada vez que introducimos un int, no pedimos el input desde el main
    * si no que llamamos a este método*/
    static int inputInt(){
        Scanner sc = new Scanner(System.in);
        do{
            try{
                return sc.nextInt();
            }catch(InputMismatchException e){
                System.err.println("Por favor introduzca una opción válida");
                sc.next();
            }
        }while(true);
    }
    /*Valida que sea un double*/
    static double inputDouble(){
        Scanner sc = new Scanner(System.in);
        do{
            try{
                double opcion = sc.nextInt();
                return opcion;
            }catch(InputMismatchException e){
                System.err.println("Por favor introduzca una cantidad válida");
                sc.next();
            }
        }while(true);
    }
    /*Valida los datos de la cuenta*/
    static boolean validacion(Cuenta cuenta){
        if(!cuenta.getTitular().toUpperCase().matches("^[A-Z]{2,}\\s[A-Z\\s\\-]+$")){
            System.out.println("Nombre o apellido no válido");
            return false;
        }
        if(!cuenta.getIBAN().matches("^ES4650[0-9]{16}$")){
            System.out.println("IBAN no vÁlido(ES4650 + 16 numeros sin espacios)");
            return false;
        }
        return true;
    }
}