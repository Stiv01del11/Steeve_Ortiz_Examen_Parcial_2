import java.util.ArrayList;

public class GestionAcademica {
    private ArrayList<Estudiante> estudiantes;
    private double limiteAprobacion;

    public GestionAcademica(double limiteAprobacion) {
        this.estudiantes = new ArrayList<>();
        this.limiteAprobacion = limiteAprobacion;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public double getLimiteAprobacion() {
        return limiteAprobacion;
    }

    public void setLimiteAprobacion(double limiteAprobacion) {
        this.limiteAprobacion = limiteAprobacion;
    }

    public boolean existeCodigo(String codigo) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getCodigo().equalsIgnoreCase(codigo.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarEstudiante(Estudiante est) {
        if (est == null) {
            return false;
        }
        if (existeCodigo(est.getCodigo())) {
            return false;
        }
        estudiantes.add(est);
        return true;
    }

    public Estudiante buscarPorCodigo(String codigo) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getCodigo().equalsIgnoreCase(codigo.trim())) {
                return estudiantes.get(i);
            }
        }
        return null;
    }

    public void listarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No existen estudiantes registrados en el sistema.");
            return;
        }
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s | %-25s | %-19s | %-11s | %-7s | %-7s | %-10s%n",
                "CODIGO", "NOMBRE COMPLETO", "CALIFICACIONES", "PROMEDIO", "MAX", "MIN", "ESTADO");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for (Estudiante e : estudiantes) {
            int[] n = e.getNotas();
            System.out.printf("%-8s | %-25s | [%2d, %2d, %2d]        | %11.2f | %7d | %7d | %-10s%n",
                    e.getCodigo(), e.getNombre(), n[0], n[1], n[2],
                    e.calcularPromedio(), e.obtenerNotaMaxima(), e.obtenerNotaMinima(),
                    e.obtenerEstado(limiteAprobacion));
        }
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }

    public void generarReporteAcademico() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados para generar el reporte academico.");
            return;
        }

        int total = estudiantes.size();
        int aprobados = 0;
        int reprobados = 0;
        double sumaPromedios = 0.0;

        Estudiante mejor = estudiantes.get(0);
        Estudiante peor = estudiantes.get(0);

        for (int i = 0; i < total; i++) {
            Estudiante actual = estudiantes.get(i);
            double prom = actual.calcularPromedio();
            sumaPromedios += prom;

            if (prom >= limiteAprobacion) {
                aprobados++;
            } else {
                reprobados++;
            }

            if (prom > mejor.calcularPromedio()) {
                mejor = actual;
            }
            if (prom < peor.calcularPromedio()) {
                peor = actual;
            }
        }

        double promedioGeneral = sumaPromedios / total;
        double porcentajeAprobados = (aprobados * 100.0) / total;

        System.out.println("\n================ REPORTE ACADEMICO DEL CURSO ================");
        System.out.printf("Limite minimo de aprobacion (Variante A): %.2f%n", limiteAprobacion);
        System.out.println("Cantidad total de estudiantes : " + total);
        System.out.println("Cantidad de aprobados         : " + aprobados);
        System.out.println("Cantidad de reprobados        : " + reprobados);
        System.out.printf("Promedio general del curso    : %.2f%n", promedioGeneral);
        System.out.printf("Porcentaje de aprobados       : %.2f%%%n", porcentajeAprobados);
        System.out.printf("Estudiante con mayor promedio : %s (%s) - %.2f%n",
                mejor.getNombre(), mejor.getCodigo(), mejor.calcularPromedio());
        System.out.printf("Estudiante con menor promedio : %s (%s) - %.2f%n",
                peor.getNombre(), peor.getCodigo(), peor.calcularPromedio());

        System.out.println("\n--- Estudiantes con promedio inferior al promedio general ---");
        boolean hayInferiores = false;
        for (Estudiante e : estudiantes) {
            if (e.calcularPromedio() < promedioGeneral) {
                System.out.printf(" * %s - %s (Promedio: %.2f)%n",
                        e.getCodigo(), e.getNombre(), e.calcularPromedio());
                hayInferiores = true;
            }
        }
        if (!hayInferiores) {
            System.out.println(" Ningun estudiante tiene promedio inferior al promedio general.");
        }

        ArrayList<Estudiante> ordenados = new ArrayList<>(estudiantes);
        for (int i = 0; i < ordenados.size() - 1; i++) {
            for (int j = 0; j < ordenados.size() - 1 - i; j++) {
                if (ordenados.get(j).calcularPromedio() < ordenados.get(j + 1).calcularPromedio()) {
                    Estudiante temp = ordenados.get(j);
                    ordenados.set(j, ordenados.get(j + 1));
                    ordenados.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n--- Listado ordenado de mayor a menor promedio ---");
        for (int i = 0; i < ordenados.size(); i++) {
            Estudiante e = ordenados.get(i);
            System.out.printf("%d. %-8s | %-25s | Promedio: %5.2f | %s%n",
                    (i + 1), e.getCodigo(), e.getNombre(),
                    e.calcularPromedio(), e.obtenerEstado(limiteAprobacion));
        }
        System.out.println("=============================================================\n");
    }
}
