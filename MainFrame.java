import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class MainFrame extends JFrame
{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete, btnChart;
MainFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());

btnAdd=new JButton("Add");
btnView=new JButton("View");
btnUpdate=new JButton("Update");
btnDelete=new JButton("Delete");
btnChart=new JButton("Chart");

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);
c.add(btnChart);

btnAdd.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
AddFrame a=new AddFrame();
dispose();
}
});
btnView.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
ViewFrame a=new ViewFrame();
dispose();
}});
btnUpdate.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
UpdateFrame a=new UpdateFrame();
dispose();
}});
btnDelete.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
DeleteFrame a=new DeleteFrame();
dispose();
}});

btnChart.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
ChartFrame a=new ChartFrame();
dispose();
}});


setSize(300,300);
setLocationRelativeTo(null);
setTitle("Student");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
public static void main(String args[])
{
MainFrame m=new MainFrame();
}
}