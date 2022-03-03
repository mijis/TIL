package kr.co.sist.statement.vo;

import java.sql.Date;

public class CpEmp4SelectOneVO {
	private int empno, sal; 	
	private String ename, job, hiredate2;
	private double comm;
	private Date hiredate;
	
	public CpEmp4SelectOneVO() {
	}

	public CpEmp4SelectOneVO(int empno, int sal, String ename, String job, String hiredate2, double comm,
			Date hiredate) {
		super();
		this.empno = empno;
		this.sal = sal;
		this.ename = ename;
		this.job = job;
		this.hiredate2 = hiredate2;
		this.comm = comm;
		this.hiredate = hiredate;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getHiredate2() {
		return hiredate2;
	}

	public void setHiredate2(String hiredate2) {
		this.hiredate2 = hiredate2;
	}

	public double getComm() {
		return comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	

	

}
