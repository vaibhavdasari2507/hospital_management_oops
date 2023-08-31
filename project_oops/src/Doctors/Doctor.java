package Doctors;
import Patients.Patient;
import java.util.ArrayList;

public class Doctor implements Comparable {
    private String docID;
    private String name;
    private String bloodGroup;
    private String specialization;
    private String gender;
    private int age;
    private String profile;
    private int patientCOunt=0;
    ArrayList<Patient> PatiensWorkedWith = new ArrayList<>();
    //constructor
    public Doctor(String docID, String name,
                  String bloodGroup, String specialization, String gender, int age, String profile) {
        this.docID = docID;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.specialization = specialization;
        this.gender = gender;
        this.age = age;
        this.profile = profile;
    }
    public Doctor(String DoctorLine){
        String attributes[]=DoctorLine.split(",");
        this.docID=attributes[0];
        this.name=attributes[1];
        this.bloodGroup=attributes[2];
        this.specialization=attributes[3];
        this.gender=attributes[4];
        this.age=Integer.parseInt(attributes[5]);
        this.profile=attributes[6];
    }
    //getter setters
    public String getDocID() {return docID;}
    public String getName() {return name;}
    public String getBloodGroup() {return bloodGroup;}
    public String getSpecialization() {return specialization;}
    public String getGender() {return gender;}
    public int getAge() {return age;}
    public String getProfile() {return profile;}
    public int getPatientCOunt() {return patientCOunt;}
    public void setPatientCOunt(int patientCOunt) {this.patientCOunt = patientCOunt;}

    @Override
    public int compareTo(Object o) {
        Doctor doctor = (Doctor) o;
        return this.getPatientCOunt()- doctor.getPatientCOunt();
    }
}
