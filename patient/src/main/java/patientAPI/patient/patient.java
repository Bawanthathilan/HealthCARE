package patientAPI.patient;

public class patient {
	private int pat_id;
	private String first_name;
	private String last_name;
	private int pat_NIC;
	private String pat_bday;
	private int pat_phno;
	private String pat_email;
	private String pat_gender;
	
	 public int getPat_id() {
		return pat_id;
	}

	public void setPat_id(int pat_id) {
		this.pat_id = pat_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getPat_NIC() {
		return pat_NIC;
	}

	public void setPat_NIC(int pat_NIC) {
		this.pat_NIC = pat_NIC;
	}

	public String getPat_bday() {
		return pat_bday;
	}

	public void setPat_bday(String pat_bday) {
		this.pat_bday = pat_bday;
	}

	public int getPat_phno() {
		return pat_phno;
	}

	public void setPat_phno(int pat_phno) {
		this.pat_phno = pat_phno;
	}

	public String getPat_email() {
		return pat_email;
	}

	public void setPat_email(String pat_email) {
		this.pat_email = pat_email;
	}

	public String getPat_gender() {
		return pat_gender;
	}

	public void setPat_gender(String pat_gender) {
		this.pat_gender = pat_gender;
	}

	@Override
	    public String toString() {
	        return "patient{" +
	                "pat_id='" + pat_id + '\'' +
	                ", first_name='" + first_name + '\'' +
	                ", last_name='" + last_name + '\'' +
	                ", pat_NIC='" + pat_NIC + '\'' +
	                ", pat_bday='" + pat_bday + '\'' +
	                ", pat_phno='" + pat_phno + '\'' +
	                ", pat_email=" + pat_email + '\'' +
	                ", pat_gender='" + pat_gender + '\'' +
	                '}';
	    }
	
	
		
}

