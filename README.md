# FRANCISCO BALONERO OLIVERA

Diario de formacion dual Viewnext 2024.

## Semana 1 (10 Eneroal 12 Enero)
En la primera semana recogí el equipo y realize los cursos obligatorios de bienvenida. Comenzé un proyecto para probar los nuevos cambios de Spring Security y repasar un poco Hibernate.

## Semana 2 (15 Enero al 19 Enero)
Estuve ultimando un poco el proyecto para que sea al menos utilizable, creando la vista con thymeleaf y probando la seguridad [ShopProject](https://github.com/Francisco-B-O/ShopProject).
Luego se planteo el proyecto gestor de torneos y se crearon los principales pasos a seguir.
En primer lugar teniamos que diseñar la base de datos para la cual nos dieron unos requisitos que estuvimos discutiendo para ver como sería la mejor manera de crearla.

## Semana 3 (22 Enero al 25 Enero)
Después de estar comparando y discutiendo como sería la mejor manera de crear esa base de datos, llegamos a un script en común y pasamos empezar a crear el CRUD con Spring Boot.En esta semana tambien dimos una charla sobre Scrum y sobre inteligencia artificial.

## Semana 4 (29 Enero al 2 Febrero)
En esta semana terminé de crear las clases entidad y sus repositorios. Estuve formándome mas sobre la arquitectura de 3 capas y sobre como programar orientado a test. Ha sido una semana de bastante madurez en la manera de ver cómo es el proceso de crear una aplicación, ya que he tenido que afrontar varias decisiones sobre como ir desarrollando las capas y sobre las necesidades que quería cubrir. Terminé los test unitarios para los repositorios y tenía pensado empezar con la segunda capa (lógica de negocio), pero hablando con mi tutor tambien llegué a la conclusión que sería buena idea crear excepciones personalizadas para los métodos de la primera capa (acceso a los datos), ya que luego podría ir manejando las excepciones e ir llevándolas capa a capa. Aunque mi idea de crear excepciones personalizadas en la capa de lógica de negocio no estaba mal, también entendí que asi tendría mas solidez la aplicación.

## Semana 5 (5 Febrero al 9 Febrero)
Esta semana ha sido un poco mas teórica para mí, ya que despues de pensar en como implementar la capa de anticorrupción, estuve investigando sobre las diferentes maneras que existen de hacer un CRUD con Spring. Al final, utilicé los metodos de la interfaz repositorio de cada entidad para crear un DAO y así tener una clase donde poder gestionar la excepción personalizada. Después de haber testeado el DAO, comencé con los objetos de negocio y sus conversiones. Ya antes de hacer los test de las conversiones, sabía que iba a tener problema de recursion infinita debido a las relaciones bidireccionales entre tablas, por lo que cree objetos de negocio auxiliares para solucionar ese problema: UserBOAux, TeamBOAux y TournamentBOAux. Esos objetos auxiliares me permitían evitar la recursión infinita y mantener información que me puede servir a lo largo de la creacion de los servicios y la presentación.

## Semana 6 (12 Febrero al 16 Febrero)
Con la recursión infinita solucionada en los BO y con todos los test realizados y comprobados, doy comienzo a la creación de servicios. Antes de empezar la creación del primer servicio (UserService), estuve pensando en como manejaria el tema de excepciones en esta capa. He pensado que lo mejor será crear una excepción general y otras que extiendan de estas para cosas mas específicas, por ejemplo:

* Esta seria la excepción general, BusinessException:
```java
public class BusinessException extends Exception{

	private static final long serialVersionUID = -1618850050663333984L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Exception e) {
		super(message, e);
	}
}
```

* Esta seria una excepción específica, DuplicatedEmailExceptión:
```java
public class DuplicatedEmailException extends BusinessException {

	private static final long serialVersionUID = 151017868746738093L;

	public DuplicatedEmailException(String message) {
		super(message);
	}

	public DuplicatedEmailException(String message, Exception e) {
		super(message, e);
	}
}
```

Esto lo hago así para poder controlar errores mas específicos, por ejemplo, si intento registrar un nuevo usuario y tiene un email que ya existe, lanzaría la excepción DuplicatedEmailException, ya que ha encontrado un usuario con el mismo email:
```java
@Override
	public UserBO registerUser(UserBO userBO) throws BusinessException {
		try {
			userDAO.findByEmail(userBO.getEmail());
			throw new DuplicatedEmailException("This email is already registered");
		} catch (DataException e) {
		}
		try {
			userDAO.findByNick(userBO.getNick());
			throw new DuplicatedNickException("This nickname is already registered");
		} catch (DataException e) {
		}
		try {

			return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(userBO)));

		} catch (DataException e) {
			throw new BusinessException("Error registering user", e);
		}
	}

```

Ya con el tema de las excepciones pensado, implementé los casos de aceptación a nivel de usuario y terminé los test con Mockito. Esta semana ha sido una semana bastante buena, en el sentido de que cada vez comprendo y me anticipo más a los posibles problemas que puedo encontrarme más adelante en el desarrollo de la aplicación y también problemas y situaciones que pueden darse en mi futuro profesional.
