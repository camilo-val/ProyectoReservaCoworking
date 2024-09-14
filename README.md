##Funcionalidad

El proyecto de reservas te permite agendar una reserva mediante un formulario que te captura los siguientes datos: nombre, fecha, espacio, hora inicio y duración.
Adicionalmente permite ver las reservas y eliminar la reserva desaeada.

7yu# Guía de Configuración del Ambiente de Desarrollo para Proyecto Java Web con XAMPP

## Descripción

Este archivo contiene instrucciones para configurar el entorno de desarrollo para un proyecto Java web utilizando XAMPP. Asegúrate de seguir cada paso cuidadosamente para asegurar que todo esté configurado correctamente.

## Requisitos

1. **JDK**: Instalar la versión 17 o superior.
2. **Apache Tomcat**: Necesitarás Apache Tomcat como servidor de aplicaciones.
3. **XAMPP**: Incluye Apache y MySQL, necesarios para crear la base de datos.

## Instalación

### 1. Instalar XAMPP

1. Descarga XAMPP desde el sitio oficial: [XAMPP Downloads](https://www.apachefriends.org/index.html).
2. Sigue las instrucciones del instalador para instalar XAMPP en tu sistema.
3. Una vez instalado, inicia los servicios de Apache y MySQL desde el panel de control de XAMPP.

### 2. Instalar JDK

1. Descarga el JDK desde el sitio oficial de Oracle
2. Sigue las instrucciones del instalador para instalar el JDK en tu sistema (no olvides configurar las variables JAVA_HOME y el path).

### 3. Configurar Apache Tomcat

1. Descarga Apache Tomcat desde el sitio oficial: [Apache Tomcat Downloads](https://tomcat.apache.org/download-90.cgi).
2. Extrae el archivo descargado a un directorio de tu elección (por ejemplo, `C:\apache-tomcat-9.x`).
3. Configura las variables de entorno:
    - **CATALINA_HOME**: Ruta al directorio de Apache Tomcat (por ejemplo, `C:\apache-tomcat-9.x`).
    - **JAVA_HOME**: Ruta al directorio donde está instalado el JDK.
4. Inicia Apache Tomcat ejecutando el script `startup.bat` (en Windows) o `startup.sh` (en Linux/Mac) desde el directorio `bin` de Tomcat.

### 4. Despliegue en servidor tomcat

1. **Coloca el proyecto Java** en la carpeta `webapps` de tu directorio de Tomcat (`C:\apache-tomcat-9.x\webapps`).
2. Asegúrate de que tu proyecto esté empaquetado como un archivo WAR (`.war`).

### 4. Configurar el Proyecto Java

1. Asegurese de descargar el mysql connector mysql-connector-java-8.0.17
2. Realiza la configuración del servidor de bases de datos.


### 5. Configurar la Conexión a la Base de Datos

1. **Accede a phpMyAdmin** desde [http://localhost/phpmyadmin](http://localhost/phpmyadmin).
2. Crea una nueva base de datos llamada reservas
3. Configura tu archivo de conexión de base de datos en el proyecto Java (normalmente `web.xml` o en un archivo de configuración de tu aplicación). Asegúrate de usar las credenciales y el nombre de la base de datos correctos.

### 6. Iniciar el Servidor y Probar

1. Asegúrate de que Apache Tomcat esté corriendo.
2. Abre tu navegador y visita `http://localhost:8080/ProyectoReservaCoworking` para verificar que tu aplicación se está ejecutando correctamente.

## Solución de Problemas

- **Problema**: La aplicación no se despliega en Tomcat.
  **Solución**: Verifica el archivo `server.xml` en el directorio `conf` de Tomcat y asegúrate de que no haya conflictos de puertos. Revisa los archivos de registro (`logs`) para errores específicos.

- **Problema**: No se puede conectar a la base de datos.
  **Solución**: Revisa la configuración en tu archivo de conexión de base de datos. Asegúrate de que los detalles (nombre de la base de datos, usuario, contraseña) sean correctos y que el servidor MySQL esté en funcionamiento.

