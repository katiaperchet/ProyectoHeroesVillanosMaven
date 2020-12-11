package heroes_villanos.ProyectoHeoresVillanos;
import java.util.*;

/**
 * @author katia perchet
 * Clase que representa a las ligas que se encuentran dentro del juego
 * Hereda de la clase componente 
 */
public class Liga extends Componente {
	
	private int IdLiga;
	private String NombreLiga;
	private List<Componente> Team;
	private boolean Villanos;

	/**
	 * Constructor vacio de la liga
	 */
	public Liga() {
		super();
		Team= new ArrayList<Componente>();
	}
	
	/**
	 * @param idLiga
	 * @param nombreLiga
	 * @param villanos
	 * @param team
	 * me permite crear la liga asignando el id, nombre, si es de villanos o no y los integrantes
	 */
	public Liga(int idLiga, String nombreLiga, boolean villanos, List<Componente> team) {
		super();
		setIdLiga(idLiga);
		setNombreLiga(nombreLiga);
		setVillanos(villanos);
		setTeam(team);
		CalcularCaracteristicasLiga();
		
	}
	/**
	 * obtengo el nombre de la liga
	 */
	@Override
	public String getNombreComponente() {
		return this.getNombreLiga();
	}
	
	@Override
	public Set<Caracteristica> getCualidades() {
		return this.Cualidades;
	}
	/**
	 * calculo las caracteristicas de la liga basándome en las que tiene cada integrante
	 * de la liga, para eso, envio al metodo AñadirCaracteristica el nombre de la
	 * cualidad
	 * @see AñadirCaracteristica 
	 */
	private void CalcularCaracteristicasLiga()
	{
		for(Componente integrante : this.getTeam())
		{
			if(integrante instanceof Personaje)
			{
				for(Caracteristica caracteristicaPersonaje:((Personaje) integrante).getCualidades())
				{
					AñadirCaracteristica(caracteristicaPersonaje);
				}
			}
			else if(integrante instanceof Liga)
			{
				for(Caracteristica caracteristicaLiga:((Liga) integrante).getCualidades())
				{
					AñadirCaracteristica(caracteristicaLiga);
				}
			}
		}
	}
	/**
	 * recorro las cualidades para ver si se encuentra en la lista, sino se encuentra
	 * lo agrego como una nueva cualidad, si existe, actualizo su valor
	 * @see getPromedioCualidad
	 */
	@Override
	public void AñadirCaracteristica(Caracteristica caracteristicaNueva)
	{
		if(this.getCualidades().size()!=0)
		{
			for(Caracteristica caracteristicaLiga:this.getCualidades())
			{		
				if(caracteristicaLiga.getDescripcion().equals(caracteristicaNueva.getDescripcion()))
				{
					caracteristicaLiga.setNivel(getPromedioCualidad(caracteristicaNueva.getDescripcion()));
				}
				else
				{
					this.getCualidades().add(new Caracteristica(caracteristicaNueva.getDescripcion(),getPromedioCualidad(caracteristicaNueva.getDescripcion())));			
				}
			}
		}
		else if(this.getCualidades().size()==0)
		{
			this.getCualidades().add(new Caracteristica(caracteristicaNueva.getDescripcion(),getPromedioCualidad(caracteristicaNueva.getDescripcion())));
		}

	}


	/**
	 * @return id de la liga
	 */
	public int getIdLiga() {
		return this.IdLiga;
	}
	
	/**
	 * @param idLiga asigno el id que tendra la liga
	 */
	public void setIdLiga(int idLiga) {
		this.IdLiga = idLiga;
	}
	
	/**
	 * @return obtengo nombre de la liga
	 */
	public String getNombreLiga() {
		return this.NombreLiga;
	}

	/**
	 * @param nombreLiga asigno el nombre que tendra la liga
	 */
	public void setNombreLiga(String nombreLiga) {
		this.NombreLiga = nombreLiga;
	}

