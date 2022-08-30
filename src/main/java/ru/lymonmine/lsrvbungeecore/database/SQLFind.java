package ru.lymonmine.lsrvbungeecore.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.lymonmine.lsrvbungeecore.main;

public class SQLFind {
    String user = main.instanse.config.getString("mysql.login");
    String pass = main.instanse.config.getString("mysql.password");
    String db = main.instanse.config.getString("mysql.db");
    String host = main.instanse.config.getString("mysql.ip");
    String urlmysql = "jdbc:mysql://" + host + ":3306" + "/" + db;
    String urlsqlite = "jdbc:sqlite:" + main.instanse.getDataFolder() + File.separator + "database.db";
    boolean enable = main.instanse.config.getBoolean("mysql.enable");

    public SQLFind() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
        Class.forName("org.sqlite.JDBC").getConstructor().newInstance();

        Connection conn = getConnection();
        Statement s = conn.createStatement();

        s.executeUpdate("CREATE TABLE IF NOT EXISTS lsrvbungeecore_find(name TEXT, data TEXT, uuid TEXT);");

        s.close();
        conn.close();
    }

    public Connection getConnection() throws SQLException {

        if (enable) {
            return DriverManager.getConnection(urlmysql, user, pass);
        } else {
            return DriverManager.getConnection(urlsqlite);
        }
    }

    public void saveData(String player, String data) {
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate("UPDATE lsrvbungeecore_find SET data = '" + data + "' WHERE name = '" + player + "';");
            s.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDataJoin(String player, String uuid) throws SQLException {
            if (getData(player) == null) {
                    Connection conn = getConnection();
                    Statement s = conn.createStatement();
                    s.executeUpdate("INSERT INTO `lsrvbungeecore_find`(`name`, `data`, `uuid`) VALUES ('" + player + "','none','" + uuid+"');");
                    s.close();
                    conn.close();
            }
    }

    public String getData(String player) throws SQLException {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT data FROM lsrvbungeecore_find WHERE name='" + player + "';");
        while (rs.next()) {
            String outs = rs.getString("data");
            s.close();
            conn.close();
            return outs;
        }
        return null;
    }
    public String getUUID(String player) throws SQLException {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT uuid FROM lsrvbungeecore_find WHERE name='" + player + "';");
        while (rs.next()) {
            String outs = rs.getString("uuid");
            s.close();
            conn.close();
            return outs;
        }
        return null;
    }


}
	
