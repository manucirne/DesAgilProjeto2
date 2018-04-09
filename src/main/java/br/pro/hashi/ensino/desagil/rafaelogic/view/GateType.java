package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

public class GateType extends JPanel implements ItemListener{

	//Linha que ainda n�o entendemos
	private static final long serialVersionUID = 1L;

	//Gate que ser� lido caso seja selecionado no menu
	private Gate gate;
	JCheckBox[] checkbox;
	Source[] sources;
	JCheckBox checkboxS;
	
	//dois sources - acho que dever�amos usar apenas uma e definir com o pinIndex
	//como ela ser� ligada. Todas as tentativas at� agora falharam
	


	public GateType(Gate gate) {
		//define qual porta est� sendo usada
		this.gate = gate;
		//vetor de checkboxes de entrada sendo criado
		checkbox = new JCheckBox[gate.size()];
		//vetor de sources sendo criado
		sources = new Source[gate.size()];
		//checkbox de sa�da sendo criado
		checkboxS = new JCheckBox();//Sa�da
		
		//JLabel - texto fixo para definir o que significam os bot�es que 
		//est�o abaixo
		JLabel entrada = new JLabel("Entrada");
		JLabel saida = new JLabel("Sa�da");

		// Leyout - um abaixo do outro
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Adiciona a label de entrada � tela
		add(entrada);
		//for que coloca, na tela, os checkboxes de entrada e os sources de acordo
		// com o size da porta selecionada
		for(int i = 0; i < gate.size(); i++){
			JCheckBox checkboxG = new JCheckBox();
			checkboxG.setSelected(false);
			add(checkboxG);
			//os checkboxes de entrada devem ser "escutados"
			checkboxG.addItemListener(this);
			//vetor de checkboxes recebendo um checkbox de entrada
			checkbox[i] = checkboxG;
			//vetor de sources recebendo um source
			Source source = new Source();
			sources[i] = source;
			
			//setamos o gate j� no in�cio conectando os sources aos pinos
			//correspondentes
			gate.connect(i,sources[i]);
		}
		
		//label de sa�da adicionado a tela
		add(saida);
		//checkbox de sa�da adicionado a tela
		add(checkboxS);
		
		
	

		
		

		//Para que a porta correspoda ao estado dos pinos desde o in�cio
		update();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
        Object event = e.getItemSelectable();
        
        //se o item em quest�o estiver selecionado a source correspondente deve 
        //ser true
        if (e.getStateChange() == ItemEvent.SELECTED) {
        	for(int i = 0; i < gate.size(); i++ ){
            	if (event == checkbox[i]) {
                    sources[i].turn(true);
            	}
        	}
        	
        	
        	
        }
 
        //se o item em quest�o estiver "deselecionado" a source correspondente deve 
        //ser false
        if (e.getStateChange() == ItemEvent.DESELECTED) {
        	for(int i = 0; i < gate.size(); i++ ){
        		if (event == checkbox[i]) {
        			sources[i].turn(false);
        	
        		}
        	}
        }
        //update para que a mudan�a do usu�rio seja registrada
		update();
	}


	// M�todo que usa conect e read dos gates para mostrar o resultado.
	// Ele tamb�m atualiza mudan�as que o usu�rio fa�a, ou seja, se ele
	//troca a conex�o dos pinos.
	private void update() {
		for(int i = 0; i < gate.size(); i++ ){
			gate.connect(i,sources[i]);
				
		}

		
		//Diz para o checkbox de sa�da o que deve ser feito (true ou false)
		//da leitura do gate com os pinos de entrada
		checkboxS.setSelected(gate.read());
	
	}

	


}
