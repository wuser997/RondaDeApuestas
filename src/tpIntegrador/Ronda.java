package tpIntegrador;

import java.util.Arrays;

//OJO REVISAR CUANDO PASE A VECTORES partidos!!!!!!!!

public class Ronda {
	
	String nro;
	Partido[] partidos; // Primero pruebo con un partido

	// Partido partidos;

	public Ronda(Partido[] partidos, String nro) {
		this.partidos = partidos;
		this.nro = nro;

	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public Partido[] getPartidos() {
		return partidos;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(partidos);
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
		Ronda other = (Ronda) obj;
		if (!Arrays.equals(partidos, other.partidos))
			return false;
		return true;
	}

	public void setPartidos(Partido[] partidos) {
		this.partidos = partidos;
	}

	public int puntos() {

		return 0;// cambiar cuando se usen más rondas
	}

	
	
	
	

}
