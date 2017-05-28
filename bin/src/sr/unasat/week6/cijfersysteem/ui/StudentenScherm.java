package sr.unasat.week6.cijfersysteem.ui;

import sr.unasat.week6.jdbc.dataaccess.StudentDatabaseAccess;
import sr.unasat.week6.jdbc.entities.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;


public class StudentenScherm {

    JFrame jFrame;
    JPanel jPanel ;
    StudentDatabaseAccess  databaseAccess;
    JTable table;
    int studentToUpdate;
    JButton updateStudentBtn;


    public StudentenScherm(){

        jFrame = new JFrame();
        jFrame.setTitle("Student Cijfersysteem");
        jFrame.setLayout(null);
        jFrame.setSize(960,500);

        placeRefreshButton();
        placeAddStudentButton();
        placeAddVakButton();
        placeUpdateStudentButton();
        placeTabel();
        jFrame.setVisible(true);
        jFrame.setResizable(false);
    }

    private void placeTabel(){
        // JTABLE
        Vector<String> columnNames  = getColumnNames();
        Vector<Vector<Object>> data = getData();
        DefaultTableModel tableData = new DefaultTableModel(data, columnNames);
        table = new JTable(tableData);
        ListSelectionModel select= table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String Data = null;
                int row = table.getSelectedRow();
                if(row > -1) {
                    //column nummer 0 is Student ID column
                    Object studentId = table.getValueAt(row, 0);
                    System.out.println("Table element selected is: " + studentId);
                    studentToUpdate = Integer.parseInt(studentId.toString());
                    updateStudentBtn.setEnabled(true);
                    updateStudentBtn.setText("Update:" + studentToUpdate);
                }
            }
        });
        JScrollPane pane = new JScrollPane(table);
        pane.setSize(800,400);
        pane.setLocation(5,20);
        jFrame.add(pane);
    }

    private void placeRefreshButton(){
        //Refresh  JButton
        JButton refreshBtn = new JButton("Refresh",new ImageIcon(ClassLoader.getSystemResource("images/refresh.png")));
        refreshBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        refreshBtn.setSize(125,30);
        refreshBtn.setLocation(815,20);
        refreshBtn.setFocusPainted(false);
        refreshBtn.setOpaque(false);
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTabel();
            }
        });
        jFrame.add(refreshBtn);
    }

    private void placeAddStudentButton(){
        //New Student JButton
        JButton newStudentBtn = new JButton("",new ImageIcon(ClassLoader.getSystemResource("images/new.png")));
        newStudentBtn.setLocation(815,60);
        newStudentBtn.setSize(125,30);
        newStudentBtn.setFocusPainted(false);
        newStudentBtn.setOpaque(false);
        newStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new AddStudentScherm(jFrame);
                jFrame.setVisible(true);
                refreshTabel();
            }
        });
        jFrame.add(newStudentBtn);
    }

    private void placeAddVakButton(){
        //New Student JButton
        JButton newVakBtn = new JButton("New course",new ImageIcon(ClassLoader.getSystemResource("images/vak.png")));
        newVakBtn.setLocation(815,100);
        newVakBtn.setSize(125,30);
        newVakBtn.setFocusPainted(false);
        newVakBtn.setOpaque(false);
        newVakBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new AddVakScherm(jFrame);
                jFrame.setVisible(true);
                refreshTabel();
            }
        });
        jFrame.add(newVakBtn);
    }

    private void placeUpdateStudentButton(){
        //New Student JButton
        updateStudentBtn = new JButton("Update",new ImageIcon(ClassLoader.getSystemResource("images/update.png")));
        updateStudentBtn.setLocation(815,140);
        updateStudentBtn.setSize(125,30);
        updateStudentBtn.setFocusPainted(false);
        updateStudentBtn.setOpaque(false);
        updateStudentBtn.setEnabled(false);
        updateStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jFrame.add(updateStudentBtn);
    }

    private Vector<Vector<Object>> getData() {
        databaseAccess = new StudentDatabaseAccess();
        ArrayList<Student> studentList = databaseAccess.selectAll();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for(Student aStudent : studentList){
            Vector<Object> row = new Vector<Object>();
            row.add(aStudent.getId());
            row.add(aStudent.getNaam());
            row.add(aStudent.getAdres());
            row.add(aStudent.getLeeftijd());
            row.add(aStudent.getStudierichting());
            row.add(aStudent.getCijfergemiddelde());
            data.add(row);
        }
        return data;
    }

    private static Vector<String>  getColumnNames(){
        Vector<String> columnNames = new  Vector<String>();
        columnNames.add("Student ID");
        columnNames.add("Naam");
        columnNames.add("Adres");
        columnNames.add("Leeftijd");
        columnNames.add("Richting");
        columnNames.add("Gemidd Cijfer");
        return columnNames;
    }

    public  void refreshTabel(){
        Vector<String> columnNames  = getColumnNames();
        Vector<Vector<Object>> data = getData();
        DefaultTableModel tableData = new DefaultTableModel(data, columnNames);
        table.setModel(tableData);
    }

}