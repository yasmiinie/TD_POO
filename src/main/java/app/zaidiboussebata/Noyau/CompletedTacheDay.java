package app.zaidiboussebata.Noyau;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
//30 tu peux changer letat
public class CompletedTacheDay {

    public static LocalDate date;
    public static int nbrTacheCompletee;


    public static int getTasksAccomplishedForDay(LocalDate date, HistoriquePlanning historiquePlanning) {
        // Code to retrieve the number of tasks accomplished for a specific day
        int count = 0;
        for (Planning plan : historiquePlanning.listPlanning) {
            if (plan.creneau.getDate().equals(date)) {
                count++;
            }
        }
        return count;
    }


    public static boolean calculateCompletedTasks(List<HistoriquePlanning> historiquePlanList, Map<LocalDate, Integer> completedTasksByDay) {
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



    public static void printCompletedTacheDay(Map<LocalDate, Integer> completedTasksByDay) {
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