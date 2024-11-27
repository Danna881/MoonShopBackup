package net.cfl.MoonShop.servicios.imagen;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.cfl.MoonShop.dto.ImagenDto;
import net.cfl.MoonShop.modelo.Imagen;

public interface IImagenServicio {
	Imagen listaImagenPorId(Long id);
	void borraImagenPorId(Long id);
	List<ImagenDto> guardaImagenes(List<MultipartFile> archivo, Long productoId);
	void actualizaImagen(MultipartFile archivo, Long imagenId);
}