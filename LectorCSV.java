import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV{
    private List<List<String>> contenidoCSV;

    public LectorCSV(){
        contenidoCSV = new ArrayList<>();
    }
    

    public void leerCSV(String nombreArchivo){
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] valores = linea.split(",");
                List<String> fila = new ArrayList<>();
                for (String valor : valores) {
                    fila.add(valor);
                }
                contenidoCSV.add(fila);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void mostrarDatos(){
        System.out.println("    Nombre\t   Precio\t   Codigo");
        System.out.println("---------------------------------------------");
        for (List<String> fila : contenidoCSV) {
            for (String valor : fila) {
                System.out.print(valor+"\t    ");
            }
            System.out.println();
        }
    }
    

    public void mostrarUnDato(){ 
        System.out.println("AAAAAAAAAAAAAAAAAAAA");
        System.out.println(contenidoCSV.get(0).get(1));
    }

}