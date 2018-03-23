package com.hotel.script;

import java.sql.*;
import javax.swing.JOptionPane;

public class koneksi {
    private Connection con;

    String koneksi  = "jdbc:mysql://localhost/db_hotel";
    String user     = "root";
    String pass     = "";

    public Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(koneksi, user, pass);
            return con;
        } catch (Exception err) {
            System.out.println("Terjadi Kesalahan! "+err);
            return null;
        }
    }
    
    public static void main(String[] args) {
        com.hotel.script.koneksi konek = new com.hotel.script.koneksi();
        konek.getCon();
        
        if(konek.getCon() != null){
            JOptionPane.showMessageDialog(null, "Berhasil Terhubung!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Gagal Terhubung!");
        }
    }
}
