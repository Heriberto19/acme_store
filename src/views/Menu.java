/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author Lenovo
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jm_Menu = new javax.swing.JMenuItem();
        jm_Cerrar_sesion = new javax.swing.JMenuItem();
        jm_Iniciar_sesion = new javax.swing.JMenuItem();
        jm_registra_usuario = new javax.swing.JMenuItem();
        jm_restauracion = new javax.swing.JMenuItem();
        jm_editar_usuario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jm_acerca_de = new javax.swing.JMenuItem();
        jm_ayuda_producto = new javax.swing.JMenu();
        jm_ayuda_productos = new javax.swing.JMenuItem();
        jm_user = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jp_menu.setBackground(new java.awt.Color(255, 204, 204));
        jp_menu.setMaximumSize(new java.awt.Dimension(1000, 1000));

        javax.swing.GroupLayout jp_menuLayout = new javax.swing.GroupLayout(jp_menu);
        jp_menu.setLayout(jp_menuLayout);
        jp_menuLayout.setHorizontalGroup(
            jp_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 851, Short.MAX_VALUE)
        );
        jp_menuLayout.setVerticalGroup(
            jp_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/options_14771.png"))); // NOI18N
        jMenu1.setText("Opciones ");

        jm_Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/filesystems_thestartupfolder_648 (1).png"))); // NOI18N
        jm_Menu.setText("Menu");
        jMenu1.add(jm_Menu);

        jm_Cerrar_sesion.setText("Cerrar sesión ");
        jMenu1.add(jm_Cerrar_sesion);

        jm_Iniciar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/user_12821.png"))); // NOI18N
        jm_Iniciar_sesion.setText("Iniciar sesion ");
        jMenu1.add(jm_Iniciar_sesion);

        jm_registra_usuario.setText("Registrar usuario");
        jMenu1.add(jm_registra_usuario);

        jm_restauracion.setText("Restauración ");
        jMenu1.add(jm_restauracion);

        jm_editar_usuario.setText("Editar Usuario");
        jMenu1.add(jm_editar_usuario);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/businessapplications_information_briefcase_negoci_2317 (1).png"))); // NOI18N
        jMenu2.setText("Información ");

        jm_acerca_de.setText("Acerca de..");
        jMenu2.add(jm_acerca_de);

        jMenuBar1.add(jMenu2);

        jm_ayuda_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/help_support_22593 (1).png"))); // NOI18N
        jm_ayuda_producto.setText("Ayuda");

        jm_ayuda_productos.setText("Productos");
        jm_ayuda_producto.add(jm_ayuda_productos);

        jMenuBar1.add(jm_ayuda_producto);

        jm_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/user_12821.png"))); // NOI18N
        jm_user.setText("Bienvenido :");
        jMenuBar1.add(jm_user);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jm_Cerrar_sesion;
    public javax.swing.JMenuItem jm_Iniciar_sesion;
    public javax.swing.JMenuItem jm_Menu;
    public javax.swing.JMenuItem jm_acerca_de;
    private javax.swing.JMenu jm_ayuda_producto;
    public javax.swing.JMenuItem jm_ayuda_productos;
    public javax.swing.JMenuItem jm_editar_usuario;
    public javax.swing.JMenuItem jm_registra_usuario;
    public javax.swing.JMenuItem jm_restauracion;
    public javax.swing.JMenu jm_user;
    public javax.swing.JPanel jp_menu;
    // End of variables declaration//GEN-END:variables
}
