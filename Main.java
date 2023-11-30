
import java.util.Scanner; // Biblioteca para lectura de datos
import java.util.ArrayList; // Para lista dinámica
import java.util.List;

public class Main{
	public static void main(String[] args){
		Scanner scanOpc = new Scanner(System.in);
		Scanner scanOpcAgregar = new Scanner(System.in);

		int opc = 0; // Definimos un opción default
		List<List<Articulo>> misArticulos = new ArrayList<>(); // "carrito de compras"
		// Leemos el CSV
		LectorCSV contenidoCSV = new LectorCSV(); //Para leer la base datos		
		contenidoCSV.leerCSV(".//ProductosBD.csv"); // Leemos base datos
		double total = 0.00, totalEfectivo = 0.00;

		do{
			imprimirTitulo();
			imprimirLInea();
			imprimirMenu();

			opc = elegirOpcion(scanOpc); // Leemos la opción ingresada tipo scanner
			switch(opc){
				case 1: // -------------- A g r e g a r   a r t i c u l o s
					int opcAgregar = 1; // También iremos buscando los codigos
					int filaEncontrada = -1;
					String nombre = null;
					double precio = 0; 
					
					int codigo = 0;
					contenidoCSV.mostrarDatos(); // Mostramos el catálogo de productos
					imprimrMenuAgregar();
					
					do{
						opcAgregar = elegirOpcionAgregar(scanOpcAgregar);
						if (opcAgregar != 0){
							filaEncontrada = contenidoCSV.buscarArticulo(opcAgregar); // Guarda indice de fila
							if(filaEncontrada != -1){ // Acá vamos a agregar al carrito
								//contenidoCSV.mostrarUnDato(filaEncontrada);
								nombre = contenidoCSV.obtenerNombre(filaEncontrada);
								precio = contenidoCSV.obtenerPrecio(filaEncontrada);
								codigo = contenidoCSV.obtenerCodigo(filaEncontrada);
								// Creamos el objeto y lo agregamos al carrito
								List<Articulo> filaMisArticulos = new ArrayList<>(); // Creamos otra fila de la lista del carrito
								filaMisArticulos.add(new Articulo(nombre, precio, codigo));
								System.out.println("\t  '"+nombre+"' agregado al carrito");
								misArticulos.add(filaMisArticulos);
								total = sacarCuenta(misArticulos);
								filaMisArticulos = null;

							}else{ // Por si no se encontró ningun valor
								System.out.println("\t[ADVERTENCIA] No se encontro articulo");
							}
						}
						//System.out.println("FILA ENCONTRADAAAAA: "+filaEncontrada); // Array list
					}while(opcAgregar != 0);
					
					break;
				case 2: // --------------------- L i s t a r    c a r r i t o
					listarCarrito(misArticulos, total);
					break;
				case 3: // --------------------- b o r r a r   p r o d u c t o s
					listarCarritoConIndice(misArticulos, total);
					System.out.println("Ingresa el Indice del artículo que deseas borrar (ingresa -1 para cancelar): ");
					int indiceBorrar = scanOpc.nextInt();

					if (indiceBorrar >= 0 && indiceBorrar < misArticulos.size()) {
						misArticulos.remove(indiceBorrar);
						System.out.println("Producto borrado exitosamente.");
						total = sacarCuenta(misArticulos);
					} else if (indiceBorrar != -1) {
						System.out.println("Indice no válido. Producto no borrado.");
					} else {
						System.out.println("Operación cancelada.");
					}
					break;
				case 4:
					System.out.println("\n\t  --- ( P A G A R ) ---");
					System.out.println("  Se necesita pagar un total de: $"+total);
					double dineroDisponible = contarEfectivo(totalEfectivo); // Vemos la cantidad de dinero disp
					
					if(dineroDisponible < total){ // Caso donde no alcanza
						System.out.println("\n\n\tDinero insuficiente...");
						System.out.println("  Usted cuenta con: $ "+dineroDisponible);
						System.out.println("Necesitaba pagar: $ "+total);
					}else{ // Caso done se alcanza el dinero y se paga
						System.out.println("\n\n\t ==-== ( T I C K E T ) ==-==");
						listarCarrito(misArticulos, total);
						double cambio = dineroDisponible-total;

						System.out.println("\t  Pagado: \t     $ "+dineroDisponible);
						System.out.println("\n\t  Cambio: \t     $ "+cambio);
					}

					imprimirDespedida();
					System.exit(0);
					break;
				case 5: // --------------------- S a l i r
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
		System.out.println("\t\t3) Borrar prodcutos");
		System.out.println("\t\t4) Pagar");
		System.out.println("\t\t5) Salir");
	}
	public static int elegirOpcion(Scanner scanOpc){
		System.out.print("\n\t\t>> ");
		int opcion = scanOpc.nextInt();	
		return opcion;
	}
	public static void imprimirDespedida(){
		System.out.println();
		System.out.println("\t   ___     ___        ");
		System.out.println("\t  / _ |___/ (_)__  ___");
		System.out.println("\t / __ | _  / / _ |(_-<");
		System.out.println("\t/_/ |_|_,_/_/|___/___/");
		System.out.println("\n\n");
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
		System.out.print("\tCodigo: ");
		int opcion = scanOpcAgregar.nextInt();	
		return opcion;
	}

	public static double sacarCuenta(List<List<Articulo>> misArticulos){
		double total = 0.00;
		for (List<Articulo> lista : misArticulos) {
			for (Articulo articulo : lista){
				total = total + articulo.getPrecio();
			}
		}
		return total;
	}	


	public static void listarCarrito(List<List<Articulo>> misArticulos, double total){
		System.out.println("\n\t  --- ( C A R R I T O ) ---");
 		for (List<Articulo> lista : misArticulos) {
			for (Articulo articulo : lista){
				System.out.println("\t  ---------------------------");
				System.out.print("\t  "+articulo.getNombre()+"\t  $");
				System.out.println(" "+articulo.getPrecio());
				System.out.println("\t  "+articulo.getCodigo());
			}
		}
		System.out.println("\n\t  ===========================");
				System.out.println("\t  Total:\t    $ "+total);
	}

	//Para pagar
	public static double contarEfectivo(double totalEfectivo){
		Scanner cantidad = new Scanner(System.in);
		int numCantidad = 0;
		System.out.println("\n  Ingrese la cantiddad de dinero que tenga\n");

		// billetes 50 pesos
		System.out.print("   Cantidad billetes $50 pesos: ");
		numCantidad = cantidad.nextInt();
		totalEfectivo = totalEfectivo + (50*numCantidad);

		// Billetes 20 pesos
		System.out.print("   Cantidad billetes $20 pesos: ");
		numCantidad = cantidad.nextInt();
		totalEfectivo = totalEfectivo + (20*numCantidad);

		// Monedas de 10 pesos
		System.out.print("   Cantidad monedas $10 pesos: ");
		numCantidad = cantidad.nextInt();
		totalEfectivo = totalEfectivo + (10*numCantidad);

		imprimirLInea();
		System.out.println();



		cantidad.close();
		return totalEfectivo;
	}

	public static void listarCarritoConIndice(List<List<Articulo>> misArticulos, double total) {
    System.out.println("\n\t  --- ( C A R R I T O ) ---");
    for (int i = 0; i < misArticulos.size(); i++) {
        for (Articulo articulo : misArticulos.get(i)) {
            System.out.println("\t  ---------------------------");
            //System.out.println("\t  Indice: " + i);
            System.out.print("\t  ["+i+"]  "+ articulo.getNombre() + "\t  $");
            System.out.println(" " + articulo.getPrecio());
            System.out.println("\t  " + articulo.getCodigo());
        }
    }
    System.out.println("\n\t  ===========================");
    System.out.println("\t  Total:\t    $ " + total);
}


} // Cierra clase Main