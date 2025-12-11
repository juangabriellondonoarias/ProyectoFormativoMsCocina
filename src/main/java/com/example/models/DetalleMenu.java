package com.example.models;


import com.example.models.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "DETALLE_MENU")
public class DetalleMenu {
@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
		
@Column(name="id_detalle_menu")
	private Integer iddetallemenu; 

/*la relacion con el menu*/

@ManyToOne(fetch = FetchType.EAGER)

/*mapea el fk del ai_menu*/
@JoinColumn(name = "id_menu", nullable = false)
private Menu menu;

/*es para datos grandes*/
//@Lob 
//private byte[] imagen; /*se cambia a un string porque esta haciendo interferencia */

@Column(name = "imagen") // Mapea al nombre de columna correcto
private String imagenUrl;

/*constructor */

public DetalleMenu() {
	
}


/*get y set*/



public void setIddetallemenu(Integer iddetallemenu) {
    this.iddetallemenu = iddetallemenu;
}

public void setMenu(Menu menu) {
    this.menu = menu;
}



public Integer getIddetallemenu() {
    return iddetallemenu;
}
public Menu getMenu() {
    return menu;
}


/*public byte[] getImagen() {
    return imagen;
}*/

/*public void setImagen(byte[] imagen) {
    this.imagen = imagen;
}*/

/*se cambian los get y los set */

public String getImagenUrl() {
    return imagenUrl;
}

public void setImagenUrl(String imagenUrl) {
    this.imagenUrl = imagenUrl;
}

}