package com.hotel.dao;

import com.hotel.script.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import com.hotel.gui.*;

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
            JOptionPane.showMessageDialog(null, "Data tamu gagal ditambahkan!" + e);
        }
        return false;
    }

    public boolean masukDataPemesanan1(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan(kode_booking, nik, harga_total, status) values (?, ?, ?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getNik());
            ps.setString(3, gs.getHar());
            ps.setString(4, gs.getSt());
            ps.executeUpdate();
            
            ps1 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps1.setString(1, gs.getBooking());
            ps1.setString(2, gs.getSr());
            ps1.setString(3, gs.getTglin());
            ps1.executeUpdate();
            
            ps2 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps2.setString(1, gs.getBooking());
            ps2.setString(2, gs.getDr());
            ps2.setString(3, gs.getTglin());
            ps2.executeUpdate();
            
            ps3 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps3.setString(1, gs.getBooking());
            ps3.setString(2, gs.getFr());
            ps3.setString(3, gs.getTglin());
            ps3.executeUpdate();
            
            ps4 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps4.setString(1, gs.getBooking());
            ps4.setString(2, gs.getSr());
            ps4.setString(3, gs.getTglout());
            ps4.executeUpdate();
            
            ps5 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps5.setString(1, gs.getBooking());
            ps5.setString(2, gs.getDr());
            ps5.setString(3, gs.getTglout());
            ps5.executeUpdate();
            
            ps6 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps6.setString(1, gs.getBooking());
            ps6.setString(2, gs.getFr());
            ps6.setString(3, gs.getTglout());
            ps6.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data pemesanan tersimpan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data pemesanan gagal ditambahkan!" + e);
        }
        return false;
    }
    
    public boolean masukDataPemesanan2(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan(kode_booking, nik, harga_total, status) values (?, ?, ?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getNik());
            ps.setString(3, gs.getHar());
            ps.setString(4, gs.getSt());
            ps.executeUpdate();
            
            ps1 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps1.setString(1, gs.getBooking());
            ps1.setString(2, gs.getSr());
            ps1.setString(3, gs.getTglin());
            ps1.executeUpdate();
            
            ps2 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps2.setString(1, gs.getBooking());
            ps2.setString(2, gs.getDr());
            ps2.setString(3, gs.getTglin());
            ps2.executeUpdate();
            
            ps4 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps4.setString(1, gs.getBooking());
            ps4.setString(2, gs.getSr());
            ps4.setString(3, gs.getTglout());
            ps4.executeUpdate();
            
            ps5 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps5.setString(1, gs.getBooking());
            ps5.setString(2, gs.getDr());
            ps5.setString(3, gs.getTglout());
            ps5.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data pemesanan tersimpan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data pemesanan gagal ditambahkan!" + e);
        }
        return false;
    }
    
    public boolean masukDataPemesanan3(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan(kode_booking, nik, harga_total, status) values (?, ?, ?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getNik());
            ps.setString(3, gs.getHar());
            ps.setString(4, gs.getSt());
            ps.executeUpdate();
            
            ps1 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps1.setString(1, gs.getBooking());
            ps1.setString(2, gs.getSr());
            ps1.setString(3, gs.getTglin());
            ps1.executeUpdate();
            
            ps3 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps3.setString(1, gs.getBooking());
            ps3.setString(2, gs.getFr());
            ps3.setString(3, gs.getTglin());
            ps3.executeUpdate();
            
            ps4 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps4.setString(1, gs.getBooking());
            ps4.setString(2, gs.getSr());
            ps4.setString(3, gs.getTglout());
            ps4.executeUpdate();
            
            ps6 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps6.setString(1, gs.getBooking());
            ps6.setString(2, gs.getFr());
            ps6.setString(3, gs.getTglout());
            ps6.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data pemesanan tersimpan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data pemesanan gagal ditambahkan!" + e);
        }
        return false;
    }
    
    public boolean masukDataPemesanan4(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan(kode_booking, nik, harga_total, status) values (?, ?, ?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getNik());
            ps.setString(3, gs.getHar());
            ps.setString(4, gs.getSt());
            ps.executeUpdate();
            
            ps2 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps2.setString(1, gs.getBooking());
            ps2.setString(2, gs.getDr());
            ps2.setString(3, gs.getTglin());
            ps2.executeUpdate();
            
            ps3 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps3.setString(1, gs.getBooking());
            ps3.setString(2, gs.getFr());
            ps3.setString(3, gs.getTglin());
            ps3.executeUpdate();
            
            ps5 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps5.setString(1, gs.getBooking());
            ps5.setString(2, gs.getDr());
            ps5.setString(3, gs.getTglout());
            ps5.executeUpdate();
            
            ps6 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps6.setString(1, gs.getBooking());
            ps6.setString(2, gs.getFr());
            ps6.setString(3, gs.getTglout());
            ps6.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data pemesanan tersimpan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data pemesanan gagal ditambahkan!" + e);
        }
        return false;
    }
    
    public boolean masukDataPemesanan5(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan(kode_booking, nik, harga_total, status) values (?, ?, ?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getNik());
            ps.setString(3, gs.getHar());
            ps.setString(4, gs.getSt());
            ps.executeUpdate();
            
            ps1 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps1.setString(1, gs.getBooking());
            ps1.setString(2, gs.getSr());
            ps1.setString(3, gs.getTglin());
            ps1.executeUpdate();
            
            ps4 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps4.setString(1, gs.getBooking());
            ps4.setString(2, gs.getSr());
            ps4.setString(3, gs.getTglout());
            ps4.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data pemesanan tersimpan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data pemesanan gagal ditambahkan!" + e);
        }
        return false;
    }
    
    public boolean masukDataPemesanan6(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan(kode_booking, nik, harga_total, status) values (?, ?, ?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getNik());
            ps.setString(3, gs.getHar());
            ps.setString(4, gs.getSt());
            ps.executeUpdate();
            
            ps2 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps2.setString(1, gs.getBooking());
            ps2.setString(2, gs.getDr());
            ps2.setString(3, gs.getTglin());
            ps2.executeUpdate();
            
            ps5 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps5.setString(1, gs.getBooking());
            ps5.setString(2, gs.getDr());
            ps5.setString(3, gs.getTglout());
            ps5.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data pemesanan tersimpan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data pemesanan gagal ditambahkan!" + e);
        }
        return false;
    }
    
    public boolean masukDataPemesanan7(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan(kode_booking, nik, harga_total, status) values (?, ?, ?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getNik());
            ps.setString(3, gs.getHar());
            ps.setString(4, gs.getSt());
            ps.executeUpdate();
            
            ps3 = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps3.setString(1, gs.getBooking());
            ps3.setString(2, gs.getFr());
            ps3.setString(3, gs.getTglin());
            ps3.executeUpdate();
            
            ps6 = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, id_kamar, tanggal) values (?, ?, ?)");
            ps6.setString(1, gs.getBooking());
            ps6.setString(2, gs.getFr());
            ps6.setString(3, gs.getTglout());
            ps6.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data pemesanan tersimpan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data pemesanan gagal ditambahkan!" + e);
        }
        return false;
    }
}
