package net.cfl.MoonShop.servicios.imagen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import net.cfl.MoonShop.dto.ImagenDto;
import net.cfl.MoonShop.excepciones.RecursoNoEncontradoEx;
import net.cfl.MoonShop.modelo.Imagen;
import net.cfl.MoonShop.modelo.Producto;
import net.cfl.MoonShop.repositorio.ImagenRepositorio;
import net.cfl.MoonShop.servicios.imagen.IImagenServicio;
import net.cfl.MoonShop.servicios.producto.IProductoServicio;

@Service
@RequiredArgsConstructor
public class ImagenServicio implements IImagenServicio {
	private final ImagenRepositorio imagenRepositorio;
	private final IProductoServicio productoServicio;
	@Override
	public Imagen listaImagenPorId(Long id) {
		return imagenRepositorio.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoEx("No se encunentra la imagen con id" + id));
	}

	@Override
	public void borraImagenPorId(Long id) {
		imagenRepositorio.findById(id).ifPresentOrElse(imagenRepositorio::delete, () -> {
			throw new RecursoNoEncontradoEx("No ses encuentra la imagen con id " + id);
		});
		
	}

	@Override
	public List<ImagenDto> guardaImagenes(List<MultipartFile> archivos, Long productoId) {
		Producto producto = productoServicio.listaProductoPorId(productoId);
		List<ImagenDto> imagenesGuardadasDto = new ArrayList<>();
		for (MultipartFile archivo : archivos) {
			try {
				Imagen imagen = new Imagen();
				imagen.setArchivoNombre(archivo.getOriginalFilename());
				imagen.setArchivoTipo(archivo.getContentType());
				imagen.setImagen(new SerialBlob(archivo.getBytes()));
				imagen.setProducto(producto);
				
				String descargaUrl = "/api/v1/imagenes/imagen/descarga/";
				imagen.setDescargaUrl(descargaUrl + imagen.getId());
				Imagen imagenGuardada = imagenRepositorio.save(imagen);
				
				imagenGuardada.setDescargaUrl(descargaUrl + imagenGuardada.getId());
				imagenRepositorio.save(imagenGuardada);
				
				ImagenDto imagenDto = new ImagenDto();
				imagenDto.setId(imagenGuardada.getId());
				imagenDto.setArchivoNombre(imagenGuardada.getArchivoNombre());
				imagenDto.setDescargaUrl(imagenGuardada.getDescargaUrl());
				imagenesGuardadasDto.add(imagenDto);
				
            }   catch(IOException | SQLException e){
                throw new RuntimeException(e.getMessage());
            }
		}
		return imagenesGuardadasDto;
	}

	@Override
	public void actualizaImagen(MultipartFile archivo, Long imagenId) {
		Imagen imagen = listaImagenPorId(imagenId);
		try {
			imagen.setArchivoNombre(archivo.getOriginalFilename());
			imagen.setImagen(new SerialBlob(archivo.getBytes()));
			imagenRepositorio.save(imagen);
		}catch(IOException | SQLException e){
			throw new RuntimeException(e.getMessage());
		}
	}

}
