package heroes_villanos.ProyectoHeoresVillanos;

import java.util.Comparator;

/**
 * @author katia perchet
 *Clase que implementa la interfaz Comparator que me permite
 *ordenar ascendentemente los personajes a través de la comparación de los nombres
 */
public class ComparadorPersonajeNombreASC implements Comparator<Personaje> {

	@Override
	public int compare(Personaje o1, Personaje o2) {
			if(o1.getNombreFicticio().compareTo(o2.getNombreFicticio())==0)
			{
				int valorfuerza1=0;
				int valorvelocidad1=0;
				for(Caracteristica car:o1.getCualidades()) 
				{
					if(car.getDescripcion()=="fuerza")
					{
						valorfuerza1=car.getNivel();
					}
					else if(car.getDescripcion()=="velocidad")
					{
						valorvelocidad1=car.getNivel();
					}
				}
				int valorfuerza2=0;
				int valorvelocidad2=0;
				for(Caracteristica car:o2.getCualidades())
				{
					if(car.getDescripcion()=="fuerza")
					{
						valorfuerza2=car.getNivel();
					}
					else if(car.getDescripcion()=="velocidad")
					{
						valorvelocidad2=car.getNivel();
					}
				}
				int comparacion= Integer.compare(valorfuerza1, valorfuerza2);
				if(comparacion!=0) 
				{
					return comparacion;
				}
				else if(comparacion==0)
				{
					int comparacionVelocidad= Integer.compare(valorvelocidad1, valorvelocidad2);
					return comparacionVelocidad;
				}
			}
			return o1.getNombreFicticio().compareTo(o2.getNombreFicticio());	
			
	}

	

}
