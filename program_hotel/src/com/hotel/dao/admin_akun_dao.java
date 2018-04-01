package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.hotel.gui.*;
import com.hotel.script.koneksi;
import javax.swing.JOptionPane;

public class admin_akun_dao {

    public Connection con;
    PreparedStatement ps;

    public admin_akun_dao() {
        try {
            this.con = new koneksi().getCon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean masukDataAkun(admin_akun_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_login(id, user, pass) values (?, ?, ?)");
            ps.setString(1, gs.getId());
            ps.setString(2, gs.getUser());
            ps.setString(3, gs.getPass());            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Akun Ditambahkan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Menambah Data!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean ubahDataAkun(admin_akun_getset gs) {
        try {
            String ID = gs.getId();
            String USER = gs.getUser();
            String PASS = gs.getPass();

            ps = con.prepareStatement("UPDATE tb_login SET pass = '"+ PASS +"' WHERE id = '" + ID + "' AND user = '"+ USER +"'");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Akun Diubah!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Mengubah Data!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean hapusDataAkun(admin_akun_getset gs) {
        try {
            String ID = gs.getId();
            ps = con.prepareStatement("DELETE FROM tb_login WHERE id = '" + ID + "'");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Akun Dihapus!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Menghapus Data!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
