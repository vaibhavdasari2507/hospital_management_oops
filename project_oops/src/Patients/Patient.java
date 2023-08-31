package Patients;
public class Patient {
    private String patientName;
    private String patientID;
    private int age;
    private String gender;
    private String admissionDate;
    private String bloodGrp;
    private String phoneNo;
    private String status;

        public String getPatientName() {return patientName;}
        public String getPatientID() {return patientID;}
        public int getAge() {return age;}
        public String getPhoneNo() {return phoneNo;}
        public String getGender() {return gender;}
        public String getAdmissionDate() {return admissionDate;}
        public String getBloodGrp() {return bloodGrp;}
        public String getStatus() {return status;}

    @Override
    public String toString() {
        return "Patient{" +
                "patientName='" + patientName + '\'' +
                ", patientID='" + patientID + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", admissionDate='" + admissionDate + '\'' +
                ", bloodGrp='" + bloodGrp + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
    public Patient(String patientName, String patientID, int age,
                   String gender, String admissionDate, String bloodGrp,
                   String phoneNo, String status){
        this.patientName = patientName;
        this.patientID = patientID;
        this.age = age;
        this.gender = gender;
        this.admissionDate = admissionDate;
        this.bloodGrp = bloodGrp;
        this.phoneNo = phoneNo;
        this.status = status;
    }
    public Patient(String PatientLine){
        String[] attributes =PatientLine.split(",");
        this.patientName=attributes[0];
        this.patientID=attributes[1];
        this.age=Integer.parseInt(attributes[2]);
        this.gender=attributes[3];
        this.admissionDate=attributes[4];
        this.bloodGrp=attributes[5];
        this.phoneNo=attributes[6];
        this.status=attributes[7];
    }
}
