package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.View_Clientes;
import libs.Conectar;

public class Controller_Clientes extends javax.swing.JFrame implements ActionListener{
 View_Clientes cliente;
 Conectar cc = new Conectar();
 Connection cn = cc.conexion();
 
public Controller_Clientes (View_Clientes Clientes){
    this.cliente = Clientes;
    this.cliente.setVisible(true);
    this.cliente.jtxt_id.setVisible(false);
    this.cliente.jl_id.setVisible(false);
    this.cliente.setLocationRelativeTo(this);
    this.cliente.jbtn_buscar_id.addActionListener(this);
    this.cliente.jbtn_buscar_cliente.addActionListener(this);
    this.cliente.jbtn_save.addActionListener(this);
    this.cliente.jbtn_update.addActionListener(this);
    this.cliente.jMenueliminar.addActionListener(this);
    this.cliente.jMenumodificar.addActionListener(this);
    this.cliente.jbtn_todo.addActionListener(this);
 } 
 
void save(){
   try {
       PreparedStatement pst = cn.prepareStatement("INSERT INTO  (Cliente,nombre,Apellido_Paterno,Apellido_Materno,Telefono,e_Mail,RFC,Calle,No.,Colonia,Ciudad,Estado) VALUES (?,?,?,?,?)");
            pst.setString(1, cliente.jtx_nom.getText());
            pst.setString(2, cliente.jtx_ap_pa.getText());
            pst.setString(3, cliente.jtx_ap_ma.getText());
            pst.setString(4, cliente.jtxt_email.getText());
            pst.setString(5, cliente.jtxt_rfc.getText());
            pst.setString(6, cliente.jtxt_calle.getText());
            pst.setString(7, cliente.jtxt_no.getText());
            pst.setString(8, cliente.jtxt_col.getText());
            pst.setString(9, cliente.jtxt_ciu.getText());
            pst.setString(10, cliente.jtxt_edo.getText());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        cliente.jtx_nom.setText("");
        cliente.jtx_ap_ma.setText("");
        cliente.jtx_tel.setText("");
        cliente.jtxt_email.setText("");
        cliente.jtxt_rfc.setText("");
    }
 
void Buscarid(String valor){
   DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO PATERNO");
        modelo.addColumn("APELLIDO MATERNO");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("E-MAIL");
        modelo.addColumn("RFC");
        modelo.addColumn("CALLE");
        modelo.addColumn("NO");
        modelo.addColumn("COLONIA");
        modelo.addColumn("CIUDAD");
        modelo.addColumn("ESTADO");
        this.cliente.jtbl_clientes.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM Cliente";
        } else {
            sql = "SELECT * FROM Clientes WHERE Id_Cliente='" + valor + "'";
        }

        String[] datos = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }
            this.cliente.jtbl_clientes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }  
 }

void Buscarproducto(String valor){
   DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO PATERNO");
        modelo.addColumn("APELLIDO MATERNO");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("E-MAIL");
        modelo.addColumn("RFC");
        modelo.addColumn("CALLE");
        modelo.addColumn("NO");
        modelo.addColumn("COLONIA");
        modelo.addColumn("CIUDAD");
        modelo.addColumn("ESTADO");
        this.cliente.jtbl_clientes.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM Clientes";
        } else {
            sql = "SELECT * FROM Clientes WHERE Cliente='" + valor + "'";
        }

        String[] datos = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }
            this.cliente.jtbl_clientes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }  
 }

void update (){
    boolean desbloquea = true;
    
    cliente.jbtn_save.setEnabled(desbloquea);
    cliente.jbtn_update.setEnabled(false);
    try {
       PreparedStatement pst = cn.prepareStatement("UPDATE Clientes SET  Cliente='" + cliente.jtx_nom.getText() + "',Nombre='" + cliente.jtx_ap_pa.getText() + "',Apellido Paterno='" + cliente.jtx_ap_pa.getText() + "',Apellido Materno='" + cliente.jtx_tel.getText() + "',Telefono='" + cliente.jtxt_email.getText()  +  "' WHERE Id_Producto='" + cliente.jtxt_id.getText() + "'");
       pst.executeUpdate();
        Buscarid("");
       } catch (Exception e) {
         System.out.print(e.getMessage());
        } 
        cliente.jtx_nom.setText("");
        cliente.jtx_ap_ma.setText("");
        cliente.jtx_tel.setText("");
        cliente.jtxt_email.setText("");
        cliente.jtxt_rfc.setText("");
}

void modifica(){
    boolean bloquea = false;
    cliente.jbtn_save.setEnabled(bloquea);
    int fila = cliente.jtbl_clientes.getSelectedRow();
    if (fila >= 0) {
        cliente.jtxt_id.setText(cliente.jtbl_clientes.getValueAt(fila, 0).toString());
        cliente.jtx_nom.setText(cliente.jtbl_clientes.getValueAt(fila, 1).toString());
        cliente.jtx_ap_ma.setText(cliente.jtbl_clientes.getValueAt(fila, 2).toString());
        cliente.jtx_tel.setText(cliente.jtbl_clientes.getValueAt(fila, 3).toString());
        cliente.jtxt_email.setText(cliente.jtbl_clientes.getValueAt(fila, 4).toString());
        cliente.jtxt_rfc.setText(cliente.jtbl_clientes.getValueAt(fila, 5).toString());
    } else {
        JOptionPane.showMessageDialog(null, "no seleciono fila");
    }
}

void elimina (){
    int fila = cliente.jtbl_clientes.getSelectedRow();
    String id = "";
    id = cliente.jtbl_clientes.getValueAt(fila, 0).toString();
    int p =JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar","Eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
    if (p ==0){
    try {
      PreparedStatement pst = cn.prepareStatement("DELETE FROM Cliente WHERE  Id_Cliente='" + id + "'");
        
        pst.executeUpdate();
        Buscarid("");
    
    } catch (Exception e) {
    }
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== this.cliente.jbtn_save){
            save();
        }
        
        else if (e.getSource() == this.cliente.jbtn_buscar_id){
             if(cliente.jtxt_busca_id.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Ingresa datos");
             }
             else {
               Buscarid(cliente.jtxt_busca_id.getText()); 
               cliente.jtxt_busca_id.setText("");
             }
        }
        
        else if (e.getSource() == this.cliente.jbtn_buscar_cliente){
             if(cliente.jtxt_busca_cliente.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Ingresa datos");
             }
             else {
               Buscarproducto(cliente.jtxt_busca_cliente.getText()); 
               cliente.jtxt_busca_cliente.setText("");
             }
        }
        
        else if (e.getSource() == this.cliente.jbtn_update){
            update();
        }
        
        else if (e.getSource() == this.cliente.jMenumodificar){
            modifica();
        }
        
        else if (e.getSource() == this.cliente.jMenueliminar){
           elimina();
        }
        
        else if (e.getSource() == this.cliente.jbtn_todo){
            Buscarid("");
        }
        
            
     }


}
