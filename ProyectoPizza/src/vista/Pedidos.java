/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import modelo.Persistencia;
import modelo.PersistenciaHibernate;
import modelo.PersistenciaJDBC;
import controlador.*;
import java.awt.Frame;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author sortKrage
 */
public class Pedidos extends javax.swing.JDialog {

    static String emp, cli;
    Persistencia p;
    static Vector vArticulos, listaVentas;
    DefaultTableModel ped;
    static MainVentana padre;
    TableModelListener tml;

    /**
     * Creates new form Pedidos
     */
    public Pedidos(Frame parent, boolean modal, String emp) {
        super(parent, modal);
        this.padre = (MainVentana) parent;
        this.emp = emp;
        this.cli = null;
        this.listaVentas = new Vector();
        this.ped = new DefaultTableModel();
        this.tml = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {

                if (tme.getType() == tme.UPDATE) {

                    String data = String.valueOf(ped.getValueAt(tme.getFirstRow(), tme.getColumn()));

                    try {

                        if (data.equals("") || Integer.valueOf(data) == 0) {

                            ped.setValueAt(1, tme.getFirstRow(), tme.getColumn());

                        }
                    } catch (NumberFormatException e) {

                        ped.setValueAt(1, tme.getFirstRow(), tme.getColumn());

                    }

                    double total = 0;

                    for (int i = 0; i < ped.getRowCount(); i++) {

                        double precio = Double.parseDouble(String.valueOf(ped.getValueAt(i, 2)));
                        int cantidad = Integer.parseInt(String.valueOf(ped.getValueAt(i, 3)));

                        total = total + (precio * cantidad);
                    }

                    jTextFieldTotal.setText(String.valueOf(total));

                }

            }
        };
        initComponents();
        setLocationRelativeTo(null);

        try {
            conectar();
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Comprueba los parametros de acceso\n" + ex.getMessage(),
                    "ERROR FATAL", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        cargarTabla();

    }

    private void conectar() throws SQLException, IOException {

        Properties prop = new Properties();
        prop.load(new InputStreamReader(PersistenciaJDBC.class.getResourceAsStream("CFG.INI")));
        String s = prop.getProperty("tipoPersistencia").toLowerCase();

        switch (s) {

            case "mysqljdbc":
                p = new PersistenciaJDBC();
                break;

            case "hibernate":
                p = new PersistenciaHibernate();
                break;

            default:
                JOptionPane.showMessageDialog(this, "El tipo de persistencia no es válido (mysqlJDBC o Hibernate)",
                        "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
                System.exit(0);

        }

    }

    public Pedidos(Frame parent, boolean modal, String emp, String cli, String total, Vector listaVentas) {
        super(parent, modal);
        this.padre = (MainVentana) parent;
        this.emp = emp;
        this.cli = cli;
        this.listaVentas = listaVentas;
        this.tml = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {

                if (tme.getType() == tme.UPDATE) {

                    String data = String.valueOf(ped.getValueAt(tme.getFirstRow(), tme.getColumn()));

                    try {

                        if (data.equals("") || Integer.valueOf(data) == 0) {

                            ped.setValueAt(1, tme.getFirstRow(), tme.getColumn());

                        }
                    } catch (NumberFormatException e) {

                        ped.setValueAt(1, tme.getFirstRow(), tme.getColumn());

                    }

                    double total = 0;

                    for (int i = 0; i < ped.getRowCount(); i++) {

                        double precio = Double.parseDouble(String.valueOf(ped.getValueAt(i, 2)));
                        int cantidad = Integer.parseInt(String.valueOf(ped.getValueAt(i, 3)));

                        total = total + (precio * cantidad);
                    }

                    jTextFieldTotal.setText(String.valueOf(total));

                }

            }
        };
        initComponents();
        setLocationRelativeTo(null);
        try {
            p = new PersistenciaJDBC();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        cargarTabla();

        cargarTablaPed();

        jTextFieldTotal.setText(total);
        jTextFieldCli.setEnabled(false);
        jButtonAcep.setEnabled(false);
        jButtonFin.setText("Modificar");
        jButtonFin.setEnabled(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCli = new javax.swing.JTextField();
        jButtonAcep = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableArt = new javax.swing.JTable();
        jButtonFin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePed = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Empleado: ");

        jTextFieldEmp.setText(emp);
        jTextFieldEmp.setEnabled(false);

        jLabel2.setText("Cliente: ");

        jTextFieldCli.setText(cli);
        jTextFieldCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCliActionPerformed(evt);
            }
        });

