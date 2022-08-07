/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Controller implements ActionListener{
    private final Formulario formulario1;
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource()==formulario1.btn_lider){
            informeLider();
        }
        if (e.getSource()==formulario1.btn_proveedor){
            informeProveedor();
        }
        if (e.getSource()==formulario1.btn_proyecto){
            informeProyectos();
        }
    }
    
    public Controller(){
        this.formulario1=new Formulario();
    }
    public void inicio(){
        this.formulario1.setVisible(true);
        this.formulario1.setLocationRelativeTo(null);
        this.formulario1.setTitle("Reto 5 Autor Mauricio Cadavid García Grupo 16 Mision TIC 2022 UTP");
        this.formulario1.setResizable(false);
        this.formulario1.btn_lider.addActionListener(this);
        this.formulario1.btn_proveedor.addActionListener(this);
        this.formulario1.btn_proyecto.addActionListener(this);
        DefaultTableModel t_vacia = new DefaultTableModel();
        t_vacia.setRowCount(0);
        this.formulario1.t_res.setModel(t_vacia);
        salir(formulario1);
    }
    public void close(JFrame vista){
        if (JOptionPane.showConfirmDialog(vista, "¿Desea salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    public void salir(JFrame vista) {
        vista.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
 
        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close(vista);
            }
        });
    }
    public void informeLider(){
        String[] nombreColumnas={"ID_Lider","Nombre","Primer_Apellido","Ciudad_Residencia"};
        String[] registros=new String[4];
        DefaultTableModel modelo=new DefaultTableModel(null,nombreColumnas);
        String sql="select ID_Lider,Nombre,Primer_Apellido,Ciudad_residencia from Lider order by Ciudad_Residencia asc";
        Connection con = null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            con= Conexion.conexion1();
            pst= con.prepareStatement(sql);
            rs= pst.executeQuery();
            while (rs.next()){
                registros[0]=rs.getString("ID_Lider");
                registros[1]=rs.getString("Nombre");
                registros[2]=rs.getString("Primer_Apellido");
                registros[3]=rs.getString("Ciudad_Residencia");
                modelo.addRow(registros);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se logro obtener el resultado");
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        formulario1.t_res.setModel(modelo);
        formulario1.t_res.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        if (formulario1.t_res.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Base de datos vacia");
        }
    }
    
    public void informeProyectos(){
        String[] nombreColumnas={"ID_Proyecto","Constructora","Numero_Habitaciones","Ciudad"};
        String[] registros=new String[4];
        DefaultTableModel modelo=new DefaultTableModel(null,nombreColumnas);
        String sql="select\n" +
                    "ID_Proyecto,\n" +
                    "Constructora,\n" +
                    "Numero_Habitaciones,\n" +
                    "Ciudad\n" +
                    "From Proyecto\n" +
                    "where Clasificacion ='Casa Campestre' and\n" +
                    "Ciudad in('Santa Marta','Cartagena', 'Barranquilla')";
        Connection con = null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            con= Conexion.conexion1();
            pst= con.prepareStatement(sql);
            rs= pst.executeQuery();
            while (rs.next()){
                registros[0]=rs.getString("ID_Proyecto");
                registros[1]=rs.getString("Constructora");
                registros[2]=rs.getString("Numero_Habitaciones");
                registros[3]=rs.getString("Ciudad");
                modelo.addRow(registros);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se logro obtener el resultado");
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        formulario1.t_res.setModel(modelo);
        formulario1.t_res.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        if (formulario1.t_res.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Base de datos vacia");
        }
    }
    
    public void informeProveedor(){
        String[] nombreColumnas={"ID_Compra","Constructora","Banco_Vinculado"};
        String[] registros=new String[3];
        DefaultTableModel modelo=new DefaultTableModel(null,nombreColumnas);
        String sql="select\n" +
                    "ID_Compra,\n" +
                    "Constructora,\n" +
                    "Banco_Vinculado\n" +
                    "From Compra c\n" +
                    "join Proyecto p on c.ID_Proyecto = p.ID_Proyecto\n" +
                    "where  c.Proveedor='Homecenter' and p.Ciudad='Salento'";
        Connection con = null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            con= Conexion.conexion1();
            pst= con.prepareStatement(sql);
            rs= pst.executeQuery();
            while (rs.next()){
                registros[0]=rs.getString("ID_Compra");
                registros[1]=rs.getString("Constructora");
                registros[2]=rs.getString("Banco_Vinculado");
                modelo.addRow(registros);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se logro obtener el resultado");
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        formulario1.t_res.setModel(modelo);
        formulario1.t_res.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        if (formulario1.t_res.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Base de datos vacia");
        }
    }
}
