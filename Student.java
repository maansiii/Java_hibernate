class Student {
public int rno;
public String name;
public Integer m1, m2, m3;

public Student() { }

public Student(int rno, String name, Integer m1, Integer m2, Integer m3) {
this.rno= rno;
this.name= name;
this.m1= m1;
this.m2= m2;
this.m3= m3;
}

public void setRno(int rno) {
	this.rno=rno;
}
public int getRno() {
	return rno;
}
public void setName(String name) {
	this.name=name;
}
public String getName() {
	return name;
}
public void setM1(Integer m1) {
	this.m1 = m1;
}
public int getM1() {
	return m1;
}
public void setM2(Integer m2) {
	this.m2 = m2;
}
public int getM2() {
	return m2;
}
public void setM3(Integer m3) {
	this.m3 = m3;
}
public int getM3() {
	return m3;
}
}