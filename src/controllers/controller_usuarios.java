/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sql.Sql;
import views.View_usuarios;
/**
 *
 * @author Lenovo
 */
public class controller_usuarios implements ActionListener{
    View_usuarios vs;
    String Sql;
    Sql sql = new Sql();
    Connection cn = sql.conexion();
    public controller_usuarios (View_usuarios vs){
        this.vs = vs;
        this.vs.setVisible(true);
        this.vs.jbtn_buscar_user.addActionListener(this);
        this.vs.jbtn_buscar_sexo.addActionListener(this);
        this.vs.jbtn_actualizar.addActionListener(this);
        this.vs.jbtn_busca_nombre.addActionListener(this);
        this.vs.jmi_editar.addActionListener(this);
        
    }
     void BuscaUsuario(String usuario) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("User");
        modelo.addColumn("Password");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido P");
        modelo.addColumn("Apellido M");
        modelo.addColumn("Sexo");
        modelo.addColumn("Edad");
        modelo.addColumn("Activo");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Tipo");
       
        if (usuario.equals("")) {
            Sql=( "SELECT * FROM usuarios");
        } else {
            Sql=("SELECT * FROM usuarios WHERE user='" + usuario+ "' ");
        }

        String[] columna = new String[11];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Sql);
            while (rs.next()) {

                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = rs.getString(3);
                columna[3] = rs.getString(4);
                columna[4] = rs.getString(5);
                columna[5] = rs.getString(6);
                columna[6] = rs.getString(7);
                columna[7] = rs.getString(8);
                columna[8] = rs.getString(9);
                columna[9] = rs.getString(10);
                columna[10] = rs.getString(11);
                modelo.addRow(columna);
            }
            this.vs.jtb_table.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vs, ex);
        }
    }
     
     void Buscanombtre(String nombre) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("User");
        modelo.addColumn("Password");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido P");
        modelo.addColumn("Apellido M");
        modelo.addColumn("Sexo");
        modelo.addColumn("Edad");
        modelo.addColumn("Activo");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Tipo");
       
        if (nombre.equals("")) {
            Sql=( "SELECT * FROM usuarios");
        } else {
            Sql=("SELECT * FROM usuarios WHERE nombre='" +nombre+ "' ");
        }

        String[] columna = new String[11];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Sql);
            while (rs.next()) {

                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = rs.getString(3);
                columna[3] = rs.getString(4);
                columna[4] = rs.getString(5);
                columna[5] = rs.getString(6);
                columna[6] = rs.getString(7);
                columna[7] = rs.getString(8);
                columna[8] = rs.getString(9);
                columna[9] = rs.getString(10);
                columna[10] = rs.getString(11);
                modelo.addRow(columna);
            }
            this.vs.jtb_table.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vs, ex);
        }
    }
     
      void BuscaRFC(String Sexo) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("User");
        modelo.addColumn("Password");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido P");
        modelo.addColumn("Apellido M");
        modelo.addColumn("Sexo");
        modelo.addColumn("Edad");
        modelo.addColumn("Activo");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Tipo");
       
        if (Sexo.equals("")) {
            Sql=( "SELECT * FROM usuarios");
        } else {
            Sql=("SELECT * FROM usuarios WHERE sexo='" +Sexo+ "' ");
        }

        String[] columna = new String[11];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Sql);
            while (rs.next()) {

                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = rs.getString(3);
                columna[3] = rs.getString(4);
                columna[4] = rs.getString(5);
                columna[5] = rs.getString(6);
                columna[6] = rs.getString(7);
                columna[7] = rs.getString(8);
                columna[8] = rs.getString(9);
                columna[9] = rs.getString(10);
                columna[10] = rs.getString(11);
                modelo.addRow(columna);
            }
            this.vs.jtb_table.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vs, ex);
        }
    }
      
      void modifica() {
      
        int fila = vs.jtb_table.getSelectedRow();
        if (fila >= 0) {
            vs.jtxt_nombre.setText(vs.jtb_table.getValueAt(fila, 0).toString());
            vs.jtxt_password.setText(vs.jtb_table.getValueAt(fila, 1).toString());
            vs.jtxt_nombres.setText(vs.jtb_table.getValueAt(fila, 2).toString());
            vs.jtxt_apellido_p.setText(vs.jtb_table.getValueAt(fila, 3).toString());
            vs.jtxt_apellidom.setText(vs.jtb_table.getValueAt(fila, 4).toString());
            vs.jcb_sexo.setSelectedItem(vs.jtb_table.getValueAt(fila, 5).toString());
            vs.jtxt_edad.setText(vs.jtb_table.getValueAt(fila, 6).toString());
            vs.jcb_activo.setSelectedItem(vs.jtb_table.getValueAt(fila, 7).toString());
            vs.jtxt_telefono.setText(vs.jtb_table.getValueAt(fila, 8).toString());
            vs.jtxt_direccion.setText(vs.jtb_table.getValueAt(fila, 9).toString());
            vs.jcb_tipo.setSelectedItem(vs.jtb_table.getValueAt(fila, 10).toString());
            
        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
    }
      
      void update() {
                     
        try {
            PreparedStatement pst = cn.prepareStatement("UPDATE usuarios SET  "
                    + "user='" + vs.jtxt_nombre.getText() + "',"
                    + "password='" + vs.jtxt_password.getText() + "',"
                    + "nombre='" + vs.jtxt_nombres.getText() + "',"
                    + "ap_p='" + vs.jtxt_apellido_p.getText()  + "',"
                    + "ap_m='" + vs.jtxt_apellidom.getText()  + "',"
                    + "Sexo='" + vs.jcb_sexo.getSelectedItem()+ "',"
                    + "edad='" + vs.jtxt_edad.getText()  + "',"
                    + "Activo='" + vs.jcb_activo.getSelectedItem()+ "',"
                    + "telefonp='" + vs.jtxt_telefono.getText()  + "',"
                    + "direccion='" + vs.jtxt_direccion.getText()  + "',"
                    + "tipo='" + vs.jcb_tipo.getSelectedItem()+ "' "
                    + "WHERE user='" + vs.jtxt_nombre.getText()  + "'");
            pst.executeUpdate();
            BuscaUsuario("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vs, e);
        }
            vs.jtxt_password.setText(""); 
            vs.jtxt_apellido_p.setText("");
            vs.jtxt_apellidom.setText("");
            vs.jtxt_direccion.setText("");
            vs.jtxt_edad.setText("");
            vs.jtxt_nombre.setText("");
            vs.jtxt_nombres.setText("");
            vs.jtxt_sexo.setText("");
            vs.jtxt_telefono.setText("");
            
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vs.jbtn_buscar_user){
            BuscaUsuario(vs.jtxt_user_bus.getText());
        }else if (e.getSource()==vs.jbtn_busca_nombre){
            Buscanombtre(vs.jtxt_nombre_bus.getText());
        }else if (e.getSource()==vs.jbtn_buscar_sexo){
            BuscaRFC(vs.jtxt_sexo.getText());
        }else if (e.getSource()==vs.jmi_editar){
           modifica();
        }else if (e.getSource()==vs.jbtn_actualizar){
           update();
        }
        
    }
    
}
