import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivos {
    private String rutaArchivo;

    public GestorArchivos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarRegistros(ArrayList<Estudiante> estudiantes) {
        File archivo = new File(rutaArchivo);
        File carpetaPadre = archivo.getParentFile();
        if (carpetaPadre != null && !carpetaPadre.exists()) {
            carpetaPadre.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            for (int i = 0; i < estudiantes.size(); i++) {
                bw.write(estudiantes.get(i).toFormatoArchivo());
                bw.newLine();
            }
            System.out.println("Registros guardados correctamente en " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar los registros en el archivo: " + e.getMessage());
        }
    }

    public ArrayList<Estudiante> cargarRegistros() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("Aviso: No existe archivo previo (" + rutaArchivo + "). Se iniciara con lista vacia.");
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numeroLinea = 0;
            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    try {
                        String codigo = partes[0].trim();
                        String nombre = partes[1].trim();
                        int n1 = Integer.parseInt(partes[2].trim());
                        int n2 = Integer.parseInt(partes[3].trim());
                        int n3 = Integer.parseInt(partes[4].trim());

                        if (!codigo.isEmpty() && !nombre.isEmpty()
                                && n1 >= 0 && n1 <= 20
                                && n2 >= 0 && n2 <= 20
                                && n3 >= 0 && n3 <= 20) {
                            int[] notas = {n1, n2, n3};
                            lista.add(new Estudiante(codigo, nombre, notas));
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Advertencia: Formato numerico invalido en linea " + numeroLinea);
                    }
                } else {
                    System.out.println("Advertencia: Estructura invalida en linea " + numeroLinea);
                }
            }
            System.out.println("Se recuperaron " + lista.size() + " registro(s) desde " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de registros: " + e.getMessage());
        }

        return lista;
    }
}
