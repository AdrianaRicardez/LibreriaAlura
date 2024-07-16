# LibreriaAlura
Change Buscar libros Alura Latam

 Es una aplicación de consola diseñada para la gestión de libros y autores, con capacidades para buscar libros y listar registros. Esta aplicación se conecta a una API para obtener datos y emplea una base de datos PostgreSQL para el almacenamiento y gestión de la información.

Funcionalidades del Menú
La aplicación ofrece un menú interactivo con las siguientes opciones:

1. Búsqueda de libro por título
Permite realizar búsquedas de libros por título utilizando la API de Gutendex. Si el libro se encuentra en la API, se registra en la base de datos. En caso de que el libro ya esté registrado, se muestra un mensaje indicando que no se puede registrar el mismo libro más de una vez.

2. Listar todos los libros registrados
Presenta una lista de todos los libros registrados en la base de datos. Si no hay libros registrados, se notifica al usuario con un mensaje.

3. Listar todos los autores registrados
Muestra una lista de todos los autores registrados en la base de datos. Si no hay autores registrados, se muestra un mensaje indicando esto.

4. Listar autores vivos en un determinado año
Permite buscar autores que estaban vivos en un año específico. Se solicita al usuario que ingrese un año y luego se muestra una lista de los autores que estaban vivos durante ese año.

5. Listar libros por idioma
Permite listar libros registrados en la base de datos según su idioma. Se solicita al usuario que ingrese un código de idioma (es, en, fr, pt) y luego se muestra una lista de los libros en ese idioma, junto con el total de libros en dicho idioma.

0. Salir
Permite cerrar la aplicación.

Configuración
Para ejecutar la aplicación, asegúrate de tener un entorno configurado con Spring Boot y una base de datos PostgreSQL. En el archivo application.properties, se deben definir las siguientes propiedades:

spring.datasource.url=jdbc:postgresql://${DB_HOST}/nombre_de_base_de_datos
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.format-sql=true

Ejecución
Para ejecutar la aplicación, utiliza el siguiente comando en la raíz del proyecto:

./mvnw spring-boot:run
