package com.hotel.dao;

import com.hotel.gui.*;
import com.hotel.script.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Gedalya Anugrah
 */
public class resep_pemesan_dao {

    public Connection con;
    PreparedStatement ps;

    public resep_pemesan_dao() {
        try {
            this.con = new koneksi().getCon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean masukDataPemesan(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_tamu(nik, nama, gender, alamat, telepon) values (?, ?, ?, ?, ?)");
            ps.setString(1, gs.getNik());
            ps.setString(2, gs.getNama());
            ps.setString(3, gs.getJenis());
            ps.setString(4, gs.getAlamat());
            ps.setString(5, gs.getHp());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Menambah Data Pemesan!\n" + e);
        }
        return false;
    }
}
