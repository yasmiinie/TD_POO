package app.zaidiboussebata.Noyau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoriquePlanning implements Serializable{
    public List<Planning> listPlanning = new ArrayList<>();

    /** ajouter l'historique d'un Planning
     *
     * @param historiqueList
     * @param listPlan
     */
    public void ajouterHistoriquePlanning(List<HistoriquePlanning> historiqueList,List<Planning> listPlan) {

        HistoriquePlanning historiquePlanning = new HistoriquePlanning();
        historiquePlanning.listPlanning.addAll(listPlan);
        historiqueList.add(historiquePlanning);

    }
//---------------------------------------------------------------------------------------------------------
    /**
     * recuperer l'historique du planning
     * @param historiqueList
     * @param index
     * @return
     */
    public  HistoriquePlanning recupererHistorique( List<HistoriquePlanning> historiqueList,int index){
        HistoriquePlanning historiquePlanning ;
        historiquePlanning = historiqueList.get(index);
        return historiquePlanning;
    }
}