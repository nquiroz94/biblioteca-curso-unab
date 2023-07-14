package com.unab.biblioteca.models;
import java.util.Date;
public class Prestamo {
    private Libro libro;
    private Date fechaPrestamo;
    private Usuario usuario;

    public Prestamo(Libro libro, Date fechaPrestamo, Usuario usuario) {
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.usuario = usuario;
    }

    public Prestamo() {
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
