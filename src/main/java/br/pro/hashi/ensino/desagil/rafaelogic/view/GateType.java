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

	//Linha que ainda não entendemos
	private static final long serialVersionUID = 1L;

	//Gate que será lido caso seja selecionado no menu
	private Gate gate;
	JCheckBox[] checkbox;
	Source[] sources;
	JCheckBox checkboxS;
	
	//dois sources - acho que deveríamos usar apenas uma e definir com o pinIndex
	//como ela será ligada. Todas as tentativas até agora falharam
	
	// Estes nomes aqui sÃ£o auto-explicativos, nÃ£o?
	private Color color;
	private Image image;


	public GateType(Gate gate) {
		
		super(245, 300);
		
		// Inicializamos esse atributo com o preto.
		color = Color.BLACK;
		
		//define qual porta está sendo usada
		this.gate = gate;
		//vetor de checkboxes de entrada sendo criado
		checkbox = new JCheckBox[gate.size()];
		//vetor de sources sendo criado
		sources = new Source[gate.size()];
		//checkbox de saída sendo criado
		checkboxS = new JCheckBox();//Saída
		
		//JLabel - texto fixo para definir o que significam os botões que 
		//estão abaixo
		JLabel entrada = new JLabel("Entrada");
		JLabel saida = new JLabel("Saída");

		// Leyout - um abaixo do outro
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Adiciona a label de entrada à tela
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
			
			//setamos o gate já no início conectando os sources aos pinos
			//correspondentes
			gate.connect(i,sources[i]);
		}
		
		//label de saída adicionado a tela
		add(saida,170,20,50,20);
		//checkbox de saída adicionado a tela
		//add(checkboxS,180,130,20,20);
		
		
	

		
		

		//Para que a porta correspoda ao estado dos pinos desde o início
		update();
		

		// Usamos isso no Projeto 1, vocÃªs lembram?
		String path = "/" + gate.toString() + ".png";
		URL url = getClass().getResource(path);
		image = new ImageIcon(url).getImage();
		
		// Toda componente Swing possui este mÃ©todo, que
		// permite adicionar objetos que reagem a eventos
		// de mouse que acontecem nela. Ou seja, ao passar
		// this como parÃ¢metro, estamos pedindo para a
		// componente reagir aos prÃ³prios eventos de mouse.
		addMouseListener(this);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
        Object event = e.getItemSelectable();
        
        //se o item em questão estiver selecionado a source correspondente deve 
        //ser true
        if (e.getStateChange() == ItemEvent.SELECTED) {
        	for(int i = 0; i < gate.size(); i++ ){
            	if (event == checkbox[i]) {
                    sources[i].turn(true);
            	}
        	}
        	
        	
        	
        }
 
        //se o item em questão estiver "deselecionado" a source correspondente deve 
        //ser false
        if (e.getStateChange() == ItemEvent.DESELECTED) {
        	for(int i = 0; i < gate.size(); i++ ){
        		if (event == checkbox[i]) {
        			sources[i].turn(false);
        	
        		}
        	}
        }
        //update para que a mudança do usuário seja registrada
		update();
	}


	// Método que usa conect e read dos gates para mostrar o resultado.
	// Ele também atualiza mudanças que o usuário faça, ou seja, se ele
	//troca a conexão dos pinos.
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

		// NÃ£o podemos esquecer desta linha, pois nÃ£o somos os
		// Ãºnicos responsÃ¡veis por desenhar o painel, como era
		// o caso no Projeto 1. Agora Ã© preciso desenhar tambÃ©m
		// componentes internas, e isso Ã© feito pela superclasse.
		super.paintComponent(g);

		// Desenha a imagem passando posiÃ§Ã£o e tamanho.
		// O Ãºltimo parÃ¢metro pode ser sempre null.
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
