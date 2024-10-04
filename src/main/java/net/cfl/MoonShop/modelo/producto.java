package net.cfl.MoonShop.modelo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private int stock;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="categoria_id")
	private Categoria categoria;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval= true)
	private List<imagen>imagenes;
}