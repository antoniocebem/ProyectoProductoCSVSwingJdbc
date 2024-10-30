package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FormConexion {
    private JPanel PanelConexion;
    private JTextField textFieldServidor;
    private JTextField textFieldPuerto;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private JLabel jLabelServidor;
    private JLabel EtiquetaPuerto;
    private JLabel EtiquetaUser;
    private JLabel EtiquetaPass;
    private JButton conectarButton;
    private JButton cancelarButton;
    private Connection conexion;
    private JFrame frame;


    public FormConexion(JFrame frame) {
        this.frame = frame;
        conectarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldServidor.getText().equals("") || textFieldPuerto.getText().equals("") || textFieldUsuario.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Ingrese todos los datos para conectar");
                }else
                {

                    String url="jdbc:mysql://"+textFieldServidor.getText()+":"+textFieldPuerto.getText()+"/northwind";
                    String password = new String(passwordField.getPassword());
                    try {
                        Connection conexion= DriverManager.getConnection(url,textFieldUsuario.getText(),password);
                        JOptionPane.showMessageDialog(frame, "Conexión exitosa");
                        FormProductos fp=new FormProductos(conexion);

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage());
                        //ex.printStackTrace();
                    }
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormConexion");
        frame.setContentPane(new FormConexion(frame).PanelConexion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
