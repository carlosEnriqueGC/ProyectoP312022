/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.modelo;

import seguridad.controlador.clsPerfilAplicaciones;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class daoPerfilApps {

    private static final String SQL_SELECT = "SELECT apl_idPerfil,apl_idaplicacion FROM tbl_aplicacionesperfil";
    private static final String SQL_INSERT = "INSERT INTO tbl_aplicacionesperfil(apl_idPerfil, apl_idaplicacion) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE tbl_aplicacionesperfil SET apl_idPerfil=?, apl_idaplicacion=? WHERE apl_idPerfil = ?";
    private static final String SQL_DELETE = "DELETE FROM tbl_aplicacionesperfil WHERE apl_idPerfil=?";
    private static final String SQL_QUERY = "SELECT apl_idPerfil, apl_idaplicacion FROM tbl_aplicacionesperfil WHERE tbl_aplicacionesperfil = ?";

    public List<clsPerfilAplicaciones> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsPerfilAplicaciones usuario = null;
        List<clsPerfilAplicaciones> usuarios = new ArrayList<clsPerfilAplicaciones>();
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                String perfil = rs.getString("apl_idPerfil");
                String aplicacion = rs.getString("apl_idaplicacion");

                usuario = new clsPerfilAplicaciones();
               
                usuario.setUsername(perfil);
                usuario.setPassword(aplicacion);

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(rs);
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return usuarios;
    }

    public int insert(clsPerfilAplicaciones perfil_app) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, perfil_app.getUsername());
            stmt.setString(2, perfil_app.getPassword());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public int update(clsPerfilAplicaciones perfil_app) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, perfil_app.getUsername());
            stmt.setString(2, perfil_app.getPassword());
            

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public int delete(clsPerfilAplicaciones perfil_app) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public clsPerfilAplicaciones query(clsPerfilAplicaciones perfil_app) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, perfil_app.getUsername());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String username = rs.getString("apl_idPerfil");
                String password = rs.getString("apl_idaplicacion");

                perfil_app = new clsPerfilAplicaciones();
                perfil_app.setUsername(username);
                perfil_app.setPassword(password);
            }
            //System.out.println("Registros buscado:" + persona);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(rs);
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        //return personas;  // Si se utiliza un ArrayList
        return perfil_app;
    }
}