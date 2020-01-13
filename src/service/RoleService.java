package service;

import bean.Role;
import util.Connexion;
import util.QueryUtil;
import util.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleService extends Connexion {

    Connection connection=Session.getConnection();

    public int create(Role role) {
        if (role != null && role.getName() != null && !role.getName().isEmpty()) {
            if (isCreated(role.getName())) {
                return -3;
            }
            String query = "CREATE ROLE " + role.getName();
            if (executeUpdate(role, query)) return -1;
        } else {
            return -2;
        }
        return 0;
    }

    public boolean isCreated(String name) {
        Statement stmt = null;
        ResultSet rs = null;
       // Connection conn = Session.getConnection();
        int count = 0;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT count (*) AS total FROM DBA_ROLES WHERE ROLE LIKE '" + name + "'");
            while (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                stmt = null;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int alter(Role role) {
        if (role != null && role.getName() != null && !role.getName().isEmpty()) {
            if (!isCreated(role.getName())) return -1;
            else {
                String query = "ALTER ROLE " + role.getName();
                if (executeUpdate(role, query)) return -2;
            }
        }
            return 0;
        }

    private boolean executeUpdate(Role role, String query) {
        query=buildIdentifier(role,query);
        if(query==null) return true;
        System.out.println(query);
        exec(query);
        return false;
    }

    private String buildIdentifier(Role role, String query) {
        if (role.getIdentified() == false) {
            query += " NOT IDENTIFIED";
        } else {
            query += " IDENTIFIED ";
            if (role.getExternal()) {
                query += "EXTERNALLY";
            } else if (role.getGlobal()) {
                query += "GLOBALLY";
            } else if (!role.getPakage().isEmpty() && !role.getSchema().isEmpty()) {
                query += "USING " + role.getSchema() + "." + role.getPakage() + "";
            } else if (role.getPassword() != null && !role.getPassword().isEmpty()) {
                query += "BY " + role.getPassword() + "";
            } else {
                return null;
            }
        }
        return query;
    }

    public String grant(List<String> granties, List<String> granted,Boolean isPublic,Boolean withAdminOption){
        String result="";
        if(granties==null || granties.isEmpty() ){
            return "veuillez choisir un role.";
        }else if(granted==null ||granted.isEmpty()){
            return "Veuillew choisir un utilisateur ou un role.";
        }else{
            String query="GRANT ";
            query+= QueryUtil.explodeStringList(granties);
            query+="TO ";
            query+=QueryUtil.explodeStringList(granted);
            if(isPublic==true)
                query+=" ,PUBLIC";
            if(withAdminOption==true)
                query+=" WITH ADMIN OPTION";
            result=exec(query);
            return result;
        }
    }

    private String exec(String query) {
        //Connection connection = null;
        Statement statement = null;
        String result="";
        try {
            //connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            result="Success";
        } catch (SQLException e) {
            e.printStackTrace();
            result=e.getMessage();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static List<String> findAll(){
        Statement stmt = null;
        ResultSet rs = null;
         Connection conn = Session.getConnection();
         String role;
         List<String> roles=new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT ROLE AS ROLE FROM DBA_ROLES ORDER by ROLE asc");
            while (rs.next()) {
                role = rs.getString("ROLE");
                roles.add(role);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                stmt = null;
            }
        }
        System.out.println(roles);
        return roles;
    }
    public static List<String> findAllUsers(){
        Statement stmt = null;
        ResultSet rs = null;
         Connection conn = Session.getConnection();
         String user;
         List<String> users=new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT USERNAME AS USERNAME FROM ALL_USERS ORDER by USERNAME asc");
            while (rs.next()) {
                user = rs.getString("USERNAME");
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                stmt = null;
            }
        }
        System.out.println(users);
        return users;
    }
    public static List<String> findAllSysPrivs(){
        Statement stmt = null;
        ResultSet rs = null;
         Connection conn = Session.getConnection();
         String priv;
         List<String> privs=new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT DISTINCT (PRIVILEGE) AS USERNAME FROM DBA_SYS_PRIVS ORDER by USERNAME asc");
            while (rs.next()) {
                priv = rs.getString("USERNAME");
                privs.add(priv);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                stmt = null;
            }
        }
        return privs;
    }



}
