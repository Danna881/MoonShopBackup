package net.cfl.MoonShop.dto;

import java.math.BigDecimal;

import lombok.Data;
import net.cfl.MoonShop.dto.ProductoDto;

@Data
public class CarritoItemDto {
	private Long CarritoItemId;
	private Integer cantidad;
	private BigDecimal precioUni;
	private ProductoDto producto;
	
}
