package kr.co.sist.statement.vo;

import java.sql.Date;

public class CpEmp4SelectOneVO {
	private int empno, sal; 	
	private String ename, job, hiredate2;
	private double comm;
	private Date hiredate;
	
	public CpEmp4SelectOneVO() {
	}

	public CpEmp4SelectOneVO(int sal, String ename, String job, double comm) {
		super();
		this.sal = sal;
		this.ename = ename;
		this.job = job;
		this.comm = comm;
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

	public double getComm() {
		return comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}


	

}
