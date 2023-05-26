package app.zaidiboussebata.Noyau;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Periode {
    private LocalDate dPeriode;
    private LocalDate fPeriode;

    public Periode() {

    }
    public Periode(LocalDate dPeriode,LocalDate fPeriode) {
        this.dPeriode = dPeriode;
        this.fPeriode = fPeriode;

    }
    public LocalDate getfPeriode() {
        return fPeriode;
    }
    public void setfPeriode(LocalDate fPeriode) {
        this.fPeriode = fPeriode;
    }
    public LocalDate getdPeriode() {
        return dPeriode;
    }
    public void setdPeriode(LocalDate dPeriode) {
        this.dPeriode = dPeriode;
    }


    /*public static boolean isCreneauInside(Creneau_libre creneau, LocalDate dPeriode, LocalDate fPeriode) {
        LocalDate creneauDate = creneau.getDate();
        LocalTime creneauDebut = creneau.getDebut();
        LocalTime creneauFin = creneau.getFin();

        LocalDateTime dPeriodeStart = dPeriode.atStartOfDay();
        LocalDateTime fPeriodeEnd = fPeriode.atTime(LocalTime.MAX);

        return creneauDate.isAfter(dPeriode) && creneauDate.isBefore(fPeriode)
                && ((creneauDebut.isAfter(dPeriodeStart.toLocalTime())
                        && creneauFin.isBefore(fPeriodeEnd.toLocalTime()))
                    || (creneauDebut.equals(dPeriodeStart.toLocalTime())
                        && creneauFin.equals(fPeriodeEnd.toLocalTime())));
    }*/

    /**
     * retourne true si la date est entre le debut et la fin de la periode entrée
     * @param date
     * @param dPeriode
     * @param fPeriode
     * @return
     */
    public  boolean isDateInside(LocalDate date, LocalDate dPeriode, LocalDate fPeriode) {


        return ((date.isAfter(dPeriode) && date.isBefore(fPeriode))||date.equals(dPeriode)|| date.equals(fPeriode));
    }
}
