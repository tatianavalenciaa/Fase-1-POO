package daos;

import conexion.Conexion;
import entidades.ParametroSistema;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParametroSistemaDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(ParametroSistemaDao.class);
    
    public List<ParametroSistema> obtenerParametroSistemas() throws SQLException {
        
        List<ParametroSistema> parametroSistemas = new ArrayList<>();
        
        sql = 
            """
            select 
                *
            from 
                parametro_sistema
            order by 
                id_parametro_sistema asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                ParametroSistema parametroSistema = new ParametroSistema();
                parametroSistema.setIdParametroSistema(rs.getInt("id_parametro_sistema"));
                parametroSistema.setNombre(rs.getString("nombre"));
                parametroSistema.setDescripcion(rs.getString("descripcion"));
                parametroSistema.setValor(rs.getString("valor"));
                parametroSistemas.add(parametroSistema);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return parametroSistemas;
        
    }
    
    public ParametroSistema obtenerParametroSistema(Integer id) throws SQLException {
        
        ParametroSistema parametroSistema = null;
        
        sql = 
            """
            select 
                *
            from 
                parametro_sistema
            where
                id_parametro_sistema = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, id);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                parametroSistema = new ParametroSistema();
                parametroSistema.setIdParametroSistema(rs.getInt("id_parametro_sistema"));
                parametroSistema.setNombre(rs.getString("nombre"));
                parametroSistema.setDescripcion(rs.getString("descripcion"));
                parametroSistema.setValor(rs.getString("valor"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return parametroSistema;
        
    }
    
    public ParametroSistema obtenerParametroSistemaXNombre(String nombre) throws SQLException {
        
        ParametroSistema parametroSistema = null;
        
        sql = 
            """
            select 
                *
            from 
                parametro_sistema
            where
                nombre = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, nombre);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                parametroSistema = new ParametroSistema();
                parametroSistema.setIdParametroSistema(rs.getInt("id_parametro_sistema"));
                parametroSistema.setNombre(rs.getString("nombre"));
                parametroSistema.setDescripcion(rs.getString("descripcion"));
                parametroSistema.setValor(rs.getString("valor"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return parametroSistema;
        
    }
    
    public void registrarParametroSistema(ParametroSistema parametroSistema) throws SQLException {
        
        sql = 
            """
            insert into parametro_sistema (
                nombre, descripcion, valor
            )
            values (
                ?, ?, ?
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, parametroSistema.getNombre());
            stmt.setString(++i, parametroSistema.getDescripcion());
            stmt.setString(++i, String.valueOf(parametroSistema.getValor()));
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarParametroSistema(ParametroSistema parametroSistema) throws SQLException {
        
        sql = 
            """
            update 
                parametro_sistema
            set
                nombre = ?,
                descripcion = ?,
                valor = ?
            where 
                id_parametro_sistema = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, parametroSistema.getNombre());
            stmt.setString(++i, parametroSistema.getDescripcion());
            stmt.setString(++i, String.valueOf(parametroSistema.getValor()));
            stmt.setInt(++i, parametroSistema.getIdParametroSistema());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void eliminarParametroSistema(Integer id) throws SQLException {
        
        sql = 
            """
            delete from
                parametro_sistema
            where
                id_parametro_sistema = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, id);
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
}
