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
                            tache.etat = Etat.UNSCHEDULED;
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

        return reportee;
    }



}




