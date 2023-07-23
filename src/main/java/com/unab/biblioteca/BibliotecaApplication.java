package com.unab.biblioteca;
import java.util.Scanner;

import com.unab.biblioteca.enums.Prestamos;
import com.unab.biblioteca.models.Libro;
import com.unab.biblioteca.models.Usuario;
import com.unab.biblioteca.models.Prestamo;
import com.unab.biblioteca.models.Biblioteca;

import java.util.Calendar;

import com.unab.biblioteca.enums.Estados;
import com.unab.biblioteca.enums.Prestamos;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		Biblioteca biblioteca = new Biblioteca();
		Libro[] libros = {
				new Libro("1984", "George Orwell", "Ciencia Ficción", Estados.DISPONIBLE, 1949),
				new Libro("Cien años de soledad", "Gabriel García Márquez", "Realismo mágico", Estados.DISPONIBLE, 1967),
				new Libro("El señor de los anillos: La comunidad del anillo", "J.R.R. Tolkien", "Fantasía", Estados.DISPONIBLE, 1954),
				new Libro("Orgullo y prejuicio", "Jane Austen", "Novela Romántica", Estados.DISPONIBLE, 1813),
				new Libro("Crónica de una muerte anunciada", "Gabriel García Márquez", "Realismo mágico", Estados.DISPONIBLE, 1981),
				new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", "Fantasía", Estados.DISPONIBLE, 1997),
				new Libro("El alquimista", "Paulo Coelho", "Ficción espiritual", Estados.DISPONIBLE, 1988),
				new Libro("La sombra del viento", "Carlos Ruiz Zafón", "Misterio", Estados.DISPONIBLE, 2001),
				new Libro("Matar a un ruiseñor", "Harper Lee", "Novela Dramática", Estados.DISPONIBLE, 1960),
				new Libro("El principito", "Antoine de Saint-Exupéry", "Literatura Infantil", Estados.DISPONIBLE, 1943)
		};

		Usuario[] usuarios = {
				new Usuario("María González", "123456789", new ArrayList<>()),
				new Usuario("Juan Pérez", "987654321", new ArrayList<>()),
				new Usuario("Luisa Rodríguez", "456789123", new ArrayList<>()),
				new Usuario("Carlos Sánchez", "654321987", new ArrayList<>()),
				new Usuario("Ana Martínez", "111111111", new ArrayList<>()),
				new Usuario("Pedro Gómez", "222222222", new ArrayList<>()),
				new Usuario("Laura Fernández", "333333333", new ArrayList<>()),
				new Usuario("José López", "444444444", new ArrayList<>()),
				new Usuario("Diana Ramírez", "555555555", new ArrayList<>()),
				new Usuario("Héctor Torres", "666666666", new ArrayList<>())
		};

		for (Libro libro : libros) {
			biblioteca.agregarLibro(libro);
		}
		for (Usuario usuario : usuarios) {
			biblioteca.agregarUsuario(usuario);
		}

		Scanner scanner = new Scanner(System.in);
		int option = -1;

		while (option != 0) {
			System.out.println("----------- MENÚ -----------");
			System.out.println("1. Agregar libro");
			System.out.println("2. Buscar libro");
			System.out.println("3. Prestar libro");
			System.out.println("4. Devolver libro");
			System.out.println("5. Mostrar libros disponibles");
			System.out.println("6. Mostrar usuarios registrados");
			System.out.println("7. Generar informe estadístico");
			System.out.println("0. Salir");
			System.out.println("-----------------------------");
			System.out.print("Ingrese una opción: ");
			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					String titulo, autor, genero;
					Integer anioPublicacion;

					do {
						System.out.print("Ingrese el título del libro: ");
						titulo = scanner.nextLine();
					} while (titulo.trim().isEmpty()); // Validación para asegurarse de que el título no esté vacío

					do {
						System.out.print("Ingrese el autor del libro: ");
						autor = scanner.nextLine();
					} while (autor.trim().isEmpty()); // Validación para asegurarse de que el autor no esté vacío

					do {
						System.out.print("Ingrese el género del libro: ");
						genero = scanner.nextLine();
					} while (genero.trim().isEmpty()); // Validación para asegurarse de que el género no esté vacío

					while (true) {
						try {
							System.out.print("Ingrese el año de publicación del libro: ");
							anioPublicacion = Integer.parseInt(scanner.nextLine());
							if (anioPublicacion <= 0) {
								throw new IllegalArgumentException("El año de publicación debe ser un número entero positivo.");
							}
							break;
						} catch (NumberFormatException e) {
							System.out.println("Error: Ingrese un valor numérico válido para el año de publicación.");
						} catch (IllegalArgumentException e) {
							System.out.println("Error: " + e.getMessage());
						}
					}

					Estados estado = Estados.DISPONIBLE;
					Libro libro = new Libro(titulo, autor, genero, estado, anioPublicacion);
					biblioteca.agregarLibro(libro);
					System.out.println("Se ha agregado el libro Exitosamente!!!");
					break;
				case 2:
					int subOption;
					do {
						System.out.println("----------- SUBMENÚ LIBROS -----------");
						System.out.println("1. Buscar Libro por Título");
						System.out.println("2. Buscar Autor");
						System.out.println("0. Volver al Menú Principal");
						System.out.println("------------------------------------");
						System.out.print("Ingrese una opción: ");
						subOption = scanner.nextInt();
						scanner.nextLine();

						switch (subOption) {
							case 1:
								System.out.print("Ingrese el Título del libro a buscar: ");
								String buscadorTitulo = scanner.nextLine();
								List<Libro> librosPorTitulo = biblioteca.buscarLibroPorTitulo(buscadorTitulo);
								if (!librosPorTitulo.isEmpty()) {
									System.out.println("----- Libros encontrados por Título -----");
									for (Libro libroTitulo : librosPorTitulo) {
										System.out.println(libroTitulo.toString());
									}
								} else {
									System.out.println("No se encontraron libros con el título proporcionado.");
								}
								break;
							case 2:
								System.out.print("Ingrese el Autor del libro a buscar: ");
								String buscadorAutor = scanner.nextLine();
								List<Libro> librosPorAutor = biblioteca.buscarLibroPorAutor(buscadorAutor);
								if (!librosPorAutor.isEmpty()) {
									System.out.println("----- Libros encontrados por Autor -----");
									for (Libro libroAutor : librosPorAutor) {
										System.out.println(libroAutor.toString());
									}
								} else {
									System.out.println("No se encontraron libros con el autor proporcionado.");
								}
								break;
							case 0:
								System.out.println("Volviendo al Menú Principal.");
								break;
							default:
								System.out.println("Opción no válida. Intente nuevamente.");
								break;
						}
					} while (subOption != 0);
					break;
				case 3:
					// Mostrar libros disponibles
					System.out.println("----- LIBROS DISPONIBLES -----");
					for (int i = 0; i < biblioteca.getLibrosDisponibles().size(); i++) {
						Integer count = i + 1;
						System.out.println("ID: " + count + " - Título: " + biblioteca.getLibrosDisponibles().get(i).getTitulo() + " - Autor: " + biblioteca.getLibrosDisponibles().get(i).getAutor() + " - Género: " + biblioteca.getLibrosDisponibles().get(i).getGenero() + " - Año Publicación: " + biblioteca.getLibrosDisponibles().get(i).getAnioPublicacion());
					}

					// Solicitar ID del libro a prestar
					int idLibro;
					do {
						System.out.println("Ingrese el ID del Libro que desea Prestar: ");
						idLibro = scanner.nextInt();
						idLibro = idLibro - 1;
						scanner.nextLine();
					} while (idLibro < 0 || idLibro >= biblioteca.getLibrosDisponibles().size());

					// Mostrar usuarios registrados
					System.out.println("----- USUARIOS REGISTRADOS -----");
					for (int j = 0; j < biblioteca.getUsuariosRegistrados().size(); j++) {
						Integer count = j + 1;
						System.out.println("ID: " + count + " - Identificación: " + biblioteca.getUsuariosRegistrados().get(j).getIdentificacion() + " - Nombres: " + biblioteca.getUsuariosRegistrados().get(j).getNombre());
					}

					// Solicitar ID del usuario que realizará el préstamo
					int idUsuario;
					do {
						System.out.println("Ingrese el ID del Usuario que desea Prestar: ");
						idUsuario = scanner.nextInt();
						idUsuario = idUsuario - 1;
						scanner.nextLine();
					} while (idUsuario < 0 || idUsuario >= biblioteca.getUsuariosRegistrados().size());

					// Solicitar costo por día
					int costoPorDia;
					do {
						System.out.println("Ingrese el Coste por día: ");
						costoPorDia = scanner.nextInt();
						scanner.nextLine();
					} while (costoPorDia <= 0);

					// Solicitar días de arriendo
					int diasArriendo;
					do {
						System.out.println("Ingrese los días de Arriendo: ");
						diasArriendo = scanner.nextInt();
						scanner.nextLine();
					} while (diasArriendo <= 0);

					// Obtener fechas para el préstamo
					Date fechaInicioPrestamo = new Date(); // Obtén la fecha actual
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(fechaInicioPrestamo);
					calendar.add(Calendar.DAY_OF_MONTH, diasArriendo);
					Date nuevaFechaFinPrestamo = calendar.getTime();

					// Realizar el préstamo
					int sizePrestamos = biblioteca.getPrestamos().size() + 1;
					biblioteca.prestarLibro(sizePrestamos, biblioteca.getUsuariosRegistrados().get(idUsuario), biblioteca.getLibrosDisponibles().get(idLibro), fechaInicioPrestamo, nuevaFechaFinPrestamo, costoPorDia);
					break;

				case 4:
					// Mostrar usuarios registrados
					System.out.println("----- USUARIOS REGISTRADOS -----");
					for (int j = 0; j < biblioteca.getUsuariosRegistrados().size(); j++) {
						Integer count = j + 1;
						System.out.println("ID: " + count + " - Identificación: " + biblioteca.getUsuariosRegistrados().get(j).getIdentificacion() + " - Nombres: " + biblioteca.getUsuariosRegistrados().get(j).getNombre());
					}

					// Solicitar ID del usuario que realizará la devolución
					int idUsuarioDev;
					do {
						System.out.println("Ingrese el ID del Usuario que desea Devolver: ");
						idUsuarioDev = scanner.nextInt();
						idUsuarioDev = idUsuarioDev - 1;
						scanner.nextLine();
					} while (idUsuarioDev < 0 || idUsuarioDev >= biblioteca.getUsuariosRegistrados().size());

					// Verificar que el usuario tenga libros prestados para devolver
					if (biblioteca.getUsuariosRegistrados().get(idUsuarioDev).getLibros().isEmpty()) {
						System.out.println("El usuario seleccionado no tiene libros prestados para devolver.");
						break;
					}

					// Mostrar libros prestados por el usuario
					System.out.println("----- LIBROS PRESTADOS AL USUARIO -----");
					for (int i = 0; i < biblioteca.getUsuariosRegistrados().get(idUsuarioDev).getLibros().size(); i++) {
						Integer count = i + 1;
						System.out.println("ID: " + count + " - Título: " + biblioteca.getUsuariosRegistrados().get(idUsuarioDev).getLibros().get(i).getTitulo() + " - Autor: " + biblioteca.getUsuariosRegistrados().get(idUsuarioDev).getLibros().get(i).getAutor() + " - Género: " + biblioteca.getUsuariosRegistrados().get(idUsuarioDev).getLibros().get(i).getGenero() + " - Año Publicación: " + biblioteca.getUsuariosRegistrados().get(idUsuarioDev).getLibros().get(i).getAnioPublicacion());
					}

					// Solicitar ID del libro que se devolverá
					int idLibroDev;
					do {
						System.out.println("Ingrese el ID del Libro que desea Devolver: ");
						idLibroDev = scanner.nextInt();
						idLibroDev = idLibroDev - 1;
						scanner.nextLine();
					} while (idLibroDev < 0 || idLibroDev >= biblioteca.getUsuariosRegistrados().get(idUsuarioDev).getLibros().size());

					// Realizar la devolución del libro
					biblioteca.devolverLibro(biblioteca.getUsuariosRegistrados().get(idUsuarioDev), biblioteca.getUsuariosRegistrados().get(idUsuarioDev).getLibros().get(idLibroDev));
					break;
				case 5:
					System.out.println("----- LIBROS DISPONIBLES -----");
					biblioteca.imprimirLibrosDisponibles();
					break;
				case 6:
					System.out.println("----- USUARIOS REGISTRADOS -----");
					biblioteca.imprimirUsuariosRegistrados();
					break;
				case 7:
					System.out.println("----- INFORME PRESTAMOS VENCIDOS -----");
					biblioteca.imprimirInformePrestamosVencidos();
					System.out.println("----- INFORME LIBROS POR USUARIO -----");
					biblioteca.imprimirInformeLibrosPorUsuario();
					break;
				case 0:
					System.out.println("Saliendo del sistema.");
					break;
				default:
					System.out.println("Opción no válida. Intente nuevamente.");
					break;
			}
		}
		scanner.close();

		SpringApplication.run(BibliotecaApplication.class, args);
	}
}
