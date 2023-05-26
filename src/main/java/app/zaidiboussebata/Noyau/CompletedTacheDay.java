package app.zaidiboussebata.Noyau;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
public class CompletedTacheDay {

    public static LocalDate date;
    public static int nbrTacheCompletee;

    /**
     *  retourne le nombre de tache  completés dans une journee specifique
     * @param date   la journee en question
     * @param historiquePlanning
     * @return
     */
    public  int getTasksAccomplishedForDay(LocalDate date, HistoriquePlanning historiquePlanning) {
        int count = 0;
        for (Planning plan : historiquePlanning.listPlanning) {
            if (plan.creneau.getDate().equals(date)) {
                count++;
            }
        }
        return count;
    }
//---------------------------------------------------------------------------------------------------

    /**
     * calcule le nombre de tache completes pour chaque journee
     * @param historiquePlanList
     * @param completedTasksByDay
     * @return
     */
    public  boolean calculateCompletedTasks(List<HistoriquePlanning> historiquePlanList, Map<LocalDate, Integer> completedTasksByDay) {
        boolean hasCompletedTasks = false;

        for (HistoriquePlanning historiquePlanning : historiquePlanList) {
            for (Planning planning : historiquePlanning.listPlanning) {
                if (planning.tache.etat == Etat.COMPLETED) {
                    LocalDate date = planning.creneau.getDate();
                    completedTasksByDay.put(date, completedTasksByDay.getOrDefault(date, 0) + 1);
                    hasCompletedTasks = true;
                }
            }
        }

        return hasCompletedTasks;
    }
//---------------------------------------------------------------------------------------------------

    /**
     * retoure le nombre de jours avec des tache completés egaux a min-tache (le nombre minimale de tache a acomplire dans une journee )
     * @param completedTasksByDay
     * @param threshold
     * @return
     */
    public  int countDaysWithCompletedTasks(Map<LocalDate, Integer> completedTasksByDay, int threshold) {
        int count = 0;

        for (Map.Entry<LocalDate, Integer> entry : completedTasksByDay.entrySet()) {
            int completedTasks = entry.getValue();

            if (completedTasks >= threshold) {
                count++;
            }
        }

        return count;
    }

//---------------------------------------------------------------------------------------------------

    /**
     *
     * @param completedTasksByDay
     */
    public  void printCompletedTacheDay(Map<LocalDate, Integer> completedTasksByDay) {
        // TODO Auto-generated method stub
        for (Map.Entry<LocalDate, Integer> entry : completedTasksByDay.entrySet()) {
            LocalDate date = entry.getKey();
            int completedTasks = entry.getValue();

            System.out.println("Date: " + date);
            System.out.println("Completed Tasks: " + completedTasks);
            System.out.println();
        }
    }
}