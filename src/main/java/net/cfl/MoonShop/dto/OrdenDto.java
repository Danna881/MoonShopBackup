package net.cfl.MoonShop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import net.cfl.MoonShop.dto.OrdenItemDto;

@Data
public class OrdenDto {
	private Long id;
	private Long usuarioId;
	private LocalDate ordenFecha;
	private BigDecimal montoTotal;
	private String ordenEstado;
	private List<OrdenItemDto> items;
	
}

