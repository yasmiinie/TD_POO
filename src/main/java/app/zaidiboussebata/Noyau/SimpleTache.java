package app.zaidiboussebata.Noyau;


import java.time.Duration;
import java.time.LocalDate;

public class SimpleTache extends app.zaidiboussebata.Noyau.Tache {


    public SimpleTache(String s, Duration duree, LocalDate deadline) {
        super();
        this.nom = s;
        this.duree = duree ;
        this.deadline = deadline ;
    }

    public SimpleTache(){}
}
