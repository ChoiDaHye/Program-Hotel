package com.hotel.dao;

import com.hotel.script.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import com.hotel.gui.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class resep_bayar_dao {
    public Connection con;
    PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6;

    public resep_bayar_dao() {
        try {
            this.con = new koneksi().getCon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public boolean ubahBayar(resep_pesan_getset gs) {
        try {
            String kode = gs.getBooking2();
            String sql = "SELECT id_kamar FROM tb_pemesanan_detail WHERE kode_booking = '"+ kode +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                String id = rs.getString("id_kamar");
                
                //UPDATE STATUS KAMAR
                ps = con.prepareStatement("UPDATE tb_kamar SET status = '1' WHERE id_kamar = '"+ id +"'");
                ps.executeUpdate();
                
                //UPDATE STATUS PEMESANAN
                ps1 = con.prepareStatement("UPDATE tb_pemesanan SET status = '1' WHERE kode_booking = '"+ kode +"'");
                ps1.executeUpdate();
            }           

            JOptionPane.showMessageDialog(null, "Data tersimpan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal tersimpan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
