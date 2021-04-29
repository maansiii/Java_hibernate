import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.util.List;

class ViewFrame extends JFrame
{
Container c;
TextArea ta;
JButton btnBack;

ViewFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());

ta=new TextArea(6,30);
ta.setEditable(false);
btnBack=new JButton("Back");

c.add(ta);
c.add(btnBack);

Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact=cfg.buildSessionFactory();

Session session=sfact.openSession();

try
{
//CRUD Operation
List<Student> stu=new ArrayList<>();
stu=session.createQuery("from Student").list();
for(Student s:stu)
	ta.append(s.rno+"\t "+s.name+"\t "+s.m1+"\t "+ s.m2 +"\t"+ s.m3 +"\n");
}
catch(Exception e)
{
JOptionPane.showMessageDialog(c,"issue"+e);
}
finally
{
session.close();
}



btnBack.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
MainFrame m=new MainFrame();
dispose();
}});

setSize(300,300);
setLocationRelativeTo(null);
setTitle("View Student");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}}