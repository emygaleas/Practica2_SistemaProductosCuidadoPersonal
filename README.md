# TAREA SEMANA 13 POO – PRÁCTICA 2

## Requisitos

- **Instalar MySQL Connector/J**

  Descargar desde: https://dev.mysql.com/downloads/connector/j/  
  - En 'Select Operating System' escogemos Platform Independent
  - Seleccionar y descargar el archivo `.zip`.  
  - Descomprimir el archivo para obtener el `.jar`.

  **Agregar el conector en IntelliJ IDEA:**

  1. Ir a **File > Project Structure** (`Ctrl + Alt + Shift + S`).
  2. Seleccionar **Libraries**.
  3. Clic en **+ > Java**.
  4. Ir a la carpeta donde está el archivo `.jar` descargado.
  5. Seleccionar el `.jar`.
  6. Aplicar cambios.

- **Configurar la base de datos**

  Ejecutar el script `sistema_productos_cuidado_personal.sql` en MySQL Workbench.  
  Asegurarse de crear la base de datos, tablas e inserciones correctamente.

- **Configurar conexión en `ConexionBD.java`**

  Si es necesario, cambiar el usuario y contraseña según su configuración local.  
  Por defecto viene:

  ```java
  private static final String USER = "root";
  private static final String PASSWORD = "1234";
  ```
  
- **Finalmente ejecutar el programa desde Main.java**

  Usuarios de prueba para Login:

    | Usuario     | Contraseña |
    | ----------- | ---------- |
    | emilygaleas | esfot123   |
    | admin       | adminABC   |
    | usuario1    | abc123     |
