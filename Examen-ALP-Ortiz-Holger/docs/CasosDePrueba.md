# Matriz de Casos de Prueba Obligatorios — Sistema AulaLogic

- **Estudiante:** Holger Steeve Ortiz Sanchez
- **Variante asignada:** A (Límite mínimo de aprobación: `11.00`)

| Caso | Situación comprobada | Datos de Entrada | Resultado Esperado | Resultado Obtenido | Estado |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **CP01** | Registro correcto de un estudiante. | Código: `E001`, Nombre: `Ana Torres`, Notas: `15, 18, 12` | Se registra en el `ArrayList`, promedio `15.00`, estado `APROBADO`. | Registrado con éxito; promedio `15.00`, estado `APROBADO`. | **APROBADO** |
| **CP02** | Intento de registrar un código duplicado. | Código: `E001` | Rechaza el código repetido y solicita otro. | Muestra error de código registrado y pide nuevo ingreso. | **APROBADO** |
| **CP03** | Calificación fuera del rango permitido. | Nota 1: `-4`, `25`, `16` | Rechaza valores fuera de `[0, 20]` y solicita nuevamente. | Muestra error de rango `[0, 20]` y acepta `16`. | **APROBADO** |
| **CP04** | Promedio igual al límite de aprobación asignado (11). | Código: `E004`, Nombre: `Carlos Gomez`, Notas: `11, 11, 11` | Promedio `11.00` (`>= 11.0`), estado `APROBADO`. | Calcula promedio `11.00` y asigna estado `APROBADO`. | **APROBADO** |
| **CP05** | Búsqueda de un estudiante existente. | Opción `3`, Código: `E003` | Muestra código, nombre (`Marta Ruiz`), notas `[20, 18, 19]`, prom `19.00`, máx `20`, mín `18`, `APROBADO`. | Despliega todos los datos y métricas de `E003`. | **APROBADO** |
| **CP06** | Búsqueda de un estudiante inexistente. | Opción `3`, Código: `E999` | Muestra mensaje informativo sin caídas. | Muestra `"No se encontro ningun estudiante con el codigo: E999"`. | **APROBADO** |
| **CP07** | Reporte sin estudiantes registrados. | Opción `4` con lista vacía | Informa que no hay estudiantes y evita división entre cero. | Muestra `"No hay estudiantes registrados para generar el reporte academico."`. | **APROBADO** |
| **CP08** | Guardado y recuperación al reiniciar. | Opción `5`/`6`, reinicio de `Main` | Guarda en `datos/estudiantes.txt` y recupera los 5 registros al iniciar. | Al iniciar muestra `"Se recuperaron 5 registro(s) desde datos/estudiantes.txt"`. | **APROBADO** |
