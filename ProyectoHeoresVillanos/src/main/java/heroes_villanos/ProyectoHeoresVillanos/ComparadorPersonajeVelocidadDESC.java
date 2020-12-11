package heroes_villanos.ProyectoHeoresVillanos;

import java.util.Comparator;

/**
 * @author katia perchet
 *Clase que implementa la interfaz Comparator que me permite
 *ordenar descendentemente los personajes a través de la comparación de la velocidad
 */
public class ComparadorPersonajeVelocidadDESC implements Comparator<Personaje> {

	@Override
	public int compare(Personaje o1, Personaje o2) {

			int valorvelocidad1=0;
			for(Caracteristica car:o1.getCualidades()) 
			{
				if(car.getDescripcion()=="velocidad")
				{
					valorvelocidad1=car.getNivel();
				}
			}

			int valorvelocidad2=0;
			for(Caracteristica car:o2.getCualidades())
				{
					if(car.getDescripcion()=="velocidad")
					{
						valorvelocidad2=car.getNivel();
					}
				}

			return Integer.compare(valorvelocidad1, valorvelocidad2)*-1;	
			
	}
}