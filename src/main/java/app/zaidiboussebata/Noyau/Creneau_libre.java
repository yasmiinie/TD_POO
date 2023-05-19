package app.zaidiboussebata.Noyau;


import java.time.LocalTime;
import java.io.Serializable;
import java.time.Duration;



/*  | pour mettre le temps sous forme 12:00 |
	import java.time.LocalTime;
	import java.time.format.DateTimeFormatter;
	LocalTime currentTime = LocalTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
	String formattedTime = currentTime.format(formatter);
*/

public class Creneau_libre implements Serializable{
    public LocalTime debut;
    public LocalTime fin;
    public static Duration  dureeMin = Duration.ofMinutes(30);

    public Creneau_libre(LocalTime debut,LocalTime fin) {
        // TODO Auto-generated constructor stub
        this.debut = debut;
        this.fin = fin;

    }


    /**
     * permet de calculer la duree entre deux instance (debut et fin)
     * @param debut
     * @param fin
     * @return Duree
     */
    public static Duration calculeDuree(LocalTime debut, LocalTime fin) {
        return Duration.between(debut, fin);
    }

}
