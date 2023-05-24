package app.zaidiboussebata.Noyau;


import java.time.LocalDate;
import java.util.Map;

public class Felicitation {
    public static int nbr_min_tache = 1;//nombre minimal des taches a realisee par jour

    public static void updateBadgeCounts(Map<LocalDate, Integer> completedTasksByDay, Map<Badge, Integer> badgeCounts, int x) {
        int consecFelicitation = 0;
        int consecGood = 0;
        int consecVeryGood = 0;

        for (Map.Entry<LocalDate, Integer> entry : completedTasksByDay.entrySet()) {
            int completedTasks = entry.getValue();

            if (completedTasks >= x) {
                badgeCounts.put(Badge.FELICITATION, badgeCounts.getOrDefault(Badge.FELICITATION, 0) + 1);
                consecFelicitation++;
                consecGood = 0;
                consecVeryGood = 0;
            } else {
                consecFelicitation = 0;
            }

            if (consecFelicitation >= 5) {
                badgeCounts.put(Badge.GOOD, badgeCounts.getOrDefault(Badge.GOOD, 0) + 1);
                consecGood++;
                consecFelicitation = 0;
                consecVeryGood = 0;
            } else {
                consecGood = 0;
            }

            if (consecGood >= 3) {
                badgeCounts.put(Badge.VERYGOOD, badgeCounts.getOrDefault(Badge.VERYGOOD, 0) + 1);
                consecVeryGood++;
                consecFelicitation = 0;
                consecGood = 0;
            } else {
                consecVeryGood = 0;
            }

            if (consecVeryGood >= 3) {
                badgeCounts.put(Badge.EXCELLENT, badgeCounts.getOrDefault(Badge.EXCELLENT, 0) + 1);
                consecFelicitation = 0;
                consecGood = 0;
                consecVeryGood = 0;
            }
        }
    }

}