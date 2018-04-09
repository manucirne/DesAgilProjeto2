package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class NandGate extends Gate {
	private Emitter[] emitters;

	public NandGate() {
		super("Nand");
		emitters = new Emitter[2];
	}

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		emitters[pinIndex] = emitter;
	}

	@Override
	public boolean read() {
		return !(emitters[0].read() && emitters[1].read());
	}
	
	public int size(){
		int s = 2;
		
		return s;
	}
}
