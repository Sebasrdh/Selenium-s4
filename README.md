# Proyecto de Automatización con Selenium

Este proyecto utiliza Selenium para automatizar pruebas de una aplicación web. También se integra con Allure para generar reportes de las ejecuciones de las pruebas.

## Requisitos Previos

Asegúrate de tener instalados los siguientes requisitos previos antes de descargar y ejecutar el proyecto:

1. **Java Development Kit (JDK)**: Asegúrate de tener la versión 23 o compatible instalada.
2. **Gradle**: Aunque el proyecto incluye el wrapper de Gradle, es recomendable tener Gradle instalado globalmente.
3. **Java IDE**: Se recomienda usar IntelliJ IDEA o Eclipse.
4. **Código FrontEnd**: Se debe poseer el código correspondiente al Front de la Semana 3 Programa Eureka.
5. **Código BackEnd**: Se debe poseer el código correspondiente al Back de la Semana 3 Programa Eureka.
6. **MySQL**: Para correr la BBDD del proyecto.

## Pasos para Descargar y Ejecutar el Proyecto

1. **Clonar el Repositorio**
   ```bash
   git clone https://github.com/Sebasrdh/Selenium-s4.git
   cd tu_repositorio
   ```

2. **Instalar las Dependencias**
   Gradle manejará las dependencias automáticamente. Verifica que el archivo `build.gradle` esté correctamente configurado para tu entorno.

   ```bash
   ./gradlew clean build
   ```
3. **Ejecutar el BackEnd de la S3**
     Para ejecutar este código debemos irnos a la terminal de nuestro IDE de preferencia y ejecutar los siguientes comandos:
      - npm install
      - npm run dev
  
4. **Ejecutar el FrontEnd de la S3**
      Para ejecutar este código debemos irnos a la terminal de nuestro IDE de preferencia y ejecutar los siguientes comandos:
      - npm install
      - npm run start
  
## Una vez ya iniciados los proyectos Front y Back + la BBDD MySQL comenzamos a ejecutar las pruebas.

1. **Ejecutar las Pruebas**
   Para ejecutar las pruebas con JUnit, usa el siguiente comando:
   ```bash
   ./gradlew test
   ```

2. **Generar Reportes con Allure**
   Para generar y ver los reportes de Allure, usa el siguiente comando:
   ```bash
   ./gradlew allureServe
   ```

   Esto iniciará un servidor y abrirá tu navegador predeterminado con el reporte de pruebas de Allure.

## Estructura del Proyecto

- `/src/test/java` - Contiene las clases de prueba.
- `build.gradle` - Archivo de configuración de Gradle.

## Personalización

Puedes personalizar las pruebas añadiendo nuevos casos o actualizando los existentes en la carpeta `src/test/java/`.

## Notas Adicionales

- **Dependencias**: Todas las dependencias necesarias están listadas en el archivo `build.gradle`.
- **Configuración del Navegador**: El proyecto está configurado para utilizar ChromeDriver. Asegúrate de tener Chrome y ChromeDriver correctamente instalados en tu sistema.
- **Tiempo de Espera en Las Pruebas**: Temporizadores están configurados para evitar fallos debidos a la carga asíncrona de elementos en la página.

## Autores

- Sebastian Gonzalez - [Sebasrdh](https://github.com/Sebasrdh)
