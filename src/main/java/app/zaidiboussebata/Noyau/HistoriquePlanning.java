package app.zaidiboussebata.Noyau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoriquePlanning implements Serializable{
    public List<Planning> listPlanning = new ArrayList<>();

    public void ajouterHistoriquePlanning(List<HistoriquePlanning> historiqueList,List<Planning> listPlan) {

        HistoriquePlanning historiquePlanning = new HistoriquePlanning();
        historiquePlanning.listPlanning.addAll(listPlan);
        historiqueList.add(historiquePlanning);

    }
    public  HistoriquePlanning recupererHistorique( List<HistoriquePlanning> historiqueList,int index){
        HistoriquePlanning historiquePlanning ;
        historiquePlanning = historiqueList.get(index);
        return historiquePlanning;
    }
}