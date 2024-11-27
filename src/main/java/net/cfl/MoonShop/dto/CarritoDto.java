package net.cfl.MoonShop.dto;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Data;
import net.cfl.MoonShop.dto.CarritoItemDto;

@Data
public class CarritoDto {
	private Long carritoId;
	private BigDecimal costoTotal;
	private Set<CarritoItemDto> carritoItems;
}
