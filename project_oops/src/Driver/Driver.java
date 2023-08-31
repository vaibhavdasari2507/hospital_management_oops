package Driver;
import Doctors.*;
import Patients.*;
import Nurse.*;
import Room.*;

public class Driver {
    public static void help(){
        System.out.println();
        System.out.format("%80s","+---------------------------------------------" +
                "--------------------------------------------------------------" +
                "-------------------------+");
        System.out.println();
        System.out.format("%83s","+--------------------------------+\n");
        System.out.format("%83s","|   WELCOME TO MARVEL HOSPITAL   |\n");
        System.out.format("%83s","+--------------------------------+\n");
        System.out.println("\n");
        System.out.println();
        System.out.format("%50s","+--------------------------------+\n");
        System.out.format("%50s","|   Column Names of the tables   |\n");
        System.out.format("%50s","+--------------------------------+\n");
        DoctorDAO doctorDAO = new DoctorDAO();
        doctorDAO.show();
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.show();
        InsurancePatientDAO insurancePatientDAO = new InsurancePatientDAO();
        insurancePatientDAO.show();
        NurseDAO nurseDAO = new NurseDAO();
        nurseDAO.show();
        RoomDAO roomDAO  = new RoomDAO();
        roomDAO.show();
        System.out.format("%15s","  +--------------------+\n");
        System.out.format("%16s","  | PATIENT OPERATIONS |\n");
        System.out.format("%15s","  +--------------------+\n");
        System.out.println("1. To Add patient             :    -add patient" +
                " < Patient name > < Patient ID > < Patient age >" +"\n"+
                "                                   < Gender > < Admission Date > " +
                "< Blood Group> < Phone number > < Status >");
        System.out.println("2. To Remove patient          :  " +
                "  -remove patient < Patient id >");
        System.out.println("3. To Show patients list      :    -display patient");
        System.out.println("4. To upload a CSV file       :    -uploadCSV patient <Absolute Path " +
                "of the CSV>");
        System.out.println("5. To Update a Patient record :    -update patient < Patient ID > " +
                "< Attribute > to < new value for the Attribute >");
        System.out.println("6. To bulk update via CSV     :    -bulkUpdate patient <Absolute Path " +
                "of the CSV>");
        System.out.println("7. To search patient          :    -search patient <column name/names seperated by comma> " +"\n"+
        "                                   <constraint (optional) '> , < , = ' and other operators can be used>");
        System.out.println();

        System.out.format("%15s","  +------------------------------+\n");
        System.out.format("%16s","  | INSURANCE PATIENT OPERATIONS |\n");
        System.out.format("%15s","  +------------------------------+\n");
        System.out.println("1. To Add patient             :    -add patient" +
                " < Patient name > < Patient ID > < Patient age >" +"\n"+
                "                                   < Gender > < Admission Date > " +
                "< Blood Group> < Phone number > < Status >" +"\n"+
                "                                   < Insurance ID > < Insurance Premium >");
        System.out.println("2. To Remove patient          :  " +
                "  -remove insurancePatient < Patient id >");
        System.out.println("3. To Show patients list      :    -display insurancePatient");
        System.out.println("4. To upload a CSV file       :    -uploadCSV insurancePatient <Absolute Path " +
                "of the CSV>");
        System.out.println("5. To Update a Patient record :    -update insurancePatient < Patient ID > " +
                "< Attribute > to < new value for the Attribute >");
        System.out.println("6. To bulk update via CSV     :    -bulkUpdate insurancePatient <Absolute Path " +
                "of the CSV>");
        System.out.println("7. To search patient          :    -search insurancePatient <column name/names seperated by comma> " +"\n"+
                "                                   <constraint (optional) '> , < , = ' and other operators can be used>");
        System.out.println();

        System.out.format("%15s","  +-------------------+\n");
        System.out.format("%16s","  | DOCTOR OPERATIONS |\n");
        System.out.format("%15s","  +-------------------+\n");
        System.out.println("1. To Add doctor             :    -add doctor" +
                " < Doctor ID > < Doctor name > < Blood Group> " +
                "< Specialization > "+"\n"+"                                  < Gender > < Doctor age >  < Profile > ");
        System.out.println("2. To Remove doctor          :  " +
                "  -remove doctor < Doctor id >");
        System.out.println("3. To Show doctors list      :    -display doctor");
        System.out.println("4. To upload a CSV file      :    -uploadCSV doctor <Absolute Path " +
                "of the CSV>");
        System.out.println("5. To Update a Doctor record :    -update doctor < Doctor ID > " +
                "< Attribute > to < new value for the Attribute >");
        System.out.println("6. To bulk update via CSV    :    -bulkUpdate doctor <Absolute Path " +
                "of the CSV>");
        System.out.println("7. To search doctor          :    -search doctor <column name/names seperated by comma> " +"\n"+
                "                                  <constraint (optional) '> , < , = ' and other operators can be used>");
        System.out.println();

        System.out.format("%15s","  +------------------+\n");
        System.out.format("%16s","  | NURSE OPERATIONS |\n");
        System.out.format("%15s","  +------------------+\n");
        System.out.println("1.  To Add nurse            :    -add nurse < Nurse ID > < Nurse name > < Age >" +
                " < Gender > < Blood Group >");
        System.out.println("2. To Remove nurse          :  " +
                "  -remove nurse < Nurse id >");
        System.out.println("3. To Show Nurses list      :    -display nurse");
        System.out.println("4. To upload a CSV file     :    -uploadCSV nurse <Absolute Path " +
                "of the CSV>");
        System.out.println("5. To Update a Nurse record :    -update nurse < Nurse ID > " +
                "< Attribute > to < new value for the Attribute >");
        System.out.println("6. To bulk update via CSV   :    -bulkUpdate nurse <Absolute Path " +
                "of the CSV>");
        System.out.println("7. To search nurse          :    -search nurse <column name/names seperated by comma> " +"\n"+
                "                                 <constraint (optional) '> , < , = ' and other operators can be used>");
        System.out.println();

        System.out.format("%15s","  +-----------------+\n");
        System.out.format("%15s","  | ROOM OPERATIONS |\n");
        System.out.format("%15s","  +-----------------+\n");
        System.out.println("1. To Add Room             :    -add room < Room number > " +
                "< Patient ID > < admission date of the patient > " +"\n"+
                "                                < discharge date of the patient > < status >");
        System.out.println("2. To Remove Room          :  " +
                "  -remove room < Room number >");
        System.out.println("3. To Show Rooms list      :    -display room");
        System.out.println("4. To upload a CSV file    :    -uploadCSV room <Absolute Path " +
                "of the CSV>");
        System.out.println("5. To Update a Room record :    -update room < Room No. > " +
                "< Attribute > to < new value for the Attribute >");
        System.out.println("6. To bulk update via CSV  :    -bulkUpdate room <Absolute Path " +
                "of the CSV>");
        System.out.println("7. To search room          :    -search room <column name/names seperated by comma> " +"\n"+
                "                                <constraint (optional) '> , < , = ' and other operators can be used>");
        System.out.println();
        System.out.format("%80s","+---------------------------------------------" +
                "--------------------------------------------------------------" +
                "-------------------------+");
    }
     public static void main(String[] args) throws Exception {
            if (args.length == 0 || args[0].equals("-help")) help();
            else {
                switch (args[0]){
                    case "-add":
                        switch (args[1]){
                            case "patient":
                                if(args.length == 10){
                                    Patient patient = new Patient(args[2], args[3], Integer.parseInt(args[4]),
                                            args[5], args[6], args[7], args[8], args[9]);
                                    PatientDAO patientDAO =new PatientDAO();
                                    patientDAO.create(patient);
                                    break;
                                }
                                else if(args.length == 12){
                                    InsurancePatient Ipatient = new InsurancePatient(args[2], args[3],
                                            Integer.parseInt(args[4]),
                                            args[5], args[6], args[7], args[8], args[9],
                                            args[9],Integer.parseInt(args[10]));
                                    InsurancePatientDAO insurancePatientDAO = new InsurancePatientDAO();
                                    insurancePatientDAO.create(Ipatient);
                                    break;
                                }
                            case "doctor":
                                Doctor doctor = new Doctor(args[2], args[3], args[4],
                                        args[5], args[6], Integer.parseInt(args[7]), args[8]);
                                DoctorDAO doctorDAO=new DoctorDAO();
                                doctorDAO.create(doctor);
                                break;
                            case "nurse":
                                Nurse nurse = new Nurse(args[2], args[3], Integer.parseInt(args[4]),
                                        args[5], args[6]);
                                NurseDAO nurseDAO = new NurseDAO();
                                nurseDAO.create(nurse);
                                break;
                            case "room":
                                Room room = new Room(args[2], args[3],args[4],
                                        args[5], Boolean.parseBoolean(args[6]));
                                RoomDAO roomDAO = new RoomDAO();
                                roomDAO.create(room);
                                break;
                            default : System.err.println("WRONG TABLE NAME");
                        }
                        break;
                    case "-display":
                        switch (args[1]) {
                            case "patient" -> {
                                PatientDAO patientDAO = new PatientDAO();
                                patientDAO.display();
                            }
                            case "insurancePatient" ->{
                                InsurancePatientDAO InsurancePatientDAO = new InsurancePatientDAO();
                                InsurancePatientDAO.display();
                            }
                            case "doctor" -> {
                                DoctorDAO doctorDAO=new DoctorDAO();
                                doctorDAO.display();
                            }
                            case "nurse" -> {
                                NurseDAO NurseDAO = new NurseDAO();
                                NurseDAO.display();
                            }
                            case "room" -> {
                                RoomDAO RoomDAO = new RoomDAO();
                                RoomDAO.display();
                            }
                            default -> System.err.println("WRONG TABLE NAME");
                        }
                        break;
                    case "-remove":
                        switch (args[1]) {
                            case "patient" ->{
                                PatientDAO patientDAO =new PatientDAO();
                                patientDAO.remove(args[2]);
                            }
                            case "insurancePatient" -> {
                                InsurancePatientDAO insurancePatientDAO = new InsurancePatientDAO();
                                insurancePatientDAO.remove(args[2]);
                            }
                            case "doctor" -> {
                                DoctorDAO doctorDAO = new DoctorDAO();
                                doctorDAO.remove(args[2]);
                            }
                            case "nurse" -> {
                                NurseDAO nurseDAO = new NurseDAO();
                                nurseDAO.remove(args[2]);
                            }
                            case "room" -> {
                                RoomDAO roomDAO = new RoomDAO();
                                roomDAO.remove(args[2]);
                            }
                            default -> System.err.println("WRONG TABLE NAME");
                        }
                        break;
                    case "-uploadCSV":
                        switch (args[1]) {
                            case "patient" -> {
                                PatientDAO patientDAO = new PatientDAO();
                                patientDAO.createFromCSV(args[2]);
                            }
                            case "insurancePatient" -> {
                                InsurancePatientDAO insurancePatientDAO = new InsurancePatientDAO();
                                insurancePatientDAO.createFromCSV(args[2]);
                            }
                            case "doctor" -> {
                                DoctorDAO doctorDAO = new DoctorDAO();
                                doctorDAO.createFromCSV(args[2]);
                            }
                            case "nurse" -> {
                                NurseDAO nurseDAO = new NurseDAO();
                                nurseDAO.createFromCSV(args[2]);
                            }
                            case "room" -> {
                                RoomDAO roomDAO = new RoomDAO();
                                roomDAO.createFromCSV(args[2]);
                            }
                            default -> System.err.println("WRONG TABLE NAME");
                        }
                        break;
                    case "-update":
                        switch (args[1]) {
                            case "doctor" -> {
                                DoctorDAO DoctorDAO = new DoctorDAO();
                                DoctorDAO.update(args[2], args[3], args[5]);
                            }
                            case "patient" -> {
                                PatientDAO PatientDAO = new PatientDAO();
                                PatientDAO.update(args[2], args[3], args[5]);
                            }
                            case "insurancePatient" -> {
                                InsurancePatientDAO InsurancePatientDAO = new InsurancePatientDAO();
                                InsurancePatientDAO.update(args[2], args[3], args[5]);
                            }
                            case "nurse" -> {
                                NurseDAO NurseDAO = new NurseDAO();
                                NurseDAO.update(args[2], args[3], args[5]);
                            }
                            case "room" -> {
                                RoomDAO roomDAO = new RoomDAO();
                                roomDAO.update(args[2], args[3], args[5]);
                            }
                            default -> System.err.println("WRONG TABLE NAME");
                        }
                        break;
                    case  "-bulkUpdate":
                        switch (args[1]) {
                            case "doctor" -> {
                                DoctorDAO doctorDAO = new DoctorDAO();
                                doctorDAO.bulkUpdate(args[2]);
                            }
                            case "patient" -> {
                                PatientDAO patientDAO = new PatientDAO();
                                patientDAO.bulkUpdate(args[2]);
                            }
                            case "insurancePatient" -> {
                                InsurancePatientDAO insurancePatientDAO = new InsurancePatientDAO();
                                insurancePatientDAO.bulkUpdate(args[2]);
                            }
                            case "nurse" -> {
                                NurseDAO nurseDAO = new NurseDAO();
                                nurseDAO.bulkUpdate(args[2]);
                            }
                            case "room" -> {
                                RoomDAO roomDAO = new RoomDAO();
                                roomDAO.bulkUpdate(args[2]);
                            }
                            default -> System.err.println("WRONG TABLE NAME");
                        }
                        break;
                    case "-search":
                        switch (args[1]) {
                                case "doctor" -> {
                                    if (args.length==4) {
                                        DoctorDAO doctorDAO = new DoctorDAO();
                                        doctorDAO.search(args[2],args[3]);
                                    }
                                    else  {
                                        DoctorDAO doctorDAO = new DoctorDAO();
                                        doctorDAO.search(args[2]);
                                    }
                                }
                                case "patient" -> {
                                    if (args.length==4) {
                                        PatientDAO patientDAO = new PatientDAO();
                                        patientDAO.search(args[2],args[3]);
                                    }
                                    else  {
                                        PatientDAO patientDAO = new PatientDAO();
                                        patientDAO.search(args[2]);
                                    }
                                }
                                case "insurancePatient" -> {
                                     if (args.length==4) {
                                         InsurancePatientDAO insurancePatientDAO = new InsurancePatientDAO();
                                         insurancePatientDAO.search(args[2],args[3]);
                                     }
                                     else  {
                                         InsurancePatientDAO insurancePatientDAO = new InsurancePatientDAO();
                                         insurancePatientDAO.search(args[2]);
                                     }
                                }
                                 case "nurse" -> {
                                     if (args.length==4) {
                                         NurseDAO nurseDAO = new NurseDAO();
                                         nurseDAO.search(args[2],args[3]);
                                     }
                                     else  {
                                         NurseDAO nurseDAO = new NurseDAO();
                                         nurseDAO.search(args[2]);
                                      }
                                     }
                                case "room" -> {
                                    if (args.length==4) {
                                        RoomDAO roomDAO = new RoomDAO();
                                        roomDAO.search(args[2],args[3]);
                                    }
                                    else  {
                                        RoomDAO roomDAO = new RoomDAO();
                                        roomDAO.search(args[2]);
                                    }
                                }
                            default -> System.err.println("WRONG TABLE NAME");
                        }
                        break;
                }
            }
     }
}

