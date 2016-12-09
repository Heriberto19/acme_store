
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sql.Sql;
import views.Productos_Restaura;

/**
 *
 * @author Lenovo
 */
public class Controller_Restaura_Producto extends javax.swing.JFrame implements ActionListener {

    Productos_Restaura restaura;
    Sql cc = new Sql();
    Connection cn = cc.conexion();
    String Sql = "";
    public Controller_Restaura_Producto(Productos_Restaura restaura) {
        this.restaura = restaura;
        this.restaura.setVisible(true);
        this.restaura.setLocationRelativeTo(null);
        this.restaura.setTitle("Papelera de reciclaje");

        this.restaura.jbtn_buscar_id.addActionListener(this);
        this.restaura.jbtn_buscar_producto.addActionListener(this);
        this.restaura.jbtn_todo.addActionListener(this);
        this.restaura.jMenueliminar.addActionListener(this);
        this.restaura.jMenurestaurar.addActionListener(this);
    }

    void Buscarproducto(String productos) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Producto");
        modelo.addColumn("Descripción");
        modelo.addColumn("Fecha_caducidad");
        modelo.addColumn("Precio Compra");
        modelo.addColumn("Precio Venta");
        modelo.addColumn("Existencia");
       
        if (productos.equals("")) {
            Sql=( "SELECT * FROM Productos where visible='"+0+"' ");
        } else {
           Sql=("SELECT * FROM Productos WHERE Producto='" + productos + "' and visible='"+0+"' ");
        }

        String[] columna = new String[7];
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
                modelo.addRow(columna);
            }
            this.restaura.jtbl_Restaura_productos.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(restaura, ex);
        }
    }


   void Buscarid(String value) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Producto");
        modelo.addColumn("Descripción");
        modelo.addColumn("Fecha_caducidad");
        modelo.addColumn("Precio Compra");
        modelo.addColumn("Precio Venta");
        modelo.addColumn("Existencia");
        String sql = "";
                if (value.equals("")) {
                    Sql=("SELECT * FROM Productos where visible='"+0+"'");
                } else {
               
                    Sql=("SELECT * FROM Productos WHERE id_producto='" + value + "' and Visible='"+ 0 +"'");
                }

                String[] columna = new String[7];
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
                        modelo.addRow(columna);
                    }
                    this.restaura.jtbl_Restaura_productos.setModel(modelo);
                   
                } catch (SQLException ex) {
                JOptionPane.showMessageDialog(restaura, ex);
                 }
    }
   
    void restaurar() throws SQLException {
        int fila = restaura.jtbl_Restaura_productos.getSelectedRow();
        
        String id =( restaura.jtbl_Restaura_productos.getValueAt(fila, 0).toString());
        int i = new Integer(id);
        int p = JOptionPane.showConfirmDialog(null, "Estas seguro de restaurar este dato", "Restaurar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (p == 0) {
            try {
                PreparedStatement pst = cn.prepareStatement("UPDATE Productos SET visible='" + 1 + "' WHERE  id_producto='" + i + "'");
                pst.executeUpdate();

                Buscarid("");
            

        } catch (ArrayIndexOutOfBoundsException e) {
         JOptionPane.showConfirmDialog(null, "No es posible restaurar este dato");
        
        }
        }
    }

    void elimina() {
        int fila = restaura.jtbl_Restaura_productos.getSelectedRow();
        String id = "";
        id = restaura.jtbl_Restaura_productos.getValueAt(fila, 0).toString();
        int p = JOptionPane.showConfirmDialog(null, "Estas seguro de restaurar este dato", "Elimina", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (p == 0) {
            try {
                PreparedStatement pst = cn.prepareStatement("DELETE FROM Productos WHERE  codigo='" + id + "'");
                pst.executeUpdate();

                Buscarid("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(restaura, "Tu archivo se ha eliminado exitosamente");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.restaura.jbtn_buscar_id) {
            Buscarid(restaura.jtxt_busca_id.getText());
        } else if (e.getSource() == this.restaura.jbtn_buscar_producto) {
            Buscarproducto(restaura.jtxt_busca_producto.getText());
        } else if (e.getSource() == this.restaura.jbtn_todo) {
            Buscarid("");
        } else if (e.getSource() == this.restaura.jMenueliminar) {
            elimina();
        } else if (e.getSource() == this.restaura.jMenurestaurar) {
            try {
                restaurar();
            } catch (SQLException ex) {
                Logger.getLogger(Controller_Restaura_Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }

}
