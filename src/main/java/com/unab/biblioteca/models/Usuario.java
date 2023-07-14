package com.unab.biblioteca.models;

import java.util.List;

public class Usuario {
    private String nombre;
    private String identificacion;
    private List<Libro> libros;

    public Usuario(String nombre, String identificacion, List<Libro> libros) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.libros = libros;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public void solicitarLibro(Libro libro){
        this.libros.add(libro);
    }

    public Libro devolverLibro(Libro libro) throws Exception {
        if(!this.libros.contains(libro))
            throw new Exception("ups! este libro no lo tengo yo");
        this.libros.remove(libro);
        return libro;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", libros=" + libros +
                '}';
    }
}
