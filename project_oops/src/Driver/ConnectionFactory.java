package Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory{
    private static final String URL="jdbc:mysql://127.0.0.1:3306/project_oops";
    private static final String USERNAME="root";
    private static final String PASSWORD="302052";
    public static Connection getConnection(){
        Properties properties=new Properties();
        properties.put("user",USERNAME);
        properties.put("password",PASSWORD);
        try{
           // System.out.println("Connection established");
            return DriverManager.getConnection(URL,properties);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
