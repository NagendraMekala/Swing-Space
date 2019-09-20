package com.mng.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabSample extends JFrame  {
	
	JLabel jlable1;
	Container c;
  static void add(JTabbedPane tabbedPane, String label) {
    JButton button = new JButton(label);
    tabbedPane.addTab(label, button);
  }
  
  public TabSample(){
	  
		c = this.getContentPane();
		c.setForeground(Color.BLUE);
		c.setLayout(null);
  }

  public static void main(String args[]) {
	  
	  TabSample tabs = new TabSample();
	  tabs.doSomething();
  }
  
  public void doSomething(){
	  JFrame frame = new JFrame("Tabbed Pane Sample");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JTabbedPane tabbedPane = new JTabbedPane();

	    String titles[] = { "General", "Security", "Content", "Connection", "Programs", "Advanced" };
	    for (int i = 0, n = titles.length; i < n; i++) {
	      add(tabbedPane, titles[i]);
	    }

	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	    	  
	   
	    	  
	        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
	        int index = sourceTabbedPane.getSelectedIndex();
	        
	        
	   	    System.out.println(sourceTabbedPane.getTitleAt(index));
	   	    
	   		
	      
	        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
	      }
	    };
	    tabbedPane.addChangeListener(changeListener);
	    frame.add(tabbedPane, BorderLayout.CENTER);
	    frame.setSize(400, 150);
	    frame.setVisible(true);
	    jlable1 = new JLabel("TestLevel");
		jlable1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jlable1.setForeground(Color.blue);
		jlable1.setVisible(true);
		jlable1.setBounds(385, 96, 200, 30);
 
	  
  }
}