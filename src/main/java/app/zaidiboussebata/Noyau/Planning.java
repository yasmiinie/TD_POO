package app.zaidiboussebata.Noyau;


import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Planning implements Serializable {
    public SimpleTache tache;
    public Creneau_libre creneau;
    public Boolean bloquee = false;
    Periode periode ;

    public Planning(SimpleTache tache2, Creneau_libre newCreneau) {
        this.tache= tache2;
        this.creneau = newCreneau;
        // TODO Auto-generated constructor stub
    }



    public Planning() {
        // TODO Auto-generated constructor stub
    }


//----------------------------------| trouverCreneauxCompatibles |--------------------------------------//
    /**
     * Parcours la liste des creneaux libres et identifie les creneaux compatibles avec la tache.
     *et les trier par rapport la longeur de tableau
     * @param tacheList        Liste des taches
     * @param creneauLibreList Liste des creneaux libres
     * @return Un tableau de deux cases. La première case contient la tache et la deuxième case contient les indices
     * des creneaux compatibles avec la tache.
     */

    public List<TacheCreneauPair> trouverCreneauxCompatibles(List<Tache> tacheList, List<Creneau_libre> creneauLibreList, LocalDate deadline) {

        System.out.println("\n\n************************| Debut Fonction |*******************");

        List<TacheCreneauPair> result = new ArrayList<>();
        creneauLibreList = Creneau_libre.CreneauxInfDeadline(creneauLibreList, deadline);
        Creneau_libre freeSlot = new Creneau_libre() ;
        freeSlot.afficherCreneauxLibres(creneauLibreList);
        System.out.println("\n\n************************| Fonction Avant boucle  |*******************");
        int i = 0;
        for (Tache tache : tacheList) {

            List<Integer> indicesCompatibles = new ArrayList<>();
            System.out.println("+Tache : | Nom = "+tache.nom);

            for (int j = 0; j < creneauLibreList.size(); j++) {
                Creneau_libre creneau = creneauLibreList.get(j);
                Duration dureeCreneau = Creneau_libre.calculeDuree(creneau.getDebut(), creneau.getFin());

                if (dureeCreneau.compareTo(tache.duree) >= 0) {
                    indicesCompatibles.add(j+i);
                    System.out.println("+Creneau[" + j + "]: | Date = " + creneau.getDate() + " | Debut" + creneau.getDebut() + " | ");
                }
            }

            if (!indicesCompatibles.isEmpty()) {
                TacheCreneauPair pair = new TacheCreneauPair(tache, indicesCompatibles);
                result.add(pair);
            }
        }
        System.out.println("\n\n************************| Fin Fonction |*******************");
        Collections.sort(result, new Comparator<TacheCreneauPair>() {
            @Override
            public int compare(TacheCreneauPair pair1, TacheCreneauPair pair2) {
                int length1 = pair1.getIndicesCreneaux().size();
                int length2 = pair2.getIndicesCreneaux().size();
                return Integer.compare(length1, length2);
            }
        });
        return result;
    }


    /**
     *  planifier tous les n jours
     * @param from
     * @param to
     * @param n
     * @param tache
     * @param creneau
     * @param FICHIER_HISTORIQUE_PLANNING
     */
    public void plannifier_n_jours(LocalDate from,LocalDate to,int n,SimpleTache tache,Creneau_libre creneau,String FICHIER_HISTORIQUE_PLANNING) {
        List<HistoriquePlanning> historiquePlanList= Utilisateur.recupererObjetFichier(FICHIER_HISTORIQUE_PLANNING);


        List<Planning> listplanList = new ArrayList<>();
        HistoriquePlanning historiquePlanning = new HistoriquePlanning();

        if(historiquePlanList.size()!= 0) {
            historiquePlanning = historiquePlanning.recupererHistorique(historiquePlanList, historiquePlanList.size()-1);
        }

        Planning plan = new Planning();
        plan.tache = tache;
        plan.creneau = creneau;
        while (Periode.isDateInside(creneau.getDate(), from, to)) {
            creneau.setDate(plan.creneau.getDate().plusDays(n));
            System.out.println("\n| Date: " + plan.creneau.getDate());

            Creneau_libre newCreneau = new Creneau_libre(plan.creneau.getDebut(), plan.creneau.getFin(), creneau.getDate());
            Planning newPlanning = new Planning(plan.tache, newCreneau);

            listplanList.add(newPlanning);
            plan.creneau.setDate(creneau.getDate());
        }
        System.out.println("\n\n inside insidedebnjduiebiub\n");


        historiquePlanning.listPlanning.addAll(listplanList);
        historiquePlanning.ajouterHistoriquePlanning(historiquePlanList, historiquePlanning.listPlanning);

        Utilisateur.sauvegarderObjetFichier(FICHIER_HISTORIQUE_PLANNING, historiquePlanList);
        for(Planning p:historiquePlanning.listPlanning) {
            System.out.println("\n name : "+p.tache.nom+" | Date : "+p.creneau.getDate());
        }
    }

//-------------------------------| TacheCompatibleCreneau |-------------------------------//
    /**
     * Vérifie si une tâche est compatible avec un créneau libre en comparant leurs durées.
     *
     * @param tache La tâche à vérifier.
     * @param creneau Le créneau libre à comparer.
     * @return {@code true} si la durée de la tâche est inférieure ou égale à la durée du créneau, {@code false} sinon.
     */
    public boolean TacheCompatibleCreneau(Tache tache, Creneau_libre creneau) {
        Duration dureeCreneau = Duration.between(creneau.getDebut(), creneau.getFin());
        LocalDate creneauDate = creneau.getDate();
        return ( ( creneauDate.isBefore(tache.deadline) || creneauDate.equals(tache.deadline) ) && (dureeCreneau.compareTo(tache.duree) >= 0)) ;
    }



//----------------------------------| plannifier |--------------------------------------//
    /**
     * permet de planifier la list des taches
     * @param debut de la periode
     * @param fin de la periode
     * @param tacheList la liste des taches
     * @param CreneauLibreList  la liste des creneaux libres
     * @param planningList la liste du planning a retournee
     * @return
     */
    public Boolean plannifier(LocalDate debut, LocalDate fin, List<SimpleTache> tacheList, List<Creneau_libre> CreneauLibreList, List<Planning> planningList) {

        System.out.println("Debut == " + debut + " | fin == " + fin);

        Boolean trouve = false;
        Boolean stop = false;
        Boolean reportee = false;
        int j = 0;

        for (SimpleTache tache : tacheList) {
            // on parcourt la liste des taches
            j = 0;
            trouve = false;
            stop = false;

            System.out.println("\n\n+Tache: | Name = " + tache.nom + " | Duree = " + tache.duree + " | deadline = " + tache.deadline + " Trouve = " + trouve + " Stop : " + stop);

    if(tache.etat == Etat.UNSCHEDULED){
    while (j < CreneauLibreList.size() && trouve == false && stop == false) {
        // tant qu'on a pas trouver ou la mettre

        Creneau_libre creneau = CreneauLibreList.get(j); // on parcourt la liste des creneaux
        System.out.println("Creneau : | Date " + creneau.getDate() + " | Duree = " + Duration.between(creneau.getDebut(), creneau.getFin()));

        if (tache.deadline.isAfter(debut) || tache.deadline.equals(debut)) {
            // si le ddl est entre la periode

            if (Periode.isDateInside(creneau.getDate(), debut, fin)) {
                //pour chaque creneau on verifie si sa date est dans la periode

                System.out.println("\n_______________________________| Creneau inside periode ");

                if (TacheCompatibleCreneau(tache, creneau)) {
                    //Vérifie si une tâche est compatible avec un créneau libre en comparant leurs durées.
                    System.out.println("\n===============================| Tache compatible avec creneau");
                    tache.etat = Etat.IN_PROGRESS;
                    Planning plan = new Planning();// alors elle sera planifier

                    if (tache.duree.plusMinutes(creneau.dureeMin.toMinutes()).compareTo(Duration.between(creneau.getDebut(), creneau.getFin())) <= 0) {
                        // le creneau sera diviser en 2
                        System.out.println("\n+++++++++++++++++++++++++++|Tache+DureeMin inferieur a Duree de Creneau");
                        Creneau_libre cr = new Creneau_libre(creneau.getDebut(), creneau.getDebut().plus(tache.duree), creneau.getDate());
                        plan.tache = tache;
                        plan.creneau = cr;
                        planningList.add(plan);
                        LocalTime time = creneau.getDebut().plus(tache.duree);
                        creneau.setDebut(time);
                    } else {
                        // tous le creneau sera occupee par la tache
                        System.out.println("\n+++++++++++++++++++++++++++|Tache+DureeMin superieur a Duree de Creneau");
                        plan.tache = tache;
                        plan.creneau = creneau;
                        planningList.add(plan);
                        CreneauLibreList.remove(j);
                    }

                    trouve = true; // on a trouvee ou la mettre donc on s'arrete
                } else {
                    // si la tache nest pas compatible avec le creneau alors on passe au creneau suivant
                    System.out.println("\n===============================| Tache : " + tache.nom + " deuree " + tache.duree + " ne est pas compatible avec creneau date " + creneau.getDate() + " duree : " + creneau.getDebut() + " | " + creneau.getFin());
                    if (tache.type.equals("DECOMPOSABLE")) {
                        int i = 1;
                        System.out.println("\n\n\n=====| DDDDDEEEECOMPOOO|================\n\n");

                        Duration totalDuration = Duration.ZERO;
                        Duration f;

                        // Calculate the total duration of available Creneau_libre objects
                        for (Creneau_libre creneau1 : CreneauLibreList) {
                            if (Periode.isDateInside(creneau1.getDate(), debut, fin)) {
                                f = Duration.between(creneau1.getDebut(), creneau1.getFin());
                                totalDuration = totalDuration.plus(f);
                            }
                        }
                        System.out.println("\nDuree total des creneaux = "+totalDuration);

                        if (totalDuration.compareTo(tache.duree) >= 0) {
                            System.out.println("\nDuree total des creneaux comp avec tache");



                            j = 0;
                            while(j < CreneauLibreList.size() || tache.duree.isZero()) {

                                Creneau_libre creneau1 = CreneauLibreList.get(j);
                                if(Periode.isDateInside(creneau1.getDate(), debut, fin)) {


                                    Planning x = new Planning();

                                    Duration d = Duration.between(creneau1.getDebut(), creneau1.getFin());
                                    if(tache.duree.compareTo(d)<= 0) {
                                        System.out.println("\n=========="+tache.duree+" sghire"+d+"\n");
                                        Creneau_libre c = new Creneau_libre(creneau1.getDebut(), creneau1.getDebut().plusMinutes(tache.duree.toMinutes()), creneau1.getDate());
                                        creneau1.setDebut( creneau1.getDebut().plusMinutes(tache.duree.toMinutes()));
                                        SimpleTache t = new SimpleTache(tache.nom+i, tache.duree , tache.deadline);
                                        i++;
                                        x.tache = t;
                                        x.tache.etat = Etat.IN_PROGRESS;
                                        x.creneau = c;

                                        tache.duree = tache.duree.minus(tache.duree);

                                        planningList.add(x);
                                        j++;
                                        break;

                                    }else{
                                        x.creneau = creneau1;
                                        SimpleTache t = new SimpleTache(tache.nom+i, d, tache.deadline);
                                        i++;
                                        x.tache = t;
                                        x.tache.etat = Etat.IN_PROGRESS;
                                        tache.duree = tache.duree.minus(d);
                                        planningList.add(x);
                                        CreneauLibreList.remove(j);
                                        j++;
                                    }
                                }
                            }

                            tache.etat = Etat.IN_PROGRESS;
                            for(Planning plan : planningList) {
                                System.out.println("\n+ plan = nom : "+plan.tache.nom+"| Etat = "+plan.tache.etat+" | debut : "+plan.creneau.getDebut()+" | fin : "+plan.creneau.getFin()+" | date  : "+plan.creneau.getDate());
                            }
                            break;
                        } else {
                            System.out.println("Duree total infer a duree tache decomposable");
                            tache.etat = Etat.UNSCHEDULED;
                        }
                    }else {
                        tache.etat = Etat.UNSCHEDULED;
                    }
                }
            } else {
                //pour chaque creneau on verifie si sa date est dans la periode
                // si ce nest pas le cas alors on la reporte
                if (creneau.getDate().isAfter(fin)) {
                    reportee = true; // on demande si il veut etalee la periode
                    System.out.println("\n\n REEEEEEEEEEEEEEEEEEEEEEEPPPPPPPPPOOOOOOOORTTTTEEEEEEEE\n\n");
                }
                System.out.println("\n___________teest test ____________________Creneau outside periode " + creneau.getDate() + " periode = " + debut + " | " + fin);
            }
        } else {
            // si le ddl n'est pas dans la periode
            System.out.println("\n_______________________________deadline outside periode ");
            tache.etat = Etat.UNSCHEDULED; // on ne peut pas le planifier alors on s'arrete
            stop = true; //
        }

        System.out.println(" Trouve = " + trouve + " +Stop = " + stop);

        if (stop == false && trouve == false && j == CreneauLibreList.size() && tache.deadline.isAfter(fin)) {
            System.out.println("\n\n\n\n\n\n +| Lazem tzid la periode |+");
        }

        j++;
    }
}
        }

        return reportee;
    }



}




