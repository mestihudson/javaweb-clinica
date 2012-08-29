package br.com.fiap.clinica.modelo;


public enum AgendamentoStatus {

	AGENDADO(1), CANCELADO(2), EFETIVADO(3);
	
	private final Integer id;

	private AgendamentoStatus(Integer id) {
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public static AgendamentoStatus valueOf(Integer id){
		switch (id) {
			case 1:
				return AGENDADO;
			case 2:
				return CANCELADO;
			case 3:
				return EFETIVADO;
			default:
				return null;
				
		}
	}
}
