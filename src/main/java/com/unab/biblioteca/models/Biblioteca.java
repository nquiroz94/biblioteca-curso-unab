package com.unab.biblioteca.models;

import com.unab.biblioteca.enums.Estados;
import com.unab.biblioteca.enums.Prestamos;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Biblioteca {
    private List<Libro> librosDisponibles;
    private List<Prestamo> prestamos;
    private List<Libro> librosPrestados;
    private Map<Usuario, Libro> librosReservados;
    private List<Usuario> usuariosRegistrados;

    public Biblioteca(List<Libro> librosDisponibles, List<Prestamo> prestamos, List<Libro> librosPrestados, Map<Usuario, Libro> librosReservados, List<Usuario> usuariosRegistrados) {
        this.librosDisponibles = librosDisponibles;
        this.prestamos = prestamos;
        this.librosPrestados = librosPrestados;
        this.librosReservados = librosReservados;
        this.usuariosRegistrados = usuariosRegistrados;
    }

    public Biblioteca() {
        librosDisponibles = new ArrayList<>();
        usuariosRegistrados = new ArrayList<>();
        prestamos = new ArrayList<>();
        librosPrestados = new ArrayList<>();
    }

    public List<Libro> getLibrosDisponibles() {
        return librosDisponibles;
    }

    public void setLibrosDisponibles(List<Libro> librosDisponibles) {
        this.librosDisponibles = librosDisponibles;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(List<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    public Map<Usuario, Libro> getLibrosReservados() {
        return librosReservados;
    }

    public void setLibrosReservados(Map<Usuario, Libro> librosReservados) {
        this.librosReservados = librosReservados;
    }

    public List<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public void setUsuariosRegistrados(List<Usuario> usuariosRegistrados) {
        this.usuariosRegistrados = usuariosRegistrados;
    }

    // logic:

    public void agregarLibro(Libro libro){
        this.librosDisponibles.add(libro);
    }

    public void agregarUsuario(Usuario usuario){this.usuariosRegistrados.add(usuario);}


    public List<Libro> buscarLibroPorTitulo(String titulo){
        List<Libro> lst = new ArrayList<>(this.librosDisponibles);;
        List<Libro> matches = new ArrayList<Libro>();
        this.prestamos.forEach((prestamo)->{
            lst.add(prestamo.getLibro());
        });

        lst.forEach((item)->{
            if(item.getTitulo().contains(titulo))
                matches.add(item);
        });

        System.out.println("Libros encontrados para el título: " + titulo);
        for (Libro libro : matches) {
            System.out.println("Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor());
        }

        return matches;
    }
    public List<Libro> buscarLibroPorAutor(String autor){
        List<Libro> lst = new ArrayList<Libro>(this.librosDisponibles);
        List<Libro> matches = new ArrayList<Libro>();
        this.prestamos.forEach((prestamo)->{
            lst.add(prestamo.getLibro());
        });

        lst.forEach((item)->{
            if(item.getAutor().contains(autor))
                System.out.println(item);
                matches.add(item);
        });

        System.out.println("Libros encontrados para el autor: " + autor);
        for (Libro libro : matches) {
            System.out.println("Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor());
        }
        return matches;
    }

    public Boolean prestarLibro(Integer id, Usuario usuario, Libro libro, Date fechaInicioPrestamo, Date fechaFinPrestamo, double costoPorDia){

        if(!this.librosDisponibles.contains(libro))
            return false;

        usuario.solicitarLibro(libro);
        this.librosDisponibles.remove(libro);
        this.librosPrestados.add(libro);
        this.prestamos.add(new Prestamo(id, libro, fechaInicioPrestamo, fechaFinPrestamo, usuario, costoPorDia, Prestamos.EN_CURSO));
        libro.setEstado(Estados.PRESTADO);
        return true;
    }

    public Boolean devolverLibro(Usuario usuario, Libro libro){
        try{
            boolean estaPrestado = false;
            Prestamo prestamo = new Prestamo();
            for(Prestamo reg : this.prestamos){
                if (reg.getLibro().equals(libro)) {
                    estaPrestado = true;
                    prestamo = reg;
                    break;
                }
            }

            if(!estaPrestado)
                return false;

            usuario.devolverLibro(prestamo.getLibro());
            this.prestamos.remove(prestamo);
            prestamo.getLibro().setEstado(Estados.DISPONIBLE);
            this.librosPrestados.remove(prestamo.getLibro());
            this.librosDisponibles.add(prestamo.getLibro());
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public void imprimirLibrosDisponibles(){
        for(Libro libro : this.librosDisponibles){
            System.out.println(libro.toString());
        }
    }

    public void imprimirUsuariosRegistrados(){
        for(Usuario usuario : this.usuariosRegistrados){
            System.out.println(usuario.toString());
        }
    }

    public void imprimirInformePrestamosVencidos(){
        System.out.println("Prestamos vencidos: ");
        Date hoy = new Date();
        for(Prestamo prestamo : this.prestamos){
            long diffInMillies = Math.abs(prestamo.getFechaInicioPrestamo().getTime() - hoy.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff >= 30){
                System.out.println(prestamo.toString());
            }
        }
    }

    public void imprimirInformeLibrosPorUsuario(){
        for(Usuario usuario : this.usuariosRegistrados){
            System.out.println("Nombre usuario: "+usuario.getNombre()+" cantidad de libros: "+usuario.getLibros().size());
        }
    }

    public void reservarLibroPrestado(Usuario usuario, Libro libro) {
        if (librosPrestados.contains(libro) && !librosReservados.containsKey(usuario)) {
            librosReservados.put(usuario, libro);
            System.out.println(usuario.getNombre() + " ha reservado el libro '" + libro.getTitulo() + "'.");
        } else {
            System.out.println("El libro '" + libro.getTitulo() + "' no está prestado o el usuario ya lo tiene reservado.");
        }
    }

    public void notificarLibroDisponible(Libro libro) {
        Iterator<Map.Entry<Usuario, Libro>> iterator = librosReservados.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Usuario, Libro> entry = iterator.next();
            if (entry.getValue().equals(libro)) {
                Usuario usuario = entry.getKey();
                System.out.println("El libro '" + libro.getTitulo() + "' está disponible para " + usuario.getNombre() + ". Puede pasar a recogerlo.");
                iterator.remove();
                break;
            }
        }
    }


    public List<Prestamo> obtenerPrestamosVencidos() {
        List<Prestamo> prestamosVencidos = new ArrayList<>();
        Date fechaActual = new Date();

        for (Prestamo prestamo : prestamos) {
            Date fechaFinPrestamo = prestamo.getFechaFinPrestamo();
            long diferenciaDias = (fechaActual.getTime() - fechaFinPrestamo.getTime()) / (24 * 60 * 60 * 1000);
            if (diferenciaDias > 30) {
                prestamosVencidos.add(prestamo);
            }
        }

        return prestamosVencidos;
    }

}
