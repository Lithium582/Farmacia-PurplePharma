/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFazGraFica;

import Clases.*;
import Interfaces.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lithium582
 */
public class frmListarVentas extends javax.swing.JFrame {

    private final Farmacia farmaArticulos;
    /**
     * Creates new form ListarArticulos
     */
    
    public frmListarVentas() {
        initComponents();
        farmaArticulos = null;
        this.setSize(400,400);
        this.setResizable(false);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);
        
    }
    
    public frmListarVentas(Farmacia pFarma) {
        initComponents();
        
        this.dgVentas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        farmaArticulos = pFarma;
        
        this.setSize(400,400);
        this.setResizable(false);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);
        
        this.dgVentas.setRowSelectionAllowed(true);
        this.dgVentas.setRowSelectionAllowed(true);
        DefaultTableModel elementTable = (DefaultTableModel) this.dgVentas.getModel();
        
//        ILista<IArticulo> listaArticulos = new Lista<IArticulo>();
//        listaArticulos = farmaArticulos.retornarArticulos("");
        

        ILista<IMovimiento> lista = farmaArticulos.retornarVentas(0);

        if (lista != null){
            INodoLista<IMovimiento> nodoActual = lista.getPrimero();
            
        while (nodoActual != null){
            IMovimiento ventaActual = nodoActual.getObjeto();
                if (ventaActual != null){
                    String[] prodInfo = new String[12];
                    prodInfo[0] = (ventaActual.getID()).toString();
                    prodInfo[1] = ventaActual.GetFecha().toString();
                    prodInfo[2] = (ventaActual.GetIdArticulo()).toString();
                    prodInfo[3] = Double.toString(ventaActual.GetValorFinal());
                    prodInfo[4] = Integer.toString(ventaActual.GetCantidad());
                    
                    elementTable.addRow(prodInfo);
                }
            nodoActual = nodoActual.getSiguiente();
        }
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCerrarForm = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        dgVentas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listar Ventas");

        btnCerrarForm.setText("Cerrar");
        btnCerrarForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarFormActionPerformed(evt);
            }
        });

        dgVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID","Fecha","ID Articulo","Valor Final","Cantidad"
            }
        ));
        jScrollPane2.setViewportView(dgVentas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarForm)
                .addGap(402, 402, 402))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrarForm)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarFormActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarFormActionPerformed

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
            java.util.logging.Logger.getLogger(frmListarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmListarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmListarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmListarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmListarVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarForm;
    private javax.swing.JTable dgVentas;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}