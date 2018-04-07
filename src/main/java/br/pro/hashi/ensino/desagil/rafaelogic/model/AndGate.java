package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class AndGate extends Gate {
	private NandGate nand1;
	
	public AndGate(){
		super("And");
		nand1= new NandGate();
	}

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		nand1.connect(pinIndex,emitter);
		
	}
	
	@Override
	public boolean read() {
		return !nand1.read();
	}
	

}
