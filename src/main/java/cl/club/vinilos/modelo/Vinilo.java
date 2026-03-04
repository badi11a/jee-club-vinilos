package cl.club.vinilos.modelo;

public class Vinilo {
    private int id;
    private String titulo;
    private String artista;
    private int anioLanzamiento;
    private String genero;

    public Vinilo() {}

    public Vinilo(int id, String titulo, String artista, int anioLanzamiento, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.anioLanzamiento = anioLanzamiento;
        this.genero = genero;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public int getAnioLanzamiento() { return anioLanzamiento; }
    public void setAnioLanzamiento(int anioLanzamiento) { this.anioLanzamiento = anioLanzamiento; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}