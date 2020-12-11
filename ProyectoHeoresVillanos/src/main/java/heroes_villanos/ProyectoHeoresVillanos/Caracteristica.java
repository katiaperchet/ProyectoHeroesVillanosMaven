package heroes_villanos.ProyectoHeoresVillanos;

/**
 * @author katia perchet
 *
 */
public class Caracteristica {
	
	/**
	 * Constructor vacio de las cualidades
	 */
	public Caracteristica() {
		super();
	}
	
	/**
	 * @param descripcion que tendrá la caracteristica creada
	 * @param nivel valor que tendra la caracteristica creada
	 */
	public Caracteristica(String descripcion, int nivel) {
		super();
		this.setDescripcion(descripcion);
		this.setNivel(nivel);
	}
	
	private String Descripcion;
	private int Nivel;
	
	/**
	 * @return obtengo la descripcion de la caracteristica
	 */
	public String getDescripcion() {
		return this.Descripcion;
	}
	/**
	 * @param descripcion 
	 * ingresa la descripcion que se le asignará a la caracteristica
	 */
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}
	/**
	 * @return obtengo el nivel de la cualidad (valor que tiene la caracteristica)
	 */
	public int getNivel() {
		return this.Nivel;
	}
	/**
	 * @param nivel (valor que va a tener la caracteristica)
	 */
	public void setNivel(int nivel) {
		this.Nivel = nivel;
	}
	/**
	 * Override de ambos métodos para que no haya valores repetidos en el HashSet
	 * de las cualidades del personaje
	 */

	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Caracteristica)
		{
			if(((Caracteristica) other).Descripcion==this.Descripcion)
			{
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode()
	{
		return this.getDescripcion().hashCode();
	}
	
}
