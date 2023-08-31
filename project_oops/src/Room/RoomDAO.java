package Room;
import DAOpackage.DAO;
import Driver.ConnectionFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class RoomDAO implements DAO {

    public void create(Object o){
        if(Room.roomCount<10){
            try {
                Room room = (Room) o;
                Connection connection = ConnectionFactory.getConnection();
                String sql = "insert into room values(?,?,?,?,?)";
                assert connection != null;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, room.getRoomNo());
                preparedStatement.setString(2, room.getPatientID());
                preparedStatement.setString(3, room.getAdmissionDate());
                preparedStatement.setString(4, room.getDischargeDate());
                preparedStatement.setBoolean(5, room.getStatus());
                preparedStatement.executeUpdate();
                System.out.println("Added Room to the database");
                Room.roomCount++;
            }catch (Exception e){e.printStackTrace();}
        }
        else{
            System.out.println("Room limit has been exceeded");
        }
    }
    public void createFromCSV(String CSVpath){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CSVpath));
            String RoomLine;
            while ((RoomLine=bufferedReader.readLine())!=null)
                create(new Room(RoomLine));
            System.out.println("Added CSV data to the database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void remove(String roomNo) {
        Connection connection= ConnectionFactory.getConnection();
        String sql="delete from room where roomNo = ?";

        assert connection != null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.setString(1,roomNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            int a=preparedStatement.executeUpdate();
            if (a>0)
                System.out.println("Removed room from the database");
            else
                System.out.println("Doesn't Exist");
        } catch (SQLException e) {
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
            System.out.format("%15s","  +------------+\n");
            System.out.format("%16s","  | ROOM TABLE |\n");
            System.out.format("%15s","  +------------+\n");

            String sql="select * from room";
            Connection connection = ConnectionFactory.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                a += resultSetMetaData.getColumnName(i).length()+2;

            for (int i=0;i<=a+12-3-1;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+12-3-1) System.out.print("-+");
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

            for (int i=0;i<=a+12-3-1;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+12-3-1) System.out.print("-+");
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
            for (int i=0;i<=a+12-3-1;i++)
                if(i==0) System.out.print("+-");
                else if(i==a+12-3-1) System.out.print("-+");
                else System.out.print("-");
        }catch (SQLException e){System.out.println(e.getMessage());}
    }
    public void update(String rID,String column,String info) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "update room set "+column+" = "+"'"+info+"'"+" where roomNo = "+"'"+rID+"'";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Updated the room's "+column);
        }catch (SQLException e){
            System.out.println("The column to be updated is not present.");
        }
    }
    public void bulkUpdate(String csv){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csv))) {
            String roomline;
            int size=0;
            while((roomline=bufferedReader.readLine())!=null){

                String[] att =roomline.split(",");

                String sql="select * from room where roomNo ='"+att[0]+"'";
                Connection connection = ConnectionFactory.getConnection();
                assert connection != null;
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                ResultSet resultSet=preparedStatement.executeQuery();

                while(resultSet.next()) size++;
                if(size>=1){
                    preparedStatement=connection.prepareStatement(
                            "delete from room where roomNo ='"+att[0]+"'");
                    preparedStatement.executeUpdate();
                } create(new Room(roomline));
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void search(String columns,String constraint){
        try {
            int a=0;
            String sql="select "+columns+" from room where "+constraint;

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
    public void search(String columns){
        try {
            int a=0;
            String sql="select "+columns+" from room";

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
            String sql="select * from room";
            Connection connection = ConnectionFactory.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
            System.out.format("%15s","+-----------------------------+\n");
            System.out.format("%15s","|   Room table column names   |\n");
            System.out.format("%15s","+-----------------------------+\n");
            for (int i=1;i<=resultSetMetaData.getColumnCount();i++)
                System.out.print(resultSetMetaData.getColumnName(i)+" ");
            System.out.println("\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}