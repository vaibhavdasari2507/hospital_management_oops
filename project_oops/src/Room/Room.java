package Room;

import java.util.ArrayList;

public class Room {
    private String roomNo;
    private String patientID;
    private boolean status;
    private String admissionDate;
    private String dischargeDate;
    static int roomCount = 0;

    public String getRoomNo() {return roomNo;}
    public void setRoomNo(String roomNo) {this.roomNo = roomNo;}
    public String getPatientID() {return patientID;}
    public void setPatientID(String patientID) {this.patientID = patientID;}
    public boolean getStatus() {return status;}
    public void setStatus(boolean status) {this.status = status;}
    public String getAdmissionDate() {return admissionDate;}
    public void setAdmissionDate(String admissionDate) {this.admissionDate = admissionDate;}
    public String getDischargeDate() {return dischargeDate;}
    public void setDischargeDate(String dischargeDate) {this.dischargeDate = dischargeDate;}

    public Room(String roomNo, String patientID,String admissionDate, String dischargeDate, boolean status) {
        this.roomNo = roomNo;
        this.patientID = patientID;
        this.status = status;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.status=status;
    }
    public Room(String PatientLine){
        String attributes[]=PatientLine.split(",");
        this.roomNo  =attributes[0];
        this.patientID=attributes[1];
        this.admissionDate=attributes[2];
        this.dischargeDate=attributes[3];
        this.status=Boolean.parseBoolean(attributes[4]);
    }
    @Override
    public String toString() {
        return "Room{" +
                "roomNo='" + roomNo + '\'' +
                ", patientID='" + patientID + '\'' +
                ", status=" + status +
                ", admissionDate='" + admissionDate + '\'' +
                ", dischargeDate='" + dischargeDate + '\'' +
                '}';
    }
}