/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import models.*;
import views.*;

/**
 *
 * @author Lenovo
 */
public class contrller_menus implements ActionListener{

  
    View_Producto view_Producto = new View_Producto();
    Model_Producto mp =  new Model_Producto();
    
    View_Proveedor view_Proveedor = new View_Proveedor();
    
    Punto_Venta punto_Venta = new Punto_Venta();
    Productos_Restaura productos_Restaura = new Productos_Restaura();
    View_Clientes view_Clientes = new View_Clientes();
    Model_PV model_PV = new Model_PV();
    Productos_Encotrados productos_Encotrados = new Productos_Encotrados();
    Clientes_encontrados ce = new Clientes_encontrados();
    view_Login vl = new view_Login();
    Model_Login ml = new Model_Login();
    Registro_Usuario ru = new Registro_Usuario();
    Menus menu;
    
    public contrller_menus(Menus menu) {
        this.menu = menu;
        
        
        this.menu.jbtn_productos.addActionListener(this);
        this.menu.jbtn_proveedores.addActionListener(this);
        this.menu.jbtn_punto_venta.addActionListener(this);
        this.menu.jbtn_restarar.addActionListener(this);
        this.menu.jbtn_clientes.addActionListener(this);
        this.menu.jbtn_ajustes.addActionListener(this); 
        
    }
    

 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.jbtn_productos) {
            Controller_Producto controller_Producto = new Controller_Producto(view_Producto,mp);
        } else if (e.getSource() == menu.jbtn_proveedores) {
            Controller_Proveedores controller_Proveedores = new Controller_Proveedores(view_Proveedor);
        } else if (e.getSource() == menu.jbtn_punto_venta) {
            Controlle_Punto_venta controlle_Punto_venta = new Controlle_Punto_venta(punto_Venta,productos_Encotrados, model_PV, ce );
        } else if (e.getSource() == menu.jbtn_restarar) {
            Controller_Restaura_Producto crp = new Controller_Restaura_Producto(productos_Restaura);
        } else if (e.getSource() == menu.jbtn_clientes) {
            Controller_Clientes cc = new Controller_Clientes(view_Clientes);
        }
        else if (e.getSource()== menu.jbtn_ajustes){
            System.exit(0);
        }
       
    }
   
}
