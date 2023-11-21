package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import view.CadPacientes;
import view.Opcoes;

public class Consulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	private JScrollPane scrollPane;
	
	private JTextField txtName;
	private JTextField txtDN;
	private JTextField txtAge;
	private JTextField txtRoom;
	private JTextField txtEmail;
	private JTextField txtNum;
	private JTextField txtD;

	static Connection connect = null;
    static Statement statement = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consulta frame = new Consulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Consulta() {
		setResizable(false);
		setTitle("Consulta de dados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton show_data = new JButton("consultar");
		show_data.setBounds(613, 538, 110, 43);
		show_data.setBackground(new Color(0, 255, 255));
		show_data.setFont(new Font("Tahoma", Font.BOLD, 15));
		show_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				String url = "jdbc:mysql://localhost:3306/elder_admin";
		        String usuario = "root";
		        String senha = "";

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conexao = DriverManager.getConnection(url, usuario, senha);
					statement = conexao.createStatement();
					
					String query = "select * from elder";
					resultSet = statement.executeQuery(query);
					ResultSetMetaData rsmd = resultSet.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];				// imprime o nome das colunas
					for(int i=0; i<cols; i++) {
						colName[i]=rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
					}
					
					String id,name,birthday,age,sala,email,numRes,doencas; // imprime as linhas
					while(resultSet.next()) {
						id = resultSet.getString(1);	
		                name = resultSet.getString(2);
		                birthday = resultSet.getString(3);
		                age = resultSet.getString(4);
		                sala = resultSet.getString(5);
		                email = resultSet.getString(6);
		                numRes = resultSet.getString(7);
		                doencas = resultSet.getString(8);
		                
		                String[] row = {id,name,birthday,age,sala,email,numRes,doencas};
		                model.addRow(row);
					}
					conexao.close();
					
		        } catch (ClassNotFoundException er) {
		            System.err.println("Erro ao carregar o driver JDBC");
		            er.printStackTrace();
		            
		        } catch (SQLException er) {
		            System.err.println("Erro ao conectar ao banco de dados");
		            er.printStackTrace();
		        }
			}
		});
		contentPane.setLayout(null);
		contentPane.add(show_data);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 1324, 351);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(table);		
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setBounds(10, 11, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Opcoes vc = new Opcoes();
				setVisible(false);
				vc.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cadastrar Clientes");
		btnNewButton_2.setBounds(1101, 28, 215, 23);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPacientes cp = new CadPacientes();
				setVisible(false);
				cp.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Consulta de Dados");
		lblNewJgoodiesTitle.setForeground(new Color(0, 0, 255));
		lblNewJgoodiesTitle.setBounds(160, 40, 288, 53);
		lblNewJgoodiesTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));
		contentPane.add(lblNewJgoodiesTitle);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.setBounds(1101, 85, 89, 23);
		btnNewButton.setBackground(new Color(0, 255, 128));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					setTitle("Área de Edição");
					setResizable(false);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setBounds(100, 100, 535, 390);
					contentPane = new JPanel();
					contentPane.setBackground(new Color(192, 192, 192));
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

					setContentPane(contentPane);
					contentPane.setLayout(null);
					
					JLabel idUser = new JLabel("ID do usuario:");
				    idUser.setForeground(new Color(80, 80, 80));
				    idUser.setFont(new Font("Tahoma", Font.BOLD, 15));
				    idUser.setBounds(51, 65, 170, 14);
					contentPane.add(idUser);
					
					if (table.getSelectedRow() != -1) {
					    int selectedRow = table.getSelectedRow();
					    String selectedId = (String) table.getValueAt(selectedRow, 0);
					    
					    String id = selectedId;
					    
					    JLabel numID = new JLabel(selectedId);
					    numID.setForeground(new Color(255, 0, 0));
					    numID.setFont(new Font("Tahoma", Font.BOLD, 15));
					    numID.setBounds(170, 65, 170, 14);
						contentPane.add(numID);
					}
					
					JLabel nome = new JLabel("Nome:");
					nome.setForeground(new Color(0, 0, 0));
					nome.setFont(new Font("Tahoma", Font.BOLD, 15));
					nome.setBounds(51, 100, 170, 14);
					contentPane.add(nome);
					
					JLabel dnasc = new JLabel("Data de nascimento:");
					dnasc.setForeground(new Color(0, 0, 0));
					dnasc.setFont(new Font("Tahoma", Font.BOLD, 15));
					dnasc.setBounds(51, 123, 170, 14);
					contentPane.add(dnasc);
					
					JLabel Age = new JLabel("Idade:");
					Age.setForeground(new Color(0, 0, 0));
					Age.setFont(new Font("Tahoma", Font.BOLD, 15));
					Age.setBounds(51, 146, 170, 14);
					contentPane.add(Age);
					
					JLabel quarto = new JLabel("Sala:");
					quarto.setForeground(new Color(0, 0, 0));
					quarto.setFont(new Font("Tahoma", Font.BOLD, 15));
					quarto.setBounds(51, 169, 170, 14);
					contentPane.add(quarto);
					
					JLabel contatoEmail = new JLabel("Email:");
					contatoEmail.setForeground(new Color(0, 0, 0));
					contatoEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
					contatoEmail.setBounds(51, 192, 170, 14);
					contentPane.add(contatoEmail);
					
					JLabel contatoNum = new JLabel("Numero Responsavel:");
					contatoNum.setForeground(new Color(0, 0, 0));
					contatoNum.setFont(new Font("Tahoma", Font.BOLD, 15));
					contatoNum.setBounds(51, 215, 170, 14);
					contentPane.add(contatoNum);
					
					JLabel doenca = new JLabel("Doenças:");
					doenca.setForeground(new Color(0, 0, 0));
					doenca.setFont(new Font("Tahoma", Font.BOLD, 15));
					doenca.setBounds(51, 238, 170, 19);
					contentPane.add(doenca);
					
					
					txtName = new JTextField();
					txtName.setHorizontalAlignment(SwingConstants.RIGHT);
					txtName.setBounds(251, 100, 210, 20);
					contentPane.add(txtName);
					txtName.setColumns(10);
					
					txtDN = new JTextField();
					txtDN.setHorizontalAlignment(SwingConstants.RIGHT);
					txtDN.setColumns(10);
					txtDN.setBounds(251, 123, 210, 20);
					contentPane.add(txtDN);
					
					txtAge = new JTextField();
					txtAge.setEditable(false);
					txtAge.setHorizontalAlignment(SwingConstants.RIGHT);
					txtAge.setColumns(10);
					txtAge.setBounds(251, 146, 210, 20);
					contentPane.add(txtAge);
					
					txtRoom = new JTextField();
					txtRoom.setHorizontalAlignment(SwingConstants.RIGHT);
					txtRoom.setColumns(10);
					txtRoom.setBounds(251, 169, 210, 20);
					contentPane.add(txtRoom);
					
					txtEmail = new JTextField();
					txtEmail.setHorizontalAlignment(SwingConstants.RIGHT);
					txtEmail.setColumns(10);
					txtEmail.setBounds(251, 192, 210, 20);
					contentPane.add(txtEmail);
					
					txtNum = new JTextField();
					txtNum.setHorizontalAlignment(SwingConstants.RIGHT);
					txtNum.setColumns(10);
					txtNum.setBounds(251, 215, 210, 20);
					contentPane.add(txtNum);
					
					txtD = new JTextField();
					txtD.setHorizontalAlignment(SwingConstants.RIGHT);
					txtD.setColumns(10);
					txtD.setBounds(251, 238, 210, 20);
					contentPane.add(txtD);
					txtD.setToolTipText("Caso o idoso possua alguma doença, digite o nome da doença, caso contrário digite nulo!");
					
					JButton back = new JButton("Voltar");
					back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Consulta c = new Consulta();
							setVisible(false);
							c.setVisible(true);
						}
					});
					back.setBounds(10, 11, 89, 23);
					contentPane.add(back);
					JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Painel Editar");
					lblNewJgoodiesTitle.setForeground(new Color(0, 0, 255));
					lblNewJgoodiesTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 25));
					lblNewJgoodiesTitle.setBounds(263, 11, 170, 27);
					contentPane.add(lblNewJgoodiesTitle);
					Idade();
					
					JButton alterar = new JButton("Salvar alterações");
					alterar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (txtName.getText().isEmpty() || txtDN.getText().isEmpty() || txtAge.getText().isEmpty() || txtRoom.getText().isEmpty() || txtEmail.getText().isEmpty() || txtNum.getText().isEmpty() || txtD.getText().isEmpty()) {
					            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
					            
					        } else {
					            int idadeInt = Integer.parseInt(txtAge.getText());
					            
					            if (idadeInt < 65) {
					                JOptionPane.showMessageDialog(null, "O cliente precisa ter pelo menos 65 anos!");
					                
					            } else {
					            	String name = txtName.getText();
					            	String birth = txtDN.getText();
					            	int age = Integer.parseInt(txtAge.getText());
					            	String sala = txtRoom.getText();
					            	String contato = txtEmail.getText();
					            	String numRes = txtNum.getText();
					            	String doencas = txtD.getText();
					            	
					            	insert(name, birth, age, sala, contato, numRes, doencas);
					            	
					            	txtName.setText("");
					            	txtDN.setText("");
					            	txtAge.setText("");
					            	txtRoom.setText("");
					            	txtEmail.setText("");
					            	txtNum.setText("");
					            	txtD.setText("");
					            	
					                JOptionPane.showMessageDialog(null, "Dados do cliente atualizados!");
					                
					                Consulta c = new Consulta();
									setVisible(false);
									c.setVisible(true);
					                
					            }
					        }
						}
					});
					alterar.setFont(new Font("Tahoma", Font.BOLD, 13));
					alterar.setBackground(new Color(128, 255, 0));
					alterar.setBounds(191, 301, 160, 23);
					contentPane.add(alterar);
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Selecione uma linha para editar!");
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Excluir");
		btnNewButton_3.setBounds(1227, 85, 89, 23);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "jdbc:mysql://localhost:3306/elder_admin";
		        String usuario = "root";
		        String senha = "";

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conexao = DriverManager.getConnection(url, usuario, senha);
					//statement = conexao.createStatement();
					
					if(table.getSelectedRow() != -1) {
						String query = "select * from elder";
			            Statement statement = conexao.createStatement();
			            ResultSet rs = statement.executeQuery(query);
			            
					    int selectedRow = table.getSelectedRow();
					    String selectedId = (String) table.getValueAt(selectedRow, 0);
					    
					    String id = selectedId;
						int idInt = Integer.parseInt(id);
						
			            String queryElder = ("DELETE FROM elder WHERE id = ?");
			            PreparedStatement ps = conexao.prepareStatement(queryElder); // comunica oq foi solicitado
			            ps.setInt(1, idInt);

			            int rowsAffected = ps.executeUpdate();	// executa o que foi solicitado (funciona para insert,update e delete)
			            
			            DefaultTableModel linha = (DefaultTableModel) table.getModel();
						linha.removeRow(table.getSelectedRow());
						
						JOptionPane.showMessageDialog(null,"Cliente removido!");
					}
					else {
						JOptionPane.showMessageDialog(null,"Selecione uma linha para excluir!");
					}
					
					conexao.close();
		        } catch (ClassNotFoundException er) {
		            System.err.println("Erro ao carregar o driver JDBC");
		            er.printStackTrace();
		        } catch (SQLException er) {
		            System.err.println("Erro ao conectar ao banco de dados");
		            er.printStackTrace();
		        }
				
			}
		});
		btnNewButton_3.setBackground(new Color(255, 66, 66));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btnNewButton_3);
	}
	
	public static void insert(String name, String birth, int age, String room, String contato, String numero, String doenca){
        String url = "jdbc:mysql://localhost:3306/elder_admin";
        String usuario = "root";
        String senha = "";
        
        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String query = "select * from elder";
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            if (table.getSelectedRow() != -1) {
			    int selectedRow = table.getSelectedRow();
			    String selectedId = (String) table.getValueAt(selectedRow, 0);
			    
			    String id = selectedId;
				int idInt = Integer.parseInt(id);
				
	            String queryElder = ("UPDATE elder SET name = ?, birthday = ?, age = ?, sala = ?, email = ?, numRes = ?, doencas = ? WHERE id = ?");
	            PreparedStatement ps = conexao.prepareStatement(queryElder); // comunica oq foi solicitado
	            ps.setString(1, name);
	            ps.setString(2, birth);
	            ps.setInt(3, age);
	            ps.setString(4, room);
	            ps.setString(5, contato);
	            ps.setString(6, numero);
	            ps.setString(7, doenca);
	            ps.setInt(8, idInt);

	            int rowsAffected = ps.executeUpdate();	// executa o que foi solicitado (funciona para insert,update e delete)

	            conexao.close();
			}

        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }
    }
	
	
 public void Idade() {
	    txtDN.getDocument().addDocumentListener(new DocumentListener() {
	        public void insertUpdate(DocumentEvent e) {
	            atualizarDivisao();
	        }
	        public void removeUpdate(DocumentEvent e) {
	            atualizarDivisao();
	        }
	        public void changedUpdate(DocumentEvent e) {
	            atualizarDivisao();
	        }
	        public void atualizarDivisao() {
	            String texto = txtDN.getText();
	            String[] parte = texto.split("/");

	            if (parte.length == 3) {
	                int dia = Integer.parseInt(parte[0]);
	                int mes = Integer.parseInt(parte[1]);
	                int ano = Integer.parseInt(parte[2]);

	                LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
	                LocalDate dataAtual = LocalDate.now();
	                Period periodo = Period.between(dataNascimento, dataAtual);

	                int anos = periodo.getYears();
	                
	                if (anos < 0) {
	                	txtAge.setText("0");
	                } else {
	                	txtAge.setText(String.valueOf(anos));
	                }
	            }
	        }
	    });
	}
}
