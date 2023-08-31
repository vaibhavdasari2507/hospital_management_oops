package Doctors;
import Driver.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import DAOpackage.DAO;
import Patients.Patient;

public class DoctorDAO implements DAO{
    ArrayList<Doctor> doctorList=new ArrayList<>();
    public void create(Object o){
        try {
            Doctor doctor = (Doctor) o;
            Connection connection= ConnectionFactory.getConnection();
            String sql="insert into doctors values(?,?,?,?,?,?,?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,doctor.getDocID());
            preparedStatement.setString(2,doctor.getName());
            preparedStatement.setString(3,doctor.getBloodGroup());
            preparedStatement.setString(4,doctor.getSpecialization());
            preparedStatement.setString(5,doctor.getGender());
            preparedStatement.setInt(6,doctor.getAge());
            preparedStatement.setString(7,doctor.getProfile());
            preparedStatement.executeUpdate();
            System.out.println("Added doctor to the database");
            doctorList.add(doctor);
        }catch (Exception e){e.printStackTrace();}
    }
    public void addDoctorToList(){
        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader("C:\\VD\\IIITS\\UG_2\\sem_3\\OOPS\\project_oops\\src\\CSVfiles\\doctor.csv"))) {
            String doctorLine;
            while ((doctorLine=bufferedReader.readLine())!=null)
                doctorList.add(new Doctor(doctorLine));
        }catch (Exception e){e.printStackTrace();}
    }
    public  void createFromCSV(String CSVpath){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(CSVpath))) {
            String doctorLine;
            while ((doctorLine=bufferedReader.readLine())!=null)
                create(new Doctor(doctorLine));
            System.out.println("Added CSV data to the database");
        }catch (Exception e){e.printStackTrace();}
    }
    public void display(){
        try {
            int a=0;
            System.out.println();
            System.out.format("%83s","+--------------------------------+\n");
            System.out.format("%83s","|   WELCOME TO MARVEL HOSPITAL   |\n");
            System.out.format("%83s","+--------------------------------+\n");
            System.out.println("\n");
            System.out.format("%15s","  +--------------+\n");
            System.out.format("%16s","  | DOCTOR TABLE |\n");
            System.out.format("%15s","  +--------------+\n");

            String sql="select * from doctors";
            Connection connection = ConnectionFactory.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                a += resultSetMetaData.getColumnName(i).length()+2;

            for (int i=0;i<=a+12;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+12) System.out.print("-+");
                else System.out.print("-");
            System.out.println();
            System.out.print("|");
            for (int i=1;i<=resultSetMetaData.getColumnCount();i++) {
                if (i==resultSetMetaData.getColumnCount())
                    System.out.print(resultSetMetaData.getColumnName(i)+"   | ");
                else
                    System.out.print(resultSetMetaData.getColumnName(i)+"  | ");
            }

            System.out.println();

            for (int i=0;i<=a+12;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+12) System.out.print("-+");
                else System.out.print("-");
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
                    System.out.format("| ","%-"+(resultSetMetaData.getColumnName(i).length())+"s","  |");
                    if(i==1) System.out.format("%-"+(resultSetMetaData.getColumnName(i).length()+1)+"s",
                            resultSet.getString(i));
                    else System.out.format("%-"+(resultSetMetaData.getColumnName(i).length()+2)+"s",
                            resultSet.getString(i));
                }
                System.out.print(" |");
                System.out.println();
            }
            for (int i=0;i<=a+12;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+12) System.out.print("-+");
                else System.out.print("-");
        }catch (SQLException e){System.out.println(e.getMessage());}
    }
    public  void update(String DocID,String column,String info){
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "update doctors set "+column+" = "+"'"+info+"'"+" where docID = "+"'"+DocID+"'";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Updated the doctor "+column);
        }catch (SQLException e){//System.out.println("The column to be updated is not present.");
            e.printStackTrace(); }
    }
    public void remove(String DocID) {
        Connection connection= ConnectionFactory.getConnection();
        String sql="delete from doctors where docID = ?";
        assert connection != null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.setString(1,DocID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            int a=preparedStatement.executeUpdate();
            if (a>0)
                System.out.println("Removed doctor from the database");
            else
                System.out.println("Doesn't Exist");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void bulkUpdate(String csv){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csv))) {
            String doctorline;
            int size=0;
            while((doctorline=bufferedReader.readLine())!=null){

                String[] att =doctorline.split(",");
                String sql="select * from doctors where docID ='"+att[0]+"'";

                Connection connection = ConnectionFactory.getConnection();
                assert connection != null;
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                ResultSet resultSet=preparedStatement.executeQuery();

                while(resultSet.next()) size++;
                if(size==1){
                    preparedStatement=connection.prepareStatement(
                            "delete from doctors where docID ='"+att[0]+"'");
                    preparedStatement.executeUpdate();
                } create(new Doctor(doctorline));
            }
            System.out.println("BULK UPDATING SUCCESSFUL");
        }catch (Exception e){
            System.out.println(e.getMessage());}
    }
    public void search(String columns,String constraint){
        try {
            int a=0;
            String sql="select "+columns+" from doctors where "+constraint;

            Connection connection = ConnectionFactory.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                a += resultSetMetaData.getColumnName(i).length()+2;
            for (int i=0;i<=a+2;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+2) System.out.print("-+");
                else System.out.print("-");
            System.out.println();
            System.out.print("|");
            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                System.out.print(resultSetMetaData.getColumnName(i)+" | ");
            System.out.println();
            for (int i=0;i<=a+2;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+2) System.out.print("-+");
                else System.out.print("-");
            System.out.println();

            //
            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
                    System.out.format("|","%"+(resultSetMetaData.getColumnName(i).length())+"s","|");
                    //
                    if(i==1) System.out.format("%"+(resultSetMetaData.getColumnName(i).length()+1)+"s",resultSet.getString(i));
                    else System.out.format("%"+(resultSetMetaData.getColumnName(i).length()+2)+"s",resultSet.getString(i));
                }
                System.out.println();
            }
            for (int i=0;i<=a+2;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+2) System.out.print("-+");
                else System.out.print("-");
        }catch (SQLException e){System.out.println(e.getMessage());}
    }
    public void search(String columns){
        try {
            int a=0;
            String sql="select "+columns+" from doctors";

            Connection connection = ConnectionFactory.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                a += resultSetMetaData.getColumnName(i).length()+2;
            for (int i=0;i<=a+2;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+2) System.out.print("-+");
                else System.out.print("-");
            System.out.println();
            System.out.print("|");
            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                System.out.print(resultSetMetaData.getColumnName(i)+" | ");
            System.out.println();
            for (int i=0;i<=a+2;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+2) System.out.print("-+");
                else System.out.print("-");
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
                    System.out.format("|","%"+(resultSetMetaData.getColumnName(i).length()/2)+"s","|");
                    if(i==1) System.out.format("%"+(resultSetMetaData.getColumnName(i).length()+1)+"s",resultSet.getString(i));
                    else System.out.format("%"+(resultSetMetaData.getColumnName(i).length()+2)+"s",resultSet.getString(i));
                }
                System.out.println();
            }
            for (int i=0;i<=a+2;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+2) System.out.print("-+");
                else System.out.print("-");
        }catch (SQLException e){System.out.println(e.getMessage());}
    }
    public String setDoctorToPatient(Patient patient){
        Collections.sort(doctorList);
        Doctor doctor = doctorList.get(0);
        doctor.setPatientCOunt(doctor.getPatientCOunt()+1);
        System.out.println(doctor.getPatientCOunt());
        doctor.PatiensWorkedWith.add(patient);
        return "Doctor "+doctor.getName()+" assigned to patient "+patient.getPatientName();
    }
    public void show(){
        try{
            String sql="select * from doctors";
            Connection connection = ConnectionFactory.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

            System.out.format("%15s","+--------------------------------+\n");
            System.out.format("%15s","|   Doctor table column names   |\n");
            System.out.format("%15s","+--------------------------------+\n");
            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                System.out.print(resultSetMetaData.getColumnName(i)+" ");
            System.out.println("\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
