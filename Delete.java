import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;


class DeleteFrame extends JFrame
{
Container c;
JLabel lblRno,lblName,lblM1, lblM2, lblM3;
JTextField txtRno,txtName,txtM1, txtM2, txtM3;
JButton btnDelete,btnBack;

DeleteFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());

lblRno=new JLabel("Rollno");
txtRno=new JTextField(25);

btnDelete=new JButton("Delete");
btnBack=new JButton("Back");


btnBack.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
MainFrame a=new MainFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener()
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
int rno=Integer.parseInt(txtRno.getText());
Student s=(Student)session.get(Student.class,rno);

if (rno < 0) {
	t.rollback();
	JOptionPane.showMessageDialog(c,"rollno should be greater than zero");
	txtRno.setText("");
	txtRno.requestFocus();
}


if (s == null) {
	t.rollback();
	JOptionPane.showMessageDialog(c,"record does not exits");
	txtRno.setText("");
	txtRno.requestFocus();
}
else
{
session.delete(s);
t.commit();
JOptionPane.showMessageDialog(c,"record deleted");
txtRno.setText("");
txtRno.requestFocus();
}


}
catch(Exception e)
{
t.rollback();
JOptionPane.showMessageDialog(c,"invalid");
txtRno.setText("");
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
c.add(btnDelete);
c.add(btnBack);

setSize(300,300);
setLocationRelativeTo(null);
setTitle("Delete Student");

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}}