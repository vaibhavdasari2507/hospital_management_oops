package DAOpackage;
public interface DAO {
    void create(Object o);
    void display() ;
    void createFromCSV(String CSVpath);
    void remove(String s);
    void update(String a,String b,String c);
    void bulkUpdate(String CSVpath);
    void search(String a);
    void search(String columns,String constraint);
    void show();
}



































