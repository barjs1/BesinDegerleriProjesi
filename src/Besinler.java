import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class Besinler extends JFrame {
	
	
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField besintxt;
	private JTextField enerjitxt;
	private JTextField karbontxt;
	private JTextField proteintxt;
	private JTextField yagtxt;
	private JTable table;
	
	

	/**
	 * Launch the application.
	 */
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Besinler frame = new Besinler();
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
	
	
	
	 private void tabloyuDoldur() {
	        try {
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/user", "root", "123456");
	            String sql = "SELECT * FROM besins";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                 ResultSet resultSet = preparedStatement.executeQuery()) {

	                DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
	                tblModel.setRowCount(0); // Tabloyu temizle

	                while (resultSet.next()) {
	                    String besin = resultSet.getString("besin");
	                    String enerji = resultSet.getString("enerji");
	                    String karbonhidrat = resultSet.getString("karbonhidrat");
	                    String protein = resultSet.getString("protein");
	                    String yag = resultSet.getString("yag");

	                    String[] data = {besin, enerji, karbonhidrat, protein, yag};
	                    tblModel.addRow(data);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Veri çekme işlemi sırasında hata oluştu: " + e.getMessage());
	        }
	    }
	
	public Besinler() {
		  
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl = new JLabel("Besin Değerleri Uygulaması");
		lbl.setFont(new Font("Unispace", Font.PLAIN, 40));
		lbl.setBounds(10, 32, 686, 86);
		contentPane.add(lbl);
		
		JLabel besin = new JLabel("Besin Adı :");
		besin.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
		besin.setBounds(10, 154, 144, 38);
		contentPane.add(besin);
		
		JLabel enerji = new JLabel("Enerji :");
		enerji.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 25));
		enerji.setBounds(10, 203, 144, 38);
		contentPane.add(enerji);
		
		JLabel karbon = new JLabel("Karbonhidrat:");
		karbon.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
		karbon.setBounds(10, 252, 186, 38);
		contentPane.add(karbon);
		
		JLabel protein = new JLabel("Protein :");
		protein.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
		protein.setBounds(10, 301, 144, 38);
		contentPane.add(protein);
		
		JLabel yag = new JLabel("Yag :");
		yag.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
		yag.setBounds(10, 350, 144, 38);
		contentPane.add(yag);
		
		besintxt = new JTextField();
		besintxt.setBounds(174, 162, 196, 33);
		contentPane.add(besintxt);
		besintxt.setColumns(10);
		
		enerjitxt = new JTextField();
		enerjitxt.setColumns(10);
		enerjitxt.setBounds(174, 211, 196, 33);
		contentPane.add(enerjitxt);
		
		karbontxt = new JTextField();
		karbontxt.setColumns(10);
		karbontxt.setBounds(174, 260, 196, 33);
		contentPane.add(karbontxt);
		
		proteintxt = new JTextField();
		proteintxt.setColumns(10);
		proteintxt.setBounds(174, 308, 196, 33);
		contentPane.add(proteintxt);
		
		yagtxt = new JTextField();
		yagtxt.setColumns(10);
		yagtxt.setBounds(174, 357, 196, 33);
		contentPane.add(yagtxt);
		
		
		
    

    
		
		
		JButton btn = new JButton("KAYDET");
		btn.setFont(new Font("Impact", Font.PLAIN, 38));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(besintxt.getText().equals("")||enerjitxt.getText().equals("")||karbontxt.getText().equals("")||proteintxt.getText().equals("")
						||yagtxt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "EKSİK BİLGİ GİRİLDİ!!!");
				}else {
					String data[]= {besintxt.getText(),enerjitxt.getText(),karbontxt.getText(),proteintxt.getText(),yagtxt.getText()};
					
					DefaultTableModel tblModel =(DefaultTableModel)table.getModel();
					
					tblModel.addRow(data);
					
					  try {
			                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/baris", "root", "123456");
			                String sql = "INSERT INTO besins (besin, enerji, karbonhidrat, protein, yag) VALUES (?, ?, ?, ?, ?)";
			                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			                    preparedStatement.setString(1, besintxt.getText());
			                    preparedStatement.setString(2, enerjitxt.getText());
			                    preparedStatement.setString(3, karbontxt.getText());
			                    preparedStatement.setString(4, proteintxt.getText());
			                    preparedStatement.setString(5, yagtxt.getText());
			                    preparedStatement.executeUpdate();
			                }
			                JOptionPane.showMessageDialog(null, "BESİN DEĞERLERİ EKLENDİ");

			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Veritabanına kaydetme işlemi sırasında hata oluştu: " + ex.getMessage());
			            }

					
					
					
					
				
					
					JOptionPane.showMessageDialog(null, "BESİN DEĞERLERİ EKLENDİ");
					besintxt.setText("");	
					enerjitxt.setText("");	
					karbontxt.setText("");	
					proteintxt.setText("");
					yagtxt.setText("");	
					 
					
					
				}
				
				
			}
		});
		
		
		
		btn.setBounds(209, 428, 161, 81);
		contentPane.add(btn);
		 
		
		JPanel panel = new JPanel();
		panel.setBounds(383, 154, 391, 377);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 391, 377);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Besin", "Eneji", "Karbonhidrat", "Protein", "Ya\u011F"
			}
		));
		
		
		JLabel lblNewLabel = new JLabel("Made for Yaren");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(657, 534, 127, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tolga Barış Anlı");
		lblNewLabel_1.setFont(new Font("Mistral", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(620, 11, 222, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnVerileriGuncelle = new JButton("GÜNCELLE");
		btnVerileriGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  tabloyuDoldur1();
			}
		});
		
		btnVerileriGuncelle.setFont(new Font("Impact", Font.PLAIN, 20));
		btnVerileriGuncelle.setBounds(383, 534, 231, 38);
		contentPane.add(btnVerileriGuncelle);
		
		
		
		btnVerileriGuncelle.setFont(new Font("Impact", Font.PLAIN, 32));
		btnVerileriGuncelle.setBounds(10, 428, 161, 81);
		contentPane.add(btnVerileriGuncelle);
		
		JButton btnSil = new JButton("TEMİZLE");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			        int selectedRow = table.getSelectedRow();

			        if (selectedRow == -1) {
			            JOptionPane.showMessageDialog(null, "Lütfen silinecek bir satır seçin.");
			        } else {
			            // Seçili satırı sil
			            String besin = tblModel.getValueAt(selectedRow, 0).toString(); // Seçili satırdaki besin adını al
			            tblModel.removeRow(selectedRow);

			            // Veritabanından da sil
			            try {
			                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/baris", "root", "123456");
			                String sql = "DELETE FROM besins WHERE besin = ?";
			                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			                    preparedStatement.setString(1, besin);
			                    preparedStatement.executeUpdate();
			                }
			                JOptionPane.showMessageDialog(null, "Seçili satır silindi.");
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Veritabanından silme işlemi sırasında hata oluştu: " + ex.getMessage());
			            }
			        }
			    
			}
		});
		btnSil.setBounds(685, 120, 89, 23);
		contentPane.add(btnSil);
		
	
		
	
	}
	private void tabloyuDoldur1() {
	    try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/baris", "root", "123456");
	        String sql = "SELECT * FROM besins";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
	            tblModel.setRowCount(0); // Tabloyu temizle

	            while (resultSet.next()) {
	                String besin = resultSet.getString("besin");
	                String enerji = resultSet.getString("enerji");
	                String karbonhidrat = resultSet.getString("karbonhidrat");
	                String protein = resultSet.getString("protein");
	                String yag = resultSet.getString("yag");

	                String[] data = {besin, enerji, karbonhidrat, protein, yag};
	                tblModel.addRow(data);
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Veri çekme işlemi sırasında hata oluştu: " + ex.getMessage());
	    }
	}
	    }
	
	
	 
