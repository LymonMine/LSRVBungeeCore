package ru.lymonmine.lsrvbungeecore.database;

import ru.lymonmine.lsrvbungeecore.main;

import java.io.File;
import java.sql.*;

public class SQLMaintenance {
    String user = main.instanse.config.getString("mysql.login");
    String pass = main.instanse.config.getString("mysql.password");
    String db = main.instanse.config.getString("mysql.db");
    String host = main.instanse.config.getString("mysql.ip");
    String urlmysql = "jdbc:mysql://" + host + ":3306" + "/" + db;
    String urlsqlite = "jdbc:sqlite:" + main.instanse.getDataFolder() + File.separator + "database.db";
    boolean enable = main.instanse.config.getBoolean("mysql.enable");

    public SQLMaintenance() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
        Class.forName("org.sqlite.JDBC").getConstructor().newInstance();

        Connection conn = getConnection();
        Statement s = conn.createStatement();

        s.executeUpdate("CREATE TABLE IF NOT EXISTS lsrvbungeecore_maintenance(enable INT);");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS lsrvbungeecore_maintenance_plist(playername TEXT);");
        setFirstEnable();

    }

    public Connection getConnection() throws SQLException {

        if (enable) {
            return DriverManager.getConnection(urlmysql, user, pass);
        } else {
            return DriverManager.getConnection(urlsqlite);
        }
    }

    public void addPlayerMaintenance(String player) {
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate("INSERT INTO `lsrvbungeecore_maintenance_plist`(`playername`) VALUES ('" + player + "')");
            s.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removePlayerMaintenance(String player) {
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate("DELETE FROM `lsrvbungeecore_maintenance_plist` WHERE playername='" + player + "';");
            s.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPlayerContains(String player) {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT `playername` FROM `lsrvbungeecore_maintenance_plist` WHERE playername='" + player + "';");
            while (rs.next()) {
                String outs = rs.getString("playername");
                s.close();
                conn.close();
                if (outs == null || outs.equals("")) {
                    return false;
                } else {
                    return true;
                }
            }
            return false;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void saveEnable() {
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate("UPDATE lsrvbungeecore_maintenance SET enable = 1;");
            s.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDisable() {
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate("UPDATE lsrvbungeecore_maintenance SET enable = 0;");
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public int getEnabled() throws SQLException {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT enable FROM lsrvbungeecore_maintenance;");
        while (rs.next()) {
            int outs = rs.getInt("enable");
            s.close();
            conn.close();
            return outs;
        }
        return 0;
    }

    private void setFirstEnable() throws SQLException {
        if (!(getEnabled() == 1) || !(getEnabled() == 0)) {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate("INSERT INTO `lsrvbungeecore_maintenance`(`enable`) VALUES (0)");
            s.close();
            conn.close();
        }
    }
}