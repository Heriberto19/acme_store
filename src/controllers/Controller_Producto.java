package controllers;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import javax.swing.table.DefaultTableModel;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import views.View_Producto;
import models.Model_Producto;
import solo_letras.letras;
import sql.Sql;

/**
 *
 * @author Lenovo
 */
public class Controller_Producto extends javax.swing.JFrame implements ActionListener {

    letras letra = new letras();
    View_Producto producto;
    Model_Producto mp;
    Sql sql = new Sql();
    Connection cn = sql.conexion();
    DateFormat df = DateFormat.getDateInstance();
    String fechaobt = "";
    Image icon = new ImageIcon(getClass().getResource("/imges/icono_producto.png")).getImage();

    public Controller_Producto(View_Producto producto, Model_Producto mp) {
        this.mp = mp;
        this.producto = producto;
        this.producto.setVisible(true);
        this.producto.setTitle(mp.getTitle());
        this.producto.setIconImage(icon);
        init_view();
    }

    void init_view() {
        this.producto.jtxt_id.setVisible(false);
        this.producto.jl_id.setVisible(false);
        this.producto.setLocationRelativeTo(this);
        this.producto.jbtn_buscar_id.addActionListener(this);
        this.producto.jbtn_buscar_producto.addActionListener(this);
        this.producto.jbtn_save.addActionListener(this);
        this.producto.jbtn_update.addActionListener(this);
        this.producto.jMenueliminar.addActionListener(this);
        this.producto.jMenumodificar.addActionListener(this);
        this.producto.jbtn_todo.addActionListener(this);
        this.producto.jbtn_update.setEnabled(false);
        this.producto.jtx_producto.addKeyListener(mp.letras);
        this.producto.jtxt_precio_compra.addKeyListener(mp.numeros);
        this.producto.jtxt_precio_venta.addKeyListener(mp.numeros);
        this.producto.jtxt_existencia.addKeyListener(mp.numeros);
        this.producto.jtxt_busca_id.addKeyListener(mp.numeros);
        this.producto.jtxt_busca_producto.addKeyListener(mp.letras);
    }

    void save() {
        try {
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO productos (Producto,"
                    + "Descripcion,"
                    + "Fecha_Caducidad,"
                    + "Precio_compra,"
                    + "Precio_venta,"
                    + "Existencia,"
                    + "Visible) VALUES (?,?,?,?,?,?,?)");
                    pst.setString(1, producto.jtx_producto.getText());
                    pst.setString(2, producto.jtxt_descripcion.getText());
                    fechaobt = df.format(producto.jct_fecha.getDate());
                    pst.setString(3, fechaobt);
                    pst.setString(4, producto.jtxt_precio_compra.getText());
                    pst.setString(5, producto.jtxt_precio_venta.getText());
                    pst.setString(6, producto.jtxt_existencia.getText());
                    pst.setString(7, "1");
                    pst.executeUpdate();
                    Buscarid("");
                    JOptionPane.showMessageDialog(producto, mp.getMessage1());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(producto, mp.getMessage2());
                }
                producto.jtx_producto.setText(mp.getDelete());
                producto.jtxt_descripcion.setText(mp.getDelete());
                producto.jtxt_precio_compra.setText(mp.getDelete());
                producto.jtxt_precio_venta.setText(mp.getDelete());
                producto.jtxt_existencia.setText(mp.getDelete());
                producto.jct_fecha.setToolTipText(mp.getDelete());
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
                    mp.setSql("SELECT * FROM Productos where visible='"+1+"'");
                } else {
               
                    mp.setSql("SELECT * FROM Productos WHERE id_producto='" + value + "' and Visible='"+ 1 +"'");
                }

