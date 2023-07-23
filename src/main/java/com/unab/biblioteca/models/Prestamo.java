package com.unab.biblioteca.models;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;import com.unab.biblioteca.enums.Estados;
import com.unab.biblioteca.enums.Prestamos;

import java.util.Date;
public class Prestamo {
    private Integer id;
    private Libro libro;
    private Date fechaInicioPrestamo;
    private Date fechaFinPrestamo;
    private Usuario usuario;
    private Double costoPorDia;
    private Double total;
    private Prestamos estado;

    public Prestamo(Integer id, Libro libro, Date fechaInicioPrestamo, Date fechaFinPrestamo, Usuario usuario, Double costoPorDia, Prestamos estado) {
        this.id = id;
        this.libro = libro;
        this.fechaInicioPrestamo = fechaInicioPrestamo;
        this.fechaFinPrestamo = fechaFinPrestamo;
        this.usuario = usuario;
        this.costoPorDia = costoPorDia;
        this.total = this.calcularTotal(this.costoPorDia, this.fechaInicioPrestamo, this.fechaFinPrestamo);
        this.estado = estado;
    }

    public Prestamo() {
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFechaInicioPrestamo() {
        return fechaInicioPrestamo;
    }

    public void setFechaInicioPrestamo(Date fechaInicioPrestamo) {
        this.fechaInicioPrestamo = fechaInicioPrestamo;
    }

    public Date getFechaFinPrestamo() {
        return fechaFinPrestamo;
    }

    public void setFechaFinPrestamo(Date fechaFinPrestamo) {
        this.fechaFinPrestamo = fechaFinPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getcostoPorDia() {
        return costoPorDia;
    }

    public void setCostoPorDia(Double costoPorDia) {
        this.costoPorDia = costoPorDia;
    }

    public Prestamos getEstado() {
        return estado;
    }

    public void setEstado(Prestamos estado) {
        this.estado = estado;
    }

    public static double calcularTotal(double costoPorDia, Date fechaInicioPrestamo, Date fechaFinPrestamo) {
        LocalDate fechaInicio = fechaInicioPrestamo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaFin = fechaFinPrestamo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long numeroDeDias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        return costoPorDia * numeroDeDias;
    }
    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", libro=" + libro +
                ", fechaInicioPrestamo=" + fechaInicioPrestamo +
                ", fechaFinPrestamo=" + fechaFinPrestamo +
                ", usuario=" + usuario +
                ", costoPorDia=" + costoPorDia +
                ", total=" + total +
                ", estado=" + estado +
                '}';
    }
}
