public class Estudiante {
    private String codigo;
    private String nombre;
    private int[] notas;

    public Estudiante(String codigo, String nombre, int[] notas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.notas = new int[3];
        if (notas != null) {
            for (int i = 0; i < 3 && i < notas.length; i++) {
                this.notas[i] = notas[i];
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getNotas() {
        return notas;
    }

    public void setNotas(int[] notas) {
        this.notas = notas;
    }

    public double calcularPromedio() {
        int suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        return suma / 3.0;
    }

    public int obtenerNotaMaxima() {
        int max = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > max) {
                max = notas[i];
            }
        }
        return max;
    }

    public int obtenerNotaMinima() {
        int min = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < min) {
                min = notas[i];
            }
        }
        return min;
    }

    public String obtenerEstado(double limiteAprobacion) {
        if (calcularPromedio() >= limiteAprobacion) {
            return "APROBADO";
        } else {
            return "REPROBADO";
        }
    }

    public String toFormatoArchivo() {
        return codigo + ";" + nombre + ";" + notas[0] + ";" + notas[1] + ";" + notas[2];
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-25s | Notas: [%2d, %2d, %2d] | Prom: %5.2f | Max: %2d | Min: %2d",
                codigo, nombre, notas[0], notas[1], notas[2],
                calcularPromedio(), obtenerNotaMaxima(), obtenerNotaMinima());
    }
}
