package net.cfl.MoonShop.dto;

import java.sql.Blob;

import lombok.Data;

@Data
public class ImagenDto {
	private Long id;
	private String archivoNombre;
	private String descargaUrl;
}
