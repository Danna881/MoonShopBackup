package net.cfl.MoonShop.servicios.orden;

import java.util.List;

import net.cfl.MoonShop.dto.OrdenDto;
import net.cfl.MoonShop.modelo.Orden;

public interface IOrdenServicio {
	Orden realizaOrden(Long usuarioId);
	OrdenDto traeOrden(Long ordenId);
	List<OrdenDto> traeUsuarioOrdenes(Long usuarioId);
}
