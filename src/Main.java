import java.util.*;

public class Main {
    public static void mostrarPlayList(LinkedList<Cancion> playList){
        System.out.println("-------PlayList-------");
        ListIterator<Cancion> it = playList.listIterator();
        while (it.hasNext()){
            System.out.println((it.nextIndex() + 1) + ". " + it.next().toString());
        }
        System.out.println("----------------------");
    }

    public static void mostrarMenu(){
        System.out.print(
                "---------MENU---------" + "\n" +
                "0 – Salir de la lista de reproducción." + "\n" +
                "1 – Reproducir siguiente canción en la lista." + "\n" +
                "2 – Reproducir la canción previa de la lista." + "\n" +
                "3 – Repetir la canción actua." + "\n" +
                "4 – Imprimir la lista de canciones en la playlist" + "\n" +
                "5 – Volver a imprimir el menú." + "\n" +
                "6 – Eliminar canción actual de la playlist." + "\n" +
                "----------------------" + "\n" +
                "--> "
        );
    }

    public static int leerInt(){
        Scanner sc = new Scanner(System.in);
        int i;
        while (true) {
            try {
                i = sc.nextInt();
                return i;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Error: No es válido.");
                mostrarMenu();
            }
        }
    }

    public static void play(LinkedList<Cancion> listaDeReproduccion){
        int opcion = 0;
        boolean reproduc = false;
        boolean direccion = true;
        boolean repetir;
        ListIterator<Cancion> it = listaDeReproduccion.listIterator();

        do {
            repetir = true;
            if (!reproduc){
                System.out.println("Reproduciendo --> Nada");
            }
            if (listaDeReproduccion.size() == 0){
                System.out.println("Terminando programa: No hay canciones en la lista.");
                break;
            }else {
                mostrarMenu();
            }
            opcion = leerInt();
            while (repetir){
                repetir = false;
                switch (opcion){
                    case 0:
                        break;
                    case 1:
                        reproduc = true;
                        if (!direccion) {
                            if (it.hasNext()){
                                it.next();
                            }
                            if (it.hasNext()) {
                                System.out.println("Reproduciendo " + "pista " + (it.nextIndex() + 1) + " --> " + it.next().toString());
                                direccion = true;
                            } else {
                                System.out.println("Error: No hay más canciones.");
                                direccion = true;
                                repetir = true;
                                opcion = 3;
                            }
                        }else {
                            if (it.hasNext()) {
                                System.out.println("Reproduciendo " + "pista " + (it.nextIndex() + 1) + " --> " + it.next().toString());
                            } else {
                                System.out.println("Error: No hay más canciones.");
                                repetir = true;
                                opcion = 3;
                            }
                        }
                        break;
                    case 2:
                        if (direccion) {
                            if (it.hasPrevious()){
                                it.previous();
                            }
                            if (it.hasPrevious()) {
                                System.out.println("Reproduciendo " + "pista " + (it.previousIndex() + 1) + " --> " + it.previous().toString());
                                direccion = false;
                            } else {
                                System.out.println("Error: Estas añ final de la lista, no hay más canciones.");
                                direccion = false;
                                repetir = true;
                                opcion = 3;
                            }
                        }else {
                            if (it.hasPrevious()) {
                                System.out.println("Reproduciendo " + "pista " + (it.previousIndex() + 1) + " --> " + it.previous().toString());
                            } else {
                                System.out.println("Error: Estas al principio de la lista, no hay más canciones.");
                                repetir = true;
                                opcion = 3;
                            }
                        }
                        break;
                    case 3:
                        if (it.hasPrevious()) {
                            if (reproduc && direccion) {
                                it.previous();
                                System.out.println("Reproduciendo " + "pista " + (it.nextIndex() + 1) + " --> " + it.next().toString());
                            }else if (reproduc) {
                                it.next();
                                System.out.println("Reproduciendo " + "pista " + (it.previousIndex() + 1) + " --> " + it.previous().toString());
                            }
                        }else if (it.hasNext()){
                            if (reproduc && direccion) {
                                it.previous();
                                System.out.println("Reproduciendo " + "pista " + (it.nextIndex() + 1) + " --> " + it.next().toString());
                            }else if (reproduc) {
                                it.next();
                                System.out.println("Reproduciendo " + "pista " + (it.previousIndex() + 1) + " --> " + it.previous().toString());
                            }
                        }
                        break;
                    case 4:
                        mostrarPlayList(listaDeReproduccion);
                        if (reproduc){
                            repetir = true;
                            opcion = 3;
                        }
                        break;
                    case 5:
                        break;
                    case 6:
                        if (listaDeReproduccion.size() > 0){
                            int index;
                            if (!it.hasPrevious()){
                                listaDeReproduccion.remove(0);
                                index = 0;
                            }else if (!it.hasNext()){
                                index = it.nextIndex();
                                listaDeReproduccion.remove(index - 1);
                                index -= 1;
                            }else if (direccion){
                                index = it.nextIndex();
                                listaDeReproduccion.remove(index - 1);
                            }else {
                                index = it.nextIndex();
                                listaDeReproduccion.remove(index);
                                index += 1;
                                direccion = true;
                            }
                            it = listaDeReproduccion.listIterator();
                            for (int i = 0; i < index; i++){
                                it.next();
                            }
                            repetir = true;
                            opcion = 3;
                        }
                        break;
                }
            }
            if (opcion > 6 || opcion < 0){
                System.out.println("Error: Opción no válida");
            }
        }while (opcion != 0);
    }

    public static void main(String[] args) {
        ArrayList<Album> Lista = new ArrayList<Album>();
        Lista.add(new Album("Como me gusta", "Bad Bunny"));
        Lista.add(new Album("Hasia el sielo", "Romeo Santos"));
        Lista.get(0).addSong("Si veo a tu mama", 3.41);
        Lista.get(0).addSong("Agachate", 2.34);
        Lista.get(0).addSong("Valley Of The Fallen", 7.51);
        Lista.get(0).addSong("Zapatillas", 4.12);
        Lista.get(1).addSong("Volví", 3.23);
        Lista.get(1).addSong("Obsesión", 2.12);
        Lista.get(1).addSong("Propuesta Indecente", 4.01);
        Lista.get(1).addSong("Ella y yo", 1.56);
        LinkedList<Cancion> listaDeReproduccion = new LinkedList<Cancion>();
        Lista.get(0).addToPlayList("Agachate", listaDeReproduccion);
        Lista.get(0).addToPlayList("si veo a tu mama", listaDeReproduccion);
        Lista.get(0).addToPlayList(3, listaDeReproduccion);
        Lista.get(1).addToPlayList("Ella y yo", listaDeReproduccion);
        Lista.get(1).addToPlayList("Obsesión", listaDeReproduccion);
        Lista.get(1).addToPlayList(2, listaDeReproduccion);
        //mostrarPlayList(listaDeReproduccion);
        play(listaDeReproduccion);

    }
}
