
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.swing.Icon;
import views.*;
import controllers.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import models.*;


/**
 *
 * @author Lenovo
 */
public class Controller_Menu implements ActionListener {

    Menu menu = new Menu();
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
    Image icon = new ImageIcon(getClass().getResource("/imges/logo.png")).getImage();
    JPanel views[];
    Menus menus;
    
    public Controller_Menu(Menu menu, Models_Menu mm,   JPanel [] views, Menus menus) {
        this.menu = menu;
        this.views = views;
        this.menus = menus;
        this.menu.setVisible(true);
        this.menu.setLocationRelativeTo(null);
        this.menu.setIconImage(icon);
        this.menu.jm_Iniciar_sesion.addActionListener(this);
         this.menu.jm_Cerrar_sesion.addActionListener(this);
        this.menu.jm_registra_usuario.addActionListener(this);
        this.menu.jm_Menu.addActionListener(this);
        this.menu.jm_editar_usuario.addActionListener(this);
        
         oculta(); 
    }
    
   public  void oculta(){
   menu.jm_Menu.setVisible(false); 
   menu.jm_Cerrar_sesion.setVisible(false); 
   menu.jm_acerca_de.setVisible(false); 
   menu.jm_ayuda_productos.setVisible(false); 
   menu.jm_registra_usuario.setVisible(false); 
   menu.jm_restauracion.setVisible(false);
   menu.jm_editar_usuario.setVisible(false);
 }
    
    public void jm_registra_usuarioActionPerformed(){
        this.menu.setContentPane(views [0]);
        this.menu.revalidate();
        this.menu.repaint();
    }
    
    public void jm_MenuActionPerformed(){
        this.menu.setContentPane(views [1]);
        this.menu.revalidate();
        this.menu.repaint();
        menus.jpanel_menu.setVisible(true);
    }
    
    public void jm_editar_usuarioActionPerformed(){
       this.menu.setContentPane(views [2]);
        this.menu.revalidate();
        this.menu.repaint();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        if (e.getSource()== menu.jm_Iniciar_sesion){
              Controller_Login login = new Controller_Login(vl, ml, menu,punto_Venta);
        }
        else if (e.getSource()==menu.jm_registra_usuario){
            jm_registra_usuarioActionPerformed();
        }
        else if (e.getSource()==menu.jm_Menu){
            jm_MenuActionPerformed();
        }
        else if (e.getSource()==menu.jm_editar_usuario){
            jm_editar_usuarioActionPerformed();
        }
        else if (e.getSource()==menu.jm_Cerrar_sesion){
            menu.jm_user.setText("Bienvenido:"); 
            oculta();
            menu.jm_Iniciar_sesion.setEnabled(true);
            menus.jpanel_menu.setVisible(false);
            
        }
    }


}
