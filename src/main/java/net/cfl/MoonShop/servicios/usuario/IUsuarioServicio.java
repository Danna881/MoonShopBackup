package net.cfl.MoonShop.servicios.usuario;

import net.cfl.MoonShop.dto.UsuarioDto;
import net.cfl.MoonShop.modelo.Usuario;
import net.cfl.MoonShop.request.ActualizaUsuarioReq;
import net.cfl.MoonShop.request.AgregaUsuarioReq;

public interface IUsuarioServicio {
	Usuario traeUsuarioPorId(Long usuarioId);
	Usuario crearUsuario(AgregaUsuarioReq request);
	Usuario actualizarUsuario(ActualizaUsuarioReq request, Long usuarioId);
	void borrarUsuario(Long usuarioId);
	UsuarioDto convertirAUsuarioDto(Usuario usuario);
}
