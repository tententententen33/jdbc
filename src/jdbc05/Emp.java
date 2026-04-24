package jdbc05;

public class Emp {
	private Integer empNo;
	private String eName;
	private String job;
	
	public void setEmpNo (Integer empNo){
		this.empNo = empNo;
	}
	
	public void setEName (String eName){
		this.eName = eName;
	}
	
	public void setJob (String job){
		this.job = job;
	}
	
	public Integer getEmpNo () {
		return empNo;
	}
	
	public String getEName () {
		return eName;
	}
	
	public String getJob () {
		return job;
	}
}
