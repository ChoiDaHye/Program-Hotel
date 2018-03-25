/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.gui;

/**
 *
 * @author Gedalya Anugrah
 */
public class admin_kamar_getset {
    private int nomorkamar;
    private int lantai;
    private int tipe;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNomorkamar() {
        return nomorkamar;
    }

    public void setNomorkamar(int nomorkamar) {
        this.nomorkamar = nomorkamar;
    }

    public int getLantai() {
        return lantai;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public int getTipe() {
        return tipe;
    }

    public void setTipe(int tipe) {
        this.tipe = tipe;
    }
    
}
