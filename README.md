<div align="center">
  
# ForoHub

</div>

## Descripción
Es un proyecto que replica el funcionamiento de un foro, enfocado en la gestión de tópicos relacionados con cursos. Permite a los usuarios realizar operaciones de crear, leer, actualizar y eliminar (CRUD) sobre estos tópicos, con validaciones según las reglas de negocio. Además, utiliza una base de datos relacional para garantizar la persistencia de la información, acompañada de un servicio integral de autenticación y autorización que restringe el acceso a la información.

## Funcionalidades
- `Crear un nuevo tópico`: Al crear un tópico, los datos que se enviarán son: título, mensaje, nombre del usuario y nombre del curso.
- `Mostrar todos los tópicos`: Al mostrar los tópicos, los datos que se devolverán son: ID del tópico, título, mensaje, fecha de creación, estado, nombre del usuario y nombre del curso. Además, se puede utilizar un criterio de búsqueda con nombre del curso y año específico.
- `Mostrar un tópico específico`: Al mostrar un tópico, en la URI se enviará el ID del tópico y los datos que se devolverán son: ID del tópico, título, mensaje, fecha de creación, estado, nombre del usuario y nombre del curso.
- `Actualizar un tópico`: Al actualizar un tópico, en la URI se enviará el ID del tópico y los datos que se enviarán son: título, mensaje y nombre del curso.
- `Eliminar un tópico`: Al eliminar un tópico, en la URI se enviará el ID del tópico y no se devolverá nada en el cuerpo de la respuesta.

## Acceso al proyecto
- Clona este repositorio en tu git bash o descárgalo como archivo ZIP:  
```
git clone https://github.com/gianellannie/Challenge-Four.git
```

## Abre y ejecuta el proyecto
1. Para conectar con la base de datos, crea variables de entorno y coloca el nombre de las variables que están en el archivo application.properties;
2. Luego de crear las variables de entorno reinica tu computadora.
3. Abre el archivo "Challenge-Four" en IntelliJ IDEA.
4. Busca y haz clic en la clase "ChallengeFourApplication.java" del proyecto.
5. Selecciona "Run" en el menú superior y luego "Run 'ChallengeFourApplication'".
6. Para probar las funcionalidades de la API se puede utilizar alguna herramienta de pruebas de API, como Postman.

## Tecnologías utilizadas
- Java 17
- Spring
- MySQL
- Intellij IDEA
