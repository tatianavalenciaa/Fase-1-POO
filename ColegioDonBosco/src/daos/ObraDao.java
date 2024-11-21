package daos;

import conexion.Conexion;
import entidades.Obra;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObraDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(ObraDao.class);
    
    public List<Obra> obtenerObras() throws SQLException {
        
        List<Obra> obras = new ArrayList<>();
        
        sql = 
            """
            select 
                *
            from 
                obra
            order by 
                codigo asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Obra obra = new Obra();
                obra.setCodigo(rs.getString("codigo"));
                obra.setTitulo(rs.getString("titulo"));
                obra.setAutor(rs.getString("autor"));
                obra.setEditorial(rs.getString("editorial"));
                obra.setGenero(rs.getString("genero"));
                obra.setUnidadesDisp(rs.getInt("unidades_disp"));
                obra.setUbicacion(rs.getString("ubicacion"));
                obras.add(obra);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return obras;
        
    }
    
    public Obra obtenerObra(String codigo) throws SQLException {
        
        Obra obra = null;
        
        sql = 
            """
            select 
                *
            from 
                obra
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
                obra = new Obra();
                obra.setCodigo(rs.getString("codigo"));
                obra.setTitulo(rs.getString("titulo"));
                obra.setAutor(rs.getString("autor"));
                obra.setEditorial(rs.getString("editorial"));
                obra.setGenero(rs.getString("genero"));
                obra.setUnidadesDisp(rs.getInt("unidades_disp"));
                obra.setUbicacion(rs.getString("ubicacion"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return obra;
        
    }
    
    public void registrarObra(Obra obra) throws SQLException {
        
        sql = 
            """
            insert into obra (
                codigo, titulo, autor, editorial, genero, unidades_disp, ubicacion
            )
            values (
                ?, ?, ?, ?, ?, ?, ? 
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, obra.getCodigo());
            stmt.setString(++i, obra.getTitulo());
            stmt.setString(++i, obra.getAutor());
            stmt.setString(++i, obra.getEditorial());
            stmt.setString(++i, obra.getGenero());
            stmt.setInt(++i, obra.getUnidadesDisp());
            stmt.setString(++i, obra.getUbicacion());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarObra(Obra obra) throws SQLException {
        
        sql = 
            """
            update 
                obra
            set
                titulo = ?, 
                autor = ?, 
                editorial = ?, 
                genero = ?, 
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
            stmt.setString(++i, obra.getTitulo());
            stmt.setString(++i, obra.getAutor());
            stmt.setString(++i, obra.getEditorial());
            stmt.setString(++i, obra.getGenero());
            stmt.setInt(++i, obra.getUnidadesDisp());
            stmt.setString(++i, obra.getUbicacion());
            stmt.setString(++i, obra.getCodigo());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void eliminarObra(String codigo) throws SQLException {
        
        sql = 
            """
            delete from
                obra
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
