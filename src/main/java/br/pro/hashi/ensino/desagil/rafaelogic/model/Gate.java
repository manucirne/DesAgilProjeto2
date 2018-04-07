package br.pro.hashi.ensino.desagil.rafaelogic.model;

public abstract class Gate implements Emitter, Receiver {
	private String name;

	protected Gate(String name) {
		this.name = name;
	}

	// Quando você ler a classe View, vai entender o nome deste método.
	public String toString() {
		return name;
	}
	
	public int size(){
		int s;
		if(name == "Not"){
			s = 1;
			
		}
		else{
			s = 2;
		}
		
		return s;
	}
}
