package daos;

import conexion.Conexion;
import entidades.Documento;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DocumentoDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(DocumentoDao.class);
    
    public List<Documento> obtenerDocumentos() throws SQLException {
        
        List<Documento> documentos = new ArrayList<>();
        
        sql = 
            """
            select 
                *
            from 
                documento
            order by 
                codigo asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Documento documento = new Documento();
                documento.setCodigo(rs.getString("codigo"));
                documento.setTitulo(rs.getString("titulo"));
                documento.setAutor(rs.getString("autor"));
                documento.setEditorial(rs.getString("editorial"));
                documento.setTematica(rs.getString("tematica"));
                documento.setUnidadesDisp(rs.getInt("unidades_disp"));
                documento.setUbicacion(rs.getString("ubicacion"));
                documentos.add(documento);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return documentos;
        
    }
    
    public Documento obtenerDocumento(String codigo) throws SQLException {
        
        Documento documento = null;
        
        sql = 
            """
            select 
                *
            from 
                documento
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
                documento = new Documento();
                documento.setCodigo(rs.getString("codigo"));
                documento.setTitulo(rs.getString("titulo"));
                documento.setAutor(rs.getString("autor"));
                documento.setEditorial(rs.getString("editorial"));
                documento.setTematica(rs.getString("tematica"));
                documento.setUnidadesDisp(rs.getInt("unidades_disp"));
                documento.setUbicacion(rs.getString("ubicacion"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return documento;
        
    }
    
    public void registrarDocumento(Documento documento) throws SQLException {
        
        sql = 
            """
            insert into documento (
                codigo, titulo, autor, editorial, tematica, unidades_disp, ubicacion
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
            stmt.setString(++i, documento.getCodigo());
            stmt.setString(++i, documento.getTitulo());
            stmt.setString(++i, documento.getAutor());
            stmt.setString(++i, documento.getEditorial());
            stmt.setString(++i, documento.getTematica());
            stmt.setInt(++i, documento.getUnidadesDisp());
            stmt.setString(++i, documento.getUbicacion());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarDocumento(Documento documento) throws SQLException {
        
        sql = 
            """
            update 
                documento
            set
                titulo = ?, 
                autor = ?, 
                editorial = ?, 
                tematica = ?, 
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
            stmt.setString(++i, documento.getTitulo());
            stmt.setString(++i, documento.getAutor());
            stmt.setString(++i, documento.getEditorial());
            stmt.setString(++i, documento.getTematica());
            stmt.setInt(++i, documento.getUnidadesDisp());
            stmt.setString(++i, documento.getUbicacion());
            stmt.setString(++i, documento.getCodigo());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void eliminarDocumento(String codigo) throws SQLException {
        
        sql = 
            """
            delete from
                documento
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
