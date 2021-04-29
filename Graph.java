import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.util.List;


class ChartFrame extends JFrame {
ChartFrame() {

DefaultCategoryDataset d1 = new DefaultCategoryDataset();

Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact=cfg.buildSessionFactory();

Session session=sfact.openSession();

Transaction t=null;

try {
t=session.beginTransaction();

List<Student> stu=new ArrayList<>();
stu=session.createQuery("from Student").list();
for(Student s:stu) {
	d1.addValue(s.m1, s.name, "Maths");
	d1.addValue(s.m2, s.name, "Phy");
	d1.addValue(s.m3, s.name, "Chem");
}

JFreeChart chart = ChartFactory.createBarChart("Student Performance", "Subjects", "Marks", d1, PlotOrientation.VERTICAL, true, false, false);

ChartPanel panel = new ChartPanel(chart);
setContentPane(panel);

setTitle("Mark Sheet");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        MainFrame a=new MainFrame();
		dispose();
       
    }
});

setVisible(true);


}


catch(Exception e) {
t.rollback();
JOptionPane.showMessageDialog(this,e);
}
finally {
session.close();
}


}

}