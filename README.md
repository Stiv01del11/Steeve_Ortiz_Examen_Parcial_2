[README.md](https://github.com/user-attachments/files/35520211/README.md)
# Steeve_Ortiz_Examen_Parcial_2# Sistema de Seguimiento del Rendimiento Académico — AulaLogic

## Identificación del Estudiante
- **Estudiante:** Holger Steeve Ortiz Sanchez
- **Institución:** Universidad Técnica de Ambato (UTA)
- **Facultad:** Ingeniería en Sistemas, Electrónica e Industrial
- **Carrera:** Ingeniería de Software
- **Asignatura:** Algoritmos y Lógica de Programación
- **Fecha:** 27 de noviembre de 2026
- **Variante asignada:** **A** (Promedio mínimo de aprobación: **11,00**)

## Descripción del Problema
La Universidad Técnica de Ambato requiere una aplicación de consola desarrollada en Java que permita registrar, listar, consultar, procesar y almacenar las calificaciones de los estudiantes de una asignatura. El sistema garantiza la persistencia de datos mediante archivos de texto plano (`datos/estudiantes.txt`), aplica validaciones estrictas de entrada y calcula indicadores estadísticos del rendimiento académico del curso.

## Estructura del Repositorio
```text
Examen-ALP-Ortiz-Holger/
|-- README.md
|-- src/
|   |-- Main.java
|   |-- Estudiante.java
|   |-- GestionAcademica.java
|   `-- GestorArchivos.java
|-- datos/
|   `-- estudiantes.txt
|-- docs/
|   |-- Analisis.md
|   `-- CasosDePrueba.md
`-- evidencias/
    `-- Ejecucion.png
```

## Instrucciones de Compilación y Ejecución
1. Abrir una terminal en la carpeta raíz del proyecto (`Examen-ALP-Ortiz-Holger/`).
2. Compilar todos los archivos fuente:
   ```bash
   javac -d bin src/*.java
   ```
3. Ejecutar la clase principal desde la raíz del proyecto:
   ```bash
   java -cp bin Main
   ```

## Funcionalidades Implementadas y Limitaciones
- **RF01 (Registro y validación):** Control de códigos únicos, rechazo de campos vacíos o con `;`, validación de notas en `[0, 20]`, almacenamiento en arreglo `int[3]` y `ArrayList<Estudiante>`.
- **RF02 (Procesamiento):** Promedio decimal, nota máxima, mínima y estado académico (`APROBADO` / `REPROBADO`) según el límite de la Variante A (`11.0`).
- **RF03 (Consulta):** Búsqueda individual por código mostrando detalle completo.
- **RF04 (Reporte académico):** Conteo total, aprobados, reprobados, promedio general, porcentaje de aprobados, extremos, estudiantes bajo el promedio general y ordenamiento descendente.
- **RF05 (Persistencia):** Lectura y escritura con `FileReader`/`BufferedReader` y `FileWriter`/`BufferedWriter` en `datos/estudiantes.txt`.
- **Limitaciones:** Ejecución exclusiva por consola estándar y restricción del carácter `;` en nombres y códigos.
