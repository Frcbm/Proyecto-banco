package Dominio;

import java.util.ArrayList;
import java.util.HashSet;

public class Bank {
    /*Esta clase va a contener simplemente un ArrayList de cuentas llamado banco con los métodos necesarios para usar
    * dicho arraylist fuera de la clase*/
    private ArrayList<Cuenta> banco;
    public Bank() {this.banco = new ArrayList<>();}
    public void getBanco(){
        int i = 1;
        for (Cuenta c : this.banco) {
            System.out.println("Cuenta " + i + ":");
            System.out.println(c.toString());
            System.out.println("");
            i++;
        }
    }
    public void add(Cuenta c){banco.add(c);}
    public void remove(int i){banco.remove(i);}
    public Cuenta get(int i){
        try{
            return banco.get(i);
        }catch(IndexOutOfBoundsException e){
            System.out.println("No tenemos esa cuenta en nuestros registros");
        }
        return null;
    }
    public int size(){return banco.size();}
}
