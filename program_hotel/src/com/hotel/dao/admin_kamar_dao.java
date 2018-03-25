/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.dao; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.hotel.gui.*;
import com.hotel.script.koneksi;
import javax.swing.JOptionPane;
/**
 *
 * @author Gedalya Anugrah
 */
public class admin_kamar_dao {
    public Connection con;
    PreparedStatement ps;

    
    public admin_kamar_dao(){
         try {
            this.con = new koneksi().getCon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public boolean masukDataKamar(admin_kamar_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_kamar(id_kamar, lantai, id_tipe, status, jumlah) values (?, ?, ?, ?, ?)");
            ps.setInt(1, gs.getNomorkamar());
            ps.setInt(2, gs.getLantai());
            ps.setInt(3, gs.getTipe());
            ps.setInt(4, gs.getStatus());
            ps.setInt(5, 1);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Kamar Ditambahkan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Menambah Data!\n" + e);
        }
        return false;
    }

    public boolean ubahDataKamar(admin_kamar_getset gs) {
        try {
            int NOMORKAMAR = gs.getNomorkamar();
            int LANTAI = gs.getLantai();
            int TIPE = gs.getTipe();
            int STATUS = gs.getStatus();

            ps = con.prepareStatement("UPDATE tb_kamar SET lantai = '"+ LANTAI +"', id_tipe = '"+ TIPE +"', status = '"+ STATUS+"' WHERE id_kamar = '" + NOMORKAMAR + "'");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Kamar Diubah!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Mengubah Data!\n" + e);
        }
        return false;
    }

    public boolean hapusDataKamar(admin_kamar_getset gs) {
        try {
            int NOMORKAMAR = gs.getNomorkamar();
            ps = con.prepareStatement("DELETE FROM tb_kamar WHERE id_kamar = '" + NOMORKAMAR + "'");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Akun Dihapus!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Menghapus Data!\n" + e);
        }
        return false;
    }
}




