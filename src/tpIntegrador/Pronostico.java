package tpIntegrador;

public class Pronostico {
	
	Partido partido;
	Equipo equipo;
	ResultadoEnum resultado;

	public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado) {

		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;

	}
	
	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public ResultadoEnum getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoEnum resultado) {
		this.resultado = resultado;
	}

	
	
	
	
	
	public int puntos() {
		
		int c=0;
		
		return  c+=1;//cambiar valor
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
