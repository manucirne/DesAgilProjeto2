package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class AndGate extends Gate {
	private NandGate nand1;
	private NandGate nand2;
	
	public AndGate(){
		super("And");
		nand1= new NandGate();
		nand2= new NandGate();

	}

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		nand1.connect(pinIndex,emitter);
		
		
	}
	
	@Override
	public boolean read() {
		nand2.connect(0, nand1);
		nand2.connect(1, nand1);
		return nand2.read();
	}
	
	public int size(){
		int s = 2;
		
		return s;
	}

}
