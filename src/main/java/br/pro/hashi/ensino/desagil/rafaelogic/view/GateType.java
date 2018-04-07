package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

public class GateType extends JPanel implements ActionListener, ItemListener{

	//Linha que ainda n�o entendemos
	private static final long serialVersionUID = 1L;

	//Gate que ser� lido caso seja selecionado no menu
	private Gate gate;

	private JCheckBox checkbox1;
	private JCheckBox checkbox2;


	public GateType(Gate gate) {
		this.gate = gate;
		
		//JLabel - texto fixo para definir o que significam os bot�es que 
		//est�o abaixo
		JLabel entrada = new JLabel("Entrada");
		JLabel saida = new JLabel("Sa�da");
		
		//Chackboxes para leitura de booleanos
		checkbox1 = new JCheckBox();
		checkbox2 = new JCheckBox();
		JCheckBox checkbox3 = new JCheckBox();
		 
		//Criamos os checkboxes com nomes (.setText) e n�o selecionaods a princ�pio
		checkbox1.setText("A");
		checkbox1.setSelected(false);
		checkbox2.setText("B");
		checkbox2.setSelected(false);
		//checkbox3.setText("Sa�da");
		checkbox3.setSelected(false);

		// Leyout - um abaixo do outro
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Adiciona todas as componentes a este subpainel.
		add(entrada);
		add(checkbox1);
		add(checkbox2);
		add(saida);
		add(checkbox3);

		// Estabelece que este subpainel reage ao usu�rio 
		checkbox1.addActionListener(this);
		checkbox1.addItemListener(this);
		checkbox2.addItemListener(this);
		
		// Define que o segundo checkbox s� vai ser usado se o size n�o for 1
		if (gate.size() == 1){
			checkbox2.setEnabled(false);
			
		}
		else{
			checkbox2.setSelected(false);
			checkbox2.addActionListener(this);
			
		}
		
		
		

		// Estabelece que a terceira checkbox desativado, ela mostra o resultado.
		checkbox3.setEnabled(false);

		// Não podemos esquecer de chamar update na inicialização,
		// caso contrário a interface só ficará consistente depois
		// da primeira interação do usuário com os campos de texto.
		// A definição exata do método update é dada logo abaixo.
		update();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Source source = new Source();
		int index = 0;
        Object event = e.getItemSelectable();
        
        if (event == checkbox1) {
            source.turn(true);
            System.out.println(source.read());
            gate.connect(0, source);
        } else if (event == checkbox2) {
            index = 1;
            source.turn(true);
            System.out.println(source.read());
            gate.connect(1, source);
        }
 
        //Now that we know which button was pushed, find out
        //whether it was selected or deselected.
        if (e.getStateChange() == ItemEvent.DESELECTED) {
        	source.turn(false);
        	System.out.println(source.read());
            
        }
        
        
		update();

		
	}


	// M�todo que usa conect e read dos gates para mostrar o resultado.
	// Ele tamb�m atualiza mudan�as que o usu�rio fa�a, ou seja, se ele
	//troca a porta ou a conec��o dos pinos.
	private void update() {

		try {
			

			//radius = Double.parseDouble(radiusField.getText());
		}
		catch(NumberFormatException exception) {
			//resultField.setText(select1);
			return;
		}

		//boolean result = gate.read();
		//
	}


	// Método exigido pela interface ActionListener, que representa
	// a reação a uma digitação do usuário nos dois primeiros campos.
	@Override
	public void actionPerformed(ActionEvent event) {
		
	}



}
