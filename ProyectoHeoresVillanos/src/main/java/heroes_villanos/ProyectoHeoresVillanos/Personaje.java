package heroes_villanos.ProyectoHeoresVillanos;

import java.util.*;

/**
 * @author katia perchet
 *Clase que me permite representar a los personajes del juego
 *Hereda de la clase componente
 */
public class Personaje extends Componente{
	
	/**
	 * Constructor vacio de la clase
	 */
	public Personaje() {
		super();
	}
	
	/**
	 * @param nombreReal
	 * @param nombreFicticio
	 * @param villano
	 * @param edad
	 * Me permite crear a los personajes asignando su nombre real, nombre ficticio
	 * indicar si es villano o no y la edad
	 */
	public Personaje(String nombreReal, String nombreFicticio, boolean villano, int edad) {
		super();
		setNombreReal(nombreReal);
		setNombreFicticio(nombreFicticio);
		setVillano(villano);
		setEdad(edad);
		Cualidades= new HashSet<Caracteristica>();
	}
	
	private String NombreReal;
	private String NombreFicticio;
	private boolean Villano;
	private int Edad;
	private Set<Caracteristica> Cualidades;
	
	/**
	 * @return nombreFicticio obtengo el nombre de super héroe o villano del personaje
	 */
	public String getNombreFicticio() {
		return this.NombreFicticio;
	}
	/**
	 * @param nombreFicticio me permite asignar el nombre del supero héroe o villano del personaje
	 */
	public void setNombreFicticio(String nombreFicticio) {
		this.NombreFicticio = nombreFicticio;
	}
	
	/**
	 * @return obtengo si el personaje es villano o no
	 */
	public boolean isVillano() {
		return this.Villano;
	}
	/**
	 * @param villano me permite asignar si el personaje es un villano o no
	 */
	public void setVillano(boolean villano) {
		this.Villano = villano;
	}
	/**
	 * @return obtengo la edad del personaje
	 */
	public int getEdad() {
		return this.Edad;
	}
	/**
	 * @param edad me permite asignar la edad del personaje
	 */
	public void setEdad(int edad) {
		this.Edad = edad;
	}
	/**
	 * obtengo el nombre del personaje
	 */
	@Override
	public String getNombreComponente() {
		return this.getNombreFicticio();
	}
	/**
	 * obtengo las cualidades del personaje
	 */
	@Override
	public Set<Caracteristica> getCualidades() {
		return this.Cualidades;
	}

	/**
	 * @return obtengo el nombre real del personaje
	 */
	public String getNombreReal() {
		return this.NombreReal;
	}
	/**
	 * @param nombre asigno el nombre real del personaje
	 */
	public void setNombreReal(String nombre)
	{
		this.NombreReal=nombre;
	}
	/**
	 * obtengo la modernidad basandome en si hay una caracteristica llamada tecnologia
	 * y una caracteristica llamada superpoderes, si estas se encuentran como cualidades
	 * se compara si el valor de la tecnologia es mayor que el valor de los superpoderes
	 * sino es mayor, se retorna un valor especificado 
	 */
	@Override
	public int getModernidad() {
		int valorTecnologia=0;
		int valorSuperpoder=0;
		int valorModernidad=0;
		for (Caracteristica caracteristica:this.getCualidades()) {
			if(caracteristica.getDescripcion().toLowerCase()=="tecnologia")
			{
				valorTecnologia=caracteristica.getNivel();
			}
			else if (caracteristica.getDescripcion().toLowerCase()=="superpoderes")
			{
				valorSuperpoder=caracteristica.getNivel();
			}
		}
		if(valorTecnologia>valorSuperpoder)
		{
			valorModernidad=this.getEdad();
		}
		else
		{
			valorModernidad=10;
		}
		return valorModernidad;
	}

	/**
	 * recorro las cualidades para ver si se encuentra en la lista, sino se encuentra
	 * lo agrego como una nueva cualidad, si existe, actualizo su valor
	 *
	 */
	@Override
	public void AñadirCaracteristica(Caracteristica caracteristicaNueva) {
		if (this.getCualidades().size()!=0)
		{			
			for(Caracteristica caracteristicaPersonaje:this.getCualidades())
			{		
				if(caracteristicaPersonaje.getDescripcion().equals(caracteristicaNueva.getDescripcion()))
				{
					caracteristicaPersonaje.setNivel(caracteristicaNueva.getNivel());
				}
				else
				{
					this.getCualidades().add(caracteristicaNueva);					
				}
			}
		}
		else
		{
			this.getCualidades().add(caracteristicaNueva);	
		}
	}
	
	

	

	
	
	
}
