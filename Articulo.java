public class Articulo{
	// Atributos
	String nombre;
	double precio;
	String codigoBarras;
	
	// Constructor
	public Articulo(String nom, double prec, String codigo){
		this.nombre = nom;
		this.precio = prec;
		this.codigoBarras = codigo;
	}

	//
	public void mostrarDatos(){
		System.out.println("\nProducto: "+nombre+"\nPrecio: "+precio+"\tCodigo: "+codigoBarras);
	}

	// 
}