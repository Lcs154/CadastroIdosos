package view;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import java.time.LocalDate;
import java.time.Period;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JSlider;
import java.awt.Panel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.List;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTable;
import javax.swing.JLayeredPane;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
    
public class CadPacientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JTextField number;
	private JTextField room;
	private  JTextField dataNasc;
	private  JTextField idade;
	private JTextArea doencas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	static Connection connect = null;
    static Statement statement = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    
    public static void insert(String name, String birth, int age, String room, String contato, String numero, String doenca) {
        String url = "jdbc:mysql://localhost:3306/elder_admin";
        String usuario = "root";
        String senha = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            String queryElder = ("insert into elder(name,birthday,sala,age,email,numRes,doencas) VALUES ('"+ name + "','" + birth +"','" +room + "',"+age+",'"+contato +"','" +numero+"','"+doenca+"')");
            preparedStatement = conexao.prepareStatement(queryElder); // comunica oq foi solicitado
            //preparedStatement.setString(1, name);

            int rowsAffected = preparedStatement.executeUpdate();	// executa o que foi solicitado (funciona para insert,update e delete)

            conexao.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadPacientes frame = new CadPacientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CadPacientes() {
		setResizable(false);
		setTitle("Tela de cadastro de idosos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1113, 560);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(94, 148, 52, 20);
		contentPane.add(lblNewLabel);
		
		name = new JTextField();
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setLabelFor(name);
		name.setBounds(144, 151, 237, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel data_de_nascimento = new JLabel("Data de nascimento:");
		data_de_nascimento.setForeground(new Color(0, 0, 0));
		data_de_nascimento.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		data_de_nascimento.setBounds(94, 179, 147, 20);
		contentPane.add(data_de_nascimento);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Cadastro de Idosos");
		lblNewJgoodiesTitle.setForeground(new Color(0, 0, 255));
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 25));
		lblNewJgoodiesTitle.setBounds(346, 22, 367, 41);
		contentPane.add(lblNewJgoodiesTitle);
		
		JLabel lblEmailDoResponsvel = new JLabel("Email:");
		lblEmailDoResponsvel.setForeground(new Color(0, 0, 0));
		lblEmailDoResponsvel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblEmailDoResponsvel.setBounds(556, 172, 46, 20);
		contentPane.add(lblEmailDoResponsvel);
		
		email = new JTextField();
		email.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailDoResponsvel.setLabelFor(email);
		email.setColumns(10);
		email.setBounds(601, 175, 333, 20);
		contentPane.add(email);
		
		JLabel lblNewLabel_1_1 = new JLabel("N° do reponsável:");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(557, 209, 123, 20);
		contentPane.add(lblNewLabel_1_1);
		
		number = new JTextField();
		number.setHorizontalAlignment(SwingConstants.RIGHT);
		number.setToolTipText("Número do responsável");
		lblNewLabel_1_1.setLabelFor(number);
		number.setColumns(10);
		number.setBounds(680, 212, 254, 20);
		contentPane.add(number);
		
		JLabel possui = new JLabel("Possui doenças?");
		possui.setForeground(new Color(0, 0, 0));
		possui.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		possui.setBounds(94, 290, 112, 23);
		contentPane.add(possui);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Quarto do Idoso:");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(94, 241, 123, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		room = new JTextField();
		room.setHorizontalAlignment(SwingConstants.RIGHT);
		room.setToolTipText("O quarto em que o idoso ficará hospedado");
		lblNewLabel_1_1_1.setLabelFor(room);
		room.setBounds(214, 244, 167, 20);
		contentPane.add(room);
		room.setColumns(10);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Sobre o Cliente");
		lblNewJgoodiesLabel.setForeground(new Color(0, 0, 94));
		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewJgoodiesLabel.setBounds(124, 106, 167, 20);
		contentPane.add(lblNewJgoodiesLabel);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Contato do Responsável");
		lblNewJgoodiesLabel_1.setForeground(new Color(0, 0, 94));
		lblNewJgoodiesLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewJgoodiesLabel_1.setBounds(616, 106, 222, 20);
		contentPane.add(lblNewJgoodiesLabel_1);
		
		
		doencas = new JTextArea();
		
		
		JRadioButton sim = new JRadioButton("Sim");
		sim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				doencas.setVisible(true);
				doencas.setEnabled(true);
				doencas.setEditable(true);
				doencas.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
				doencas.setLineWrap(true);
				doencas.setToolTipText("Insira as doenças");
				doencas.setBounds(94, 353, 287, 122);
				contentPane.add(doencas);
				contentPane.repaint();
				String doenca = doencas.getText();
			}
		});
		buttonGroup.add(sim);
		sim.setForeground(new Color(0, 0, 0));
		sim.setToolTipText("Marque essa opção somente se o idoso for doente, depois, informe as doenças");
		sim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		sim.setBackground(new Color(192, 192, 192));
		sim.setBounds(94, 323, 52, 23);
		contentPane.add(sim);
		
		JRadioButton nao = new JRadioButton("Não");
		nao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doencas.setText("");
				doencas.setVisible(false);
				doencas.setEnabled(false);
				doencas.setEditable(false);
				doencas.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
				doencas.setLineWrap(true);
				doencas.setToolTipText("Insira as doenças");
				doencas.setBounds(94, 353, 287, 122);
				contentPane.add(doencas);
			}
		});
		buttonGroup.add(nao);
		nao.setForeground(new Color(0, 0, 0));
		possui.setLabelFor(nao);
		nao.setToolTipText("Marque essa opção caso o idoso não possua doenças");
		nao.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		nao.setBackground(new Color(192, 192, 192));
		nao.setBounds(148, 323, 52, 23);
		contentPane.add(nao);		
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Opcoes vc = new Opcoes();
				setVisible(false);
				vc.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(24, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Consultar Clientes");
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consulta c = new Consulta();
				setVisible(false);
				c.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(933, 11, 138, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Idade:");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(94, 209, 52, 21);
		contentPane.add(lblNewLabel_1);
		
		dataNasc = new JTextField();
		dataNasc.setHorizontalAlignment(SwingConstants.RIGHT);
		dataNasc.setColumns(10);
		dataNasc.setBounds(234, 182, 147, 20);
		contentPane.add(dataNasc);
		
		idade = new JTextField();
		idade.setHorizontalAlignment(SwingConstants.RIGHT);
		idade.setEditable(false);
		idade.setBounds(144, 212, 237, 20);
		contentPane.add(idade);
		idade.setColumns(10);
		Idade();
		
		JButton btnNewButton = new JButton("Salvar");			// btnSalvar
		btnNewButton.setEnabled(true);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (name.getText().isEmpty() || email.getText().isEmpty() || number.getText().isEmpty() || room.getText().isEmpty() || dataNasc.getText().isEmpty() || idade.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
		            
		        } else {
		            int idadeInt = Integer.parseInt(idade.getText());
		            
		            if (idadeInt < 65) {
		                JOptionPane.showMessageDialog(null, "O cliente precisa ter pelo menos 65 anos!");
		                
		            } else {
		            	String name1 = name.getText();
		            	String birth = dataNasc.getText();
		            	int age = Integer.parseInt(idade.getText());
		            	String sala = room.getText();
		            	String contato = email.getText();
		            	String numRes = number.getText();
		            	String texto = doencas.getText();
		            	
		            	insert(name1, birth, age, sala, contato, numRes, texto);
		            	
		                name.setText("");
		                email.setText("");
		                number.setText("");
		                room.setText("");
		                dataNasc.setText("");
		                idade.setText("");
		                doencas.setText("");
		                JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
		                
		            }
		        }
			}
		});
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 15));
		btnNewButton.setBounds(485, 452, 123, 23);
		contentPane.add(btnNewButton);
	}
	
	public void Idade() {
	    dataNasc.getDocument().addDocumentListener(new DocumentListener() {
	        public void insertUpdate(DocumentEvent e) {
	            atualizarDivisao(dataNasc.getText());
	        }
	        public void removeUpdate(DocumentEvent e) {
	            atualizarDivisao(dataNasc.getText());
	        }
	        public void changedUpdate(DocumentEvent e) {
	            idade.setText(atualizarDivisao(dataNasc.getText()));
	        }
	    });
	}
	
	    public String atualizarDivisao(String data) {	// calcula a idade do cliente usando a data de nascimento

            String[] parte = data.split("/");
         
            if (parte.length == 3) {
                int dia = Integer.parseInt(parte[0]);	// pega o dia
                int mes = Integer.parseInt(parte[1]);	// pega o mes
                int ano = Integer.parseInt(parte[2]);	// pega o ano

                LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
                LocalDate dataAtual = LocalDate.now();
                Period periodo = Period.between(dataNascimento, dataAtual);

                int anos = periodo.getYears();
                
                if (anos < 0) {
                	idade.setText("0");
                	return "0";
                } else {
                	idade.setText(String.valueOf(anos));
                	return String.valueOf(anos);
                	
                }
            }
            return "0";
	}
}
