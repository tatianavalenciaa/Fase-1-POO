package utilidades;

import daos.ParametroSistemaDao;
import entidades.ParametroSistema;
import entidades.Prestamo;
import java.util.Calendar;
import java.util.Date;

public class Utilidades {
    
    public static Double calcularMora(Prestamo prestamo) {
        ParametroSistemaDao parametroSistemaDao = new ParametroSistemaDao();
        long diasEnMora;
        Double mora = 0.0;
        
        try {
            // Obtenemos el precio de mora diaria parametrizada en la Base de Datos
            ParametroSistema parametroSistema = parametroSistemaDao.obtenerParametroSistemaXNombre("MORA");
            Double parametroMora = Double.valueOf(parametroSistema.getValor());

            Date fechaHoy = new Date();
            Calendar fechaHoyCal = Calendar.getInstance();
            fechaHoyCal.setTime(fechaHoy);
            Calendar fechaDevolucionCal = Calendar.getInstance();
            fechaDevolucionCal.setTime(prestamo.getFechaDevolucion()); 

            long diffMiliSegundos = fechaHoyCal.getTimeInMillis() - fechaDevolucionCal.getTimeInMillis();
            if(diffMiliSegundos < 0) {
                mora = 0.0;                
            } else {
                diasEnMora = Math.round(diffMiliSegundos / (1000 * 60 * 60 * 24.0));
                mora = diasEnMora * parametroMora;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mora;
    }
    
}
