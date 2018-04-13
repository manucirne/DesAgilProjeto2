package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.rafaelogic.view.SimplePanel;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

public class GateType extends SimplePanel implements ItemListener, MouseListener{

	//Linha que ainda n�o entendemos
	private static final long serialVersionUID = 1L;

	//Gate que ser� lido caso seja selecionado no menu
	private Gate gate;
	JCheckBox[] checkbox;
	Source[] sources;
	JCheckBox checkboxS;
	
	//dois sources - acho que dever�amos usar apenas uma e definir com o pinIndex
	//como ela ser� ligada. Todas as tentativas at� agora falharam
	
	// Estes nomes aqui são auto-explicativos, não?
	private Color color;
	private Image image;


	public GateType(Gate gate) {
		
		super(245, 300);
		
		// Inicializamos esse atributo com o preto.
		color = Color.BLACK;
		
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
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Adiciona a label de entrada � tela
		add(entrada,20,20,50,20);
		//for que coloca, na tela, os checkboxes de entrada e os sources de acordo
		// com o size da porta selecionada
		for(int i = 0; i < gate.size(); i++){
			JCheckBox checkboxG = new JCheckBox();
			checkboxG.setSelected(false);
			if(gate.size()==1){
				add(checkboxG,20,130,20,20);
				//os checkboxes de entrada devem ser "escutados"
				checkboxG.addItemListener(this);
			}
			else{
				add(checkboxG,20,i*84+88,20,20);
				//os checkboxes de entrada devem ser "escutados"
				checkboxG.addItemListener(this);
			}
			
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
		add(saida,170,20,50,20);
		//checkbox de sa�da adicionado a tela
		//add(checkboxS,180,130,20,20);
		
		
	

		
		

		//Para que a porta correspoda ao estado dos pinos desde o in�cio
		update();
		

		// Usamos isso no Projeto 1, vocês lembram?
		String path = "/" + gate.toString() + ".png";
		URL url = getClass().getResource(path);
		image = new ImageIcon(url).getImage();
		
		// Toda componente Swing possui este método, que
		// permite adicionar objetos que reagem a eventos
		// de mouse que acontecem nela. Ou seja, ao passar
		// this como parâmetro, estamos pedindo para a
		// componente reagir aos próprios eventos de mouse.
		addMouseListener(this);
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

	
	
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g) {

		// Não podemos esquecer desta linha, pois não somos os
		// únicos responsáveis por desenhar o painel, como era
		// o caso no Projeto 1. Agora é preciso desenhar também
		// componentes internas, e isso é feito pela superclasse.
		super.paintComponent(g);

		// Desenha a imagem passando posição e tamanho.
		// O último parâmetro pode ser sempre null.
		g.drawImage(image, 10, 40, 200, 200, null);
		
		if(gate.read()== true){
			g.setColor(Color.RED);
			
		}
		else{
			g.setColor(Color.BLACK);
		}
		g.fillOval(180,130,20,20);
		repaint();
		
		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
		
		update();
    }

	


}
