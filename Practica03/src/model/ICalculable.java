package model;

import java.util.Calendar;

public interface ICalculable {
    // Constantes de la clase Calendar traducidas al español
    int DIA_DEL_MES = Calendar.DAY_OF_MONTH;
    int MES = Calendar.MONTH;
    int ANIO = Calendar.YEAR;

    boolean cumpleMes();
    boolean cumpleAnio();
}
