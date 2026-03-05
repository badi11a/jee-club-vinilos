package cl.club.vinilos.modelo;

import java.sql.Date;

public class Prestamo {
    private int idPrestamo;
    private int idSocio;
    private int idVinilo;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String nombreSocio;
    private String tituloVinilo;

    public Prestamo() {}

    public int getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(int idPrestamo) { this.idPrestamo = idPrestamo; }
    public int getIdSocio() { return idSocio; }
    public void setIdSocio(int idSocio) { this.idSocio = idSocio; }
    public int getIdVinilo() { return idVinilo; }
    public void setIdVinilo(int idVinilo) { this.idVinilo = idVinilo; }
    public Date getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(Date fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public Date getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(Date fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
    public String getNombreSocio() { return nombreSocio; }
    public void setNombreSocio(String nombreSocio) { this.nombreSocio = nombreSocio; }
    public String getTituloVinilo() { return tituloVinilo; }
    public void setTituloVinilo(String tituloVinilo) { this.tituloVinilo = tituloVinilo; }
}