package tpIntegrador;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClaseMain {

	public static void main(String[] args) throws ClassNotFoundException {

		// ARCHIVO_RESULTADOS-----------------------------------------------------------------------
		// -----------------------------------------------------------------------------------------

		List<List<String>> Archivo_Resultados = new ArrayList<>();

		try {

			String aux_args = "C:\\Users\\Kefren\\eclipse-workspace\\Resultados2.csv";

			Path direccion_archivo = Paths.get(aux_args);

			Charset charset = Charset.defaultCharset();

			int tamanio = Files.readAllLines(direccion_archivo, charset).size();

			for (int i = 0; i < tamanio; i++) {

				Archivo_Resultados.add(new ArrayList<String>());

			}

			int cont = 0;

			for (String linea : Files.readAllLines(direccion_archivo, charset)) {

				String[] partes = linea.split(";");

				for (int j = 0; j <= partes.length - 1; j++) {

					Archivo_Resultados.get(cont).add(partes[j]);

				}

				cont = cont + 1;

			}

		} catch (IOException e) {

			e.printStackTrace();

		} // fin try-catch de Resultados

		Archivo_Resultados.remove(0);

		// --------------------------------------------------------------------------------------------------------//
		List<Equipo> Equipos_De_Resultados = new ArrayList<>();

		for (int i = 0; i <= Archivo_Resultados.size() - 3; i++) {
			Equipo equipo1 = new Equipo(Archivo_Resultados.get(i).get(1), Archivo_Resultados.get(i).get(0));
			Equipos_De_Resultados.add(equipo1);
			Equipo equipo2 = new Equipo(Archivo_Resultados.get(i).get(4), Archivo_Resultados.get(i).get(5));
			Equipos_De_Resultados.add(equipo2);
		}

		// --------------------------------------------------------------------------------------------------------//

		List<Partido> Partidos_De_Resultados = new ArrayList<>();

		for (int i = 0; i <= Equipos_De_Resultados.size() - 1; i++) {

			// Construyo el primer partido
			if (i == 0) {
				Partido partido = new Partido(Equipos_De_Resultados.get(0), Equipos_De_Resultados.get(1),
						Integer.parseInt(Archivo_Resultados.get(i).get(2)),
						Integer.parseInt(Archivo_Resultados.get(i).get(3)));
				Partidos_De_Resultados.add(partido);
			} // PARTIDO 1

			// Construyo el segundo partido
			if (i == 1) {
				Partido partido = new Partido(Equipos_De_Resultados.get(2), Equipos_De_Resultados.get(3),
						Integer.parseInt(Archivo_Resultados.get(i).get(2)),
						Integer.parseInt(Archivo_Resultados.get(i).get(3)));
				Partidos_De_Resultados.add(partido);
			} // PARTIDO 2

			// Construyo el tercer partido
			if (i == 2) {
				Partido partido = new Partido(Equipos_De_Resultados.get(0), Equipos_De_Resultados.get(2),
						Integer.parseInt(Archivo_Resultados.get(i).get(2)),
						Integer.parseInt(Archivo_Resultados.get(i).get(3)));
				Partidos_De_Resultados.add(partido);
			} // PARTIDO 3

			// Construyo el cuarto partido
			if (i == 3) {
				Partido partido = new Partido(Equipos_De_Resultados.get(3), Equipos_De_Resultados.get(1),
						Integer.parseInt(Archivo_Resultados.get(i).get(2)),
						Integer.parseInt(Archivo_Resultados.get(i).get(3)));
				Partidos_De_Resultados.add(partido);
			} // PARTIDO 3

		} // fin del ciclo for

		// --------------------------------------------------------------------------------------------------------//

		Partido[] ArregloDePartidos = new Partido[Partidos_De_Resultados.size()];

		for (int i = 0; i <= Partidos_De_Resultados.size() - 1; i++) {

			ArregloDePartidos[i] = Partidos_De_Resultados.get(i);

		}

		// FIN
		// ARCHIVO_RESULTADOS----------------------------------------------------------------------
		// ----------------------------------------------------------------------------------------
		// **************************************************************************************//
		// COMIENZO LECTURA DESDE LA BASE DE DATOS
		// PRONOSTICO--------------------------------------
		// -----------------------------------------------------------------------------------------

		List<List<String>> Equipos_Codigo_Pronostico_String = new ArrayList<>();
		List<List<String>> Tabla_Ronda_1_Pronostico = new ArrayList<>();

		// PRIMER try-catch
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

			con.close();

		} catch (SQLException e) {

			System.out.println(e);
		} // fin PRIMER TRY-CATCH

		// SEGUNDO TRY-CATCH
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/partidosmundial_v1", "root",
					"pass123");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM ronda_1");

			int i = 0;

			while (rs.next()) {

				Tabla_Ronda_1_Pronostico.add(new ArrayList<String>());

				Tabla_Ronda_1_Pronostico.get(i).add(rs.getString("equipo1"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs.getString("codigo1"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs.getString("equipo2"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs.getString("codigo2"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs.getString("equipo_elegido"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs.getString("enumerado"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs.getString("ronda"));
				Tabla_Ronda_1_Pronostico.get(i).add(rs.getString("participante"));

				i += 1;

			} // fin del while

			stmt.close();

		} catch (SQLException e) {

			System.out.println(e);
		} // fin SEGUNDO try-catch

		List<Equipo> Equipos_Del_Pronostico = new ArrayList<>();

		for (int i = 0; i <= Equipos_Codigo_Pronostico_String.size() - 1; i++) {
			Equipo equipo = new Equipo(Equipos_Codigo_Pronostico_String.get(i).get(0),
					Equipos_Codigo_Pronostico_String.get(i).get(1));
			Equipos_Del_Pronostico.add(equipo);

		}

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

		// FIN LECTURA
		// PRONOSTICO------------------------------------------------------------------
		// ----------------------------------------------------------------------------------------
		// ----------------------------------------------------------------------------------------

		Ronda ronda1 = new Ronda(ArregloDePartidos, "1");

		int almacena_puntos_pronostico = 0;
		for (int i = 0; i <= Lista_Pronostico.size() - 1; i++) {

			if (ronda1.getPartidos()[i].equals(Lista_Pronostico.get(i).getPartido())) {

				String resultado_del_partido;

				resultado_del_partido = ronda1.getPartidos()[i].resultado(Lista_Pronostico.get(i).getEquipo());

				if (Lista_Pronostico.get(i).getResultado().toString().equals(resultado_del_partido)) {

					almacena_puntos_pronostico = Lista_Pronostico.get(i).puntos() + almacena_puntos_pronostico;

				}

			} else {
				System.out.println(
						"El partido  " + i + "cargado en pronóstico no coincide con el partido " + i + "de la ronda");
			}

		} // fin del FOR

		System.out.println("Los aciertos del usuario anónimo fueron  " + almacena_puntos_pronostico + ".");

	}// fin main

}// fin CLASE ClaseMain
