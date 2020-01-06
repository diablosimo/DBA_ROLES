package service;

import bean.Role;
import util.Connexion;
import util.QueryUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RoleService extends Connexion {
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
        Connection conn = getConnection();
        int count = 0;
        try {
            stmt = conn.createStatement();
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

    public int grant(List<String> granties, List<String> granted,Boolean withAdminOption){
        if(granties==null || granties.isEmpty() ){
            return -1;
        }else if(granted==null ||granted.isEmpty()){
            return -2;
        }else{
            String query="GRANT ";
            query+= QueryUtil.explodeStringList(granties);
            query+="TO ";
            query+=QueryUtil.explodeStringList(granted);
            if(withAdminOption==true)
                query+="WITH ADMIN OPTION";

            exec(query);
            return 1;
        }
    }

    private void exec(String query) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
