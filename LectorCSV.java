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
    

    public void mostrarUnDato(int indice){ 
        System.out.println("AAAAAAAAAAAAAAAAAAAA");
        System.out.println(contenidoCSV.get(indice));
    }

    // Función que devuelve el indice de la lista del articulo
    public int buscarArticulo(int codigoBuscado){
        int filaEncontrada = -1;
        for (int i=0; i<contenidoCSV.size(); i++) {
            if (codigoBuscado == Integer.parseInt(contenidoCSV.get(i).get(2))){
                filaEncontrada = i;
            }
        }

        return filaEncontrada;
    }

    // Para la creación de los objetos
    // Reciben como parametro el indice de la fila del producto
    public String obtenerNombre(int indice){
        String nombre = contenidoCSV.get(indice).get(0).toString(); // 0 porque nombres están en indice 0
        return nombre;
    }

    public double obtenerPrecio(int indice){
        double precio = Double.parseDouble(contenidoCSV.get(indice).get(1)); // Casteamos ArrayList -> double
        return precio;
    }

    public int obtenerCodigo(int indice){
        int codigo = Integer.parseInt(contenidoCSV.get(indice).get(2));
        return codigo;
    }

    

}