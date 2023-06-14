# Trabajo Práctico Grupal. Desarrollo en Java

El objetivo principal es proporcionar un conjunto de funcionalidades que permitan realizar un seguimiento detallado de las publicaciones de un perfil de Instagram, incluyendo datos como el nombre, la fecha de subida, la cantidad de "me gusta", las etiquetas y los comentarios asociados.

## Materia: Algoritmos y Estructura de Datos II
Este proyecto fue desarrollado como parte de la materia "Algoritmos y Estructura de Datos II". 

## Características principales
Características del sistema de gestión de estadísticas de publicaciones de Instagram:

1. Registro de datos específicos para cada tipo de publicación:
   - Videos: duración, resolución, cantidad de cuadros.
     - Operaciones: avanzar, detener, aplicar filtros.
   - Imágenes: resolución, ancho, alto.
     - Operaciones: aplicar filtros.
   - Audios: duración, velocidad de bits.
     - Operaciones: avanzar, detener.
   - Texto: cantidad de caracteres, fuente, tamaño.

2. Estructura de agrupaciones lógicas (álbumes):
   - Creación, gestión y eliminación de álbumes.
   - Publicaciones pueden pertenecer a cero o más álbumes.
   - Álbumes pueden contener cero o más sub-álbumes (estructura de árbol de álbumes).
   - Cálculo de estadísticas por álbum: cantidad de "me gusta" acumulada, cantidad de publicaciones por tipo y totales.

3. Carga de datos desde archivos:
   - Permite cargar datos de publicaciones desde un archivo XML.
   - La lista de publicaciones se encuentra alfabéticamente por su nombre.

4. Consulta y reproducción de publicaciones seleccionadas:
   - Aplicación de filtros.
   - Reproducción simulada con un listado detallado del proceso, indicando inicio, fin y configuraciones aplicadas.

5. Generación de estadísticas:
   - Generar estadísticas resumidas de las publicaciones mediante gráficos, tablas, histogramas u otras herramientas visuales.
  
6. Generación de reportes:
   - Listado completo de publicaciones agrupadas por tipo (audio, foto, video, texto), ordenado por cantidad de "me gusta" descendente, mostrando cantidad de publicaciones y cantidad de "me gusta" promedio por tipo.
   - Listado alfabético de álbumes, detallando cantidad de publicaciones subidas en un rango de fechas solicitado, incluyendo la cantidad de comentarios correspondientes.

## Uso del sistema
El sistema se puede utilizar mediante una interfaz intuitiva que permite agregar y visualizar las publicaciones, así como realizar operaciones específicas en cada tipo de contenido. Además, se ofrece la capacidad de crear álbumes y sub-álbumes para organizar las publicaciones según criterios personalizados.

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- Java
- JFreeChart(https://github.com/jfree/jfreechart/releases/tag/v1.5.2)

## Instalación

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu entorno de desarrollo favorito.
3. Configura las dependencias necesarias (si las hay).
4. Compila el código fuente.
5. ¡Listo! Ahora podes ejecutar el programa.

## Equipo de desarrollo

- Ramiro Lazzaro
- Fausto Villegas
- Franco Benettini
- Joaquin Vai

## Contacto

Si tienes alguna pregunta o consulta relacionada con este proyecto, no dudes en ponerte en contacto con el equipo de desarrollo:

- Ramiro Lazzaro: ramirol2235@gmail.com
- Fausto Villegas: fausto@example.com
- Franco Benettini: franbenettini@gmail.com
- Joaquin Vai: joaquinvai654@gmail.com
