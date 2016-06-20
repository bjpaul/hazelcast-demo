package map.store.configure.db;


import java.sql.*;

public class JDBCConnection implements AutoCloseable{

    private static JDBCConnection jdbcConnection;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    static {
        jdbcConnection = new JDBCConnection();
    }

    public static JDBCConnection getConnnection(){
        return jdbcConnection;
    }
    public static int executeUpdate(String query) throws ClassNotFoundException,SQLException, InterruptedException{
        if(connection == null || connection.isClosed()){
            connection = connection();
        }
        statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    public static ResultSet executeQuery(String query) throws ClassNotFoundException,SQLException, InterruptedException{
        if(connection == null || connection.isClosed()){
            connection = connection();
        }
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }
    private static Connection connection() throws ClassNotFoundException, SQLException, InterruptedException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/intellimeet", "root", "igdefault");
        return connection;
    }


    @Override
    public void close() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
