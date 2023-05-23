package my_desktop_planner;
import java.util.List;
import java.util.*;                      

public class TacheCreneauPair {
    private Tache tache;
    private List<Integer> indicesCreneaux;

    public TacheCreneauPair(Tache tache, List<Integer> indicesCreneaux) {
        this.tache = tache;
        this.indicesCreneaux = indicesCreneaux;
    }

    public Tache getTache() {
        return tache;
    }

    public List<Integer> getIndicesCreneaux() {
        return indicesCreneaux;
    }
    
    @Override
    public String toString() {
        return "TacheCreneauPair{" +
                "tache=" + tache +
                ", indicesCreneaux=" + indicesCreneaux +
                '}';
    }
    public static List<TacheCreneauPair> sortTacheCreneauPairs(List<TacheCreneauPair> inputList) {
        List<TacheCreneauPair> sortedList = new ArrayList<>(inputList);
        
        Collections.sort(sortedList, new Comparator<TacheCreneauPair>() {
            @Override
            public int compare(TacheCreneauPair pair1, TacheCreneauPair pair2) {
                int length1 = pair1.getIndicesCreneaux().size();
                int length2 = pair2.getIndicesCreneaux().size();
                return Integer.compare(length1, length2);
            }
        });
        
        return sortedList;
    }
}

