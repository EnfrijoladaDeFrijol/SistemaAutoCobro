public class Articulo{
	// Atributos
	String nombre;
	double precio;
	int codigo;
	
	// Constructor
	public Articulo(String nom, double prec, int codigo){
		this.nombre = nom;
		this.precio = prec;
		this.codigo = codigo;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public int getCodigo() {
		return codigo;
	}

	public void mostrarDatos(){
		System.out.println("\nProducto: "+nombre+"\nPrecio: "+precio+"\tCodigo: "+codigo);
	}

	// 
}