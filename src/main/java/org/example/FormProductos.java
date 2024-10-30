package org.example;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class FormProductos {
    private JPanel panel1;
    private JTable table1;
    private JScrollPane ScrollTabla;
    private Connection conn;
    private int numColumnas;
    private String [] nombreColumnas=null;
    private Object [] fila=null;
    private DefaultTableModel model;

    public FormProductos(Connection conn) throws SQLException {
        this.conn=conn;
        JFrame frame = new JFrame("FormProductos");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        definirTabla();
        model = new DefaultTableModel(nombreColumnas,0  );
        table1.setModel(model);
        rellenarTabla();


    }

    private void definirTabla() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from products");
        ResultSetMetaData rsmd = rs.getMetaData();

        numColumnas=rsmd.getColumnCount();

        nombreColumnas=new String[numColumnas];
        for(int i=0;i<numColumnas;i++) {
            nombreColumnas[i]=rsmd.getColumnName(i+1);
        }
    }

    private void rellenarTabla() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from products");
        while (rs.next()) {
            fila = new Object[numColumnas];
            for (int i = 0; i < numColumnas; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            model.addRow(fila);
        }
    }
}