        jButtonAcep.setText("Aceptar");
        jButtonAcep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcepActionPerformed(evt);
            }
        });

        jScrollPane1.setPreferredSize(new java.awt.Dimension(429, 190));

        jTableArt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableArt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableArtMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableArt);

        jButtonFin.setText("Finalizar");
        jButtonFin.setEnabled(false);
        jButtonFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinActionPerformed(evt);
            }
        });

        jLabel3.setText("Total:");

        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setText("0");

        jTablePed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Artículo", "Precio", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePedMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTablePed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldCli, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAcep))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(311, 311, 311)
                                .addComponent(jButtonFin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAcep)
                    .addComponent(jTextFieldCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonFin, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTablePedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePedMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {

            DefaultTableModel dtm = (DefaultTableModel) jTablePed.getModel();

            double precio = Double.parseDouble(String.valueOf(dtm.getValueAt(jTablePed.getSelectedRow(), 2)));

            double nuevo = Double.parseDouble(jTextFieldTotal.getText()) - precio;

            jTextFieldTotal.setText(String.valueOf(nuevo));

            dtm.removeRow(jTablePed.getSelectedRow());

        }

    }//GEN-LAST:event_jTablePedMouseClicked

    private void jButtonFinActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonFinActionPerformed
        // TODO add your handling code here:

        int cli = Integer.parseInt(jTextFieldCli.getText());
        int emp = Integer.parseInt(jTextFieldEmp.getText());
        double total = Double.parseDouble(jTextFieldTotal.getText());

        if (total == 0) {

            JOptionPane.showMessageDialog(this, "No has pedido ningún artículo", "ERROR", JOptionPane.WARNING_MESSAGE);

        } else {

            Vector ped = new Vector();
            ped.add(cli);
            ped.add(emp);
            ped.add(total);

            DefaultTableModel dtm = (DefaultTableModel) jTablePed.getModel();
            Vector venta = null;
            listaVentas.clear();

            for (int i = 0; i < dtm.getRowCount(); i++) {

                String cod = String.valueOf(dtm.getValueAt(i, 0));
                String nombre = String.valueOf(dtm.getValueAt(i, 1));
                String precio = String.valueOf(dtm.getValueAt(i, 2));
                String cant = String.valueOf(dtm.getValueAt(i, 3));

                venta = new Vector();
                venta.add(cod);
                venta.add(nombre);
                venta.add(precio);
                venta.add(cant);

                listaVentas.add(venta);

            }

            if (jButtonFin.getText().equals("Modificar")) {

                padre.borrarPed();

            }
            padre.addPed(ped);
            padre.addlistaVentas(listaVentas);

            this.dispose();

        }
    }// GEN-LAST:event_jButtonFinActionPerformed

    private void jTableArtMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTableArtMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() >= 2) {

            Vector art = (Vector) vArticulos.get(jTableArt.getSelectedRow());

            ped = (DefaultTableModel) jTablePed.getModel();

            int cnt = 0;
            boolean rep = false;

            for (int i = 0; i < ped.getRowCount(); i++) {
                if (String.valueOf(ped.getValueAt(i, 0)).equals(art.get(0))) {
                    cnt++;
                    if (cnt == 1) {

                        int cant = Integer.parseInt(String.valueOf(ped.getValueAt(i, 3)));

                        ped.setValueAt(cant + 1, i, 3);

                        rep = true;

                    }

                }

            }

            if (!rep) {

                ped.addRow(new Object[]{art.get(0), art.get(1), art.get(2), 1});

                ped.addTableModelListener(tml);

                double total = Double.parseDouble(jTextFieldTotal.getText()) + Double.parseDouble((String) art.get(2));

                jTextFieldTotal.setText(String.valueOf(total));
            }

        }

    }// GEN-LAST:event_jTableArtMouseClicked

    private void jTextFieldCliActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldCliActionPerformed

        if (!jTextFieldCli.getText().matches("[0-9]{1,6}")) {

            JOptionPane.showMessageDialog(this, "El código debe ser un número de máximo 6 digitos",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            jTextFieldCli.setText(null);
            jTextFieldCli.grabFocus();

        } else {

            try {

                ArrayList<Cliente> consulta = p.consultarCli(Integer.parseInt(jTextFieldCli.getText()));

                if (consulta.isEmpty()) {

                    JDialog i = new Clientes(null, true, jTextFieldCli.getText());
                    i.setVisible(true);

                } else {

                    jTextFieldCli.setEnabled(false);
                    jButtonAcep.setEnabled(false);
                    jButtonFin.setEnabled(true);

                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());

            }
        }

    }// GEN-LAST:event_jTextFieldCliActionPerformed

    private void jButtonAcepActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonAcepActionPerformed
        // TODO add your handling code here:

        jTextFieldCliActionPerformed(evt);

    }// GEN-LAST:event_jButtonAcepActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pedidos dialog = new Pedidos(new javax.swing.JFrame(), true, emp);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);

                    }

                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAcep;
    private javax.swing.JButton jButtonFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableArt;
    private javax.swing.JTable jTablePed;
    private javax.swing.JTextField jTextFieldCli;
    private javax.swing.JTextField jTextFieldEmp;
    private javax.swing.JTextField jTextFieldTotal;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * Este método crea un objeto de la clase "DefaultTableModel", el
     * constructor recibe un Vector de Vectores con los datos y un Vector simple
     * con los nombres de las columnas
     *
     */
    private void cargarTabla() {

        try {

            Vector vTitColum = new Vector();
            vTitColum.add("Código");
            vTitColum.add("Nombre");
            vTitColum.add("Precio");

            vArticulos = p.cargarArt();

            DefaultTableModel dtm = new DefaultTableModel(vArticulos, vTitColum) {

                @Override
                public boolean isCellEditable(int row, int column) {

                    return false;
                }

            };

            jTableArt.setModel(dtm);
            jTableArt.setSelectionMode(0);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());

        }
    }

    private void cargarTablaPed() {

        Vector titPed = new Vector();
        titPed.addElement("Código");
        titPed.addElement("Artículo");
        titPed.addElement("Precio");
        titPed.addElement("Cantidad");

        Vector data = new Vector();

        for (int i = 0; i < listaVentas.size(); i++) {

            Vector ped = (Vector) listaVentas.get(i);
            Vector fila = new Vector();

            fila.add(ped.get(0));
            fila.add(ped.get(1));
            fila.add(ped.get(2));
            fila.add(ped.get(3));

            data.add(fila);

        }

        ped = new DefaultTableModel(data, titPed) {

            @Override
            public boolean isCellEditable(int row, int column) {

                if (column == 3) {

                    return true;

                }

                return false;
            }

        };

        ped.addTableModelListener(tml);

        jTablePed.setModel(ped);
    }

}
