package net.cfl.MoonShop.servicios.usuario;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.MoonShop.dto.UsuarioDto;
import net.cfl.MoonShop.excepciones.RecursoNoEncontradoEx;
import net.cfl.MoonShop.excepciones.UsuarioExistenteEx;
import net.cfl.MoonShop.modelo.Usuario;
import net.cfl.MoonShop.repositorio.UsuarioRepositorio;
import net.cfl.MoonShop.request.ActualizaUsuarioReq;
import net.cfl.MoonShop.request.AgregaUsuarioReq;
import net.cfl.MoonShop.servicios.usuario.IUsuarioServicio;

@Service
@RequiredArgsConstructor
public class UsuarioServicio implements IUsuarioServicio{
	
	private final UsuarioRepositorio usuarioRepositorio;
	private final ModelMapper modelMapper;
	
	
	
	@Override
	public Usuario traeUsuarioPorId(Long usuarioId) {
		return usuarioRepositorio.findById(usuarioId)
				.orElseThrow(() -> new RecursoNoEncontradoEx("usuario no encontrado"));
	}

	@Override
	public Usuario crearUsuario(AgregaUsuarioReq request) {
		return Optional.of(request)
				.filter(usuario -> !usuarioRepositorio.existsByEmail(request.getEmail()))
				.map(req -> {
					Usuario usuario = new Usuario();
					usuario.setEmail(request.getEmail());
					usuario.setPwd(request.getPwd());
					usuario.setUsuarioNombre(request.getUsuarioNombre());
					usuario.setUsuarioApellido(request.getUsuarioApellido());
					return usuarioRepositorio.save(usuario);
				}).orElseThrow(() -> new UsuarioExistenteEx("El usuario " + request.getEmail() + " ya existe"));
	}

	@Override
	public Usuario actualizarUsuario(ActualizaUsuarioReq request, Long usuarioId) {
		return usuarioRepositorio.findById(usuarioId).map(usuarioExistente -> {
			usuarioExistente.setUsuarioNombre(request.getUsuarioNombre());
			usuarioExistente.setUsuarioApellido(request.getUsuarioApellido());
			return usuarioRepositorio.save(usuarioExistente);
		}).orElseThrow(() -> new RecursoNoEncontradoEx("usuario no encontrado!")) ;
	}

	@Override
	public void borrarUsuario(Long usuarioId) {
		usuarioRepositorio.findById(usuarioId)
		.ifPresentOrElse(usuarioRepositorio :: delete, () ->{
			throw new RecursoNoEncontradoEx("usuario no encontrado!");
		});
		
	}
	
	@Override
	public UsuarioDto convertirAUsuarioDto(Usuario usuario){
		return modelMapper.map(usuario, UsuarioDto.class);
	}

}
