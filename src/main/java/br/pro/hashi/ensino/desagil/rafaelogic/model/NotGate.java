package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class NotGate extends Gate{
	private NandGate nand1;
	
	public NotGate(){
		super("Not");
		nand1 = new NandGate();
	}
	
	@Override
	public void connect(int pinIndex, Emitter emitter) {
		nand1.connect(0, emitter);
		nand1.connect(1, emitter);

		
	}

	@Override
	public boolean read() {
		return nand1.read();
	}
	
	public int size(){
		int s = 1;
		
		return s;
	}


	

}
