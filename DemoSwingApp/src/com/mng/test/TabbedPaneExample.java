package com.mng.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.mng.utill.BrowserControl;
import com.mng.utill.LoadItems;
import com.mng.utill.UrlGeneration;

public class TabbedPaneExample extends JFrame {

	private static final long serialVersionUID = 1L;  
	
	private JTabbedPane tabbedPane;
	private JPanel accessPanel;
	private JPanel xmlServicePanel;
	private JPanel panel2;
	private JPanel panel3;

	private JLabel jLabel, xmlLabel, jlable2, jlable3, jlable4, jlable5, jlable6, jlable7;
	private JComboBox comboBox1, comboBox2, comboBox3;

	BrowserControl browserControl = null;
	UrlGeneration urlGeneration = null;
	JProgressBar pb;

	JTextArea txt, txt2;
	JScrollPane scrolltxt, scrolltxt2;

	String serviceName;
	JButton goButton;

	// Container c;
	String[] tempArr;
	JTextArea textArea, textArea2;
	String tempUrl;
	String tempServiceName;

	static List<String[]> list = new ArrayList<String[]>();
	static Map<String, String[]> map = new HashMap<String, String[]>();

	public TabbedPaneExample() throws IOException {
		setTitle("Flags Service Tester Apllication");
		setSize(300, 200);
		
	
		setBackground(Color.gray);

		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1500,1600));
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);

		// Create the tab pages
		xmlServicePage();
		accessPage();
		createPage2();
		createPage3();

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("XMl Service", xmlServicePanel);

		tabbedPane.addTab("Access Service", accessPanel);
		tabbedPane.addTab("Page 2", panel2);
		tabbedPane.addTab("Page 3", panel3);
		/*tabbedPane.setBackgroundAt(0,Color.GRAY);
		tabbedPane.setForegroundAt(0,Color.lightGray);
		tabbedPane.setBackgroundAt(1,Color.GRAY);
		tabbedPane.setForegroundAt(1,Color.lightGray);*/
		
		
		
		
		topPanel.add(tabbedPane, BorderLayout.CENTER);
	}

	public void accessPage() throws IOException {
		accessPanel = new JPanel();
		accessPanel.setLayout(null);

		browserControl = new BrowserControl();
		urlGeneration = new UrlGeneration();

		jLabel = new JLabel("Flags Access Service Tester Application");
		jLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		jLabel.setForeground(Color.blue);
		jLabel.setBounds(500, 20, 400, 30);
	
		accessPanel.add(jLabel);

		jlable2 = new JLabel("ServiceNames");
		jlable2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jlable2.setForeground(Color.blue);
		jlable2.setBounds(15, 92, 400, 35);
		accessPanel.add(jlable2);

		jlable3 = new JLabel("TestLevel");
		jlable3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jlable3.setForeground(Color.blue);
		jlable3.setVisible(true);
		jlable3.setBounds(385, 96, 200, 30);
		accessPanel.add(jlable3);

		jlable4 = new JLabel("HostNames");
		jlable4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jlable4.setForeground(Color.blue);
		jlable4.setVisible(false);
		jlable4.setBounds(720, 96, 200, 30);
		accessPanel.add(jlable4);

		serviceComboBox();

		accessPanel.add(jLabel);
		accessPanel.add(jlable2);
		accessPanel.add(jlable4);
		accessPanel.add(comboBox1);
		accessPanel.add(jlable3);
		accessPanel.add(jlable5);
		accessPanel.add(scrolltxt);
		accessPanel.add(comboBox2);
		accessPanel.add(comboBox3);
		accessPanel.add(textArea);
		accessPanel.add(textArea2);
		accessPanel.add(scrolltxt2);
		accessPanel.add(goButton);
		accessPanel.add(jlable6);
		accessPanel.add(pb);

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

		goButton = new JButton("GO");
		goButton.setForeground(Color.blue);
		goButton.setFont(new Font("Dialog", Font.BOLD, 12));
		goButton.setVisible(true);
		goButton.setBounds(1050, 96, 200, 25);

		goButton = new JButton("GO");
		goButton.setForeground(Color.blue);
		goButton.setFont(new Font("Dialog", Font.BOLD, 12));
		goButton.setVisible(false);
		goButton.setBounds(1100, 96, 70, 25);

		pb = new JProgressBar();
		pb.setForeground(Color.BLUE);
		pb.setStringPainted(true);
		pb.setValue(0);
		pb.setString("in progress...");
		pb.setStringPainted(true);
		pb.setBounds(1255, 96, 170, 25);
		pb.setVisible(false);

		jlable5 = new JLabel("Service Staus");
		jlable5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jlable5.setForeground(Color.blue);
		jlable5.setVisible(false);
		jlable5.setBounds(10, 725, 200, 30);

		jlable6 = new JLabel();
		jlable6.setBounds(10, 150, 200, 30);
		jlable6.setVisible(false);

		jlable7 = new JLabel("Progress");
		jlable7.setForeground(Color.blue);
		jlable7.setVisible(true);
		jlable7.setBounds(1150, 9, 200, 30);

		txt = new JTextArea();
		scrolltxt = new JScrollPane(txt);
		scrolltxt.setBounds(10, 750, 1420, 80);
		scrolltxt.setVisible(false);

		textArea = new JTextArea(5, 15);
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED));

		txt2 = new JTextArea();
		scrolltxt2 = new JScrollPane(txt2);
		scrolltxt2.setBounds(10, 175, 1420, 520);
		scrolltxt2.setVisible(false);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				scrolltxt2.getVerticalScrollBar().setValue(0);
			}
		});

		textArea2 = new JTextArea(5, 15);
		textArea2.setBorder(new BevelBorder(BevelBorder.LOWERED));

		//
		// For listening to the changes of the selected items in the combo box
		// we need to add an ItemListener to the combo box component as shown
		// below.
		//
		comboBox1.addItemListener(new ItemListener() {
			//
			// Listening if a new items of the combo box has been selected.
			//
			String temp;

			public void itemStateChanged(ItemEvent event) {
				JComboBox comboBox = (JComboBox) event.getSource();

				// The item affected by the event.
				Object item = event.getItem();
				if (!(item.toString().equalsIgnoreCase("=======SELECT=========="))) {

					temp = item.toString();
					System.out.println("Service Name:  " + temp);

					urlGeneration.setTempUrl(temp);

					System.out.println("Service Name:  " + temp);
				}
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

					comboBox3Logic(map.get(item.toString()), item.toString());

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
					urlGeneration.setHostName(item.toString());
					goButton.setVisible(true);
				}
				System.out.println("combo3 Affected items: " + item.toString());
			}
		});

		goButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doProgressBar(e);

				jlable5.setVisible(true);
				scrolltxt.setVisible(true);
				Border border = BorderFactory.createLineBorder(Color.RED);

				tempUrl = getWsdlUrl(urlGeneration.getHostName(), urlGeneration.getTempUrl());

				System.out.println("Final Wsdl Url:  " + tempUrl);
				String status = browserControl.serviceStaus(tempUrl);

				if (status.contains("Exception")) {
					txt2.setText(browserControl.error);
					txt2.setToolTipText("this Box displayed service status information");
					txt2.setBorder(border);
					txt2.setFont(new Font("Arial", Font.PLAIN, 15));
					txt2.setForeground(Color.BLUE);
					txt2.setBackground(Color.lightGray);
					scrolltxt2.setVisible(true);

					String result = "FAIL: " + urlGeneration.getTempUrl() + " Service Unavailable " + "\n" + "WSDL: "
							+ tempUrl + "\n" + "Exception Details: " + status;

					txt.setText(result);
					txt.setToolTipText("this Box displayed service status information");
					txt.setBorder(border);
					txt.setForeground(Color.BLUE);
					txt.setBackground(Color.orange);
					scrolltxt.setVisible(true);

				} else {

					txt2.setText(browserControl.getResponse());

					jlable6.setFont(new Font("Times New Roman", Font.BOLD, 14));
					jlable6.setText("Wsdl Information" + ":" + urlGeneration.getTempUrl());
					jlable6.setForeground(Color.blue);
					jlable6.setVisible(true);

					txt2.setToolTipText("this Box displayed service status information");
					txt2.setBorder(border);
					txt2.setFont(new Font("Arial", Font.PLAIN, 13));
					txt2.setForeground(Color.RED);
					txt2.setBackground(Color.lightGray);
					scrolltxt2.setVisible(true);
					scrolltxt2.getViewport().setViewPosition(new Point(0, 0));

					String result = "SUCCESS: " + urlGeneration.getTempUrl() + " Service Available" + "\n" + "WSDL: "
							+ tempUrl;

					txt.setText(result);
					txt.setToolTipText("this Box displayed service status information");
					txt.setBorder(border);
					txt.setForeground(Color.BLUE);
					txt.setBackground(Color.GREEN);
					scrolltxt.setVisible(true);
				}
				System.out.println("***********Button url printed**********" + tempUrl);
			}

			private String getWsdlUrl(String hostName, String tempUrl) {

				String endPoints[] = map.get("endPonit");
				String protoCols[] = map.get("protoCol");

				StringBuffer url = new StringBuffer(protoCols[0]);
				url.append(hostName + "/" + tempUrl + "/" + endPoints[0]);

				return url.toString();

			}
		});
	}

	public void doProgressBar(ActionEvent e) {
		int i = 0;
		if (e.getSource() == goButton) {
			accessPanel.add(jlable7);
			pb.setVisible(true);
			try {
				while (i <= 100) {
					Thread.sleep(50);
					pb.paintImmediately(0, 0, 200, 25);
					pb.setValue(i);
					i++;
				}
				pb.setVisible(false);
				jlable7.setVisible(false);
			} catch (Exception e1) {
				System.out.print("Caughted exception is =" + e1);
			}
		}
	}

	public void xmlServicePage() {
		xmlServicePanel = new JPanel();
		xmlLabel = new JLabel("Flags Xml Service Tester Application");
        xmlLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

		xmlLabel.setForeground(Color.blue);
		xmlLabel.setBounds(500, 20, 400, 30);
		xmlServicePanel.add(xmlLabel);
	

	}

	public void createPage2() {
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());

		panel2.add(new JButton("North"), BorderLayout.NORTH);
		panel2.add(new JButton("South"), BorderLayout.SOUTH);
		panel2.add(new JButton("East"), BorderLayout.EAST);
		panel2.add(new JButton("West"), BorderLayout.WEST);
		panel2.add(new JButton("Center"), BorderLayout.CENTER);
	}

	public void createPage3() {
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3, 2));

		panel3.add(new JLabel("Field 1:"));
		panel3.add(new TextArea());
		panel3.add(new JLabel("Field 2:"));
		panel3.add(new TextArea());
		panel3.add(new JLabel("Field 3:"));
		panel3.add(new TextArea());
	}

	// Main method to get things started
	public static void main(String args[]) throws IOException {
		LoadItems loadItems = new LoadItems();
		map = loadItems.loadItems("serviceNames.properties");
		// Create an instance of the test application
		TabbedPaneExample mainFrame = new TabbedPaneExample();
		mainFrame.setVisible(true);
	}
}