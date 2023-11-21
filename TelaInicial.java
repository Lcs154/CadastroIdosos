package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField senha;
	private JTextField admin;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 // Create the frame.
	 
	public TelaInicial() {
		setTitle("Tela de Login Admin");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 445);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(90, 127, 99, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(90, 192, 99, 34);
		contentPane.add(lblNewLabel_1);
		
		senha = new JPasswordField();
		senha.setBounds(90, 225, 165, 28);
		contentPane.add(senha);
		
		admin = new JTextField();
		admin.setBounds(90, 161, 165, 28);
		contentPane.add(admin);
		admin.setColumns(10);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setBackground(new Color(128, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(checkLogin(admin.getText(), new String(senha.getPassword()))) {
					Opcoes vc = new Opcoes();
					setVisible(false);
					vc.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Usuario ou senha incorretos!");
				}
			}
		});
		btnNewButton.setBounds(127, 294, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Login Admin");
		lblNewJgoodiesTitle.setForeground(new Color(0, 0, 255));
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		lblNewJgoodiesTitle.setBounds(46, 64, 255, 34);
		contentPane.add(lblNewJgoodiesTitle);
	}
	// Escolha um nome e senha para login
	public boolean checkLogin(String login, String senha) {
		return login.equals("usuario") && senha.equals("senha123");
	}
}
