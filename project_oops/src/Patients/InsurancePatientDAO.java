package Patients;
import DAOpackage.DAO;
import Driver.ConnectionFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class InsurancePatientDAO extends PatientDAO implements DAO {
    public void create(Object o) {
        try {
            InsurancePatient Ipatient = (InsurancePatient) o;
            Connection connection = ConnectionFactory.getConnection();
            String sql = "insert into insurancepatients values(?,?,?,?,?,?,?,?,?,?)";
            assert connection != null;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, Ipatient.getPatientID());
            preparedStatement.setString(2, Ipatient.getPatientName());
            preparedStatement.setInt(3, Ipatient.getAge());
            preparedStatement.setString(4, Ipatient.getGender());
            preparedStatement.setString(5, Ipatient.getAdmissionDate());
            preparedStatement.setString(6, Ipatient.getBloodGrp());
            preparedStatement.setString(7, Ipatient.getPhoneNo());
            preparedStatement.setString(8, Ipatient.getStatus());
            preparedStatement.setString(9, Ipatient.getInsuranceID());
            preparedStatement.setInt(10, Ipatient.getInsurancePremium());
            preparedStatement.executeUpdate();
            System.out.println("Added patient to the database");
        }catch (Exception e){e.printStackTrace();}
    }
    public void createFromCSV(String CSVpath){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CSVpath));
            String patientLine;
            while ((patientLine=bufferedReader.readLine())!=null)
                create(new InsurancePatient(patientLine));
            System.out.println("Added CSV data to the database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void display(){
        try {
            int a=0;
            System.out.println();
            System.out.format("%83s","+--------------------------------+\n");
            System.out.format("%83s","|   WELCOME TO MARVEL HOSPITAL   |\n");
            System.out.format("%83s","+--------------------------------+\n");
            System.out.println("\n");
            System.out.format("%15s","  +--------------------------+\n");
            System.out.format("%16s","  | INSURANCE PATIENTS TABLE |\n");
            System.out.format("%15s","  +--------------------------+\n");

            String sql="select * from insurancepatients";
            Connection connection = ConnectionFactory.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                a += resultSetMetaData.getColumnName(i).length()+2;

            for (int i=0;i<=a+21;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+21) System.out.print("-+");
                else System.out.print("-");
            System.out.println();
            System.out.print("|");
            for (int i=1;i<=resultSetMetaData.getColumnCount();i++) {
                if (i==resultSetMetaData.getColumnCount()-2)
                    System.out.print(resultSetMetaData.getColumnName(i)+"        | ");
                else
                    System.out.print(resultSetMetaData.getColumnName(i)+"  | ");
            }

            System.out.println();

            for (int i=0;i<=a+21;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+21) System.out.print("-+");
                else System.out.print("-");
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
                    System.out.format("| ","%-"+(resultSetMetaData.getColumnName(i).length())+"s","  |");
                    if(i==1)
                        System.out.format("%-"+(resultSetMetaData.getColumnName(i).length()+1)+"s",
                                resultSet.getString(i));
                    else if(i==resultSetMetaData.getColumnCount()-2)
                        System.out.format("%-"+(resultSetMetaData.getColumnName(i).length()+8)+"s",
                                resultSet.getString(i));
                    else System.out.format("%-"+(resultSetMetaData.getColumnName(i).length()+2)+"s",
                                resultSet.getString(i));
                }
                System.out.print(" |");
                System.out.println();
            }
            for (int i=0;i<=a+21;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+21) System.out.print("-+");
                else System.out.print("-");
        }catch (SQLException e){System.out.println(e.getMessage());}
    }
    public void update(String pID,String column,String info){
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "update insurancepatients set "+column+" = "+"'"+info+"'"+" where patientID = "+"'"+pID+"'";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Updated the patient's "+column);
        }catch (SQLException e){
            System.out.println("The column to be updated is not present.");
        }
    }
    public void bulkUpdate(String csv){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csv))) {
            String patientline;
            int size=0;
            while((patientline=bufferedReader.readLine())!=null){

                String[] att =patientline.split(",");

                String sql="select * from insurancepatients where patientID ='"+att[1]+"'";
                Connection connection = ConnectionFactory.getConnection();
                assert connection != null;
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                ResultSet resultSet=preparedStatement.executeQuery();

                while(resultSet.next()) size++;
                if(size>=1){
                    preparedStatement=connection.prepareStatement(
                            "delete from insurancepatients where patientID ='"+att[1]+"'");
                    preparedStatement.executeUpdate();
                }create(new InsurancePatient(patientline));
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void search(String columns,String constraint){
        try {
            int a=0;
            String sql="select "+columns+" from insurancepatients where "+constraint;

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
                    System.out.format("|","%"+(resultSetMetaData.getColumnName(i).length())+"s","|");
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
            String sql="select "+columns+" from insurancepatients";

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
    public void show(){
        try{
            String sql="select * from insurancepatients";
            Connection connection = ConnectionFactory.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
            System.out.format("%15s","+-------------------------------------------+\n");
            System.out.format("%15s","|   Insurance Patients table column names   |\n");
            System.out.format("%15s","+-------------------------------------------+\n");
            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                System.out.print(resultSetMetaData.getColumnName(i)+" ");
            System.out.println("\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void remove(String patientID) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "delete from insurancepatients where patientID = ?";
            assert connection != null;
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, patientID);
            int a=preparedStatement.executeUpdate();
            if (a>0)
                System.out.println("Removed patient from the database");
            else
                System.out.println("Doesn't Exist");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