	/**
	 * @return obtengo los integrantes de la liga
	 */
	public List<Componente> getTeam() {
		return this.Team;
	}

	/**
	 * @param team asigno los integrantes que tendra la liga
	 */
	public void setTeam(List<Componente> team) {
		this.Team = team;
	}

	/**
	 * @return si la liga es de villanos o no
	 */
	public boolean isVillanos() {
		return this.Villanos;
	}

	/**
	 * @param villanos que me indica si la liga es de villanos o no
	 */
	public void setVillanos(boolean villanos) {
		this.Villanos = villanos;
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
		for (Caracteristica caracteristicaLiga:this.Cualidades) {
			if(caracteristicaLiga.getDescripcion().toLowerCase()=="tecnologia")
			{
				valorTecnologia=caracteristicaLiga.getNivel();
			}
			else if (caracteristicaLiga.getDescripcion().toLowerCase()=="superpoderes")
			{
				valorSuperpoder=caracteristicaLiga.getNivel();
			}
		}
		if(valorTecnologia>valorSuperpoder)
		{
			//agarro la edad del mayor de la liga
			valorModernidad=MayorEdadLiga(this.Team);
		}
		else
		{
			valorModernidad=10;
		}
		return valorModernidad;
	}
	/**
	 * obtengo el promedio de la cualidad/caracteristica de todos los integrantes
	 * que pertenecen a la liga para poder asignarlo como caracteristica/ cualidad
	 * de la liga
	 * @param cualidad ingreso el nombre de la cualidad que quiero obtener el promedio. 
	 * @return retorna el promedio de la cualidad que se tiene
	 */
	private int getPromedioCualidad(String cualidad) {
		int valorFuerza=0;
		int contador=0;
		cualidad=cualidad.toLowerCase();
		for(Componente integranteLiga: this.Team)
		{
			if(integranteLiga instanceof Personaje)
			{
				for(Caracteristica c:((Personaje) integranteLiga).getCualidades())
				{
					if(c.getDescripcion().toLowerCase()==cualidad)
					{
						valorFuerza+=c.getNivel();
						contador++;
					}
				}
			}
			else if(integranteLiga instanceof Liga)
			{
				for(Caracteristica c:((Liga) integranteLiga).getCualidades())
				{
					if(c.getDescripcion().toLowerCase()==cualidad)
					{
						valorFuerza+=c.getNivel();
						contador++;
					}
				}
			}
		}
		int promedioFuerza=0;
		if(contador!=0)
		{
			promedioFuerza= valorFuerza/contador;
		}

		return promedioFuerza;
	}
	
	/**
	 * 
	 * Obtengo la edad del personaje cuyo valor sea el mayor de los integrantes 
	 * para poder asignarlo cuando se define la modernidad
	 * recibe una lista con los integrantes de la liga
	 * @param team recibo los integrantes de la liga para recorrer
	 * @return retorna la mayor edad que hay entre los integrantes. 
	 */
	private int MayorEdadLiga(List<Componente> team)
	{
		int mayorEdad=0;
		//recorro los integrantes de la lista
		for(Componente integranteLiga: team)
		{
			if(integranteLiga instanceof Personaje)
			{
				if(((Personaje) integranteLiga).getEdad()>mayorEdad)
				{
					mayorEdad= ((Personaje) integranteLiga).getEdad();
				}
			}
			else if(integranteLiga instanceof Liga)
			{
				int valor= MayorEdadLiga(((Liga) integranteLiga).Team);
				if(valor>mayorEdad)
				{
					mayorEdad=valor;
				}
			}
		}
		return mayorEdad;
	}

	/**
	 * @param integranteNuevo que se agregará a los integrantes de la liga
	 */
	public void AñadirComponenteLiga(Componente integranteNuevo)
	{
		this.Team.add(integranteNuevo);
		CalcularCaracteristicasLiga();
	}


}
