package pafProject.pafProHospital;

public class hospitals {
 
		private int hosp_id;
		private String hosp_name;
		private int phn_no;
		private String hosp_address;
		private String hosp_type;
		private String description;
		
		//genarate getters setters
		
		public int getHosp_id() {
			return hosp_id;
		}
		public void setHosp_id(int hosp_id) {
			this.hosp_id = hosp_id;
		}
		public String getHosp_name() {
			return hosp_name;
		}
		public void setHosp_name(String hosp_name) {
			this.hosp_name = hosp_name;
		}
		public int getPhn_no() {
			return phn_no;
		}
		public void setPhn_no(int phn_no) {
			this.phn_no = phn_no;
		}
		public String getHosp_address() {
			return hosp_address;
		}
		public void setHosp_address(String hosp_address) {
			this.hosp_address = hosp_address;
		}
		public String getHosp_type() {
			return hosp_type;
		}
		public void setHosp_type(String hosp_type) {
			this.hosp_type = hosp_type;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		 @Override
		    public String toString() {
		        return "hospital{" +
		                "hosp_id='" + hosp_id + '\'' +
		                ", hosp_name='" + hosp_name + '\'' +
		                ", phn_no='" + phn_no + '\'' +
		                ", hosp_address='" + hosp_address + '\'' +
		                ", hosp_type='" + hosp_type + '\'' +
		                ", description='" + description + '\'' +
		                '}';
		 }
		
}
