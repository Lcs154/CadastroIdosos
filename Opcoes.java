package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Opcoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opcoes frame = new Opcoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Opcoes() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 342);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar Clientes");
		btnNewButton.setBackground(new Color(128, 255, 255));
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPacientes cp = new CadPacientes();
				setVisible(false);
				cp.setVisible(true);
			}
		});
		btnNewButton.setBounds(39, 156, 175, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Consultar Clientes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consulta c = new Consulta();
				setVisible(false);
				c.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(new Color(128, 255, 255));
		btnNewButton_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnNewButton_1.setBounds(224, 156, 175, 37);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Area do Administrador");
		lblNewJgoodiesTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 25));
		lblNewJgoodiesTitle.setBounds(70, 28, 294, 73);
		contentPane.add(lblNewJgoodiesTitle);
		
		JButton btnNewButton_2 = new JButton("Sair");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.setBounds(172, 249, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
