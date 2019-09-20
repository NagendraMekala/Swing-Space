package com.mng.test;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboPanel extends JPanel {

    JComboBox jcbo;
    // this is constructor
    public ComboPanel(ArrayList<String> items) {
        jcbo = new JComboBox();

        // getting exiting combo box model
        DefaultComboBoxModel model = (DefaultComboBoxModel) jcbo.getModel();
        // removing old data
        model.removeAllElements();

        for (String item : items) {
            model.addElement(item);
        }

        // setting model with new data
        jcbo.setModel(model);
        // adding combobox to panel
        this.add(jcbo);    
    }
}