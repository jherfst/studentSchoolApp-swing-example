package sr.unasat.week6.cijfersysteem.ui;

import sr.unasat.week6.jdbc.dataaccess.VakDatabaseAccess;
import sr.unasat.week6.jdbc.entities.Vak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;


public class AddVakScherm extends JDialog implements ActionListener {


    JTextField naamTxtField = new JTextField();
    JList vakkenListField = new JList();

    JButton saveAndCloseBtn = new JButton("Save");
    JButton cancelBtn = new JButton("Cancel");

    public AddVakScherm(JFrame owner) {
        super(owner,"Add Vak", true);

        Container container = getContentPane();
        container.setLayout(null);

        JLabel labelNaam = new JLabel("Nieuw vak:");
        labelNaam.setSize(100, 20);
        labelNaam.setLocation(20, 20);
        labelNaam.setBackground(Color.green);
        container.add(labelNaam);
        naamTxtField.setSize(200, 20);
        naamTxtField.setLocation(180, 20);
        container.add(naamTxtField);

        JLabel labelVakken = new JLabel("Alle vakken:");
        labelVakken.setSize(100, 20);
        labelVakken.setLocation(20, 50);
        labelVakken.setBackground(Color.green);
        container.add(labelVakken);
        vakkenListField = new JList(getVakkenData());
        vakkenListField.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        vakkenListField.setSelectedIndex(0);
        vakkenListField.setVisibleRowCount(3);
        vakkenListField.setSelectionBackground(Color.GREEN);
        JScrollPane vakListScrollPane = new JScrollPane(vakkenListField);
        vakListScrollPane.setLocation(180,50);
        vakListScrollPane.setSize(200, 100);
        container.add(vakListScrollPane);

        saveAndCloseBtn.setSize(95, 30);
        saveAndCloseBtn.setLocation(180, 170);
        saveAndCloseBtn.addActionListener(this);
        add(saveAndCloseBtn);

        cancelBtn.setSize(95, 30);
        cancelBtn.setLocation(285, 170);
        cancelBtn.addActionListener(this);
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setForeground(Color.white);
        add(cancelBtn);

        setSize(420, 250);
        setLocation(150, 150);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveAndCloseBtn) {
            //INSERT IN Vak TABEL
            Vak vak = new Vak();
            vak.setNaam(naamTxtField.getText());

            VakDatabaseAccess databaseAccess = new VakDatabaseAccess();
            databaseAccess.insert(vak);

            dispose();
        }else if(e.getSource() == cancelBtn){
            dispose();
        }
    }
    private Vector<Vector<Object>> getVakkenData() {
        VakDatabaseAccess vakDatabaseAccess = new VakDatabaseAccess();
        ArrayList<Vak> vakList = vakDatabaseAccess.selectAll();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for(Vak aVak : vakList){
            Vector<Object> row = new Vector<Object>();
            row.add(aVak.getId());
            row.add(aVak.getNaam());
            data.add(row);
        }
        return data;
    }
}



