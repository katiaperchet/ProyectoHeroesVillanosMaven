package heroes_villanos.ProyectoHeoresVillanos;

import java.util.Comparator;

/**
 * @author katia perchet
 *Clase que implementa la interfaz Comparator que me permite
 *ordenar descendentemente los personajes a través de la comparación de la fuerza
 */
public class ComparadorPersonajeFuerzaDESC  implements Comparator<Personaje> {

	@Override
	public int compare(Personaje o1, Personaje o2) {
		int valorfuerza1=0;
		for(Caracteristica car:o1.getCualidades()) 
		{
			if(car.getDescripcion()=="fuerza")
			{
				valorfuerza1=car.getNivel();
			}
		}
		int valorfuerza2=0;
		for(Caracteristica car:o2.getCualidades())
		{
			if(car.getDescripcion()=="fuerza")
			{
				valorfuerza2=car.getNivel();
			}

		}
		return Integer.compare(valorfuerza1, valorfuerza2)*-1;
	}
}