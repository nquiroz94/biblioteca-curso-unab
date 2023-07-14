package com.unab.biblioteca.models;


import com.unab.biblioteca.enums.Estados;

public class Libro {
    private String titulo;
    private String autor;
    private String genero;
    private Estados estado;
    private int anioPublicacion;


    public Libro(String titulo, String autor, String genero, Estados estado, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.estado = estado;
        this.anioPublicacion = anioPublicacion;
    }

    public Libro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnio_publicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", estado=" + estado +
                ", anio_publicacion=" + anioPublicacion +
                '}';
    }
}
