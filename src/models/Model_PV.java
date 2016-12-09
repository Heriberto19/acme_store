/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Lenovo
 */
public class Model_PV {
   
    public double Iva = 0.16;
    public  double getIva() {
        return Iva;
    }

    public void setIva(double Iva) {
        this.Iva = Iva;
    }
    
    public String Ivapro = "16%";
    public  String getIvapro() {
        return Ivapro;
    }

    public void setIvapro(String Ivapro) {
        this.Ivapro = Ivapro;
    }
 
}
