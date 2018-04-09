package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class XorGate extends Gate {
	NandGate nand1;
	NandGate nand2;
	NandGate nand3;
	NandGate nand4;
	
	public XorGate(){
		super("Xor");
		nand1 = new NandGate();
		nand2 = new NandGate();
		nand3 = new NandGate();
		nand4 = new NandGate();

		
	}
	
	@Override
	public void connect(int pinIndex, Emitter emitter) {
		if (pinIndex == 0){
			nand1.connect(0, emitter);
			nand2.connect(0, emitter);
		}
		else if(pinIndex == 1){
			nand1.connect(1, emitter);
			nand3.connect(1, emitter);
		}
		
		
	}

	@Override
	public boolean read() {
		nand2.connect(1,nand1);
		nand3.connect(0, nand1);
		nand4.connect(0, nand2);
		nand4.connect(1, nand3);
		return nand4.read();
	}
	
	public int size(){
		int s = 2;
		
		return s;
	}



}
