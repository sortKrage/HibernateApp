/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Articulo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Persistencia;
import modelo.PersistenciaHibernate;
import modelo.PersistenciaJDBC;

/**
 *
 * @author alumno
 */
public class Articulos extends javax.swing.JDialog {

    Persistencia p;

    /**
     * Creates new form Articulos
     */
    public Articulos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        try {
            conectar();
        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Comprueba los parametros de acceso\n" + ex.getMessage(),
                    "ERROR FATAL", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            
        }

        initComponents();
        setLocationRelativeTo(null);
        jPanel1.setVisible(false);
        jLabel5.setVisible(false);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jTextFieldCod = new javax.swing.JTextField();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jTextFieldNom = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jTextFieldDes = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jTextFieldPre = new javax.swing.JTextField();
		jButtonGrab = new javax.swing.JButton();
		jButtonCan = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		jButtonAcep = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		jLabel1.setText("Código: ");

		jTextFieldCod.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextFieldCodActionPerformed(evt);
			}
		});

		jLabel2.setText("Nombre: ");

		jTextFieldNom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextFieldNomActionPerformed(evt);
			}
		});

		jLabel3.setText("Descripción: ");

		jLabel4.setText("Precio: ");

		jTextFieldPre.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextFieldPreActionPerformed(evt);
			}
		});

		jButtonGrab.setText("Guardar");
		jButtonGrab.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGrabActionPerformed(evt);
			}
		});

		jButtonCan.setText("Cancelar");
		jButtonCan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCanActionPerformed(evt);
			}
		});

		jLabel5.setForeground(new java.awt.Color(255, 0, 0));
		jLabel5.setText("* Rellene todos los campos");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(24, 24, 24).addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jTextFieldNom)
								.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addComponent(jTextFieldDes)
								.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
										.addComponent(jTextFieldPre, javax.swing.GroupLayout.Alignment.LEADING))
								.addGap(18, 18, 18).addComponent(jButtonGrab)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButtonCan)))
						.addContainerGap(22, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jTextFieldDes, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel4)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextFieldPre, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonGrab).addComponent(jButtonCan))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel5)
						.addContainerGap(24, Short.MAX_VALUE)));

		jButtonAcep.setText("Aceptar");
		jButtonAcep.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonAcepActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(24, 24, 24)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jTextFieldCod)
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(jButtonAcep)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextFieldCod, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonAcep))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPreActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldPreActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextFieldPreActionPerformed

    private void jButtonAcepActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonAcepActionPerformed

        if (jTextFieldCod.getText().matches("[0-9]{1,6}")) {

            ArrayList<Articulo> a;

            try {
                a = p.consultarArt(Integer.parseInt(jTextFieldCod.getText()));
                if (a.isEmpty()) {

                    jPanel1.setVisible(true);

                } else {

                    jTextFieldCod.setEnabled(false);
                    jButtonAcep.setEnabled(false);

                    jTextFieldNom.setText(a.get(0).getNombre());
                    jTextFieldDes.setText(a.get(0).getDescripcion());
                    jTextFieldPre.setText(String.valueOf(a.get(0).getPrecio()));
                    jPanel1.setVisible(true);

                }
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());

            }

        } else {

            JOptionPane.showMessageDialog(this, "El código debe ser un número de máximo 6 digitos",
                    "ERROR", JOptionPane.WARNING_MESSAGE);

            jTextFieldCod.setText(null);
            jTextFieldCod.grabFocus();

        }

    }// GEN-LAST:event_jButtonAcepActionPerformed

    private void jTextFieldNomActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldNomActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextFieldNomActionPerformed

    private void jTextFieldCodActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldCodActionPerformed
        // TODO add your handling code here:

        jButtonAcepActionPerformed(evt);

    }// GEN-LAST:event_jTextFieldCodActionPerformed

    private void jButtonCanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCanActionPerformed
        // TODO add your handling code here:

        jTextFieldCod.setText(null);
        jTextFieldNom.setText(null);
        jTextFieldDes.setText(null);
        jTextFieldPre.setText(null);

        jPanel1.setVisible(false);

        jTextFieldCod.setEnabled(true);
        jButtonAcep.setEnabled(true);

    }// GEN-LAST:event_jButtonCanActionPerformed

    private void jButtonGrabActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonGrabActionPerformed
        // TODO add your handling code here:

        if (validarCampos()) {

            Articulo a = new Articulo(Integer.parseInt(jTextFieldCod.getText()), jTextFieldNom.getText(),
                    jTextFieldDes.getText(), Double.parseDouble(jTextFieldPre.getText()));

            try {
                p.guarModArt(a);
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());

            }

            jButtonCanActionPerformed(evt);
            jTextFieldCod.grabFocus();

        }

    }// GEN-LAST:event_jButtonGrabActionPerformed

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
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Articulos dialog = new Articulos(new javax.swing.JFrame(), true);
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
	private javax.swing.JButton jButtonCan;
	private javax.swing.JButton jButtonGrab;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTextField jTextFieldCod;
	private javax.swing.JTextField jTextFieldDes;
	private javax.swing.JTextField jTextFieldNom;
	private javax.swing.JTextField jTextFieldPre;
	// End of variables declaration//GEN-END:variables

    private boolean validarCampos() {
        boolean val = true;

        if (!jTextFieldNom.getText().equals("")) {
            if (!jTextFieldDes.getText().equals("")) {
                if (jTextFieldPre.getText().matches("[0-9]+(.[0-9])?")) {

                } else {

                    jTextFieldPre.grabFocus();
                    jLabel5.setVisible(true);
                    val = false;

                }
            } else {

                jTextFieldDes.grabFocus();
                jLabel5.setVisible(true);
                val = false;

            }
        } else {

            jTextFieldNom.grabFocus();
            jLabel5.setVisible(true);
            val = false;

        }

        return val;
    }
}