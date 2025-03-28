package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.Game.percent;

public class List_Locations extends JFrame implements ActionListener {
    public String selectedRow;
    public String selectedColumn;
    private JComboBox<String> firstComboBox;
    private JComboBox<String> secondComboBox;
    String[] row = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    String[] column1_11 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    String[] column2_10 = {"1", "2", "3", "4"};
    String[] column3_9 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
    String[] column4_8 = {"1", "2", "3", "4", "5"};
    String[] column5_7 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
    String[] column6 = {"1", "2", "3", "4", "5", "6"};

    public List_Locations(String text, boolean possibleToCancel) {
        super("");
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, (int) (40 * percent)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstComboBox = new JComboBox<>(row);
        firstComboBox.addActionListener(this);
        secondComboBox = new JComboBox<>(column1_11);
        secondComboBox.addActionListener(this);
        setLayout(new FlowLayout());
        add(label);
        add(firstComboBox);
        add(secondComboBox);
        JButton b = new JButton("Cancel");
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setActionCommand("Cancel");
        b.addActionListener(this);
        if (possibleToCancel) add(b);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cancel")) {
            selectedRow = "0";
            selectedColumn = "0";
            dispose();
        };
        if (selectedRow != firstComboBox.getSelectedItem()) {
            selectedRow = (String) firstComboBox.getSelectedItem();
            switch (selectedRow) {
                case "1", "11" -> secondComboBox.setModel(new DefaultComboBoxModel<>(column1_11));
                case "2", "10" -> secondComboBox.setModel(new DefaultComboBoxModel<>(column2_10));
                case "3", "9" -> secondComboBox.setModel(new DefaultComboBoxModel<>(column3_9));
                case "4", "8" -> secondComboBox.setModel(new DefaultComboBoxModel<>(column4_8));
                case "5", "7" -> secondComboBox.setModel(new DefaultComboBoxModel<>(column5_7));
                case "6" -> secondComboBox.setModel(new DefaultComboBoxModel<>(column6));
            }
        }
        else {
            selectedColumn = (String) secondComboBox.getSelectedItem();
            dispose();
        }
    }


}
