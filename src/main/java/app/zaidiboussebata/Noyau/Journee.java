package app.zaidiboussebata.Noyau;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

public class Journee {

    public LocalDate date;
    public int nbrTachesRealisee;
    public Badge badge;

    public static List<Planning> tacheOfDay(LocalDate d, String FICHIER_HISTORIQUE_PLANNING) {
        List<HistoriquePlanning> historiquePlanList= Utilisateur.recupererObjetFichier(FICHIER_HISTORIQUE_PLANNING);
        HistoriquePlanning history = new HistoriquePlanning() ;
        HistoriquePlanning historiquePlanning ;
        List<Planning> dateList = new ArrayList<>();

        if (historiquePlanList.size() > 0){
            historiquePlanning= history.recupererHistorique(historiquePlanList, historiquePlanList.size()-1);
            int j= 0;
            while(j< historiquePlanning.listPlanning.size() ) {

                Planning plan = historiquePlanning.listPlanning.get(j);
                if(d.equals(plan.creneau.getDate())) {
                    dateList.add(plan);
                }
                j++;
            }
        }

        return dateList;
    }
    public static <T> Etat getEtatGlobal(List<T> itemList) {
        Etat etatGlobal = null;

        for (T item : itemList) {
            Etat etat;

            if (item instanceof Planning) {
                Planning plan = (Planning) item;
                etat = plan.tache.etat;
            } else if (item instanceof SimpleTache) {
                SimpleTache tache = (SimpleTache) item;
                etat = tache.etat;
            } else {
                // Gérer le cas où le type de l'objet n'est pas pris en charge
                throw new IllegalArgumentException("Type d'objet non pris en charge");
            }

            if (etatGlobal == null) {
                etatGlobal = etat;
            } else if (etatGlobal != etat) {
                if (etat == Etat.IN_PROGRESS) {
                    return Etat.IN_PROGRESS;
                } else {
                    return null; // Les tâches ont des états différents, l'état global est indéterminé
                }
            }
        }

        return etatGlobal;
    }

    public static int TacheComplete(List<Planning> dateList,Etat etat) {
        int count = 0;

        for (Planning plan : dateList) {
            if (plan.tache.etat == etat) {
                count++;
            }
        }

        return count;
    }

    public static Float rendementJournalier(LocalDate date,String FICHIER_HISTORIQUE_PLANNING) {

        List<Planning> dateList = Journee.tacheOfDay(date, FICHIER_HISTORIQUE_PLANNING);

        for(Planning plan: dateList){
            System.out.println("\n name : "+plan.tache.nom+" etat "+plan.tache.etat);
        }
        // jsp esq prevues 9esdo les taches li kaynin dans cette journee ou bien le nbr minimal des tache li hwa ydiro
        return (float) Journee.TacheComplete(dateList,Etat.COMPLETED) / dateList.size();
    }


    public static LocalDate bestDay(String fileName, Map<LocalDate, Integer> completedTasksByDay) {
        List<HistoriquePlanning> historiquePlanList = Utilisateur.recupererObjetFichier(fileName);
        CompletedTacheDay.calculateCompletedTasks(historiquePlanList, completedTasksByDay);

        LocalDate maxDay = null;
        int maxCount = 0;

        for (Map.Entry<LocalDate, Integer> entry : completedTasksByDay.entrySet()) {
            LocalDate day = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount) {
                maxCount = count;
                maxDay = day;
            }
        }
        return maxDay;
    }


    public static Map<Categorie, Duration> CategoryDuration(String FICHIER_HISTORIQUE_PLANNING) {
        List<HistoriquePlanning> historiquePlanList = Utilisateur.recupererObjetFichier(FICHIER_HISTORIQUE_PLANNING);

        Map<Categorie, Duration> categoryDurationMap = new HashMap<>();

        for (HistoriquePlanning historiquePlanning : historiquePlanList) {
            for (Planning planning : historiquePlanning.listPlanning) {
                Categorie category = planning.tache.categorie;
                Duration duration = planning.tache.duree;

                if (categoryDurationMap.containsKey(category)) {
                    Duration totalDuration = categoryDurationMap.get(category).plus(duration);
                    categoryDurationMap.put(category, totalDuration);
                } else {
                    categoryDurationMap.put(category, duration);
                }
            }
        }

        return categoryDurationMap;
    }
}