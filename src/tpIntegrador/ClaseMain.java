package tpIntegrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClaseMain {

	public static void main(String[] args) throws ClassNotFoundException {

		// PRONOSTICO-------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------------

		List<List<String>> Equipos_Codigo_Pronostico_String = new ArrayList<>();
		List<List<String>> Tabla_Ronda_1_Pronostico = new ArrayList<>();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/partidosmundial_v1", "root",
					"pass123");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM equipospronosticosronda1");

			int i = 0;

			while (rs.next()) {

				Equipos_Codigo_Pronostico_String.add(new ArrayList<String>());

				Equipos_Codigo_Pronostico_String.get(i).add(rs.getString("equipos"));
				Equipos_Codigo_Pronostico_String.get(i).add(rs.getString("codigo"));

				i += 1;

			} // fin del while
            
			stmt.close();
			
			
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM ronda_1");
			
			while (rs2.next()) {
				

				Tabla_Ronda_1_Pronostico.add(new ArrayList<String>());

				Tabla_Ronda_1_Pronostico.get(i).add(rs2.getString("equipo1"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs2.getString("codigo1"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs2.getString("equipo2"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs2.getString("codigo2"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs2.getString("equipo_elegido"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs2.getString("enumerado"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs2.getString("ronda"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs2.getString("participante"));

				i = i + 1;
			} // fin del while

			con.close();

		} catch (SQLException e) {

			System.out.println(e);
		} // fin catch-try
		
		
		System.out.println(Tabla_Ronda_1_Pronostico.size()+"    tabla pronostico TAMAÑO");


		List<Equipo> Equipos_Del_Pronostico = new ArrayList<>();

		for (int i = 0; i <= Equipos_Codigo_Pronostico_String.size() - 1; i++) {
			Equipo equipo = new Equipo(Equipos_Codigo_Pronostico_String.get(i).get(0),
					Equipos_Codigo_Pronostico_String.get(i).get(1));
			Equipos_Del_Pronostico.add(equipo);

		}

		// imprimo equipos
		for (int i = 0; i <= Equipos_Del_Pronostico.size() - 1; i++) {
			System.out.println(Equipos_Del_Pronostico.get(i).nombre);
		}

		
		
		System.out.println("tabla pronostico");

		
			System.out.println(Tabla_Ronda_1_Pronostico.get(0).get(4)+"    tabla pronostico");
		
		
		
		
		
		
		
		List<Partido> Partidos_Del_Pronostico = new ArrayList<>();
		List<Pronostico> Lista_Pronostico = new ArrayList<>();

		for (int i = 0; i <= Equipos_Del_Pronostico.size() - 1; i++) {

			if (i == 0) {

				Partido partido = new Partido(Equipos_Del_Pronostico.get(0), Equipos_Del_Pronostico.get(1));
				Partidos_Del_Pronostico.add(partido);

				// Si hay empate por defecto guardo el equipo1 porque la función Resultado de
				// Partido necesita un equipo como variable de entrada

				if (Partidos_Del_Pronostico.get(i).getEquipo1().nombre.equals(Tabla_Ronda_1_Pronostico.get(i).get(4))) {

					if (Tabla_Ronda_1_Pronostico.get(i).get(5).equals("GANADOR")) {
						Pronostico pro0 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.GANADOR);
						Lista_Pronostico.add(pro0);

					} else {
						Pronostico pro0 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.EMPATE);
						Lista_Pronostico.add(pro0);
					}

				} else {

					if (Tabla_Ronda_1_Pronostico.get(i).get(5).equals("GANADOR")) {
						Pronostico pro0 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo2(), ResultadoEnum.GANADOR);
						Lista_Pronostico.add(pro0);
					} else {
						Pronostico pro0 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.EMPATE);
						Lista_Pronostico.add(pro0);

					}

				}

			}

			/*
			 * Tabla_Ronda_1_Pronostico.get(0).get(0)=Catar
			 * Tabla_Ronda_1_Pronostico.get(0).get(1)=QAT
			 * Tabla_Ronda_1_Pronostico.get(0).get(2)=Ecuador
			 * Tabla_Ronda_1_Pronostico.get(0).get(3)=ECU
			 * Tabla_Ronda_1_Pronostico.get(0).get(4)=Partido elegido
			 * Tabla_Ronda_1_Pronostico.get(0).get(5)=enumerado
			 */

			/*
			 * Partidos_Del_Pronostico.get(i).getEquipo1().nombre + "+++++++++"
			 * +Partidos_Del_Pronostico.get(i).getEquipo2().nombre)
			 * Partidos_Del_Pronostico.get(i).getEquipo1().getCodigo()
			 */

			if (i == 1) {

				Partido partido = new Partido(Equipos_Del_Pronostico.get(2), Equipos_Del_Pronostico.get(3));
				Partidos_Del_Pronostico.add(partido);

				if (Partidos_Del_Pronostico.get(i).getEquipo1().nombre.equals(Tabla_Ronda_1_Pronostico.get(i).get(4))) {

					if (Tabla_Ronda_1_Pronostico.get(i).get(5).equals("GANADOR")) {
						Pronostico pro1 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.GANADOR);
						Lista_Pronostico.add(pro1);
					} else {
						Pronostico pro1 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.EMPATE);
						Lista_Pronostico.add(pro1);

					}

				} else {

					if (Tabla_Ronda_1_Pronostico.get(i).get(5).equals("GANADOR")) {
						Pronostico pro1 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo2(), ResultadoEnum.GANADOR);
						Lista_Pronostico.add(pro1);
					} else {
						Pronostico pro1 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.EMPATE);
						Lista_Pronostico.add(pro1);

					}

				}

			} // fin if==1

			if (i == 2) {

				Partido partido = new Partido(Equipos_Del_Pronostico.get(0), Equipos_Del_Pronostico.get(2));
				Partidos_Del_Pronostico.add(partido);

				if (Partidos_Del_Pronostico.get(i).getEquipo1().nombre.equals(Tabla_Ronda_1_Pronostico.get(i).get(4))) {

					if (Tabla_Ronda_1_Pronostico.get(i).get(5).equals("GANADOR")) {
						Pronostico pro2 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.GANADOR);
						Lista_Pronostico.add(pro2);
					} else {
						Pronostico pro2 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.EMPATE);
						Lista_Pronostico.add(pro2);

					}

				} else {

					if (Tabla_Ronda_1_Pronostico.get(i).get(5).equals("GANADOR")) {
						Pronostico pro2 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo2(), ResultadoEnum.GANADOR);
						Lista_Pronostico.add(pro2);
					} else {
						Pronostico pro2 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.EMPATE);
						Lista_Pronostico.add(pro2);

					}

				}

			}

			if (i == 3) {

				Partido partido = new Partido(Equipos_Del_Pronostico.get(3), Equipos_Del_Pronostico.get(1));
				Partidos_Del_Pronostico.add(partido);

				if (Partidos_Del_Pronostico.get(i).getEquipo1().nombre.equals(Tabla_Ronda_1_Pronostico.get(i).get(4))) {

					if (Tabla_Ronda_1_Pronostico.get(i).get(5).equals("GANADOR")) {
						Pronostico pro3 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.GANADOR);
						Lista_Pronostico.add(pro3);
					} else {
						Pronostico pro3 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.EMPATE);
						Lista_Pronostico.add(pro3);
					}

				} else {

					if (Tabla_Ronda_1_Pronostico.get(i).get(5).equals("GANADOR")) {
						Pronostico pro3 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo2(), ResultadoEnum.GANADOR);
						Lista_Pronostico.add(pro3);
					} else {
						Pronostico pro3 = new Pronostico(Partidos_Del_Pronostico.get(i),
								Partidos_Del_Pronostico.get(i).getEquipo1(), ResultadoEnum.EMPATE);
						Lista_Pronostico.add(pro3);

					}

				}

			}

		} // fin FOR Equipos_Del_Pronostico

		// ¡¡¡¡¡¡RECORDAR PARTIDOS TIENE DOS CONSTRUCTORES !!!!!!!!!!

		System.out.println();

		for (int i = 0; i <= Partidos_Del_Pronostico.size() - 1; i++) {
			System.out.println(Partidos_Del_Pronostico.get(i).getEquipo1().nombre + "+++++++++"
					+ Partidos_Del_Pronostico.get(i).getEquipo2().nombre);
		}

		// Verifico que la LISTA DE PRONOSTICOS tenga los partidos correctos
		// List<Equipo> Equipos_Del_Pronostico = new ArrayList<>();

		System.out.println();

		for (int i = 0; i <= Partidos_Del_Pronostico.size() - 1; i++) {
			System.out.println(Partidos_Del_Pronostico.get(i).getEquipo1().nombre + "+++++++++"
					+ Partidos_Del_Pronostico.get(i).getEquipo2().nombre);

			System.out.println(Lista_Pronostico.get(i).getEquipo().getNombre());

			System.out.println(Lista_Pronostico.get(i).getPartido().getEquipo1().getNombre() + "   "
					+ Lista_Pronostico.get(i).getPartido().getEquipo2().getNombre());

			System.out.println(Lista_Pronostico.get(i).getResultado().toString());

			System.out.println();

		}

		// -----------------------------------------------------------------------------------------

	}// fin main

}// fin CLASE ClaseMain
