# CRUD Spring Boot Project
# Descripción
Este es un proyecto de ejemplo para demostrar cómo construir una aplicación CRUD utilizando Spring Boot, Spring Data JPA y PostgreSQL. La aplicación permite manejar operaciones básicas de CRUD (Crear, Leer, Actualizar, Eliminar) sobre un modelo Movimiento.

# Dependencias
Este proyecto utiliza las siguientes dependencias principales:

- Spring Boot Starter Data JPA: Para la integración con JPA y la gestión de la base de datos.
- Spring Boot Starter Web: Para crear servicios web RESTful.
- PostgreSQL Driver: Para la conexión con la base de datos PostgreSQL.
- Spring Boot Starter Test: Para pruebas unitarias y de integración.
- Spring Boot DevTools: Para mejorar la experiencia de desarrollo con características como recarga automática.
- Configuración
- Configuración de la Base de Datos
- Asegúrate de que PostgreSQL esté instalado y en ejecución en tu máquina local. Puedes cambiar la URL, el usuario y la contraseña de la base de datos en el archivo application.properties según tu configuración local.
  
- spring.application.name=crud
- spring.datasource.url=jdbc:postgresql://localhost:5432/crud
- spring.datasource.username=postgres
- spring.datasource.password=123456
- spring.jpa.hibernate.ddl-auto=create-drop
- spring.jpa.show-sql=true
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
- spring.jpa.properties.hibernate.format_sql=true


# Crear Cliente-cuenta- movimiento
- ![image](https://github.com/user-attachments/assets/c3e3bcc0-00d5-41b6-a248-2b660adca670) 
- ![image](https://github.com/user-attachments/assets/02dba18e-5afb-4704-b3f3-6371a5240a66)

# Listar Cliente-cuenta- movimiento
- ![image](https://github.com/user-attachments/assets/5b423882-2343-4148-8830-f0b0c99200c4)
- ![image](https://github.com/user-attachments/assets/af507bd2-2e77-4f25-8515-f4b5897c8d86)

# Actualizar Cliente-cuenta- movimiento
- ![image](https://github.com/user-attachments/assets/4a8deb6d-3d6c-4813-bd2d-723daf760122)
- ![image](https://github.com/user-attachments/assets/bea6c99a-1a0e-47f6-bd82-c1627bef2b0a)

# Eliminar Cliente-cuenta- movimiento
- ![image](https://github.com/user-attachments/assets/38d22eea-8c11-40d1-bedc-1d82444e2767)
- ![image](https://github.com/user-attachments/assets/963efe00-3403-4343-a1df-a02e00023c26)


