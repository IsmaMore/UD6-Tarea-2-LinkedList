import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Album {
    private String nombre;
    private String artista;
    private ArrayList<Cancion> canciones;

    public Album(String nom, String art){
        this.nombre = nom;
        this.artista = art;
        canciones = new ArrayList<Cancion>();
    }

    public boolean addSong(String tit, double dur){
        if (findSong(tit) == null){
            Cancion cancion = new Cancion(tit, dur);
            canciones.add(cancion);
            return true;
        }
        return false;
    }

    private Cancion findSong(String tit){
        for (int i = 0; i < canciones.size(); i++){
            if (canciones.get(i).getTitulo().equalsIgnoreCase(tit)){
                return canciones.get(i);
            }
        }
        return null;
    }

    private Cancion findSong(Cancion can, LinkedList<Cancion> playList){
            Iterator<Cancion> it = playList.iterator();
            for (int i = 0; it.hasNext(); i++){
                if (it.next().getTitulo() == can.getTitulo()){
                    return playList.get(i);
                }
            }
        return null;
    }

    public boolean addToPlayList(int numPista, LinkedList<Cancion> playList){
        Cancion cancion;
        if (numPista < canciones.size() && numPista >= 0) {
            cancion = canciones.get(numPista);
        }else {
            cancion = null;
        }
        if (cancion != null) {
            if (findSong(cancion, playList) == null) {
                playList.add(cancion);
                return true;
            }
        }
        return false;
    }

    public boolean addToPlayList(String titulo, LinkedList<Cancion> playList){
        Cancion cancion = findSong(titulo);
        if (cancion != null) {
            if (findSong(cancion, playList) == null) {
                playList.add(cancion);
                return true;
            }
        }
        return false;
    }
}
