/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import views.*;
import controllers.*;
import javax.swing.JPanel;
import models.*;
/**
 *
 * @author Lenovo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     Registro_Usuario ru = new Registro_Usuario();
     controller_registro_usuario cru = new controller_registro_usuario(ru);
     
     Menus mn = new Menus();
     contrller_menus cm1 = new contrller_menus(mn);
     
     View_usuarios vs =  new View_usuarios();
     controller_usuarios cu= new controller_usuarios(vs);
      JPanel  views[] = new JPanel [3];
      views [0] = ru;
      views [1] = mn;
      views [2] = vs;
      Menu menu= new  Menu();
      Models_Menu mm = new Models_Menu();
      Controller_Menu cm =  new Controller_Menu(menu, mm,views,mn);
    }
    
}
