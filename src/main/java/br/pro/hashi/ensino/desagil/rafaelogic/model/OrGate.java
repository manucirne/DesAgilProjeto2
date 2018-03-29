package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class OrGate extends Gate {
	private NandGate nand1;
	private NandGate nand2;
	private NandGate nand3;
	
	public OrGate(){
		nand1 = new NandGate();
		nand2 = new NandGate();
		nand3 = new NandGate();
		nand3.connect(0,nand1);
		nand3.connect(1, nand2);
	}

	
	@Override
	public void connect(int pinIndex, Emitter emitter) {
		if (pinIndex == 0){
			nand1.connect(0, emitter);
			nand1.connect(1, emitter);
		}
		else if(pinIndex == 1){
			nand2.connect(0, emitter);
			nand2.connect(1, emitter);
		}
	}
	

	
	@Override
	public boolean read() {	
		return nand3.read();
	}


}
