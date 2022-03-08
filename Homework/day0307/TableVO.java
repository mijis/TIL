package day0307;

public class TableVO {
	
	private String column_name, data_type; //0308 수정 private 설정하지 않음 (자바는 _를 좋아하지 않음)
	private int data_length;
	
	public TableVO() {
		super();
	}

	public TableVO(String column_name, String data_type, int data_length) {
		super();
		this.column_name = column_name;
		this.data_type = data_type;
		this.data_length = data_length;
	}
	
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public int getData_length() {
		return data_length;
	}
	public void setData_length(int data_length) {
		this.data_length = data_length;
	}
	
	
	
}
