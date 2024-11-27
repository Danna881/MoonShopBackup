package net.cfl.MoonShop.excepciones;

public class CategoriaExistenteEx extends RuntimeException{
	public CategoriaExistenteEx(String mensaje){
		super(mensaje);
	}
}