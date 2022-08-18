package jdbcConnection;

import environmentConfig.Environment;
import org.aeonbits.owner.ConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
    public static Connection getMySqlConnection() {
        String environmentName = System.getProperty("envMaven");
        ConfigFactory.setProperty("envOwner", environmentName);
        Environment env = ConfigFactory.create(Environment.class);
        return getMySqlConnection(env.dbHotName(), env.dbPort(), env.ddName(), env.dbUserName(), env.dbPassword());

    }

    private static Connection getMySqlConnection(String hostName, String port, String dbName, String userName,
                                                 String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName;
            conn = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
