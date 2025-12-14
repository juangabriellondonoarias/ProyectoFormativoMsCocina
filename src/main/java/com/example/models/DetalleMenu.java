package com.example.models;

 
//import com.example.models.Receta; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
 @JoinColumn(name = "id_menu", nullable = false)
 private Menu menu;

 /*Campo para la URL de la imagen */
 @Column(name = "imagen")
 private String imagenUrl;

 
 /*relacion con la receta (comentada)*/
 // @ManyToOne(fetch = FetchType.EAGER)
 // @JoinColumn(name = "id_receta", nullable = false)
 // private Receta receta;
     
 /*Campo auxiliar para la FK id_receta (Este sí se mapea por ahora)*/
 @Column(name = "id_receta" , nullable = false)
 private Integer idReceta; 

 /*constructor */
 public DetalleMenu() {
     
 }


 /*get y set*/

 public Integer getIddetallemenu() {
     return iddetallemenu;
 }

 public void setIddetallemenu(Integer iddetallemenu) {
     this.iddetallemenu = iddetallemenu;
 }

 public Menu getMenu() {
     return menu;
 }

 public void setMenu(Menu menu) {
     this.menu = menu;
 }

 public String getImagenUrl() {
     return imagenUrl;
 }

 public void setImagenUrl(String imagenUrl) {
     this.imagenUrl = imagenUrl;
 }
 
 public Integer getIdReceta() {
     return idReceta;
 }

 public void setIdReceta(Integer idReceta) {
     this.idReceta = idReceta;
 }
}
