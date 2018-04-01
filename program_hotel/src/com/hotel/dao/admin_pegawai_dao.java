package com.hotel.dao;

import com.hotel.script.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import com.hotel.gui.*;

public class admin_pegawai_dao {

    public Connection con;
    PreparedStatement ps;

    public admin_pegawai_dao() {
        try {
            this.con = new koneksi().getCon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean masukDataPegawai(admin_pegawai_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pegawai(id_karyawan, nama, gender, alamat, level) values (?, ?, ?, ?, ?)");
            ps.setString(1, gs.getId_pegawai());
            ps.setString(2, gs.getNama_pegawai());
            ps.setString(3, gs.getGender_pegawai());
            ps.setString(4, gs.getAlamat_pegawai());
            ps.setString(5, gs.getPosisi_pegawai());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pegawai Ditambahkan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Menambah Data!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean ubahDataPegawai(admin_pegawai_getset gs) {
        try {
            String ID = gs.getId_pegawai();
            String NAMA = gs.getNama_pegawai();
            String GENDER = gs.getGender_pegawai();
            String ALAMAT = gs.getAlamat_pegawai();
            String LEVEL = gs.getPosisi_pegawai();

            ps = con.prepareStatement("UPDATE tb_pegawai SET nama = '" + NAMA + "', gender = '" + GENDER + "', alamat = '" + ALAMAT + "', level = '" + LEVEL + "' WHERE id_karyawan = '" + ID + "'");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pegawai Diubah!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Mengubah Data!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean hapusDataPegawai(admin_pegawai_getset gs) {
        try {
            String ID = gs.getId_pegawai();
            ps = con.prepareStatement("DELETE FROM tb_pegawai WHERE id_karyawan = '" + ID + "'");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pegawai Dihapus!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Menghapus Data!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
