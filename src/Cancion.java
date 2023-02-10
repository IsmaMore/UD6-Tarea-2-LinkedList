public class Cancion {
    private String titulo;
    private double duracion;

    public Cancion(String tit, double dur){
        this.titulo = tit;
        this.duracion = dur;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo + ": " + duracion;
    }
}
