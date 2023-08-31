package Patients;
public class InsurancePatient extends Patient{
    private String InsuranceID;
    private int InsurancePremium;
    public String getInsuranceID() {return InsuranceID;}
    public int getInsurancePremium() {return InsurancePremium;}

    public InsurancePatient(String patientName, String patientID, int age, String gender,
                            String admissionDate, String bloodGrp, String phoneNo, String status,
                            String insuranceid, int insurancePremium) {
        super(patientName, patientID, age, gender, admissionDate, bloodGrp, phoneNo, status);
        this.InsuranceID=insuranceid;
        this.InsurancePremium=insurancePremium;
    }
    public InsurancePatient(String InsurancePatientLine) {
        super(InsurancePatientLine);
        String[] attributes =InsurancePatientLine.split(",");
        this.InsuranceID = attributes[8];
        this.InsurancePremium =Integer.parseInt(attributes[9]);
    }
}
