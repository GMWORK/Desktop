package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by mateo on 11/05/15.
 */
@DatabaseTable(tableName = "ProductoLog")
public class ProductoLog {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private String Op;
    @DatabaseField
    private String fecha;
    @DatabaseField
    private long idProducto;

    public ProductoLog(String Op, String fecha, long idProducto) {
        this.Op = Op;
        this.fecha = fecha;
        this.idProducto = idProducto;
    }

    public ProductoLog() {
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getOperacion() {
        return Op;
    }

    public void setOperacion(String operacion) {
        this.Op = operacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "ProductoLog{"
                + "id=" + id
                + ", operacion='" + Op + '\''
                + ", fecha=" + fecha
                + ", idProducto=" + idProducto
                + '}';
    }
}
