package Nurse;
import Patients.Patient;
import Room.Room;
import java.util.ArrayList;
import java.util.Collections;

public class Nurse implements Comparable {
    private String nurseID;
    private String nurseName;
    private int age;
    private String gender;
    private String bloodGroup;
    private int patientCount=0;
    ArrayList<Patient> PatiensWorkedWith = new ArrayList<>();
    ArrayList<Room> RoomList= new ArrayList<Room>();

    public String getNurseID() {return nurseID;}
    public void setNurseID(String nurseID) {this.nurseID = nurseID;}
    public String getNurseName() {return nurseName;}
    public void setnurseName(String name) {this.nurseName = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}
    public String getBloodGroup() {return bloodGroup;}
    public void setBloodGroup(String bloodGroup) {this.bloodGroup = bloodGroup;}
    public int getPatientCount() {return patientCount;}
    public void setPatientCount(int patientCount) {this.patientCount = patientCount;}

    @Override
    public String toString() {
        return "Nurse{" +
                "nurseID='" + nurseID + '\'' +
                ", name='" +nurseName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
    public Nurse(String nurseID, String name, int age, String gender, String bloodGroup) {
        this.nurseID = nurseID;
        this.nurseName = name;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
    }
    public Nurse(String NurseLine){
        String[] attributes =NurseLine.split(",");
        this.nurseID=attributes[0];
        this.nurseName =attributes[1];
        this.age =Integer.parseInt(attributes[2]);
        this.gender =attributes[3];
        this.bloodGroup =attributes[4];
    }

    @Override
    public int compareTo(Object o) {
        Nurse nurse = (Nurse) o;
        return this.getPatientCount()-nurse.getPatientCount();
    }

    public void patientHistory(){
        for(Patient p: this.PatiensWorkedWith){
            System.out.println(p);
        }
    }
}