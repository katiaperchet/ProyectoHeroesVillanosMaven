package heroes_villanos.ProyectoHeoresVillanos;

import java.util.*;

/**
 * @author katia perchet
 *Clase abstracta que me permite definir metodos que compartan los
 *personajes y las ligas. 
 */
public abstract class Componente {
	/**
	 * Constructor vacio de los componentes 
	 */
	public Componente() {
		super();
		this.Cualidades= new HashSet<Caracteristica>();
	}

	protected Set<Caracteristica> Cualidades;
	protected abstract Set<Caracteristica> getCualidades();
	protected abstract String getNombreComponente();
	/**
	 * Obtengo la resistencia del personaje que se calcula dividiendo la fuerza
	 * por la velocidad
	 * @return obtengo el valor de la caracteristica resistencia. 
	 */
	protected int getResistencia() {
		
		int valorFuerza=0;
		int valorVelocidad=0;
		for (Caracteristica c:this.getCualidades()) {
			if(c.getDescripcion().toLowerCase()=="fuerza")
			{
				valorFuerza=c.getNivel();
			}
			else if (c.getDescripcion().toLowerCase()=="velocidad")
			{
				valorVelocidad=c.getNivel();
			}
		}
		int valorResistencia=0;
		if (valorVelocidad!=0)
		{
			valorResistencia= valorFuerza/valorVelocidad;			
		}
		return valorResistencia;
	}
/**
 * Se obtiene el valor del camuflaje, si el valor de la cualidad invisibilidad
 * es mayor que el valor de la fuerza del personaje, se devuelve un valor fijo
 * sino, se retorna la velocidad
 * @return retorno la caracteristica camuflaje
 */
	protected int getCamuflaje() {
		int valorInvisibilidad=0;
		int valorFuerza=0;
		int valorVelocidad=0;
		for (Caracteristica c:this.getCualidades()) {
			if(c.getDescripcion().toLowerCase()=="fuerza")
			{
				valorFuerza=c.getNivel();
			}
			else if (c.getDescripcion().toLowerCase()=="velocidad")
			{
				valorVelocidad=c.getNivel();
			}
			else if(c.getDescripcion().toLowerCase()=="invisibilidad")
			{
				valorInvisibilidad=c.getNivel();
			}
		}
		int valorCamuflaje=0;
		if(valorInvisibilidad>valorFuerza)
		{
			valorCamuflaje=50;
		}
		else
		{
			valorCamuflaje=valorVelocidad;
		}
		return valorCamuflaje;
	}

	protected abstract int getModernidad();

	/**
	 * @param caracteristicaNueva recibo la caracteristica nueva que quiero agregar a la lista. 
	 */
	
	public abstract void AñadirCaracteristica(Caracteristica caracteristicaNueva);
	
	/**
	 * 
	 * @param nombreCaracteristica ingresa el nombre de la caracteristica que quiero devolver
	 * @return retorno la caracteristica encontrada o nulo si no la encuentro. 
	 */
	protected Caracteristica getCaracteristicaEspecifica(String nombreCaracteristica)
	{
		Caracteristica caracteristicaRetornar=null;
		if (this.getCualidades().size()!=0)
		{			
			for(Caracteristica caracteristicaPersonaje:this.getCualidades())
			{		
				if(caracteristicaPersonaje.getDescripcion().equals(nombreCaracteristica))
				{
					caracteristicaRetornar=caracteristicaPersonaje;
				}

			}
		}
		return caracteristicaRetornar;
	}
	
}
