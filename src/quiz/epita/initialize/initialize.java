package quiz.epita.initialize;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class initialize {

    //Connect the H2 database with URL
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/C:/1test/h2DS";
    //Database username
    private static final String USER = "sa";
    //Database password
    private static final String PASSWORD = "";
    //H2 database driver
    private static final String DRIVER_CLASS="org.h2.Driver";

    public static void main(String[] args) throws Exception {
        //load the driver
        Class.forName(DRIVER_CLASS);
        //get connection with H2 database by username, password and URL
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        //Delete the former table if exists
        stmt.execute("DROP TABLE IF EXISTS USER_INFO");
        stmt.execute("DROP TABLE IF EXISTS STUDENT_INFO");
        stmt.execute("DROP TABLE IF EXISTS TEACHER_INFO");
        stmt.execute("DROP TABLE IF EXISTS QUESTION_INFO");
        stmt.execute("DROP TABLE IF EXISTS RESULT_INFO");
        //Create USER_INFO table
        stmt.execute("CREATE TABLE USER_INFO(PrivateID VARCHAR(100),name VARCHAR(100),place VARCHAR(100),identity VARCHAR(100))");
        //Create STUDENT_INFO table
        stmt.execute("CREATE TABLE STUDENT_INFO(PrivateID VARCHAR(100),firstname VARCHAR(100),lastname VARCHAR(100),sex VARCHAR(100),nationality VARCHAR(100),difficulty VARCHAR(100))");
        //Create Question_INFO table
        stmt.execute("CREATE TABLE QUESTION_INFO(id int identity(1,1) PRIMARY KEY,difficulty VARCHAR(100),content VARCHAR(65535),choiceA VARCHAR(65535),choiceB VARCHAR(65535),choiceC VARCHAR(65535),choiceD VARCHAR(65535),correct VARCHAR(100))");
        //Create Result_INFO tabe
        stmt.execute("CREATE TABLE RESULT_INFO(answer VARCHAR(10),credit INT(100))");
        /*
        //Create TEACHER_INFO table
        stmt.execute("CREATE TABLE TEACHER_INFO(PrivateID VARCHAR(100),name VARCHAR(100),place VARCHAR(100),identity VARCHAR(100))");
        */
        //add Students
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('123','JIANGJICHEN','Kremlin-Bicetre','Student')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('456','JIANG','Villejuif','Student')");
        //add Teacher
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('65535','Thomas','Kremlin-Bicetre','Teacher')");
        //add Questions
        stmt.executeUpdate("INSERT INTO QUESTION_INFO VALUES('1','1','To define a class, the key word is:','A. class','B. public','C. struct','D. public of struct','A')");
        stmt.executeUpdate("INSERT INTO QUESTION_INFO VALUES('2','1','1+1=?','A. 2','B. 3','C. 4','D. 5','A')");
        stmt.executeUpdate("INSERT INTO QUESTION_INFO VALUES('3','2','1*1=?','A. 2','B. 3','C. 4','D. 5','A')");
        stmt.executeUpdate("INSERT INTO QUESTION_INFO VALUES('4','2','1/1=?','A. 2','B. 3','C. 4','D. 5','A')");
        stmt.executeUpdate("INSERT INTO QUESTION_INFO VALUES('5','3','1+2=?','A. 2','B. 3','C. 4','D. 5','B')");
        stmt.executeUpdate("INSERT INTO QUESTION_INFO VALUES('6','3','1*2=?','A. 2','B. 3','C. 4','D. 5','A')");
        
        //Delete
        //stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='JIANG'");
        //Change
        //stmt.executeUpdate("UPDATE USER_INFO SET name='JIANGJICHEN' WHERE name='JIANG'");
        //Search¯¢
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO");
        //Traversal
        
        while (rs.next()) {

        System.out.println(rs.getString("PrivateID") + "," + rs.getString("name")+ "," + rs.getString("place") + "," + rs.getString("identity"));
        }
        
        //Release the resource
        stmt.close();
        //Disable the connection
        conn.close();

    }

}
