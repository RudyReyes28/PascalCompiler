/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.rudyreyes.pascalcompiler.vista.principal;

import com.rudyreyes.pascalcompiler.controlador.analisis.parser;
import com.rudyreyes.pascalcompiler.controlador.analisis.scanner;
import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.funciones.DeclaracionFuncion;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaTipos;
import com.rudyreyes.pascalcompiler.vista.util.NumeroDeLinea;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author rudyo
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        
        //NumeroDeLinea lineaConsola = new NumeroDeLinea(areaConsola);
        //scrollConsola.setRowHeaderView(lineaConsola);
        
        this.setLocationRelativeTo(null);
        
        File carpeta = new File("archivosPas");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea los directorios necesarios si no existen
        }
        cerrarPestanias();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        areaCodigo = new javax.swing.JTabbedPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaConsola = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        btnAbrirArchivo = new javax.swing.JButton();
        btnNuevaPestania = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pascal");

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pascal Compiler");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Area de Codigo:");

        areaConsola.setColumns(20);
        areaConsola.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        areaConsola.setRows(5);
        jScrollPane2.setViewportView(areaConsola);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Area de Consola:");

        btnAbrirArchivo.setText("Abrir Archivo");
        btnAbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirArchivoActionPerformed(evt);
            }
        });

        btnNuevaPestania.setText("Nueva Pestania");
        btnNuevaPestania.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPestaniaActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAbrirArchivo)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevaPestania)
                        .addGap(18, 18, 18)
                        .addComponent(btnEjecutar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                            .addComponent(areaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrirArchivo)
                    .addComponent(btnNuevaPestania)
                    .addComponent(btnEjecutar))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(areaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void cerrarPestanias(){
        areaCodigo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) { // Clic derecho
                    int tabIndex = areaCodigo.indexAtLocation(e.getX(), e.getY());
                    if (tabIndex >= 0) {
                        // Obtener el nombre de la pestaña
                        String nombrePestana = areaCodigo.getTitleAt(tabIndex);
                        Component tabComponent = areaCodigo.getComponentAt(tabIndex);

                        if (tabComponent instanceof JScrollPane) {
                            JScrollPane scrollPane = (JScrollPane) tabComponent;
                            JTextArea areaContenido = (JTextArea) scrollPane.getViewport().getView();

                            // Obtener el texto del JTextArea
                            String textoContenido = areaContenido.getText();

                            //AQUI DEBERIA DE GUARDAR LA INFOR
                            int guadarDatos = JOptionPane.showConfirmDialog(null,
                                    "¿Guardar los datos antes de cerrar '" + nombrePestana + "'?", "Guardar Datos",
                                    JOptionPane.YES_NO_OPTION);
                            if (guadarDatos == JOptionPane.YES_OPTION) {
                                //GUARDAR ARCHIVOS
                                boolean realizado = true;

                                try {

                                    String rutaCompleta = "archivosJC" + File.separator + nombrePestana; // Construir la ruta completa del archivo

                                    BufferedWriter writer = new BufferedWriter(new FileWriter(rutaCompleta));
                                    writer.write(textoContenido);
                                    writer.close();
                                    realizado = true;
                                } catch (IOException ex) {
                                    realizado = false;
                                }

                                if (realizado) {
                                    JOptionPane.showMessageDialog(null, "Se guardo correctamente");
                                }

                                areaCodigo.removeTabAt(tabIndex);
                            } else {
                                areaCodigo.removeTabAt(tabIndex);
                            }
                        }

                    }
                }
            }
        });
    }
    
    private void btnAbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirArchivoActionPerformed
        // TODO add your handling code here:
        
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.txt, *.py, *.xml, *.csv, *.pas)", "txt", "py", "xml", "csv","pas");
        fc.setFileFilter(filtro);

        int seleccion = fc.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(fichero));
                StringBuilder contenido = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                reader.close();

                JTextArea textArea = new JTextArea(contenido.toString());
                textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

                NumeroDeLinea areaC = new NumeroDeLinea(textArea);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setRowHeaderView(areaC);
                areaCodigo.addTab(fichero.getName(), scrollPane);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnAbrirArchivoActionPerformed

    private void btnNuevaPestaniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaPestaniaActionPerformed
        // TODO add your handling code here:
        
        String nombrePestania = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva pestaña:");

        if (nombrePestania != null && !nombrePestania.trim().isEmpty()) {
            JTextArea textArea = new JTextArea();
            textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            NumeroDeLinea areaC = new NumeroDeLinea(textArea);

            JScrollPane scrollPane = new JScrollPane(textArea);
            //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setRowHeaderView(areaC);
            areaCodigo.addTab(nombrePestania, scrollPane);
        } else {
            JOptionPane.showMessageDialog(this, "El nombre de la pestaña no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnNuevaPestaniaActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        // TODO add your handling code here:
        
        String contenido="";
        int selectedIndex = areaCodigo.getSelectedIndex();
        if (selectedIndex != -1) {
            JScrollPane scrollPane = (JScrollPane) areaCodigo.getComponentAt(selectedIndex);
            JViewport viewport = scrollPane.getViewport();
            JTextArea textArea = (JTextArea) viewport.getView();
            contenido = textArea.getText();
       
        try{
         scanner s = new scanner(new BufferedReader(new StringReader(contenido)));
                parser p = new parser(s);
                var resultado = p.parse();
                
                if (resultado != null) {
                    var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
                    var tabla = new TablaSimbolos();
                    var tablaTipos = new TablaTipos();
                    tablaTipos.setNombre("GLOBAL");
                    tabla.setNombre("GLOBAL");
                    ast.setConsola("");
                    ast.setTablaGlobal(tabla);
                    ast.setTablaTipos(tablaTipos);
                    LinkedList<Object> lista = new LinkedList<>();
                    
                    lista.addAll(s.listaErrores);
                    lista.addAll(p.listaErrores);
                 
                    //almacenar funciones, metodos o structs
                    for (var a : ast.getInstrucciones()) {
                        if (a == null) {
                            continue;
                        }

                        if (a instanceof DeclaracionFuncion) {
                            ast.addFunciones(a);
                        }
                        //if (a instanceof DeclaracionStruct) {
                        //    ast.addStructs(a);
                        //}
                        
                        
                    }
                   
                    for (var a : ast.getInstrucciones()) {
                        if (a == null) {
                            continue;
                        }
                        if (a instanceof DeclaracionFuncion) {
                            //ast.addFunciones(a);
                        } else {
                            var res = a.interpretar(ast, tabla);

                            if (res instanceof Errores) {

                                lista.add((Errores) res);
                            }
                        }

                    }
                    areaConsola.setText(ast.getConsola());
                    
                    for (var i : lista) {
                        areaConsola.append(i.toString()+"\n");
                    }
                    
                    //areaConsola.append(tablaTipos.mostrarSimbolosTablaActual());
                    areaConsola.append(tabla.mostrarTodosLosSimbolos());
                }
                } catch (Exception ex) {
                //areaConsola.setText("Algo salio mal: "+ex);
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                String stackTrace = sw.toString();

                // Muestra el mensaje y el stack trace en el área de consola
                areaConsola.setText("Algo salió mal: " + ex.getMessage() + "\n" + stackTrace);
            }
        }else{
            areaConsola.setText("No hay pestañas abiertas");
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    
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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane areaCodigo;
    private javax.swing.JTextArea areaConsola;
    private javax.swing.JButton btnAbrirArchivo;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnNuevaPestania;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
