package net.cfl.MoonShop.modelo;

import java.sql.Blob;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class imagen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String archivoNombre;
	private String archivoTipo;
	@Lob
	private Blob imagen; 
	private String descargasUrl;

	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private producto producto;
}
