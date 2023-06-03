import java.util.Scanner; // Biblioteca para lectura de datos

public class Main{
	public static void main(String[] args){
		Scanner scanOpc = new Scanner(System.in);
		int opc = 0; // Definimos un opción default
		Articulo misArticulos [] = new Articulo[2];

		do{
			imprimirTitulo();
			imprimirLInea();
			imprimirMenu();

			opc = elegirOpcion(scanOpc); // Leemos la opción ingresada	
			switch(opc){
				case 1: // Agregar articulos
					imprimrMenuAgregar();
					misArticulos[0] = new Articulo("Jabón", 17.5, "HS7HVCD89");
					misArticulos[1] = new Articulo("Jamon", 34.9, "JDC712UIH");
					for (Articulo articulo: misArticulos){
						articulo.mostrarDatos();
					}
					// Hay que agreagar un while
					break;
				case 2:
					System.out.println( "HOla 2");
					break;
				case 3:
					imprimirDespedida();
					break;
				default:
					System.out.println("Opción no válida");
					break;


			}// Cierra el switch	
			
		}while(opc != 3);

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
		System.out.println("\n\t     --- ( M E N Ú ) ---");
		System.out.println("   Ingresa el número de la opción que deseas\n");
		System.out.println("\t\t1) Agregar articulos");
		System.out.println("\t\t2) Pagar");
		System.out.println("\t\t3) Salir");
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
		System.out.println("\n\t - Ingresa la clave del articulo\n	- Ingresa '0' para pagar\n");
		
		// Pedimos datos
	}

} // Cierra clase Main