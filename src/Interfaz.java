import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField caja_entrada;
	private JTextArea caja_P;
	private JTextField caja_res;
	private JComboBox combo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 607, 376);
		contentPane.add(tabbedPane);
		
		JPanel panel_MT = new JPanel();
		panel_MT.setBackground(new Color(60, 179, 113));
		tabbedPane.addTab("Maquina de Turing", null, panel_MT, null);
		panel_MT.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Maquina de Turing");
		lblNewLabel.setFont(new Font("Stencil", Font.ITALIC, 18));
		lblNewLabel.setBounds(10, 11, 319, 40);
		panel_MT.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Entrada: ");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 62, 75, 14);
		panel_MT.add(lblNewLabel_1);
		
		caja_entrada = new JTextField();
		caja_entrada.setBounds(95, 59, 137, 20);
		panel_MT.add(caja_entrada);
		caja_entrada.setColumns(10);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo.getSelectedIndex()==1) {
					String estados="q0,0q1xD,yq5yD\r\n"
						+ "q1,0q10D,1q2yD,yq1yD\r\n"
						+ "q2,0q3zD,1q21D,zq2zD\r\n"
						+ "q3,0q30D,bq4bI\r\n"
						+ "q4,0q40I,1q41I,xq0xD,yq4yI,zq4zI\r\n"
						+ "q5,yq5yD,zq6zD\r\n"
						+ "q6,zq6zD,bq7bP";
				
				char vacio = 'b';
				String cadena = caja_entrada.getText();
				int indice = 0;
				String siguienteNodo = "q0";
				String nodosDeAceptacion = "q7";
				String conjuntoDeNodos = estados;
				
				caja_P.setText("");
				MT m1 = new MT(vacio,cadena,indice,siguienteNodo,nodosDeAceptacion,conjuntoDeNodos);
				
				m1.analizarCadena(cadena,caja_P,caja_res);
				}else if(combo.getSelectedIndex()==2) {
					//se cargan los estados que contendra la MT
					String estados="q0,0q1xD\r\n"
						+ "q1,0q2yD\r\n"
						+ "q2,0q30D,1q41D\r\n"
						+ "q3,0q20D\r\n"
						+ "q4,1q51D\r\n"
						+ "q5,1q41D,bq6bI,zq6zI\r\n"
						+ "q6,1q7wI\r\n"
						+ "q7,1q8zI\r\n"
						+ "q8,yq9yP,1q101I\r\n"
						+ "q10,0q110I,1q101I\r\n"
						+ "q11,0q110I,yq0yD";
				
				char vacio = 'b';
				String cadena = caja_entrada.getText();
				int indice = 0;
				String siguienteNodo = "q0";
				String nodosDeAceptacion = "q7";
				String conjuntoDeNodos = estados;
				
				caja_P.setText("");
				MT m1 = new MT(vacio,cadena,indice,siguienteNodo,nodosDeAceptacion,conjuntoDeNodos);
				
				m1.analizarCadena(cadena,caja_P,caja_res);}
				
				
				
			}
		});
		btnNewButton.setBounds(269, 58, 89, 23);
		panel_MT.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 115, 422, 207);
		panel_MT.add(scrollPane);
		
		caja_P = new JTextArea();
		scrollPane.setViewportView(caja_P);
		
		caja_res = new JTextField();
		caja_res.setEnabled(false);
		caja_res.setColumns(10);
		caja_res.setBounds(95, 90, 137, 20);
		panel_MT.add(caja_res);
		
		JLabel lblNewLabel_1_1 = new JLabel("Salida");
		lblNewLabel_1_1.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 90, 75, 14);
		panel_MT.add(lblNewLabel_1_1);
		
		combo = new JComboBox();
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		combo.setModel(new DefaultComboBoxModel(new String[] {"Selecciona la maquina de Turing que deseas usar", "MT 1", "MT 2"}));
		combo.setBounds(269, 21, 283, 20);
		panel_MT.add(combo);
		
		JPanel panel_maquina1 = new JPanel();
		panel_maquina1.setBackground(new Color(100, 149, 237));
		tabbedPane.addTab("Maquina 1", null, panel_maquina1, null);
		panel_maquina1.setLayout(null);
		
		ImageIcon iconito=new ImageIcon(Interfaz.class.getResource("/Imagenes/MT_1.png"));
		
		JLabel lblNewLabel_3 = new JLabel("Tabla de tranciciones");
		lblNewLabel_3.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(314, 11, 278, 33);
		panel_maquina1.add(lblNewLabel_3);
		
		JLabel tabla_tranciciones_1 = new JLabel("New label");
		tabla_tranciciones_1.setBounds(326, 81, 266, 256);
		
		ImageIcon iconito3=new ImageIcon(Interfaz.class.getResource("/Imagenes/Tabla_1.png"));
		tabla_tranciciones_1.setIcon(resizeIcon(iconito3,tabla_tranciciones_1));
		
		panel_maquina1.add(tabla_tranciciones_1);
		
		JLabel MT_1 = new JLabel("New label");
		MT_1.setBounds(10, 58, 266, 279);
		ImageIcon iconito1=new ImageIcon(Interfaz.class.getResource("/Imagenes/MT_1.png"));
		MT_1.setIcon(resizeIcon(iconito1,MT_1));
		
		panel_maquina1.add(MT_1);
		
		JPanel panel_maquina2 = new JPanel();
		panel_maquina2.setBackground(new Color(60, 179, 113));
		tabbedPane.addTab("Maquina 2", null, panel_maquina2, null);
		panel_maquina2.setLayout(null);
		
		JLabel MT_2 = new JLabel("New label");
		MT_2.setBounds(10, 58, 266, 279);
		MT_2.setBounds(10, 58, 266, 279);
		ImageIcon iconito2=new ImageIcon(Interfaz.class.getResource("/Imagenes/MT_2.png"));
		MT_2.setIcon(resizeIcon(iconito2,MT_2));
		panel_maquina2.add(MT_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("Tabla de tranciciones");
		lblNewLabel_3_1.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		lblNewLabel_3_1.setBounds(314, 11, 278, 33);
		panel_maquina2.add(lblNewLabel_3_1);
		
		JLabel tabla_tranciciones_2 = new JLabel("New label");
		tabla_tranciciones_2.setBounds(326, 81, 266, 256);
		
		ImageIcon iconito4=new ImageIcon(Interfaz.class.getResource("/Imagenes/Tabla_2.png"));
		tabla_tranciciones_2.setIcon(resizeIcon(iconito3,tabla_tranciciones_1));
		
		panel_maquina2.add(tabla_tranciciones_2);
		
	}
	private Icon resizeIcon(ImageIcon icon,JLabel label) {
	    Image img = icon.getImage();
	    Image resizedImage = img.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImage);
	}
	
}
