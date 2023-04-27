package tpIntegrador;


public class Partido {

	Equipo equipo1;
	Equipo equipo2;

	int golesEquipo1;
	int golesEquipo2;

	public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;

	}
	
	public Partido(Equipo equipo1, Equipo equipo2) {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		
	

	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipo1 == null) ? 0 : equipo1.hashCode());
		result = prime * result + ((equipo2 == null) ? 0 : equipo2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		if (equipo1 == null) {
			if (other.equipo1 != null)
				return false;
		} else if (!equipo1.equals(other.equipo1))
			return false;
		if (equipo2 == null) {
			if (other.equipo2 != null)
				return false;
		} else if (!equipo2.equals(other.equipo2))
			return false;
		return true;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}

	// Partido partido1=new Partido(equipo1,equipo2,golesEquipo1,golesEquipo2)

	// partido1.resultado(equipo1)

	public String resultado(Equipo equipodesconocido) {

		// ResultadoEnum ganador=ResultadoEnum.GANADOR;
		// ResultadoEnum perdedor=ResultadoEnum.PERDEDOR;
		// ResultadoEnum empate=ResultadoEnum.EMPATE;

		ResultadoEnum elequipofue;

		if (equipodesconocido.equals(equipo1)) {

			if (golesEquipo1 > golesEquipo2) {

				elequipofue = ResultadoEnum.GANADOR;
				return elequipofue.toString();

			} else if (golesEquipo1 < golesEquipo2) {
				elequipofue = ResultadoEnum.PERDEDOR;
				return elequipofue.toString();

			} else {
				elequipofue = ResultadoEnum.EMPATE;
				return elequipofue.toString();
			}

		} else if (equipodesconocido.equals(equipo2)) {

			if (golesEquipo2 > golesEquipo1) {

				elequipofue = ResultadoEnum.GANADOR;
				return elequipofue.toString();

			} else if (golesEquipo2 < golesEquipo1) {
				elequipofue = ResultadoEnum.PERDEDOR;
				return elequipofue.toString();

			} else {
				elequipofue = ResultadoEnum.EMPATE;
				return elequipofue.toString();
			}

		} else {
			
			return "DEBE INGRESAR UN TIPO \"PARTIDO\"   pero el correcto por eso no hay coincidencia, al menos no hubo coincidencia entre la comparacion de un partido";

		}

	}// fin método resultado





























}
