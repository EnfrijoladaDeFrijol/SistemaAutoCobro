import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;

public class LectorCSV{
    private BufferedReader lector; //lee el archivo
    private String linea; // linea de cada fila
    private String contenidoCSVLinea[] = null;

    public void leerArchivoCSV(String nombreArchivo){
        try{
            lector  = new BufferedReader(new FileReader(nombreArchivo));

            while((linea = lector.readLine()) != null){
                contenidoCSVLinea = linea.split(",");
                System.out.println(); // Solo para dar formato
            }
                
            }
            lector.close();
            linea = null; // null para evitar acumulación de datos
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void imprimirLinea(){
        System.out.println("    Nombre\t   Precio\t   Codigo");
        System.out.println("---------------------------------------------");
        for(int i=0; i<contenidoCSV.length; i++){
            System.out.print(contenidoCSV[i]+"\t    ");
        }
    }

}