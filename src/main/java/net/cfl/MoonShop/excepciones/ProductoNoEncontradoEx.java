package net.cfl.MoonShop.excepciones;

public class ProductoNoEncontradoEx extends RuntimeException {
	public ProductoNoEncontradoEx (String mensaje) {
		super(mensaje);
	}
}
