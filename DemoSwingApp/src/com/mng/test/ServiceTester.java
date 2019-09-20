		package com.mng.test;
		
		import java.awt.Color;
		import java.awt.Container;
		import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
		import java.awt.event.ItemListener;
		import java.io.IOException;
		import java.util.ArrayList;
		import java.util.HashMap;
		import java.util.List;
		import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.swing.ComboBoxModel;
		import javax.swing.DefaultComboBoxModel;
		import javax.swing.JComboBox;
		import javax.swing.JFrame;
		import javax.swing.JLabel;
		import javax.swing.JPanel;
		import javax.swing.JTextArea;
		import javax.swing.border.BevelBorder;

import com.mng.utill.LoadItems;
		
		public class ServiceTester extends JFrame {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			JLabel jLabel, jlable2, jlable3, jlable4;
			JComboBox comboBox1, comboBox2, comboBox3;
			Container c;
			String[] tempArr;
			JTextArea textArea;
			String temp;
		
			static List<String[]> list = new ArrayList<String[]>();
			static Map<String, String[]> map = new HashMap<String, String[]>();
		
			public ServiceTester() {
				c = this.getContentPane();
				
				c.setForeground(Color.BLUE);
				c.setLayout(null);
		
				jLabel = new JLabel("Welcome To Service Test Aplication");
				jLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
				jLabel.setForeground(Color.RED);
				jLabel.setBounds(500, 20, 400, 30);
		
				jlable2 = new JLabel("ServiceNames");
				jlable2.setFont(new Font("Times New Roman", Font.BOLD, 18));
				jlable2.setForeground(Color.blue);
				jlable2.setBounds(15, 92, 400, 35);
		
				jlable3 = new JLabel("TestLevel");
				jlable3.setFont(new Font("Times New Roman", Font.BOLD, 18));
				jlable3.setForeground(Color.blue);
				jlable3.setVisible(true);
				jlable3.setBounds(385, 96, 200, 30);
		
				jlable4 = new JLabel("HostNames");
				jlable4.setFont(new Font("Times New Roman", Font.BOLD, 18));
				jlable4.setForeground(Color.blue);
				jlable4.setVisible(false);
				jlable4.setBounds(720, 96, 200, 30);
		
				serviceComboBox();
		
				c.add(jLabel);
				c.add(jlable2);
				c.add(jlable4);
				c.add(comboBox1);
				c.add(jlable3);
				c.add(comboBox2);
				c.add(comboBox3);
				c.add(textArea);
		
			}
		
			private void serviceComboBox() {
		
				comboBox1 = new JComboBox(map.get("serviceList"));
				comboBox2 = new JComboBox(map.get("testLevel"));
				comboBox3 = new JComboBox(map.get("L1"));
		
				comboBox1.setToolTipText("please choose atlest one service");
				comboBox1.setForeground(Color.blue);
				comboBox1.setFont(new Font("Dialog", Font.BOLD, 12));
				comboBox1.setVisible(true);
				comboBox1.setBounds(128, 96, 200, 25);
		
				comboBox2.setToolTipText("please choose atlest one test level");
				comboBox2.setForeground(Color.blue);
				comboBox2.setFont(new Font("Dialog", Font.BOLD, 12));
				comboBox2.setVisible(true);
				comboBox2.setBounds(465, 96, 200, 25);
		
				comboBox3.setToolTipText("please choose atlest one hostName");
				comboBox3.setForeground(Color.blue);
				comboBox3.setFont(new Font("Dialog", Font.BOLD, 12));
				comboBox3.setVisible(false);
				comboBox3.setBounds(815, 96, 200, 25);
		
				textArea = new JTextArea(5, 15);
				textArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
				//
				// For listening to the changes of the selected items in the combo box
				// we need to add an ItemListener to the combo box component as shown
				// below.
				//
				comboBox1.addItemListener(new ItemListener() {
					//
					// Listening if a new items of the combo box has been selected.
					//
					public void itemStateChanged(ItemEvent event) {
						JComboBox comboBox = (JComboBox) event.getSource();
		
						// The item affected by the event.
						Object item = event.getItem();
						if (!(item.toString().equalsIgnoreCase("=======SELECT=========="))) {
		
							temp = item.toString();
							System.out.println("Service Name:  " + temp);
						}
						//System.out.println(" combo 1 Affected items: " + item.toString());
		
					}
				});
		
				comboBox2.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent event) {
						JComboBox comboBox = (JComboBox) event.getSource();
						// The item affected by the event.
						Object item = event.getItem();
		
						if (!item.toString().equalsIgnoreCase("=======SELECT==========")) {
							tempArr = map.get(item.toString());
		
							comboBox3Logic(map.get(item.toString()),item.toString());
		
		
						}
						System.out.println("combo2 Affected items: " + item.toString());
					
					}
		
					private void comboBox3Logic(String[] testLevel, String hostName) {
						ComboBoxModel[] models = new ComboBoxModel[3];
						models[0] = new DefaultComboBoxModel(testLevel);
		
						comboBox3.setModel(models[0]);
						jlable4.setVisible(true);
						comboBox3.setVisible(true);
						
					}
				});
				
				comboBox3.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent event) {
					
						// The item affected by the event.
						Object item = event.getItem();
		
						if (!item.toString().equalsIgnoreCase("=======SELECT==========")) {
							tempArr = map.get(item.toString());
							StringBuffer urls = new StringBuffer("http://");
							
							urls.append(item.toString() + "/" + temp + "/service.wsdl");
							System.out.println("Completed url format:  " + urls);
						
		
						}
						System.out.println("combo3 Affected items: " + item.toString());
						
					}
				});
		
			}
		
			public static void main(String[] args) throws IOException {
		
				LoadItems loadItems = new LoadItems();
				map = loadItems.loadItems("serviceNames.properties");
		
				ServiceTester obj = new ServiceTester();
		
				obj.setTitle("Jframe Demo");
				obj.setSize(300, 300);
				obj.setVisible(true);
		
				obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			}
		}
