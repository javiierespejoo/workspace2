package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import datos.Equipo;
import datos.Jugador;

public class Aplicacion {

	public static void main(String[] args) {
		
		//conectarnos a la base de datos
		ODB baseDatos = ODBFactory.open("bbdd/testOO", "javier", "Pass!123456");
		
		Jugador j1 = new Jugador("Raul", "Tello", "padel", new Date("2002/07/19"));
		Jugador j2 = new Jugador("Javier", "Espejo", "padel", new Date("2002/07/29"));
		Jugador j3 = new Jugador("Adrian", "fraga", "padel", new Date("2002/02/06"));
		Jugador j4 = new Jugador("Jorge", "Ariño", "padel", new Date("2002/07/03"));
		
		baseDatos.store(j1);
		baseDatos.store(j2);
		baseDatos.store(j3);
		baseDatos.store(j4);
		
		
		HashSet<Jugador> cjtoJugadores = new HashSet();
		
		cjtoJugadores.add(j1);
		cjtoJugadores.add(j2);
		cjtoJugadores.add(j3);
		cjtoJugadores.add(j4);
		
		Equipo elequipo = new Equipo("Tenis Wins", "absoluta", cjtoJugadores);
		
		baseDatos.store(elequipo);
		baseDatos.commit();
		baseDatos.close();
		
		System.out.println("TERMINE");
	}

}
