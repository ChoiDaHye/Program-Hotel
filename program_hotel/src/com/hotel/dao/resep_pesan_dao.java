package com.hotel.dao;

import com.hotel.script.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import com.hotel.gui.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class resep_pesan_dao {

    public Connection con;
    PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6;

    public resep_pesan_dao() {
        try {
            this.con = new koneksi().getCon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean masukDataTamu(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_tamu(nik, nama, gender, alamat, telepon) values (?, ?, ?, ?, ?)");
            ps.setString(1, gs.getNik());
            ps.setString(2, gs.getNama());
            ps.setString(3, gs.getGender());
            ps.setString(4, gs.getAlamat());
            ps.setString(5, gs.getNotelp());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data tamu berhasi ditambahkan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tamu gagal ditambahkan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean masukDataPemesanan(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan(kode_booking, nik, t_in, t_out, lama, t_r, status) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getNik());
            ps.setString(3, gs.getTglin());
            ps.setString(4, gs.getTglout());
            ps.setInt(5, gs.getLm());
            ps.setString(6, gs.getTglr());
            ps.setString(7, "0");
            ps.executeUpdate();

            //AMBIL SEMUA DATA DARI tb_pemesanan_tmp
            String sql = "SELECT * FROM tb_pemesanan_tmp";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("id_kamar");
                int har = rs.getInt("harga");

                //MEMINDAHKAN DATA DARI tb_pemesanan_tmp KE tb_pemesanan_detail
                ps2 = con.prepareStatement("INSERT INTO tb_pemesanan_detail(kode_booking, id_kamar, harga) values (?, ?, ?)");
                ps2.setString(1, gs.getBooking());
                ps2.setString(2, id);
                ps2.setInt(3, har);
                ps2.executeUpdate();

                //UPDATE STATUS KAMAR
                ps3 = con.prepareStatement("UPDATE tb_kamar SET status = '2' WHERE id_kamar = '" + id + "'");
                ps3.executeUpdate();

                //UPDATE HARGA TOTAL
                ps5 = con.prepareStatement("UPDATE tb_pemesanan SET harga_total = ((SELECT SUM(harga) FROM tb_pemesanan_tmp) * "+ gs.getLm() +") WHERE kode_booking = '" + gs.getBooking() + "'");
                ps5.executeUpdate();
            }           

            JOptionPane.showMessageDialog(null, "Data pemesanan tersimpan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data pemesanan gagal ditambahkan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    public boolean hapusDataTmp() {
        try {
            //HAPUS DATA PADA tb_pemesanan_tmp
            ps4 = con.prepareStatement("DELETE FROM tb_pemesanan_tmp");
            ps4.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data tamu berhasi ditambahkan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tamu gagal ditambahkan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
