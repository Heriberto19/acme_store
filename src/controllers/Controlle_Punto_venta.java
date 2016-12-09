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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleConstants;
import libs.Conectar;
import views.Punto_Venta;
import views.Productos_Encotrados;
import models.Model_PV;
import views.Clientes_encontrados;
/**
 *
 * @author Lenovo
 */
public class Controlle_Punto_venta implements ActionListener{
    Punto_Venta punto_Venta;
    Productos_Encotrados productos_Encotrados;
    Model_PV model_PV;
    Clientes_encontrados  ce;
    String sql;
    Conectar cc = new Conectar();
    Connection cn = cc.conexion();
    
 public Controlle_Punto_venta(Punto_Venta punto_Venta, Productos_Encotrados productos_Encotrados, Model_PV model_PV, Clientes_encontrados ce){
     this.punto_Venta = punto_Venta;
     this.model_PV =  model_PV;
     this.ce = ce;
     this.punto_Venta.jtxt_id.setVisible(false);
     this.productos_Encotrados =  productos_Encotrados;
     this.punto_Venta.setVisible(true);
     this.punto_Venta.jtxt_iva.setText(""+model_PV.getIvapro());
     this.punto_Venta.setLocationRelativeTo(null);
     this.punto_Venta.jbtn_agregar.addActionListener(this);
     this.productos_Encotrados.jm_agregar.addActionListener(this);
     this.ce.jbtn_buscar.addActionListener(this);
     this.punto_Venta.jbtn_cliente.addActionListener(this);
     this.ce.jbtn_buscar.addActionListener(this);
     this.ce.jm_agregarcliente.addActionListener(this);
     this.punto_Venta.jbtn_realizar_venta.addActionListener(this);
     this.punto_Venta.jpop_quitar.addActionListener(this);
     fecha();
 }
 
 void busca_producto(String value){

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("PRECIO VENTA");
        modelo.addColumn("EXISTENCIA");
       
        String sql = "";
        if (value.equals("")) {
            sql = "SELECT * FROM Productos";
        } else {
            sql = "SELECT * FROM Productos WHERE Producto='" + value+ "'";
        }

        String[] datos = new String[5];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(6);
                datos[4] = rs.getString(7);
                modelo.addRow(datos);
            }
            this.productos_Encotrados.jtbl_productos_encotrados.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 void busca_cliente(String cliente){
     DefaultTableModel model = new DefaultTableModel();
     model.addColumn("Id");
     model.addColumn("Nombre");
     model.addColumn("Apellido P");
     model.addColumn("Apellido M");
     model.addColumn("Telefono");
     model.addColumn("Email");
     model.addColumn("RFC");
     String sql ="";
     if (sql.equals("")){
         sql= "Select * From Cliente";
     }else {
         sql="Select * from cliente where Nombre='"+cliente+"'";
     }
     
     String [] dato = new String [7];
    try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                dato[5] = rs.getString(6);
                dato[6] = rs.getString(7);
                model.addRow(dato);
            }
            this.ce.jtbl_cliente.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
 }
 public  void agregar(){
        DefaultTableModel modelo = (DefaultTableModel) punto_Venta.jtbl_venta.getModel();
         int fila=  productos_Encotrados.jtbl_productos_encotrados.getSelectedRow();
         String can = JOptionPane.showInputDialog(punto_Venta, "Cantidad");
         int cantidad =  Integer.valueOf(can);
        int produc =Integer.parseInt((String) productos_Encotrados.jtbl_productos_encotrados.getValueAt(fila, 4));
        if (cantidad < produc){
        String registro [] =  new String[6];
        
        registro [0]= productos_Encotrados.jtbl_productos_encotrados.getValueAt(fila, 0).toString();
        registro [1]= productos_Encotrados.jtbl_productos_encotrados.getValueAt(fila, 1).toString();
        registro [2]= productos_Encotrados.jtbl_productos_encotrados.getValueAt(fila, 2).toString();
        registro [3]= productos_Encotrados.jtbl_productos_encotrados.getValueAt(fila, 3).toString();
        String pre = productos_Encotrados.jtbl_productos_encotrados.getValueAt(fila, 3).toString();
       
        
        int precio = Integer.valueOf(pre);
        int importe= cantidad * precio;
        String importes = String.valueOf(importe);
        registro [4]= (can);
        registro [5]= (importes);
        String sub = punto_Venta.jtxt_subtotal.getText();
        int subto= Integer.parseInt(sub);
        importe = subto + importe;
        
        punto_Venta.jtxt_subtotal.setText(""+importe);
        
        modelo.addRow(registro);
        String subt = punto_Venta.jtxt_subtotal.getText();
        int subtal = Integer.valueOf(subt);
        punto_Venta.jtxt_total.setText(""+ ((subtal* model_PV.getIva())+ subtal));
        }else {
            JOptionPane.showMessageDialog(punto_Venta, "Existen menos productos en el almacen \n de los"
                    + "que se desean vender");
        }
        
}
  
void agregar_cliente(){
    DefaultTableModel modelo = new DefaultTableModel();
    String registro [] =  new String[6];
    int fila=  ce.jtbl_cliente.getSelectedRow();
    registro [0]= ce.jtbl_cliente.getValueAt(fila, 0).toString();
    registro [1]= ce.jtbl_cliente.getValueAt(fila, 1).toString();
    registro [2]= ce.jtbl_cliente.getValueAt(fila, 2).toString();
    registro [3]= ce.jtbl_cliente.getValueAt(fila, 3).toString();
    registro [4]= ce.jtbl_cliente.getValueAt(fila, 5).toString();
    registro [5]= ce.jtbl_cliente.getValueAt(fila, 6).toString();
    punto_Venta.jtxt_id.setText(registro [0]);
    punto_Venta.jtx_cliente.setText(registro [1]+"  "+ registro [2]+ " " + registro [3]);
    punto_Venta.jtx_direccion.setText(registro [4]);
    punto_Venta.jtx_codigo.setText(registro [5]);
    
}

