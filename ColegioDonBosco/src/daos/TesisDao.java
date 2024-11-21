package daos;

import conexion.Conexion;
import entidades.Tesis;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TesisDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(TesisDao.class);
    
    public List<Tesis> obtenerTesises() throws SQLException {
        
        List<Tesis> tesises = new ArrayList<>();
        
        sql = 
            """
            select 
                *
            from 
                tesis
            order by 
                codigo asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Tesis tesis = new Tesis();
                tesis.setCodigo(rs.getString("codigo"));
                tesis.setTitulo(rs.getString("titulo"));
                tesis.setAutor(rs.getString("autor"));
                tesis.setEditorial(rs.getString("editorial"));
                tesis.setFacultad(rs.getString("facultad"));
                tesis.setCarrera(rs.getString("carrera"));
                tesis.setUnidadesDisp(rs.getInt("unidades_disp"));
                tesis.setUbicacion(rs.getString("ubicacion"));
                tesises.add(tesis);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return tesises;
        
    }
    
    public Tesis obtenerTesis(String codigo) throws SQLException {
        
        Tesis tesis = null;
        
        sql = 
            """
            select 
                *
            from 
                tesis
            where
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, codigo);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                tesis = new Tesis();
                tesis.setCodigo(rs.getString("codigo"));
                tesis.setTitulo(rs.getString("titulo"));
                tesis.setAutor(rs.getString("autor"));
                tesis.setEditorial(rs.getString("editorial"));
                tesis.setFacultad(rs.getString("facultad"));
                tesis.setCarrera(rs.getString("carrera"));
                tesis.setUnidadesDisp(rs.getInt("unidades_disp"));
                tesis.setUbicacion(rs.getString("ubicacion"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return tesis;
        
    }
    
    public void registrarTesis(Tesis tesis) throws SQLException {
        
        sql = 
            """
            insert into tesis (
                codigo, titulo, autor, editorial, facultad, carrera, unidades_disp, ubicacion
            )
            values (
                ?, ?, ?, ?, ?, ?, ?, ? 
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, tesis.getCodigo());
            stmt.setString(++i, tesis.getTitulo());
            stmt.setString(++i, tesis.getAutor());
            stmt.setString(++i, tesis.getEditorial());
            stmt.setString(++i, tesis.getFacultad());
            stmt.setString(++i, tesis.getCarrera());
            stmt.setInt(++i, tesis.getUnidadesDisp());
            stmt.setString(++i, tesis.getUbicacion());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarTesis(Tesis tesis) throws SQLException {
        
        sql = 
            """
            update 
                tesis
            set
                titulo = ?, 
                autor = ?, 
                editorial = ?, 
                facultad = ?, 
                carrera = ?, 
                unidades_disp = ?,
                ubicacion = ?
            where 
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, tesis.getTitulo());
            stmt.setString(++i, tesis.getAutor());
            stmt.setString(++i, tesis.getEditorial());
            stmt.setString(++i, tesis.getFacultad());
            stmt.setString(++i, tesis.getCarrera());
            stmt.setInt(++i, tesis.getUnidadesDisp());
            stmt.setString(++i, tesis.getUbicacion());
            stmt.setString(++i, tesis.getCodigo());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void eliminarTesis(String codigo) throws SQLException {
        
        sql = 
            """
            delete from
                tesis
            where
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, codigo);
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    
}
