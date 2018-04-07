package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

public class GateType extends JPanel implements ActionListener, ItemListener{

	//Linha que ainda não entendemos
	private static final long serialVersionUID = 1L;

	//Gate que será lido caso seja selecionado no menu
	private Gate gate;
	
	
	//checkbox
	private JCheckBox checkbox1;
	private JCheckBox checkbox2;
	private JCheckBox checkbox3;
	
	//dois souurces - acho que deveriamos usar apenas uma e deficir com o pinIndex
	//como ela será ligada. Todas as tentativas até agora falharam
	Source source1 = new Source();
	Source source2 = new Source();
	//int pinIndex; - deveriamos usar isso


	public GateType(Gate gate) {
		this.gate = gate;
		
		//JLabel - texto fixo para definir o que significam os botões que 
		//estão abaixo
		JLabel entrada = new JLabel("Entrada");
		JLabel saida = new JLabel("Saída");
		
		//Chackboxes para leitura de booleanos
		checkbox1 = new JCheckBox();//entrada1
		checkbox2 = new JCheckBox();//entrada2
		checkbox3 = new JCheckBox();//saída
		 
		//Criamos os checkboxes com nomes (.setText) e não selecionaods a princípio
		checkbox1.setText("A");
		checkbox1.setSelected(false);
		checkbox2.setText("B");
		checkbox2.setSelected(false);
		//checkbox3.setText("Saída");
		checkbox3.setSelected(false);

		// Leyout - um abaixo do outro
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		// Define que o segundo checkbox só vai ser usado se o size não for 1
		if (gate.size() == 1){
			
			// Adiciona todas as componentes a este subpainel.
			add(entrada);
			add(checkbox1);
			add(saida);
			add(checkbox3);
			checkbox2.setEnabled(false);
			checkbox2.setSelected(true);
			
		}
		else{
			
			// Adiciona todas as componentes a este subpainel.
			add(entrada);
			add(checkbox1);
			add(checkbox2);
			add(saida);
			add(checkbox3);
			// Estabelece que este subpainel reage ao usuário 
			checkbox2.addItemListener(this);
			checkbox2.addActionListener(this);
			
		}
		
		// Estabelece que este subpainel reage ao usuário 
		checkbox1.addActionListener(this);
		checkbox1.addItemListener(this);

		// Estabelece que a terceira checkbox desativado, ela mostra o resultado.
		checkbox3.setEnabled(false);
		
		//setamos o gate já no início
		gate.connect(0,source1);
		gate.connect(1,source2);

		//Para que a porta correspoda aos pinos desde o início
		update();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
        Object event = e.getItemSelectable();
        
        //se o item em questão estuver selecionado
        if (e.getStateChange() == ItemEvent.SELECTED) {
        	
        	//se for a chechbox 1 usaremos o primeiro pino
        	if (event == checkbox1) {
                source1.turn(true);
                
            //se for a checkbox2 usaremos o segundo pino
            } else if (event == checkbox2) {
                source2.turn(true);
            }
        	
        }
 
        //se o item em questão for "deselecionado"
        if (e.getStateChange() == ItemEvent.DESELECTED) {
        	//se for a chechbox 1 usaremos o primeiro pino
        	if (event == checkbox1) {
                source1.turn(false);
                
              //se for a chechbox 2 usaremos o primeiro pino
            } else if (event == checkbox2) {
                source2.turn(false);
            }
        	
        }
        //update para que a mudança do usuário seja registrada
		update();
	}


	// Método que usa conect e read dos gates para mostrar o resultado.
	// Ele também atualiza mudanças que o usuário faça, ou seja, se ele
	//troca a conecção dos pinos.
	private void update() {

		try {
			if (gate.size() == 1){
				gate.connect(0,source1);
				
			}
			else{
				gate.connect(0,source1);
				gate.connect(1,source2); 
				
			}
			
		}
		
		//não sei como usar o que pra que servirá para nós isso, já que não tem como 
		//dar erro nos booleanos
		catch(NumberFormatException exception) {
			return;
		}
		checkbox3.setSelected(gate.read());
		

		
	}


	// MÃ©todo exigido pela interface ActionListener, que representa
	// a reaÃ§Ã£o a uma digitaÃ§Ã£o do usuÃ¡rio nos dois primeiros campos.
	
	//será que podemos apagar isso, já que não estamo usando?
	@Override
	public void actionPerformed(ActionEvent event) {
		//update();
		
	}



}
