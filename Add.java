import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.io.*;

class AddFrame extends JFrame
{
Container c;
JLabel lblRno,lblName,lblM1, lblM2, lblM3;
JTextField txtRno,txtName,txtM1, txtM2, txtM3;
JButton btnSave,btnBack;

AddFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());

lblRno=new JLabel("Rollno");
lblName=new JLabel("Name");
lblM1=new JLabel("Marks 1 ");
lblM2=new JLabel("Marks 2 ");
lblM3=new JLabel("Marks 3 ");

txtRno=new JTextField(25);
txtName=new JTextField(25);
txtM1=new JTextField(25);
txtM2=new JTextField(25);
txtM3=new JTextField(25);

btnSave=new JButton("Save");
btnBack=new JButton("Back");

btnBack.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
MainFrame a=new MainFrame();
dispose();
}
});
btnSave.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact=cfg.buildSessionFactory();

Session session=sfact.openSession();

Transaction t=null;

try
{
t=session.beginTransaction();
Student s=new Student();
int rno=Integer.parseInt(txtRno.getText());
String name=txtName.getText();
int m1=Integer.parseInt(txtM1.getText());
int m2=Integer.parseInt(txtM2.getText());
int m3=Integer.parseInt(txtM3.getText());



if (rno < 0) {
	t.rollback();
	JOptionPane.showMessageDialog(c,"rollno should be greater than zero");
	txtRno.setText("");
	txtRno.requestFocus();
}

if (name.matches(".*\\d.*")) {
	t.rollback();
	JOptionPane.showMessageDialog(c,"name cannot contain integers");
	txtName.setText("");
	txtName.requestFocus();
}




if (name.equals("")) {
	t.rollback();
	JOptionPane.showMessageDialog(c,"name feild is empty");
	txtName.setText("");
	txtName.requestFocus();
}

if (name.length() < 2) {
	t.rollback();
	JOptionPane.showMessageDialog(c,"length of name should be greater than one");
	txtName.setText("");
	txtName.requestFocus();
}


if ((m1 | m2 | m3) < 0) {
	t.rollback();
	JOptionPane.showMessageDialog(c,"marks should be in between 0 to 100");
	txtM1.setText("");
	txtM2.setText("");
	txtM3.setText("");
	txtM1.requestFocus();
}

if ((m1 | m2 | m3) > 100) {
	t.rollback();
	JOptionPane.showMessageDialog(c,"marks should be in between 0 to 100");
	txtM1.setText("");
	txtM2.setText("");
	txtM3.setText("");
	txtM1.requestFocus();
}




s.setRno(rno);		
s.setName(name);	
s.setM1(m1);
s.setM2(m2);
s.setM3(m3);
session.save(s);	
t.commit();
JOptionPane.showMessageDialog(c,"record inserted");
txtRno.setText("");
txtName.setText("");
txtM1.setText("");
txtM2.setText("");
txtM3.setText("");
txtRno.requestFocus();
}
catch(java.lang.NumberFormatException e) {
t.rollback();
JOptionPane.showMessageDialog(c,"rollno and marks should be integer and cannot be empty");
txtRno.setText("");
txtM1.setText("");
txtM2.setText("");
txtM3.setText("");
txtRno.requestFocus();
}
catch(org.hibernate.exception.ConstraintViolationException e) {
t.rollback();
JOptionPane.showMessageDialog(c,"Record already exists");
txtRno.setText("");
txtName.setText("");
txtM1.setText("");
txtM2.setText("");
txtM3.setText("");
txtRno.requestFocus();
}
catch(Exception e)
{
t.rollback();
JOptionPane.showMessageDialog(c,e);
txtRno.setText("");
txtName.setText("");
txtM1.setText("");
txtM2.setText("");
txtM3.setText("");
txtRno.requestFocus();
}
finally
{
session.close();
}
}
});
c.add(lblRno);
c.add(txtRno);
c.add(lblName);
c.add(txtName);
c.add(lblM1);
c.add(txtM1);
c.add(lblM2);
c.add(txtM2);
c.add(lblM3);
c.add(txtM3);
c.add(btnSave);
c.add(btnBack);

setSize(300,300);
setLocationRelativeTo(null);
setTitle("Add Student");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}}