void id_venta(){
   sql="Select id_venta from venta";
   try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int consulta = Integer.getInteger(sql);
            int consultas= consulta+1;
            punto_Venta.jtx_id_venta.setText(""+consultas);
   }catch(SQLException ex){
       
   }
}
void fecha(){
    Calendar cal = Calendar.getInstance();
    String fecha=cal.get(cal.DATE)+"/"+cal.get(cal.MONTH)+"/"+cal.get(cal.YEAR);
    this.punto_Venta.jtx_fecha.setText(fecha);
    
}
void save_venta(){
    
    try {
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO venta (Fecha,"
                    + "Id_cliente,"
                    + "Subtotal,"
                    + "Iva,"
                    + "Total) VALUES (?,?,?,?,?)");
                 pst.setString(1, punto_Venta.jtx_fecha.getText());
                 pst.setString(2, punto_Venta.jtxt_id.getText());
                 pst.setString(3, punto_Venta.jtxt_subtotal.getText());
                 pst.setString(4, punto_Venta.jtxt_iva.getText());
                 pst.setString(5, punto_Venta.jtxt_total.getText());
                 pst.executeUpdate();
                    JOptionPane.showMessageDialog(punto_Venta,"Venta realizada con exito");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(punto_Venta,"Ocurrio un error al realizar la venta");
                }
}
void save_detalle_venta(){
  int fila = punto_Venta.jtbl_venta.getRowCount();

  try {
         if (fila >= 0) {    
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO detalle_venta (Id_venta,"
                    + "Id_Producto,"
                    + "Cantidad,"
                    + "Total_Producto,"
                    + "Precio,"
                    + "user) VALUES (?,?,?,?,?,?)");
                int r =0;
                int j;
                for (j=0; j<= (fila-1); j++){
                  
                
                 pst.setString(1, "1");
                 pst.setString(2+r,(String) punto_Venta.jtbl_venta.getValueAt(j, 0));
                 pst.setString(3+r, punto_Venta.jtbl_venta.getValueAt(j, 4).toString());
                 pst.setString(4+r, punto_Venta.jtbl_venta.getValueAt(j, 5).toString());
                 pst.setString(5+r, punto_Venta.jtbl_venta.getValueAt(j, 3).toString());
                 pst.setString(6, "heriberto");
                 
                }
                pst.executeUpdate();
                j=j+1;
                return;
                
                
            }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(punto_Venta,e);
                }
}
void valida(){
    int fila = productos_Encotrados.jtbl_productos_encotrados.getRowCount();
    String can = JOptionPane.showInputDialog(punto_Venta, "Cantidad");
    int cantidad =  Integer.valueOf(can);
    
        if (fila >= 0) {
            String existencia =productos_Encotrados.jtbl_productos_encotrados.getValueAt(fila, 5).toString();
            if (fila < cantidad){
                JOptionPane.showMessageDialog(punto_Venta, "Excelente");
            }
        }
        
}

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == punto_Venta.jbtn_agregar){
           this.productos_Encotrados.setVisible(true);
           this.productos_Encotrados.setLocationRelativeTo(punto_Venta);
           busca_producto(punto_Venta.jtxt_producto.getText());
       }
       else if(e.getSource() == productos_Encotrados.jm_agregar){
          
          agregar();
       }
       else if (e.getSource()== punto_Venta.jbtn_cliente){
           Clientes_encontrados.getWindows();
           ce.setVisible(true);
           ce.setLocationRelativeTo(punto_Venta);
           busca_cliente(punto_Venta.jtx_cliente.getText());
       }
       
       else if (e.getSource()== ce.jbtn_buscar){
           busca_cliente(ce.jtxt_nombre.getText());
       }
       else if (e.getSource()== ce.jm_agregarcliente){
           agregar_cliente();
       }
       else if (e.getSource()== punto_Venta.jbtn_realizar_venta){
           save_venta();
           save_detalle_venta();
       }
       else if (e.getSource()== punto_Venta.jpop_quitar){
         
               String user = JOptionPane.showInputDialog(punto_Venta, "Ingresa el usuario administrador",JOptionPane.OK_OPTION);
               String pass = JOptionPane.showInputDialog(punto_Venta, "Ingresa la contraseña");
           if (user.equals(0)){
           try {
               sql=("select user,password, tipo from usuarios where user='"+user+"' and password='"+pass+"'  and tipo='Administrador'");    
               Statement s =cn.createStatement();
               ResultSet rs= s.executeQuery(sql);
               if(user.equalsIgnoreCase("")|| pass.equalsIgnoreCase("")){
                 JOptionPane.showMessageDialog(null,"Falto Ingresar Datos");
           
             }else{
           
                if(rs.next() && "Administrador".equals(rs.getString("tipo")) ){
                    DefaultTableModel modelo = (DefaultTableModel) punto_Venta.jtbl_venta.getModel();
                    int fila= punto_Venta.jtbl_venta.getSelectedRow();
                    modelo.removeRow(fila);
                }else {
                    JOptionPane.showMessageDialog(null, "Datos Incorrectos");
                    
                }
            }
           } catch (SQLException ex) {
               Logger.getLogger(Controlle_Punto_venta.class.getName()).log(Level.SEVERE, null, ex);
           }
           DefaultTableModel modelo = (DefaultTableModel) punto_Venta.jtbl_venta.getModel();
                    int fila= punto_Venta.jtbl_venta.getSelectedRow();
                    modelo.removeRow(fila);
       }
    }
    }
}
