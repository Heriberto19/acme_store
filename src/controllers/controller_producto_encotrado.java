/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.Productos_Encotrados;
import views.Punto_Venta;
/**
 *
 * @author Lenovo
 */
public class controller_producto_encotrado implements ActionListener {
Productos_Encotrados pe;
Punto_Venta pv;
public controller_producto_encotrado(Productos_Encotrados pe){
    this.pe =  pe;
    this.pe.setVisible(true);
    this.pv = pv;
    this.pe.jm_agregar.addActionListener(this);
}

void agregar(){
    DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("PRECIO VENTA");
        modelo.addColumn("CANTIDAD");
        this.pv.jtbl_venta.setModel(modelo);
        
}
    @Override
    public void actionPerformed(ActionEvent e) {
         
          if(e.getSource()== pe.jm_agregar){
              int fila = pe.jtbl_productos_encotrados.getSelectedRowCount();
              if( fila >= 0){
              JOptionPane.showInputDialog(pe, "werew");
                  System.out.println("controllers");
              }
           }
      }
    
    
}
