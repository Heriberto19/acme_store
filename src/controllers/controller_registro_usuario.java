/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import ascii.Ascii;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import views.Registro_Usuario;
import sql.Sql;
/**
 *
 * @author Lenovo
 */
public class controller_registro_usuario implements ActionListener{
  Registro_Usuario ru;
  Sql sql = new Sql();
  PreparedStatement pst;
  Statement st;
  ResultSet rs;
  String consult, user, pass1, pass2;
  String admin = "Administrador";
  String visible = "si";
  Connection cn = sql.conexion();
  Ascii ascii = new Ascii();
  public controller_registro_usuario(Registro_Usuario ru){
      this.ru = ru;
      this.ru.jbtn_cancelar.addActionListener(this);
      this.ru.jbtn_registrar.addActionListener(this);
      this.ru.jbtn_verificar.addActionListener(this);
      this.ru.jbtn_registrar.setVisible(false);
      this.ru.jl_verificar.setVisible(false);
  }
  
  void save(){
     try {
             cn.setAutoCommit(false);
             pst = cn.prepareStatement("INSERT INTO usuarios (user,"
                    + "password,"
                    + "nombre,"
                    + "ap_p,"
                    + "ap_m,"
                    + "Sexo,"
                    + "edad,"
                    + "Activo,"
                    + "telefonp,"
                    + "direccion,"
                    + "tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, ru.jtx_nom_user.getText());
                    String pass=String.valueOf(ru.jp_password.getPassword());
                    pst.setString(2,ascii.cifra_texto(pass));
                    pst.setString(3, ru.jtx_nombre.getText());
                    pst.setString(4, ru.jtx_apellidopa.getText());
                    pst.setString(5, ru.jtx_apellidoma.getText());
                    pst.setString(6, ru.jcb_sexo.getSelectedItem().toString());
                    pst.setString(7, ru.jtx_edad.getText());
                    pst.setString(8, "si");
                    pst.setString(9, ru.jtx_telefono.getText());
                    pst.setString(10, ru.jtx_direccion.getText());
                    pst.setString(11, ru.jcb_tipo.getSelectedItem().toString());
                    pst.executeUpdate();
      }catch(SQLException e){
          JOptionPane.showMessageDialog(ru, "No es posible guardar");
      }
  }
  void verifica_texto(){
      String a = ru.jcb_sexo.getSelectedItem().toString();
      String b =  ru.jcb_tipo.getSelectedItem().toString();
      String c= ru.jp_password.getPassword().toString();
      String d = ru.jp_password_admin.getPassword().toString();
      String e = ru.jtx_admin.getText();
      String f = ru.jtx_apellidoma.getText();
      String g= ru.jtx_apellidopa.getText();
      String h = ru.jtx_direccion.getText();
      String i = ru.jtx_edad.getText();
      String j= ru.jtx_nom_user.getText();
      String k = ru.jtx_nombre.getText();
      String l = ru.jtx_telefono.getText();
      String m= ru.jp_password_confir.getPassword().toString();
      
      if (a.equalsIgnoreCase("")|| b.equalsIgnoreCase("")
              || c.equalsIgnoreCase("")|| d.equalsIgnoreCase("") 
              || e.equalsIgnoreCase("") || f.equalsIgnoreCase("")
              || g.equalsIgnoreCase("") || h.equalsIgnoreCase("")
              ||i.equalsIgnoreCase("") || j.equalsIgnoreCase("")
              || k.equalsIgnoreCase("") || l.equalsIgnoreCase("") 
              || m.equalsIgnoreCase("")){
          
           JOptionPane.showMessageDialog(ru, "Falta llenar algunos campos");
      }
  }
  void verificar(){
      user = ru.jtx_nom_user.getText();
      pass1 = String.valueOf(ru.jp_password.getPassword());
      pass2 = String.valueOf(ru.jp_password_confir.getPassword());
          consult="select user from usuarios where user='"+ user +"' ";
         try {
         st = cn.createStatement();
         rs = st.executeQuery(consult);
          
          if (user.equals(rs = st.executeQuery(consult))){
              JOptionPane.showMessageDialog(ru, "No es posible registrar el nmbre del usuario \n "
                      + "ya que existe en la base de datos un nombre igual");
          }
          else {
              if(pass1.equalsIgnoreCase(pass2)){
                 
                  verifica_admin();
                 
              }
              else {
                  
               JOptionPane.showMessageDialog(ru, "Las contraseñas no coinciden"); 
               ru.jp_password_confir.setBackground(Color.red);
              }
              
          }
      } catch (SQLException ex) {
        
      }
  }
  void verifica_admin(){
        String adminis =ru.jtx_admin.getText();
       
        String pass=String.valueOf(ru.jp_password_admin.getPassword());
        String password= ascii.cifra_texto(pass);
        consult="select * from usuarios where user='" + adminis + "' and password='"+password+"' and tipo='"+admin+"'";
         try {
         st = cn.createStatement();
         rs = st.executeQuery(consult);
         
         if(adminis.equalsIgnoreCase("") || pass.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null,"Para poder registrar un nuevo usuario \n necesitas permisos del administador");
            
         }else{
           
                if(rs.next() ){ 
                    save();
                    this.ru.jbtn_registrar.setVisible(true);
                    this.ru.jl_verificar.setVisible(true);
                    ru.jbtn_verificar.setVisible(false);
                    JOptionPane.showMessageDialog(null,"Pulsa el boton registrar para guardar");    
                }
                else {
                   JOptionPane.showMessageDialog(null,"Contraseña o usuario incorrectos"); 
                   ru.jp_password_admin.setBackground(Color.red);
                   ru.jtx_admin.setBackground(Color.red);
                }
         }
         }catch (SQLException e){
                 
         }
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== ru.jbtn_cancelar){
            try {
                Savepoint savepoint = cn.setSavepoint();
                cn.rollback();
                cn.commit();
                JOptionPane.showMessageDialog(ru, "Cancelación exitosa");
                ru.jbtn_registrar.setVisible(false);
                ru.jbtn_verificar.setVisible(true);
                this.ru.jl_verificar.setVisible(false);
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(ru, "No es posible \n cancelar la operación");
            }
        }
        else if(e.getSource()== ru.jbtn_registrar){
            try {
                cn.setAutoCommit(true);
                 JOptionPane.showMessageDialog(null,"Usuario registrado con exito \n podras tener acceso \n al sistema");  
                 ru.jbtn_registrar.setVisible(false);
                 ru.jbtn_verificar.setVisible(true);
                 this.ru.jl_verificar.setVisible(false);
            } catch (SQLException ex) {
              JOptionPane.showMessageDialog(ru, "Error al registrar");
            }
        }
        else if(e.getSource()== ru.jbtn_verificar){
            verifica_texto();
            verificar();
            
        }
    }
}
