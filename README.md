# [Proyecto] Sistema de gestión de una biblioteca

## Descripción:
*Debes crear un sistema de gestión para una biblioteca que permita realizar operaciones cómo agregar libros, buscar libros por título o autor, prestar libros a los usuarios, devolver libros, mostrar la lista de libros disponibles, y generar informes estadísticos.*

## Requisitos:

1. Crea una clase llamada "Libro" con los siguientes atributos: título, autor, año de publicación, género y estado (disponible o prestado).

2. Crea una clase llamada "Usuario" con los siguientes atributos: nombre, identificación y una lista de libros prestados, lista de libros solicitados.

3. Crea una clase llamada "Biblioteca" con los siguientes atributos: una lista de libros disponibles, una lista de usuarios registrados y una lista de préstamos.

4. Crear una clase llamada ´Préstamo’ con los siguientes atributos id, Usuario, Libro, fecha de inicio, fecha de fin y costo por día y el total un atributo de estado ( En curso o entregado) , esta clase tendrá un método para calcular el total ( costoPorDía * numeroDeDías)

5. Implementa los siguientes métodos en la clase "Biblioteca":
   - Agregar un libro a la lista de libros disponibles.
   - Buscar un libro por título o autor y mostrar los resultados.
   - Prestar un libro a un usuario.
   - Devolver un libro prestado por un usuario.
   - Mostrar la lista de libros disponibles.
   - Mostrar la lista de usuarios registrados.
   - Generar un informe estadístico con la cantidad de libros prestados por cada usuario.

6. Implementa los siguientes métodos en la clase "Usuario":
   - Mostrar los libros prestados por el usuario.
   - Solicitar un libro prestado.
   - Devolver un libro prestado.

7. Crea una interfaz de usuario (puede ser de consola o gráfica) que permita interactuar con el sistema de gestión de la biblioteca. La interfaz debe proporcionar opciones para agregar libros, buscar libros, prestar libros, devolver libros, mostrar la lista de libros disponibles, mostrar la lista de usuarios registrados y generar el informe estadístico.

8. Implementa una funcionalidad adicional en la clase "Biblioteca" para permitir la reserva de libros. Un usuario puede reservar un libro que esté prestado, y cuando el libro sea devuelto, se le notificará al usuario que puede pasar a recogerlo.

9. Implementa una funcionalidad adicional en la clase "Biblioteca" para llevar un registro de los préstamos vencidos. Un préstamo se considera vencido si ha pasado más de 30 días desde que se prestó el libro. La biblioteca debe tener la capacidad de generar un informe con los préstamos vencidos.
