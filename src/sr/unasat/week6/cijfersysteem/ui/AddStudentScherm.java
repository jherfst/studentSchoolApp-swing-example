package sr.unasat.week6.cijfersysteem.ui;

import sr.unasat.week6.jdbc.dataaccess.StudentDatabaseAccess;
import sr.unasat.week6.jdbc.dataaccess.StudentVakDatabaseAccess;
import sr.unasat.week6.jdbc.dataaccess.VakDatabaseAccess;
import sr.unasat.week6.jdbc.entities.Student;
import sr.unasat.week6.jdbc.entities.StudentVak;
import sr.unasat.week6.jdbc.entities.Vak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;


public class AddStudentScherm extends JDialog implements ActionListener {


    JTextField naamTxtField = new JTextField();
    JTextField adresTxtField = new JTextField();
    JSpinner cijfergemiddeldeField = new JSpinner ();
    JSpinner leeftijdField = new JSpinner();
    JTextField studierichtingTxtField = new JTextField();
    JList vakkenListField = new JList();

    JButton confirmBtn = new JButton("Save");
    JButton cancelBtn = new JButton("Cancel");

    AddStudentScherm(JFrame owner) {
        super(owner,"Add Student", true);

        Container container = getContentPane();
        container.setLayout(null);

        JLabel labelNaam = new JLabel("Naam:");
        labelNaam.setSize(100, 20);
        labelNaam.setLocation(20, 20);
        labelNaam.setBackground(Color.green);
        container.add(labelNaam);
        naamTxtField.setSize(200, 20);
        naamTxtField.setLocation(180, 20);
        container.add(naamTxtField);

        JLabel labelAdress = new JLabel("Adres:");
        labelAdress.setSize(100, 20);
        labelAdress.setLocation(20, 50);
        labelAdress.setBackground(Color.green);
        container.add(labelAdress);
        adresTxtField.setSize(200, 20);
        adresTxtField.setLocation(180, 50);
        container.add(adresTxtField);

        JLabel labelcijfer = new JLabel("Cijfer:");
        labelcijfer.setSize(100, 20);
        labelcijfer.setLocation(20, 80);
        labelcijfer.setBackground(Color.green);
        container.add(labelcijfer);
        SpinnerNumberModel model = new SpinnerNumberModel(0.0,-1000.0 ,1000.0,0.1);
        cijfergemiddeldeField.setModel(model);
        cijfergemiddeldeField.setSize(200, 20);
        cijfergemiddeldeField.setLocation(180, 80);
        container.add(cijfergemiddeldeField);

        JLabel labelleeftijd = new JLabel("Leeftijd:");
        labelleeftijd.setSize(100, 20);
        labelleeftijd.setLocation(20, 110);
        labelleeftijd.setBackground(Color.green);
        container.add(labelleeftijd);
        leeftijdField.setSize(200, 20);
        leeftijdField.setLocation(180, 110);
        container.add(leeftijdField);

        JLabel labelStudierichting = new JLabel("Studie Richting:");
        labelStudierichting.setSize(100, 20);
        labelStudierichting.setLocation(20, 140);
        labelStudierichting.setBackground(Color.green);
        container.add(labelStudierichting);
        studierichtingTxtField.setSize(200, 20);
        studierichtingTxtField.setLocation(180, 140);
        container.add(studierichtingTxtField);

        JLabel labelVakken = new JLabel("Student vakken:");
        labelVakken.setSize(100, 20);
        labelVakken.setLocation(20, 170);
        labelVakken.setBackground(Color.green);
        container.add(labelVakken);
        vakkenListField = new JList(getVakkenData());
        vakkenListField.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        vakkenListField.setSelectedIndex(0);
        vakkenListField.setVisibleRowCount(3);
        vakkenListField.setSelectionBackground(Color.GREEN);
        JScrollPane vakListScrollPane = new JScrollPane(vakkenListField);
        vakListScrollPane.setLocation(180,170);
        vakListScrollPane.setSize(200, 100);
        container.add(vakListScrollPane);

        confirmBtn.setSize(95, 30);
        confirmBtn.setLocation(180, 290);
        add(confirmBtn);
        confirmBtn.addActionListener(this);

        cancelBtn.setSize(95, 30);
        cancelBtn.setLocation(285, 290);
        cancelBtn.addActionListener(this);
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setForeground(Color.white);
        add(cancelBtn);

        setSize(420, 400);
        setLocation(150, 150);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmBtn) {
            //INSERT IN STUDENT TABEL
            Student student = new Student();
            student.setNaam(naamTxtField.getText());
            student.setAdres(adresTxtField.getText());
            student.setCijfergemiddelde((Double) cijfergemiddeldeField.getValue());
            student.setLeeftijd((Integer) leeftijdField.getValue());
            student.setStudierichting(studierichtingTxtField.getText());
            StudentDatabaseAccess databaseAccess = new StudentDatabaseAccess();
            int studentId = databaseAccess.insert(student);

            //INSERT IN STUDENTVAK TABEL
            if(studentId > 0) {
                if (vakkenListField.getSelectedIndex() != -1) {
                    StudentVakDatabaseAccess studentVakDatabaseAccess = new StudentVakDatabaseAccess();
                    for (Object vak : vakkenListField.getSelectedValuesList()) {
                       int vakId =Integer.parseInt(((Vector)vak).get(0).toString());
                        StudentVak studentVak = new StudentVak();
                        studentVak.setStudent(studentId);
                        studentVak.setVak(vakId);
                        studentVakDatabaseAccess.insert(studentVak);
                    }

                }
            }

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



