# Análisis del Problema — Sistema AulaLogic (Variante A)

## 1. Definición del Problema
Desarrollar un sistema de consola en Java bajo Programación Orientada a Objetos (POO) que administre el rendimiento académico de un curso mediante cuatro clases (`Estudiante`, `GestionAcademica`, `GestorArchivos` y `Main`), persistiendo los registros en `datos/estudiantes.txt` con el límite de aprobación de la **Variante A (11,00 puntos)**.

## 2. Análisis Entrada – Proceso – Salida (EPS)
- **Entradas:** Archivo `datos/estudiantes.txt` (`codigo;nombre;n1;n2;n3`), opciones de menú (`1-6`), código, nombre y tres calificaciones (`0-20`).
- **Procesos:** Recuperación automática al inicio, validación de duplicados y rangos, cálculo de promedio (`suma / 3.0`), extremos, estado académico, indicadores globales y ordenamiento descendente por promedio.
- **Salidas:** Menú en consola, listado tabular, ficha de consulta, reporte académico y actualización sin duplicados de `datos/estudiantes.txt`.
