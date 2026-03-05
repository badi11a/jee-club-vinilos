package cl.club.vinilos.modelo;

public class Vinilo {
    private int idVinilo;
    private String titulo;
    private String artista;
    private int anioLanzamiento;
    private String genero;
    private boolean disponible;

    public Vinilo() {}

    public Vinilo(int idVinilo, String titulo, String artista, int anioLanzamiento, String genero, boolean disponible) {
        this.idVinilo = idVinilo;
        this.titulo = titulo;
        this.artista = artista;
        this.anioLanzamiento = anioLanzamiento;
        this.genero = genero;
        this.disponible = disponible;
    }

    public int getIdVinilo() { return idVinilo; }
    public void setIdVinilo(int idVinilo) { this.idVinilo = idVinilo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public int getAnioLanzamiento() { return anioLanzamiento; }
    public void setAnioLanzamiento(int anioLanzamiento) { this.anioLanzamiento = anioLanzamiento; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}