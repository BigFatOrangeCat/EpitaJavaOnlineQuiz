package quiz.epita.database;

import java.sql.Connection;
import java.sql.DriverManager;
 public class H2database
    {
		private static final String DB_PASSWORD = "";
		private static final String DB_USERNAME = "sa";
		private static final String DB_URL = "jdbc:h2:tcp://localhost/C:/1test/h2DS";
	    private static final String DRIVER_CLASS="org.h2.Driver";
		public static Connection conn = null; //Database connect the instance
		public H2database()
		{
			try
			{
				if(conn==null)
				{
					Class.forName(DRIVER_CLASS).newInstance();//load the driver
					conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);//Connection by URL, username and password
				}
				else return;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
    }