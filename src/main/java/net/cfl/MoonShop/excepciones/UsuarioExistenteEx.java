package net.cfl.MoonShop.excepciones;

public class UsuarioExistenteEx extends RuntimeException {
	public UsuarioExistenteEx(String mensaje) {
		super(mensaje);
	}

}