                String[] columna = new String[7];
                try {
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(mp.getSql());
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
                    this.producto.jtbl_productos.setModel(modelo);
                   
                } catch (SQLException ex) {
                JOptionPane.showMessageDialog(producto, ex);
                 }
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
            mp.setSql( "SELECT * FROM Productos where visible='"+1+"' ");
        } else {
            mp.setSql("SELECT * FROM Productos WHERE Producto='" + productos + "' and visible='"+1+"' ");
        }

        String[] columna = new String[7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(mp.getSql());
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
            this.producto.jtbl_productos.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(producto, ex);
        }
    }

    void update() {
        boolean desbloquea = true;
        producto.jbtn_save.setEnabled(desbloquea);
        producto.jbtn_update.setEnabled(false);
        fechaobt = df.format(producto.jct_fecha.getDate());
                    
        try {
            PreparedStatement pst = cn.prepareStatement("UPDATE Productos SET  "
                    + "Producto='" + producto.jtx_producto.getText() + "',"
                    + "Descripcion='" + producto.jtxt_descripcion.getText() + "',"
                    + "Fecha_caducidad='" + fechaobt+ "',"
                    + "Precio_compra='" + producto.jtxt_precio_compra.getText() + "',"
                    + "Precio_venta='" + producto.jtxt_precio_venta.getText() + "',"
                    + "Existencia='" + producto.jtxt_existencia.getText() + "' "
                    + "WHERE id_producto='" + producto.jtxt_id.getText() + "'");
            pst.executeUpdate();
            Buscarid("");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
            producto.jtx_producto.setText("");
            producto.jtxt_descripcion.setText("");
            producto.jct_fecha.setDateFormatString("");
            producto.jtxt_precio_compra.setText("");
            producto.jtxt_precio_venta.setText("");
            producto.jtxt_existencia.setText("");
    }

    void modifica() {
        producto.jbtn_save.setEnabled(false);
        producto.jbtn_update.setEnabled(true);
        int fila = producto.jtbl_productos.getSelectedRow();
        if (fila >= 0) {
            producto.jtxt_id.setText(producto.jtbl_productos.getValueAt(fila, 0).toString());
            producto.jtx_producto.setText(producto.jtbl_productos.getValueAt(fila, 1).toString());
            producto.jtxt_descripcion.setText(producto.jtbl_productos.getValueAt(fila, 2).toString());
            producto.jct_fecha.setToolTipText(producto.jtbl_productos.getValueAt(fila, 3).toString());
            producto.jtxt_precio_compra.setText(producto.jtbl_productos.getValueAt(fila, 4).toString());
            producto.jtxt_precio_venta.setText(producto.jtbl_productos.getValueAt(fila, 5).toString());
            producto.jtxt_existencia.setText(producto.jtbl_productos.getValueAt(fila, 6).toString());
        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
    }
    
    void elimina() {
        int fila = producto.jtbl_productos.getSelectedRow();
        
       String Id=( producto.jtbl_productos.getValueAt(fila, 0).toString());
        int p = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (p == 0) {
            try {
                PreparedStatement pst = cn.prepareStatement("UPDATE Productos SET visible='" + 0 + "' WHERE  "
                        + "Id_producto='" + Id + "'");
                pst.executeUpdate();

                Buscarid("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(producto, "No es posible eliminar este dato");
            }
        }
    }
        
    void deleted() throws SQLException {
        int fila = producto.jtbl_productos.getSelectedRow();
        
        String id =( producto.jtbl_productos.getValueAt(fila, 0).toString());
        int p = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar este dato", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (p == 0) {
            try {
                PreparedStatement pst = cn.prepareStatement("UPDATE Productos SET visible='" + 0 + "' WHERE  id_producto='" + id + "'");
                pst.executeUpdate();

                Buscarid("");
            

        } catch (ArrayIndexOutOfBoundsException e) {
         JOptionPane.showConfirmDialog(null, "No es posible eliminar este dato");
        
        }
        
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.producto.jbtn_save) {
            if (producto.jtx_producto.getText().equals("") || producto.jtxt_descripcion.getText().equals("") || producto.jtxt_precio_compra.getText().equals("") || producto.jtxt_precio_venta.getText().equals("") || producto.jtxt_existencia.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Falta llenar algunos campos, por favor llenalos ");
            } else if (e.getSource() == this.producto.jbtn_save) {
                save();
            }

        } else if (e.getSource() == this.producto.jbtn_buscar_id) {
            if (producto.jtxt_busca_id.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingresa datos");
            } else {
                Buscarid(producto.jtxt_busca_id.getText());
                producto.jtxt_busca_id.setText("");
            }
        } else if (e.getSource() == this.producto.jbtn_buscar_producto) {
            if (producto.jtxt_busca_producto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingresa datos");
            } else {
                Buscarproducto(producto.jtxt_busca_producto.getText());
                producto.jtxt_busca_producto.setText("");
            }
        } else if (e.getSource() == this.producto.jbtn_update) {
            update();
        } else if (e.getSource() == this.producto.jMenumodificar) {
            modifica();
        } else if (e.getSource() == this.producto.jMenueliminar) {
           elimina();
        } else if (e.getSource() == this.producto.jbtn_todo) {
            Buscarid("");
        }

    }
    
   
}
