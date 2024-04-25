package Dominio;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    /*En esta clase nos encontramos toda la información y métodos necesarios para gestionar las cuentas*/
    private String IBAN;
    private String nombreTitular;
    private String apellidoTitular;
    private double saldo;
    private List<Double> movimientos = new ArrayList<>();

    public Cuenta(){
        //No inicializamos las demas variables porque lo haremos con métodos que se encuentran en el main
        this.saldo = 0;
    }

    //getters
    public String getIBAN() {
        return IBAN;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return nombreTitular + " " + apellidoTitular;
    }

    //setters
    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }
    public void setApellidoTitular(String apellidoTitular){
        this.apellidoTitular = apellidoTitular;
    }

    /*Tanto en los ingresos como retiradas de dinero vamos a dejar hacerlo unicamente si la longitud dfe la lista de movimientos
    * es menor que 100, en caso contrario retornamos el saldo*/
    public double ingreso(double ingreso){
        if(ingreso >= 3000){
            System.out.println("Avisar a Hacienda");
        }
        if(movimientos.size() >= 100){
            System.out.println("Has agotado los movimientos");
            return this.saldo;
        }
        movimientos.add(ingreso);
        return this.saldo += ingreso;
    }
    public double retirada(double retirada){
        /*Obtenemos el valor absoluto de la retirada para evitar problema si el usuario nos introduce un valor negativo*/
        if(retirada >= 3000){
            System.out.println("Avisar a Hacienda");
        }
        if(retirada < 0){
            retirada = Math.abs(retirada);
        }
        if(this.saldo - retirada < -50){
            System.out.println("Saldo negativo");
        }
        if(movimientos.size() >= 100){
            System.out.println("Has agotado los movimientos");
            return this.saldo;
        }
        movimientos.add(retirada *= -1);
        return this.saldo += retirada;
    }
    //Método para ver el registro de movimientos
    public void verMovimientos(){
        int i = 1;
        for(Double movimiento : movimientos){
            System.out.println("Movimiento " + i + ": " + movimiento + " Total: " + this.saldo);
            i++;
        }
    }
    //Método toString
    @Override
    public String toString(){
        return "Titular: " + getTitular() + "\nIBAN: " + getIBAN() + "\nSaldo: " + getSaldo();
    }


}
