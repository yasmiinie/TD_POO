package app.zaidiboussebata.Noyau;


import java.time.Duration;
import java.time.LocalDate;

public class SimpleTache extends app.zaidiboussebata.Noyau.Tache {


    public SimpleTache(String s, Duration duree, LocalDate deadline,Etat etat,String type,Categorie categorie,Priorite priorite) {
        super();
        this.nom = s;
        this.duree = duree ;
        this.deadline = deadline ;
        this.type = type ;
        this.etat = etat ;
        this.categorie = categorie;
        this.priorite = priorite;
    }



    public SimpleTache(){}
}
