package heroes_villanos.ProyectoHeoresVillanos;


import java.io.IOException;
import java.util.*;

/**
 * @author katia perchet
 *Clase que me permite controlar las diferentes acciones que se pueden realizar en el juego
 *Comienzo teniendo variables globales al juego que me permiten llevar un registro
 *de los personajes, cualidades y ligas que se utilizan en el juego con el objetivo
 *de facilitar búsquedas, asignaciones y enfrentamientos. 
 */
public class MainJuego {

	static List<Personaje> personajes= new ArrayList<Personaje>();
	static List<Caracteristica> CaracteristicasJuego= new ArrayList<Caracteristica>();
	static List<Liga> LigasJuego= new ArrayList<Liga>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//cargo datos de ejemplo para poder utilizar el sistema sin tener que agregar de a uno
		//DatosEjemplos();
		DatosEjemplos2();
		@SuppressWarnings("resource")
		Scanner consola= new Scanner(System.in);
		boolean cierre=true;
		try {
			/**
			 * hago una repetición de las opciones hasta que el usuario
			 * desee cerrar la aplicación 
			 */
			while (cierre){			
				System.out.println(MenuPrincipal());
				System.out.println("Ingrese la opcion que desea realizar: \n");
				int opcion= consola.nextInt();
				/**
				 * Hago una estructura que me permite recorrer las diferentes
				 * opciones que se seleccionen en el menú
				 */
				switch(opcion)
				{
				/**
				 * Solicito los valores que se deben tener para poder crear un personaje
				 * y agrego el nuevo personaje a la lista de personajes del juego
				 */
				case 1: //AGREGAR UN PERSONAJE
					System.out.println("Ingrese el nombre real del personaje: \n");
					String nombreRealPersonaje= consola.next();
					System.out.println("Ingrese el nombre ficticio del personaje: \n");
					String nombreFicticioPersonaje= consola.next();
					System.out.println("¿Es un villano? S-si. N-no: \n");
					String esVillano= consola.next();		
					boolean villian= false;
					if(esVillano.toLowerCase().equals("s"))
					{
						villian=true;
					}
					System.out.println("Ingrese la edad del personaje:\n ");
					int edadPersonaje= consola.nextInt();
					Personaje personaje= new Personaje(nombreRealPersonaje.toLowerCase(), nombreFicticioPersonaje.toLowerCase(), villian, edadPersonaje);
					personajes.add(personaje);
					System.out.println("Personaje añadido");																									
					break; 
				/**
				 * Solicito al usuario los valores que van a componer las
				 * caracteristicas y lo agrego a las caracteristicas registradas en el juego	
				 */
				case 2: //AGREGAR CARACTERISTICA AL JUEGO
					System.out.println("Ingrese la descripcion de la caracteristica:\n ");
					String descripcionCaracteristica= consola.next();
					System.out.println("Ingrese el nivel de la caracteristica:\n ");
					int nivelCaracteristica= consola.nextInt();
					CaracteristicasJuego.add(new Caracteristica(descripcionCaracteristica.toLowerCase(),nivelCaracteristica));
					System.out.println("Caracteristica añadida al juego");
					break;
				/**
				 * Solicito la caracteristica que se quiere vincular y el personaje
				 * al que se le debe agregar la caracteristica, los busco a través de
				 * distintos metodos para ver si existen en el juego
				 * @see EncontrarPersonaje()
				 * @see EncontrarCaracteristica()
				 * y si existen las agrego al personaje
				 */
				case 3: //RELACIONO UNA CARACTERISTICA AL PERSONAJE
					System.out.println(MostrarCaracteristicasJuego());
					System.out.println(MostrarPersonajesCualidades());
					System.out.println("Ingrese la descripcion de la caracteristica: \n");
					String descripcionCaracteristicaDeseada= consola.next();
					System.out.println("Ingrese el nivel de la caracteristica: \n");
					int nivelDeseado= consola.nextInt();
					System.out.println("Ingrese el nombre ficticio del personaje: \n");
					String nombrePersonaje= consola.next();
					Personaje personajeDeCaracteristica= EncontrarPersonaje(nombrePersonaje.toLowerCase());
					Caracteristica caracteristicaPersonaje=EncontrarCaracteristica(descripcionCaracteristicaDeseada.toLowerCase(),nivelDeseado);
					if (!caracteristicaPersonaje.equals(null) && !personajeDeCaracteristica.equals(null))
					{						
						System.out.println(AgregarCaracteristicaPersonaje(caracteristicaPersonaje,personajeDeCaracteristica));
					}
					else
					{
						System.out.println("No se encontró la caracteristica");
					}
					break;
				/**
				 * Solicito los nombres de super héroes o villanos que se quieren enfrentar
				 * los busco con el metodo
				 * @see EncontrarPersonaje()
				 * y si existen, invoco al metodo
				 * @see EnfrentarPersonajes()	
				 */
				case 4: //ENFRENTO DOS PERSONAJES
					System.out.println(MostrarPersonajesCualidades());
					System.out.println("Ingrese el nombre ficticio del primer personaje a enfrentar:\n ");
					String nombrePersonaje1= consola.next();
					System.out.println("Ingrese el nombre ficticio del segundo personaje a enfrentar:\n ");
					String nombrePersonaje2= consola.next();
					Personaje personaje1= EncontrarPersonaje(nombrePersonaje1.toLowerCase());
					Personaje personaje2= EncontrarPersonaje(nombrePersonaje2.toLowerCase());
					System.out.println("Ingrese el criterio para el enfrentamiento (fuerza, velocidad, etc):\n ");
					String criterioEnfrentamiento= consola.next();
					if (!personaje1.equals(null)&&!personaje2.equals(null))
					{
						String veredictoEnfrentamientoPersonajes=EnfrentarPersonajes(personaje1,personaje2,criterioEnfrentamiento.toLowerCase());
						if(veredictoEnfrentamientoPersonajes.equals(""))
						{
							veredictoEnfrentamientoPersonajes=EnfrentamientoVacio(personaje1, personaje2);
						}
						System.out.println(veredictoEnfrentamientoPersonajes);					
					}
					else
					{
						System.out.println("No se encontró alguno de los personajes");
					}
					break;
				/**
				 * Solicito el nombre super héroe o villano que se quiere enfrentar
				 * y el nombre de la liga a enfrentar
				 * los busco con los metodos
				 * @see EncontrarPersonaje()
				 * @see EncontrarLiga()
				 * y si existen, invoco al metodo
				 * @see EnfrentarPersonajes()	
				 */	
				case 5: //ENFRENTO UN PERSONAJE CON UNA LIGA
					System.out.println(MostrarPersonajesCualidades());
					System.out.println("Ingrese el nombre ficticio del primer personaje a enfrentar:\n ");
					String nombrePersonajeL1= consola.next();
					System.out.println("Ingrese el nombre de la liga a enfrentar:\n ");
					String nombrePLiga2= consola.next();
					System.out.println("Ingrese el criterio para el enfrentamiento (fuerza, velocidad, etc):\n ");
					String criterioPLEnfrentamiento= consola.next();
					Personaje PersonajeL1= EncontrarPersonaje(nombrePersonajeL1.toLowerCase());
					Liga PLiga2= EncontrarLiga(nombrePLiga2.toLowerCase());
					if(!PersonajeL1.equals(null)&& !PLiga2.equals(null))
					{
						String veredictoEnfrentamientoPersonajeLiga=EnfrentarPersonajes(PersonajeL1,PLiga2, criterioPLEnfrentamiento.toLowerCase());
						if(veredictoEnfrentamientoPersonajeLiga.equals(""))
						{
							veredictoEnfrentamientoPersonajeLiga=EnfrentamientoVacio(PersonajeL1, PLiga2);
						}
						System.out.println(veredictoEnfrentamientoPersonajeLiga);						
					}
					else
					{
						System.out.println("No se encontró alguno de los personajes");
					}
					break;
				/**
				 * Solicito los nombres de las ligas a enfrentar
				 * los busco con los metodos
				 * @see EncontrarLiga()
				 * y si existen, invoco al metodo
				 * @see EnfrentarPersonajes()	
				 */		
				case 6: //ENFRENTO DOS LIGAS
					System.out.println(MostrarLigasJuego());
					System.out.println("Ingrese el nombre de la primera liga a enfrentar:\n ");
					String nombreLigaL1= consola.next();
					System.out.println("Ingrese el nombre de la segunda liga a enfrentar:\n ");
					String nombreLigaL2= consola.next();
					System.out.println("Ingrese el criterio para el enfrentamiento (fuerza, velocidad, etc):\n ");
					String criterioLL= consola.next();
					Liga LigaL1= EncontrarLiga(nombreLigaL1.toLowerCase());
					Liga LigaL2= EncontrarLiga(nombreLigaL2.toLowerCase());
					if (!LigaL1.equals(null)&&!LigaL2.equals(null))
					{
						String veredictoEnfrentamientoLigas=EnfrentarPersonajes(LigaL1,LigaL2,criterioLL.toLowerCase());
						if(veredictoEnfrentamientoLigas.equals(""))
						{
							veredictoEnfrentamientoLigas=EnfrentamientoVacio(LigaL1, LigaL2);
						}
						System.out.println(veredictoEnfrentamientoLigas);						
					}
					else
					{
						System.out.println("No se encontró alguna de las ligas");
					}
					break;
				/**
				 * Solicito el nuevo nombre de la liga, selecciono
				 * los personajes o ligas que se quieren añadir a la nueva liga
				 * encuentro los personajes:
				 * @see EncontrarPersonaje()
				 * @see EncontrarLiga()
				 * si existe, verifico que lo seleccionado sean heroes unicamente para armar la liga
				 * armo la liga con el metodo
				 * @see ArmarLigaHeroes()	
				 */
				case 7: //CREO LIGA DE SUPER HEROES
					System.out.println("Ingrese el nuevo nombre de la liga: \n");
					String nombreLigaHeroe= consola.next();
					List<Componente> integrantesaLiga= new ArrayList<Componente>();
					System.out.println(MostrarPersonajesCualidades());
					boolean cierreBucle=true;
					while (cierreBucle)
					{
						System.out.println("\n¿Desea agregar un personaje o liga? P-personaje. L-liga: \n");
						String decisionAgregar=consola.next();
						if(decisionAgregar.toLowerCase().equals("p"))
						{
							System.out.println("Ingrese el nombre del héroe a añadir:\n ");
							String nombreHeroe= consola.next();
							Personaje heroeEncontrado= EncontrarPersonaje(nombreHeroe.toLowerCase());
							if (!heroeEncontrado.equals(null))
							{
								if(!heroeEncontrado.isVillano())
								{
									integrantesaLiga.add(heroeEncontrado);
									System.out.println("Personaje añadido.");
								}
								else {
									System.out.println("El personaje es villano, no se puede añadir.");
								}
							}
							
						}
						else if(decisionAgregar.toLowerCase().equals("l"))
						{
							System.out.println("Ingrese el nombre de la liga a añadir:\n ");
							String nombreLigaEncontrar= consola.next();
							Liga ligaEncontrada= EncontrarLiga(nombreLigaEncontrar.toLowerCase());
							if(!ligaEncontrada.equals(null))
							{
								if(!ligaEncontrada.isVillanos()) 
								{
									integrantesaLiga.add(ligaEncontrada);
									System.out.println("Liga añadida.");
								}
								else
								{
									System.out.println("La liga es de héroes, no se puede añadir");
								}
							}
						}
						System.out.println("\n¿Desea añadir otro integrante? S-si. N-no:\n ");
						String decision= consola.next();
						if(decision.toLowerCase().equals("n"))
						{
							cierreBucle=false;
						}
					}
					System.out.println(ArmarLigaHeroes(nombreLigaHeroe.toLowerCase(), integrantesaLiga));
					break;
				/**
				 * Solicito el nuevo nombre de la liga, selecciono
				 * los personajes o ligas que se quieren añadir a la nueva liga
				 * encuentro los personajes:
				 * @see EncontrarPersonaje()
				 * @see EncontrarLiga()
				 * si existe, verifico que lo seleccionado sean villanos unicamente para armar la liga
				 * armo la liga con el metodo
				 * @see ArmarLigaVillanos()
				 */
				case 8: //CREO LIGA DE VILLANOS
					System.out.println("Ingrese el nuevo nombre de la liga: \n");
					String nombreLigaVillano= consola.next();
					List<Componente> integrantesLiga= new ArrayList<Componente>();
					System.out.println(MostrarPersonajesCualidades());
					boolean cierreBucleV=true;
					while (cierreBucleV)
					{
						System.out.println("\n¿Desea agregar un personaje o liga? P-personaje. L-liga: \n");
						String decisionAgregar=consola.next();
						if(decisionAgregar.toLowerCase().equals("p"))
						{
							System.out.println("Ingrese el nombre del villano a añadir:\n ");
							String nombreVillanoEncontrar= consola.next();
							Personaje villanoEncontrado= EncontrarPersonaje(nombreVillanoEncontrar.toLowerCase());
							if (!villanoEncontrado.equals(null))
							{
								if(villanoEncontrado.isVillano())
								{
									integrantesLiga.add(villanoEncontrado);
								}
								else
								{
									System.out.println("El personaje no es villano");
								}
							}
							else
							{
								System.out.println("No se encontró");
							}
						}
						else if(decisionAgregar.toLowerCase().equals("l"))
						{
							System.out.println("Ingrese el nombre de la liga a añadir:\n ");
							String nombreLigaEncontrar= consola.next();
							Liga ligaEncontrada= EncontrarLiga(nombreLigaEncontrar.toLowerCase());
							if(!ligaEncontrada.equals(null))
							{
								if(ligaEncontrada.isVillanos()) 
								{
									integrantesLiga.add(ligaEncontrada);
								}
								else {
									System.out.println("La liga no es de villanos");
								}
							}
						}
						System.out.println("\n¿Desea añadir otro integrante? S-si. N-no:\n ");
						String decision= consola.next();
						if(decision.toLowerCase().equals("n"))
						{
							cierreBucleV=false;
						}
					}
					System.out.println(ArmarLigaVillanos(nombreLigaVillano.toLowerCase(), integrantesLiga));
					break;
				/**
				 * Devuelvo la lista de personajes del juego
				 */
				case 9: //MUESTRO LOS PERSONAJES DEL SISTEMA
					System.out.println(MostrarPersonajesCualidades());
					break;
				/**
				 * Devuelvo la lista de ligas del juego	
				 */	
				case 10: //MUESTRO LAS LIGAS DEL SISTEMA
					System.out.println(MostrarLigasJuego());
					break;
				/**
				 * Devuelvo la lista de personajes ordenadas
				 * ascendentemente por el nombre	
				 */		
				case 11: //ORDENO ASCENDENTEMENTE NOMBRE
					System.out.println(OrdenarNombresAsc());
					break;
				/**
				 * Devuelvo la lista de personajes ordenadas
				 * descendentemente por el nombre	
				 */	
				case 12: //ORDENO DESCENDENTEMENTE NOMBRE
					System.out.println(OrdenarNombresDesc());
					break;
				/**
				 * Devuelvo la lista de personajes ordenadas
				 * ascendentemente por la fuerza	
				 */	
				case 13: //ORDENO ASCENDENTEMENTE FUERZA
					System.out.println(OrdenarFuerzaAsc());
					break;
				/**
				 * Devuelvo la lista de personajes ordenadas
				 * descendentemente por la fuerza
				 */	
				case 14: //ORDENO DESCENDENTEMENTE FUERZA
					System.out.println(OrdenarFuerzaDesc());
					break;
				/**
				 * Devuelvo la lista de personajes ordenadas
				 * ascendentemente por la velocidad	
				 */	
				case 15: //ORDENO ASCENDENTEMENTE VELOCIDAD
					System.out.println(OrdenarVelocidadAsc());
					break;
				/**
				 * Devuelvo la lista de personajes ordenadas
				 * descendentemente por la velocidad	
				 */	
				case 16: //ORDENO DESCENDENTEMENTE VELOCIDAD
					System.out.println(OrdenarVelocidadDesc());
					break;
				/**
				 * me permite terminar el bucle que tiene el programa corriendo
				 */	
				case 17: //CIERRO EL PROGRAMA
					cierre=false;
					break;
					
				}
				System.out.println("Press any key to continue...");
				try {
					System.in.read();
				} catch (IOException e) {

					e.printStackTrace();
				}			
			}
		}
		catch(Exception e) {
			System.out.println("Ha ocurrido un error en el sistema.");
		}
		System.out.println("Fin..");
		
	

	}
	
	/**
	 * Cargo datos de ejemplos para utilizar el sistema
	 * se crean personajes, caracteristicas y ligas
	 */
	private static void DatosEjemplos() {
		Personaje personajeBatman=new Personaje("marcos","batman",false,45);
		personajes.add(personajeBatman);
		Personaje personajeBetty=new Personaje("beth","betty",true,19);
		personajes.add(personajeBetty);
		Personaje personajeBatwoman= new Personaje("maria","batwoman",false,35);
		personajes.add(personajeBatwoman);
		
		Caracteristica c1=new Caracteristica("fuerza",500);		
		CaracteristicasJuego.add(c1);
		Caracteristica c2=new Caracteristica("fuerza",250);
		CaracteristicasJuego.add(c2);
		Caracteristica c3=new Caracteristica("velocidad",100);
		CaracteristicasJuego.add(c3);
		Caracteristica c4=new Caracteristica("velocidad",200);
		CaracteristicasJuego.add(c4);
		
		personajeBatman.AñadirCaracteristica(c1);
		personajeBatman.AñadirCaracteristica(c3);
		
		personajeBetty.AñadirCaracteristica(c4);
		
		personajeBatwoman.AñadirCaracteristica(c2);
		personajeBatwoman.AñadirCaracteristica(c4);
		
		
		List<Componente> integrantesLiga1=new ArrayList<Componente>();
		integrantesLiga1.add(personajeBatman);
		integrantesLiga1.add(personajeBatwoman);
		ArmarLigaHeroes("liga1", integrantesLiga1);
		
		List<Componente> integrantesLiga3=new ArrayList<Componente>();
		integrantesLiga3.add(personajeBatman);
		integrantesLiga3.add(personajeBatwoman);
		
		Liga agregarLiga= EncontrarLiga("liga1");
		
		integrantesLiga3.add(agregarLiga);
		ArmarLigaHeroes("liga3", integrantesLiga3);
		
		List<Componente> integrantesLiga2=new ArrayList<Componente>();
		integrantesLiga2.add(personajeBetty);
		ArmarLigaVillanos("liga2", integrantesLiga2);
	
	} 
	
	
	/**
	 * Cargo datos de ejemplos para utilizar el sistema
	 * se crean personajes, caracteristicas y ligas
	 */
	private static void DatosEjemplos2() {
		Personaje personajeDrDoom=new Personaje("victor","doom",true,45);
		personajes.add(personajeDrDoom);
		Personaje personajeKang=new Personaje("nathaniel","kang",true,35);
		personajes.add(personajeKang);
		Personaje personajeFlash= new Personaje("wally","flash",false,25);
		personajes.add(personajeFlash);
		Personaje personajeCapitan= new Personaje("steve","capitan america",false,28);
		personajes.add(personajeCapitan);
		
		Caracteristica c1=new Caracteristica("fuerza",1500);		
		CaracteristicasJuego.add(c1);
		Caracteristica c2=new Caracteristica("fuerza",25000);
		CaracteristicasJuego.add(c2);
		Caracteristica c3=new Caracteristica("velocidad",1000);
		CaracteristicasJuego.add(c3);
		Caracteristica c4=new Caracteristica("velocidad",20000);
		CaracteristicasJuego.add(c4);
		Caracteristica c5=new Caracteristica("fuerza",900);		
		CaracteristicasJuego.add(c5);
		
		personajeDrDoom.AñadirCaracteristica(c1);
		personajeDrDoom.AñadirCaracteristica(c3);
		
		personajeKang.AñadirCaracteristica(c3);
		personajeKang.AñadirCaracteristica(c1);
		
		personajeFlash.AñadirCaracteristica(c5);
		personajeFlash.AñadirCaracteristica(c4);
		
		personajeCapitan.AñadirCaracteristica(c2);
		personajeCapitan.AñadirCaracteristica(c3);
		
		
		List<Componente> integrantesLiga1=new ArrayList<Componente>();
		integrantesLiga1.add(personajeDrDoom);
		integrantesLiga1.add(personajeKang);
		ArmarLigaVillanos("villanos1", integrantesLiga1);
		
		List<Componente> integrantesLiga3=new ArrayList<Componente>();
		integrantesLiga3.add(personajeDrDoom);
		integrantesLiga3.add(personajeKang);
		Liga agregarLiga= EncontrarLiga("villanos1");
		
		integrantesLiga3.add(agregarLiga);
		ArmarLigaVillanos("villanos2", integrantesLiga3);
		
		List<Componente> integrantesLiga2=new ArrayList<Componente>();
		integrantesLiga2.add(personajeFlash);
		integrantesLiga2.add(personajeCapitan);
		ArmarLigaHeroes("heroes1", integrantesLiga2);				
	} 
	
	
	
	/**
	 * Metodo que me permite vincular una caracteristica con un personae
	 * @param caracteristica que es la que se va a agregar a las cualidades del personaje
	 * @param personaje que va a tener la nueva caracteristica
	 * @return string para poder mostrar en pantalla si se pudo añadir o no
	 */
	private static String AgregarCaracteristicaPersonaje(Caracteristica caracteristica, Personaje personaje)
	{
		String textoDevolver="";
		try {
			personaje.AñadirCaracteristica(caracteristica);
			textoDevolver+="Caracteristica añadida";
		}
		catch(Exception e)
		{
			textoDevolver+="No se pudo añadir, ocurrió un error";
		}
		return textoDevolver;
	}
	
	
	/**
	 * Me permite comprar las cualidades de los personajes para determinar un ganador
	 * @param personaje1 a enfrentar
	 * @param personaje2 a enfrentar
	 * @param criterioEnfrentamiento por el cual se debe comparar
	 * @return un string que devuelve el veredicto de la comparación
	 */
	private static String EnfrentarPersonajes(Componente personaje1, Componente personaje2, String criterioEnfrentamiento) {
		int valorCaracteristicaP1=0;
		int valorCaracteristicaP2=0;
		for(Caracteristica caracteristicaPersonaje1:personaje1.getCualidades())
		{
			if(caracteristicaPersonaje1.getDescripcion().equals(criterioEnfrentamiento))
			{
				valorCaracteristicaP1=caracteristicaPersonaje1.getNivel();
			}
		}
		for(Caracteristica caracteristicaPersonaje2:personaje2.getCualidades())
		{
			if(caracteristicaPersonaje2.getDescripcion().equals(criterioEnfrentamiento))
			{
				valorCaracteristicaP2=caracteristicaPersonaje2.getNivel();
			}
		}
		String textoDevolver="";
		if(valorCaracteristicaP1>valorCaracteristicaP2)
		{
			textoDevolver+="El resultado del enfrentamiento es: "+ personaje1.getNombreComponente()+" es el vencedor";
		}
		else if(valorCaracteristicaP1<valorCaracteristicaP2)
		{
			textoDevolver+="El resultado del enfrentamiento es: "+personaje2.getNombreComponente()+" es el vencedor";
		}

		return textoDevolver;
	}
	
	
	/**
	 * Si el metodo de enfrentamiento no devuelve un veredicto, se comparan los
	 * personajes por los otros criterios para definir un resultado
	 * si empatan en todo, se le informa al usuario
	 * @param personaje1 que se quiere enfrentar
	 * @param personaje2 que se quiere enfrentar
	 * @return texto para mostrarle la decisión al usuario
	 */
	private static String EnfrentamientoVacio(Componente personaje1, Componente personaje2)
	{
		String veredictoRecibido= EnfrentarPersonajes(personaje1, personaje2,"fuerza");
		if(veredictoRecibido.equals(""))
		{
			veredictoRecibido= EnfrentarPersonajes(personaje1, personaje2,"velocidad");
		}
		if(veredictoRecibido.equals(""))
		{
			veredictoRecibido= EnfrentarPersonajes(personaje1, personaje2,"invisibilidad");
		}
		if(veredictoRecibido.equals(""))
		{
			veredictoRecibido= "empate por todas las caracteristicas";
		}
		
		return "El resultado del enfrentamiento es: "+veredictoRecibido;
	}
	
	
	/**
	 * creo un id que identifique la liga para facilitar la búsqueda de esta
	 * se crea una nueva liga con el nombre y los integrantes que se recibieron
	 * @param nombreLiga - que se asigna a la liga para identificarla
	 * @param team - integrantes de la liga
	 * @return texto que me permite indicarle al usuario si se pudo agregar la liga
	 * al juego o no
	 */	
	private static String ArmarLigaHeroes(String nombreLiga, List<Componente> team)
	{
		String textoDevolver="";
		boolean terminadorBucle=true;
		while (terminadorBucle)
		{
			Random random= new Random();
			int id= random.nextInt(779)+1;
			boolean existeLiga=false;
			for(Liga liga:LigasJuego)
			{
				if(liga.getNombreLiga()!=nombreLiga)
				{
					if(liga.getIdLiga()==id)
					{
						existeLiga=true;
					}					
				}
				else
				{
					nombreLiga+=id;
				}
			}
			if(!existeLiga)
			{
				LigasJuego.add(new Liga(id,nombreLiga,false,team));
				terminadorBucle=false;
				textoDevolver="LIGA ARMADA";
			}			
		}
		return textoDevolver;
		
	}
	
	
	/**
	 * creo un id que identifique la liga para facilitar la búsqueda de esta
	 * se crea una nueva liga con el nombre y los integrantes que se recibieron
	 * @param nombreLiga - que se asigna a la liga para identificarla
	 * @param team - integrantes de la liga
	 * @return texto que me permite indicarle al usuario si se pudo agregar la liga
	 * al juego o no
	 */
	private static String ArmarLigaVillanos(String nombreLiga, List<Componente> team)
	{
		String textoDevolver="";
		boolean terminaBucle=true;
		while (terminaBucle)
		{
			Random random= new Random();
			int id= random.nextInt(779)+1;
			boolean existeLiga=false;
			for(Liga liga:LigasJuego)
			{
				if(!liga.getNombreLiga().equals(nombreLiga))
				{					
					if(liga.getIdLiga()==id)
					{
						existeLiga=true;
					}
				}
				else
				{
					nombreLiga+=id;
				}
			}
			if(!existeLiga)
			{
				LigasJuego.add(new Liga(id,nombreLiga,true,team));
				terminaBucle=false;
				textoDevolver+="LIGA AÑADIDA";
			}			
		}
		return textoDevolver;
	}
	
	
	/**
	 * me realiza el ordenamiento ascendentemente de los personajes del juego por nombre
	 * con la clase de comparación que el envío
	 * @see #ComparadorPersonajeNombreASC()
	 * @return texto que me permite ver los personajes ordenados
	 */
	private static String OrdenarNombresAsc() {

		personajes.sort(new ComparadorPersonajeNombreASC());
		return MostrarPersonajesCualidades();
	}
	
	
	/**
	 * me realiza el ordenamiento descendentemente de los personajes del juego por nombre
	 * con la clase de comparación que el envío
	 * @see #ComparadorPersonajeNombreDESC()
	 * @return texto que me permite ver los personajes ordenados
	 */
	private static String OrdenarNombresDesc() {
		personajes.sort(new ComparadorPersonajeNombreDESC());
		return MostrarPersonajesCualidades();
	}
	
	
	/**
	 * me realiza el ordenamiento ascendentemente de los personajes del juego por fuerza
	 * con la clase de comparación que el envío
	 * @see #ComparadorPersonajeFuerzaASC()
	 * @return texto que me permite ver los personajes ordenados
	 */
	private static String OrdenarFuerzaAsc() {

		personajes.sort(new ComparadorPersonajeFuerzaASC());
		return MostrarPersonajesCualidades();
	}
	
	
	/**
	 * me realiza el ordenamiento descendentemente de los personajes del juego por fuerza
	 * con la clase de comparación que el envío
	 * @see #ComparadorPersonajeFuerzaDESC()
	 * @return texto que me permite ver los personajes ordenados
	 */
	private static String OrdenarFuerzaDesc() {
		personajes.sort(new ComparadorPersonajeFuerzaDESC());
		return MostrarPersonajesCualidades();
	}
	
	
	/**
	 * me realiza el ordenamiento ascendentemente de los personajes del juego por velocidad
	 * con la clase de comparación que el envío
	 * @see #ComparadorPersonajeVelocidadASC()
	 * @return texto que me permite ver los personajes ordenados
	 */
	private static String OrdenarVelocidadAsc() {

		personajes.sort(new ComparadorPersonajeVelocidadASC());
		return MostrarPersonajesCualidades();
	}
	
	
	/**
	 * me realiza el ordenamiento descendentemente de los personajes del juego por velocidad
	 * con la clase de comparación que el envío
	 * @see #ComparadorPersonajeVelocidadDESC()
	 * @return texto que me permite ver los personajes ordenados
	 */
	private static String OrdenarVelocidadDesc() {
		personajes.sort(new ComparadorPersonajeVelocidadDESC());
		return MostrarPersonajesCualidades();
	}

	
	/**
	 * Metodo que me permite mostrar los personajes
	 * registrados en el juego junto a las cualidades que tiene
	 * @return un texto que me permite mostrarle al usuario los
	 * personajes y sus cualidades
	 */
	private static String MostrarPersonajesCualidades()
	{
		String textoDevolver="Personajes del Juego.\n";
		for(Personaje personaje:personajes)
		{
			textoDevolver+="Nombre real: " + personaje.getNombreReal()+"\n";
			textoDevolver+="Nombre ficticio: " + personaje.getNombreFicticio()+"\n";
			textoDevolver+="Edad: " + personaje.getEdad()+"\n";
			textoDevolver+="Cualidades: \n";
			for(Caracteristica caracteristicac:personaje.getCualidades())
			{
				textoDevolver+="\t\t"+caracteristicac.getDescripcion()+"-Nivel: "+ caracteristicac.getNivel()+"\n";
			}
			textoDevolver+="\t\tCamuflaje: "+personaje.getCamuflaje()+"\n";
			textoDevolver+="\t\tModernidad: "+personaje.getModernidad()+"\n";
			textoDevolver+="\t\tResistencia: "+personaje.getResistencia()+"\n";
		}
		return textoDevolver;
	}
	
	
	/**
	 * Me permite mostrar las caracteristicas que se encuentran registradas
	 * en el juego 
	 * @return un texto que me permite mostrarle las caracteristicas al usuario
	 */
	private static String MostrarCaracteristicasJuego()
	{
		String textoDevolver="Las caracteristicas registradas en el juego son:\n ";
		for(Caracteristica caracteristica: CaracteristicasJuego)
		{
			textoDevolver+="\t"+caracteristica.getDescripcion()+"-Nivel: "+caracteristica.getNivel()+"\n";
		}
		return textoDevolver;
	}
	
	
	/**
	 * Me permite mostrar las ligas que se encuentran registradas en el juego
	 * @return un texto que me permite mostrar las ligas del juego al usuario
	 */
	private static String MostrarLigasJuego()
	{
		String textoDevolver="Las ligas actuales son: \n";
		for(Liga ligal:LigasJuego)
		{
			textoDevolver+="\tNombre Liga: "+ligal.getNombreLiga()+"\n\t\t Participantes: \n";
			for(Componente integrante:ligal.getTeam())
			{
				if(integrante instanceof Personaje)
				{
					textoDevolver+="\t\t\t Personaje: "+((Personaje) integrante).getNombreFicticio()+"\n";
				}
				else if( integrante instanceof Liga)
				{
					textoDevolver+="\t\t\t Liga: "+ ((Liga)integrante).getNombreLiga()+"\n";
				}
			}
			textoDevolver+="\t\tCUALIDADES DE LA LIGA:\n";
			for(Caracteristica caracteristicas:ligal.getCualidades())
			{
				textoDevolver+="\t\t\t Caracteristica: "+ caracteristicas.getDescripcion()+" -Nivel: "+caracteristicas.getNivel()+"\n";
			}
		}
		return textoDevolver;
	}
	
	
	/**
	 * Me permite mostrarle un menu al usuario para que seleccione las acciones
	 * @return texto que me permite mostrar el menu visualmente en consola. 
	 */
	private static String MenuPrincipal()
	{
		String textoDevolver="1-Añadir Personaje\n"
				+ "2-Añadir Caracteristica\n"
				+ "3-Añadir Caracteristica a Personaje\n"
				+ "4-Enfrentar Personajes\n"
				+ "5-Enfrentar Personaje-Liga\n"
				+ "6-Enfrentar Liga-Liga\n"
				+ "7-Armar liga heores\n"
				+ "8-Armar liga villanos\n"
				+ "9-Mostrar Personajes\n"
				+ "10-Mostrar Ligas\n"
				+ "11-Ordenar ascendentemente por nombre\n"
				+ "12-Ordenar descendentemente por nombre\n"
				+ "13-Ordenar ascendentemente por fuerza\n"
				+ "14-Ordenar descendentemente por fuerza\n"
				+ "15-Ordenar ascendentemente por velocidad\n"
				+ "16-Ordenar descendentemente por velocidad\n"
				+ "17-Salir";
		return textoDevolver;
	}
	
	
	/**
	 * Metodo que recorre la lista de personajes que se encuentran registrados
	 * en el juego con el fin de obtener el personaje buscado
	 * @param nombrePersonaje - nombre de super heroe o villano que busco
	 * @return retorno el personaje encontrado o nulo en su defecto
	 */
	private static Personaje EncontrarPersonaje(String nombrePersonaje)
	{
		Personaje personajeEncontrado=null;
		for(Personaje personaje:personajes)
		{
			if(personaje.getNombreFicticio().equals(nombrePersonaje))
			{
				personajeEncontrado=personaje;
			}
		}
		return personajeEncontrado;
	}
	
	
	/**
	 * Metodo que recorre las ligas que se encuentran registradas en el juego con el
	 * fin de devolver la liga buscada
	 * @param nombreLiga - nombre de la liga de superheroes o villanos que busco
	 * @return retorno la liga que buscaba
	 */
	private static Liga EncontrarLiga(String nombreLiga)
	{
		Liga ligaEncontrada=null;
		for(Liga liga:LigasJuego)
		{
			if(liga.getNombreLiga().equals(nombreLiga))
			{
				ligaEncontrada=liga;
			}
		}
		return ligaEncontrada;
	}
	
	
	/**
	 * Me permite encontrar la caracteristica recorriendo las que se encuentran
	 * registradas en el juego y viendo si corresponde con la descripcion y el nivel
	 * @param nombreCaracteristica - me permite obtener la descripcion de la caracteristica que busco
	 * @param nivelCaracteristica - me permite obtener el nivel que se desea obtener
	 * @return me devuelve la caracteristica encontrada o nulo en su defecto. 
	 */
	private static Caracteristica EncontrarCaracteristica (String nombreCaracteristica, int nivelCaracteristica)
	{
		Caracteristica caracteristicaEncontrada=null;
		for(Caracteristica caracteristica:CaracteristicasJuego)
		{
			if(caracteristica.getDescripcion().equals(nombreCaracteristica)&& caracteristica.getNivel()==nivelCaracteristica)
			{
				caracteristicaEncontrada=caracteristica;
			}
		}
		return caracteristicaEncontrada;
	}
	

}
