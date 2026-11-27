import java.util.Scanner;

public class Main {
    // Variante A asignada -> Promedio minimo de aprobacion: 11.0
    private static final double LIMITE_APROBACION = 11.0;
    private static final String RUTA_ARCHIVO = "datos/estudiantes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionAcademica gestion = new GestionAcademica(LIMITE_APROBACION);
        GestorArchivos gestor = new GestorArchivos(RUTA_ARCHIVO);

        gestion.setEstudiantes(gestor.cargarRegistros());

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    opcionRegistrarEstudiante(scanner, gestion);
                    break;
                case 2:
                    gestion.listarEstudiantes();
                    break;
                case 3:
                    opcionBuscarEstudiante(scanner, gestion);
                    break;
                case 4:
                    gestion.generarReporteAcademico();
                    break;
                case 5:
                    gestor.guardarRegistros(gestion.getEstudiantes());
                    break;
                case 6:
                    gestor.guardarRegistros(gestion.getEstudiantes());
                    System.out.println("Saliendo del sistema AulaLogic. Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Debe ingresar un numero entre 1 y 6.");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println();
        System.out.println("=====================================");
        System.out.println("        SISTEMA AULALOGIC");
        System.out.println("=====================================");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Generar reporte academico");
        System.out.println("5. Guardar registros");
        System.out.println("6. Salir");
        System.out.println("=====================================");
        System.out.print("Seleccione una opcion: ");
    }

    public static int leerEntero(Scanner scanner) {
        while (true) {
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Ingrese un numero entero valido: ");
            }
        }
    }

    public static String leerTextoValido(Scanner scanner, String mensaje) {
        String texto;
        while (true) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Error: El campo no puede estar vacio.");
            } else if (texto.contains(";")) {
                System.out.println("Error: El campo no puede contener punto y coma (;).");
            } else {
                return texto;
            }
        }
    }

    public static int leerCalificacionValida(Scanner scanner, int numeroNota) {
        int nota;
        while (true) {
            System.out.print("Ingrese Calificacion " + numeroNota + " (0 a 20): ");
            nota = leerEntero(scanner);
            if (nota >= 0 && nota <= 20) {
                return nota;
            } else {
                System.out.println("Error: La calificacion debe estar comprendida entre 0 y 20.");
            }
        }
    }

    public static void opcionRegistrarEstudiante(Scanner scanner, GestionAcademica gestion) {
        System.out.println("\n--- REGISTRO DE ESTUDIANTE ---");
        String codigo;
        while (true) {
            codigo = leerTextoValido(scanner, "Ingrese codigo del estudiante: ");
            if (gestion.existeCodigo(codigo)) {
                System.out.println("Error: El codigo '" + codigo + "' ya se encuentra registrado. Intente con otro.");
            } else {
                break;
            }
        }

        String nombre = leerTextoValido(scanner, "Ingrese nombre completo del estudiante: ");
        int[] notas = new int[3];
        for (int i = 0; i < 3; i++) {
            notas[i] = leerCalificacionValida(scanner, i + 1);
        }

        Estudiante nuevo = new Estudiante(codigo, nombre, notas);
        if (gestion.registrarEstudiante(nuevo)) {
            System.out.println("Estudiante registrado con exito. Promedio: "
                    + String.format("%.2f", nuevo.calcularPromedio())
                    + " | Estado: " + nuevo.obtenerEstado(gestion.getLimiteAprobacion()));
        } else {
            System.out.println("No se pudo registrar el estudiante.");
        }
    }

    public static void opcionBuscarEstudiante(Scanner scanner, GestionAcademica gestion) {
        System.out.println("\n--- CONSULTA DE ESTUDIANTE ---");
        String codigo = leerTextoValido(scanner, "Ingrese el codigo del estudiante a buscar: ");
        Estudiante encontrado = gestion.buscarPorCodigo(codigo);

        if (encontrado == null) {
            System.out.println("No se encontro ningun estudiante con el codigo: " + codigo);
        } else {
            int[] n = encontrado.getNotas();
            System.out.println("Codigo          : " + encontrado.getCodigo());
            System.out.println("Nombre completo : " + encontrado.getNombre());
            System.out.println("Calificaciones  : " + n[0] + ", " + n[1] + ", " + n[2]);
            System.out.printf("Promedio        : %.2f%n", encontrado.calcularPromedio());
            System.out.println("Nota maxima     : " + encontrado.obtenerNotaMaxima());
            System.out.println("Nota minima     : " + encontrado.obtenerNotaMinima());
            System.out.println("Estado academico: " + encontrado.obtenerEstado(gestion.getLimiteAprobacion()));
        }
    }
}
