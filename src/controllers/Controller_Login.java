
package controllers;
import ascii.Ascii;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import views.*;
import models.*;
import sql.Sql;

/**
 *
 * @author Lenovo
 */
public class Controller_Login implements ActionListener {
 view_Login login;
 Model_Login ml;
 Menu menu ;
 Punto_Venta pv;
 Models_Menu mm = new Models_Menu();
 String sql="";
 Sql s = new Sql();
 Connection cn = s.conexion();
 Statement st;
 ResultSet rs;
 String usuario,password, tipo ="";
  Ascii ascii = new Ascii();
  
 public Controller_Login (view_Login login, Model_Login ml, Menu menu, Punto_Venta pv){
  this.login = login;
  this.pv = pv;
  this.menu = menu;
  this.ml = ml;
  this.login.setVisible(true);
  this.login.setLocationRelativeTo(null);
  this.login.setTitle("Login");
  this.login.jbtn_aceptar.addActionListener(this);
  this.login.aceptar_btn.addActionListener(this);
  this.login.jbtn_cancelar.addActionListener(this);
  this.login.jcb_tipo.addActionListener(this);
  this.login.jbtn_aceptar.setVisible(false);
  this.login.aceptar_btn.setVisible(false);
  
 }


 
 void desoculta_admin(){
   
   menu.jm_Cerrar_sesion.setVisible(true);
   menu.jm_acerca_de.setVisible(true);
   menu.jm_ayuda_productos.setVisible(true);
   menu.jm_registra_usuario.setVisible(true);
   menu.jm_restauracion.setVisible(true);
    menu.jm_Iniciar_sesion.setEnabled(false);
    menu.jm_editar_usuario.setVisible(true);
 }
 


  void obten_ingreso(){
      usuario=login.txt_usuario.getText();
      sql = "Select nombre from usuarios where user='"+usuario+"'";
      try {
         st = cn.createStatement();
         rs = st.executeQuery(sql);
         if(rs.next()){
           rs.getString("nombre");
            JOptionPane.showMessageDialog(menu, "Bienvenido:" + rs.getString("nombre") );
  	    menu.jm_user.setText("Bienvenido:" +  rs.getString("nombre")); 
            pv.txt_nombre.setText("po");
            this.menu.jm_Menu.setVisible(true);
         }
      }catch(SQLException ex){
              
     }
  }
 
 void ingreso_admin(){
     
        usuario=login.txt_usuario.getText();
        password=String.valueOf(login.jpasswoor.getPassword());
        String pass= ascii.cifra_texto(password);
        tipo =  login.jcb_tipo.getSelectedItem().toString();
         sql="select user,password, tipo, activo from usuarios where user='"+usuario+"' and password='"+pass+"'  and tipo='"+tipo+"' and activo='si'";
         try {
         st = cn.createStatement();
         rs = st.executeQuery(sql);
         
         if(usuario.equalsIgnoreCase("")|| password.equalsIgnoreCase("") || tipo.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null,"Faltan Ingresar Datos");
           
             }else{
           
                if(rs.next() && "Administrador".equals(rs.getString("tipo")) ){
                    login.setVisible(false);
                   desoculta_admin();
                   obten_ingreso();                
                }else {
                    JOptionPane.showMessageDialog(null, "Datos Incorrectos");
                    limpia();
                }
            }
        
     } catch (SQLException ex) {
         Logger.getLogger(Controller_Login.class.getName()).log(Level.SEVERE, null, ex);
     }
  
  
   if(usuario.equals("") ){
       JOptionPane.showMessageDialog(null,ml.getNo_Existe());
   }
  
   
 }
 void ingreso_empeado(){
     
        usuario=login.txt_usuario.getText();
        password=String.valueOf(login.jpasswoor.getPassword());
        String pass= ascii.cifra_texto(password);
        tipo =  login.jcb_tipo.getSelectedItem().toString();
         sql="select user,password,tipo, activo from usuarios where user='"+usuario+"' and password='"+pass+"'  and tipo='vendedor' and activo='si'";
         try {
         st = cn.createStatement();
         rs = st.executeQuery(sql);
         
         if(usuario.equalsIgnoreCase("")|| password.equalsIgnoreCase("") || tipo.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null,"Faltan Ingresar Datos");
           
             }else{
           
                 if(rs.next() && "Vendedor".equals(rs.getString("tipo"))){
                   login.setVisible(false);
                   menu.jm_Iniciar_sesion.setEnabled(false);
                   menu.jm_Cerrar_sesion.setVisible(true);
                   obten_ingreso();
               
                   
                }else{
                    JOptionPane.showMessageDialog(null, "Datos Incorrectos");
                    limpia();
                   
                }
            }
        
     } catch (SQLException ex) {
         Logger.getLogger(Controller_Login.class.getName()).log(Level.SEVERE, null, ex);
     }
  
  
   if(usuario.equals("") ){
       JOptionPane.showMessageDialog(null,ml.getNo_Existe());
   }
  
   
 }
 
 
 void limpia(){
      login.jpasswoor.setText("");
      login.txt_usuario.setText("");
      login.jcb_tipo.setSelectedIndex(0);
 }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.jbtn_aceptar){
           ingreso_admin();
           
           limpia();
        }
        else if (e.getSource()== login.jbtn_cancelar){
           login.setVisible(false);
           limpia();
        }
        else if (e.getSource()== login.aceptar_btn){
           ingreso_empeado();
           limpia();
        }
        else if (e.getSource()== login.jcb_tipo){
           if(login.jcb_tipo.getSelectedItem().equals("Administrador")){
               login.jbtn_aceptar.setVisible(true);
               login.aceptar_btn.setVisible(false);
           }else if (login.jcb_tipo.getSelectedItem().equals("Vendedor")){
               login.aceptar_btn.setVisible(true);
               login.jbtn_aceptar.setVisible(false);
           }
        }
    }
}
