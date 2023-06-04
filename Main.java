
import java.util.Scanner; // Biblioteca para lectura de datos
import java.util.ArrayList; // Para lista dinámica
import java.util.List;

public class Main{
	public static void main(String[] args){
		Scanner scanOpc = new Scanner(System.in);
		Scanner scanOpcAgregar = new Scanner(System.in);

		int opc = 0; // Definimos un opción default
		List<Articulo> misArticulos = new ArrayList<>(); // "carrito de compras"
		// Leemos el CSV
		LectorCSV contenidoCSV = new LectorCSV(); //Para leer la base datos		
		contenidoCSV.leerCSV(".//ProductosBD.csv"); // Leemos base datos

		do{
			imprimirTitulo();
			imprimirLInea();
			imprimirMenu();

			opc = elegirOpcion(scanOpc); // Leemos la opción ingresada tipo scanner
			switch(opc){
				case 1: // Agregar articulos
					int opcAgregar = 1;
					contenidoCSV.mostrarDatos(); // Mostramos el catálogo de productos
					imprimrMenuAgregar();
					//contenidoCSV.mostrarUnDato();
					do{
						opcAgregar = elegirOpcionAgregar(scanOpcAgregar);
					}while(opcAgregar != 0);
					//misArticulos.add(new Articulo("Jamon", 34.9, 1001));
					break;
				case 2: // Listar carrito
					System.out.println("L I S T A M O S   C A R R I T O");
					listarCarrito(misArticulos);
					break;
				case 3: // Pagar
					System.out.println("P A G A R");
					break;
				case 4:
					imprimirDespedida();
					break;
				default:
					System.out.println("Opción no válida");
					break;


			}// Cierra el switch	
			
		}while(opc != 4);

		scanOpc.close();
	} // Cierra void main

	


	// M é t o d o s  /  F u n c i o n e s
	public static void imprimirTitulo(){
		System.out.println("");
		System.out.println("\t   ___          M<3               ");
		System.out.println("\t  / _ |__ _____________ _______ _ ");
		System.out.println("\t / __ / // / __/ __/ -_) __/ _ `/ ");
		System.out.println("\t/_/ |_|_,_/_/ /_/  |__/_/  |_,_/  ");
	}
	public static void imprimirLInea(){
		System.out.println("\n-------------------------------------------------");
	}
	public static void imprimirMenu(){
		System.out.println("\n\t     --- ( M E N U ) ---");
		System.out.println("   Ingresa el numero de la opcion que deseas\n");
		System.out.println("\t\t1) Agregar articulos");
		System.out.println("\t\t2) Ver carrito"); 
		System.out.println("\t\t3) Pagar");
		System.out.println("\t\t4) Salir");
	}
	public static int elegirOpcion(Scanner scanOpc){
		System.out.print("\n\t\t>> ");
		int opcion = scanOpc.nextInt();	
		return opcion;
	}
	public static void imprimirDespedida(){
		System.out.println("Gracias por irse");
		System.out.println("Modificar este mensaje p0ara que se vea pro");
	}
    // Métodos para la opción de agregar articulos
	public static void imprimrMenuAgregar(){
		// Listamos los articulos, pedimos clave agregamos y salimos
		// Cada vez que se agregue un ariculo lo creamos
		System.out.println("\n     --- ( A G R E G A R   A R T I C U L O S) ---");
		System.out.println("\n\t - Ingresa la clave del articulo");
		System.out.println("\t - Ingresa '0' para salir de este menu\n");
	}

	public static int elegirOpcionAgregar(Scanner scanOpcAgregar){
		System.out.print("  Codigo >> ");
		int opcion = scanOpcAgregar.nextInt();	
		return opcion;
	}
		
		// Pedimos datos

	public static void listarCarrito(List<Articulo> misArticulos){
		for (Articulo articulo: misArticulos){
			articulo.mostrarDatos();
		}
	}

} // Cierra clase Main