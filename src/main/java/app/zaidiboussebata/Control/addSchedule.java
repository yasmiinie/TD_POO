package app.zaidiboussebata.Control;
import app.zaidiboussebata.Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static app.zaidiboussebata.Control.LogInController.*;

public class addSchedule {
    String type = "SIMPLE"; //par defaut cest une tache simple
    Categorie categorie = Categorie.OTHER ; //par defaut cest une other
    Priorite priorite = Priorite.HIGH ; //par defaut cest une High
    Etat etat = Etat.IN_PROGRESS ; // par defaut in progress


    // ---------------------------------Check boxes pour la page editTache--------------------------------
    @FXML
    public TextField tacheName;

    @FXML
    public DatePicker  startPeriod;
    @FXML
    public DatePicker  endPeriod;
    @FXML
    public CheckBox checkName;
    @FXML
    public CheckBox checkType;
    @FXML
    public CheckBox checkDuree;
    @FXML
    public CheckBox checkDDL;
    @FXML
    public CheckBox checkCat;
    @FXML
    public CheckBox checkPri;
    @FXML
    public CheckBox checkState;
    //-------------------------------------------------------------------------------------------------
    @FXML
    public TextField tacheID;

    @FXML
    public TextField  supID ;

    @FXML
    public TextField tacheDuree;
    @FXML
    public DatePicker ddl;
    @FXML
    public MenuButton typeM;
    @FXML
    public MenuButton categories ;
    @FXML
    public MenuButton priorities ;
    @FXML
    public MenuButton states ;
    @FXML
    public DatePicker deadLine ;
    @FXML
    public Label TaskErreur ;// le message d'erreur dans le cas d'une tache non trouvee
    //--------------------------Les bouttns------------------------------------------------------------------------------------
    @FXML
    public Button removeBut;
    @FXML
    public Button addBut ;// le bouton pour ajouter
    @FXML
    public Button editBut ;// le bouton pour modifier
    @FXML
    public Button acceptBut;
    @FXML
    public Button refuseBut;
    @FXML
    public Button addPeriod;
    @FXML
    protected void taskNotFoundClick() {
        TaskErreur.setText("This Task doesn't existe ! Try again");
    }
    // ----------------------------Les priorites---------------------------

    /**
     * quand je click sur le bouton High
     * @param event
     */
    public void HighClicked(ActionEvent event){
        priorite =  Priorite.HIGH;
        priorities.setText("HIGH");

    }

    public void MediumClicked(ActionEvent event){
        priorite = Priorite.MEDIUM;
        priorities.setText("MEDIUM");

    }
    public void LowClicked(ActionEvent event){
        priorite = Priorite.LOW;
        priorities.setText("LOW");
    }
    // ----------------------------Les categories---------------------------

    public void OtherClicked(ActionEvent event){
        categorie =  Categorie.OTHER;
        categories.setText("Other");
    }
    public void StudiesClicked(ActionEvent event){
        categorie =  Categorie.STUDIES;
        categories.setText("Studies");
    }
    public void HobbyClicked(ActionEvent event){
        categorie =  Categorie.HOBBY;

        categories.setText("HOBBY");
    }
    public void WorkClicked(ActionEvent event){
        categorie =  Categorie.WORK;
        categories.setText("WORK");
    }
    public void SportClicked(ActionEvent event){
        categorie =  Categorie.SPORT;
        categories.setText("SPORT");
    }
    public void HealthClicked(ActionEvent event){
        categorie =  Categorie.HEALTH;
        categories.setText("HEALTH");
    }
    //----------------------------------------------Les Etats--------------------------------------------
    @FXML
    public void DelayedClicked(ActionEvent event) {
        etat = Etat.DELAYED ;
        states.setText("DELAYED");
    }
    @FXML
    public void CancelledClicked(ActionEvent event) {
        etat = Etat.CANCELLED ;
        states.setText("CANCELLED");
    }
    @FXML
    public void InProgressClicked(ActionEvent event) {
        etat = Etat.IN_PROGRESS ;
        states.setText("IN_PROGRESS");
    }
    @FXML
    public void UnscheduledClicked(ActionEvent event) {
        etat = Etat.UNSCHEDULED ;
        states.setText("UNSCHEDULED");
    }
    @FXML
    public void RealizedClicked(ActionEvent event) {
        etat = Etat.NON_REALISEE ;
        states.setText("NOT-REALIZED");
    }
    @FXML
    public void CompletedClicked(ActionEvent event) {
        etat = Etat.COMPLETED ;
        states.setText("COMPLETED");
    }

//----------------------------------------------------------------------------------------------
    @FXML
    public Label SlotErreur;
    @FXML
    public TextField startSlot;
    @FXML
    public TextField endSlot ;
    @FXML
    public DatePicker dateSlot;
//-----------------------------------------------------------------------------------------------



    /**
     * Permet de convertir le text en typede DURATION
     *
     * @param durationText
     * @return type Duration
     */
    private Duration parseDuration(String durationText) {
        // Implement your custom parsing logic here
        // Example: Parse duration in the format "HH:MM"
        String[] parts = durationText.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        // int seconds = Integer.parseInt(parts[2]);

        return Duration.ofHours(hours).plusMinutes(minutes);
    }


    // ------------------------------Les Types---------------------------------------------------------------------
    /**
     * permet de choisir un element de mon menu
     * @param event
     */
    public void simpleButtonClicked(ActionEvent event){
        type = "SIMPLE";
        typeM.setText("SIMPLE");
    }

    /**
     * permet de choisir un element de mon menu
     * @param event
     */
    public void decomposableButtonClicked(ActionEvent event){
        type = "DECOMPOSABLE";
        typeM.setText("DECOMPOSABLE");
    }

//---------------------------------Ajouter une tache---------------------------------------------------------------------------
    /**
     *  ajouter une nouvelle tache au creneau
     *
     * @param event
     */
    public void addTache(ActionEvent event) {
        List<SimpleTache> tacheList = recupererObjetFichier(pseudo+"_taches.ser");

        SimpleTache simple = new SimpleTache();
        simple.nom = tacheName.getText() ;
        simple.type = type;
        simple.etat = Etat.IN_PROGRESS ;
        simple.deadline = deadLine.getValue();
        simple.categorie = categorie ;
        simple.priorite = priorite ;

        //Exception dans la duree
        try {
            Duration duration = parseDuration(tacheDuree.getText());
            simple.duree = duration;

        } catch (IllegalArgumentException e) {

        }


        tacheList.add(simple) ;
        sauvegarderObjetFichier(pseudo+"_taches.ser", tacheList); // on sauvegarde dans le fichier
        navigateTo(addBut , "/app/zaidiboussebata/manSchedule1.fxml","Schedule",true);
    }




//-------------------------------------Modifier une tache-----------------------------------------------------------------------------------------------------

    /**
     * permet de faire des modifications de tous les champs d'une tache
     *
     * @param event
     */
    @FXML
    public void editTache(ActionEvent event) {
        // on lit la liste des taches
        List<SimpleTache> tacheList = recupererObjetFichier(pseudo + "_taches.ser");

        SimpleTache simple = new SimpleTache();

        SimpleTache rechrcherTache = new SimpleTache();
        rechrcherTache = simple.rechercherTache(tacheList, tacheID.getText());

        String name = rechrcherTache.nom;
        Duration duree = rechrcherTache.duree;
        LocalDate deadlin = rechrcherTache.deadline;
        Priorite prio = rechrcherTache.priorite;
        Categorie cate = rechrcherTache.categorie;
        Etat eta = rechrcherTache.etat;
        String typ = rechrcherTache.type;


        System.out.println(rechrcherTache.priorite);
        System.out.println(rechrcherTache.type);
        System.out.println(rechrcherTache.deadline);
        System.out.println(rechrcherTache.nom);

        if (checkName.isSelected()) {
            name = tacheName.getText();
            System.out.println("name selected");
            System.out.println(tacheName.getText());
        }
        if (checkType.isSelected()) {
            typ = type;
        }
        if (checkDuree.isSelected()) {
            Duration duration = parseDuration(tacheDuree.getText());
            duree = duration;

        }
        if (checkDDL.isSelected()) {
            deadlin = ddl.getValue();
        }
        if (checkCat.isSelected()) {
            cate = categorie;
        }
        if (checkPri.isSelected()) {
            prio = priorite;
        }
        if (checkState.isSelected()) {
            eta = etat;
        }

        if (simple.modifieTache(pseudo + "_taches.ser", tacheList, tacheID.getText(), name, duree, deadlin, eta, prio, cate, typ)) {

            // on ferme la fenetre
            Stage currentStage = (Stage) editBut.getScene().getWindow();
            currentStage.close();
        } else {
            taskNotFoundClick();
        }
    }
    //-----------------------------------------Remove-----------------------------------
    @FXML
    public void removeTache(ActionEvent event) {
        // on lit la liste des taches
        List<SimpleTache> tacheList = recupererObjetFichier(pseudo + "_taches.ser");

        SimpleTache simple = new SimpleTache() ;

        if (simple.supprimerTache(FICHIER_TACHE , tacheList , supID.getText() )){
            // on ferme la fenetre
            Stage currentStage = (Stage) removeBut.getScene().getWindow();
            currentStage.close();
        }
        else {
            taskNotFoundClick();
        }

    }

    /**
     * bloquer le creneau pour la tache
     * @param event
     */
    public void acceptClick(ActionEvent event) {

        Planning planning = new Planning();
        planning.bloquee = true ;
       SimpleTache simpleTache ;
       Creneau_libre creneauLibre;

       List<SimpleTache>  tacheList = recupererObjetFichier(pseudo+"_taches.ser") ;
       List<Creneau_libre>  creneauList = recupererObjetFichier(pseudo+"_creneau.ser") ;


        simpleTache =  tacheList.get( tacheList.size()-1 ) ; // je recupere la derniere tache introduite
        creneauLibre = creneauList.get( creneauList.size()-1 ) ; // je recupere le dernier creneau introduit


        planning.tache = simpleTache ;
        planning.creneau = creneauLibre ;


        List<HistoriquePlanning> historiquePlanList= recupererObjetFichier(pseudo+"_historiquePlanning.ser");
        HistoriquePlanning history = new HistoriquePlanning();

        // pour recuperer le dernier planning
        HistoriquePlanning historiquePlanning = new HistoriquePlanning();
        System.out.println(historiquePlanList.size());
            if((historiquePlanList.size()) >0){
                // pour recuperer le dernier planning
                System.out.println("dkhel ");
                historiquePlanning = history.recupererHistorique(historiquePlanList, historiquePlanList.size()-1);
                historiquePlanning.listPlanning.add(planning);

            }
            else {
                // pour le premier element
                historiquePlanning.listPlanning.add(planning);
                historiquePlanList.add(historiquePlanning);

            }

        sauvegarderObjetFichier(pseudo+"_historiquePlanning.ser",historiquePlanList);
        for(Planning plan : historiquePlanning.listPlanning) {
            System.out.println("\n+ plan = nom : "+plan.tache.nom+"| Etat = "+plan.tache.etat+" | debut : "+plan.creneau.getDebut()+" | fin : "+plan.creneau.getFin()+" | date  : "+plan.creneau.getDate());
        }
        navigateTo(acceptBut,"/app/zaidiboussebata/SchedulePage.fxml","Schedule" , true) ;

    }

    public void addSlot(ActionEvent event) {
        Creneau_libre creneau = new  Creneau_libre();

        List<Creneau_libre> CreneauList = recupererObjetFichier(pseudo+"_creneau.ser");
        System.out.println(toLocalTime(startSlot.getText()));
        System.out.println(endSlot.getText());
        System.out.println(toLocalTime(endSlot.getText()));

        if (creneau.createrCreneauLibre(pseudo+"_creneau.ser",toLocalTime(startSlot.getText()),toLocalTime(endSlot.getText()),dateSlot.getValue(),CreneauList)){
            // on ferme la fenetre
            Stage currentStage = (Stage) addBut.getScene().getWindow();
            currentStage.close();
        }
        else{
            SlotClick();
        }

        navigateTo(addBut,"/app/zaidiboussebata/bloquePlanning.fxml","Schedule" , true) ;

    }
    @FXML
    protected void SlotClick() {
        SlotErreur.setText("The duration is less than minimum required , try again !");
    }
//------------------------------------------------------------------------------------
    /**
     *
     * @param text le text pour convertir en Local time
     * @return
     *
     */
    public LocalTime toLocalTime(String text){

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(text, formatter);
            System.out.println(localTime);
            return localTime ;
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * si on refuse de blocker le creneau pour cette tache
     *
     * @param event
     */
    public void refuseClick(ActionEvent event) {
        Planning planning = new Planning();
        planning.bloquee = false ;
        SimpleTache simpleTache ;
        Creneau_libre creneauLibre;

        List<SimpleTache>  tacheList = recupererObjetFichier(pseudo+"_taches.ser") ;
        List<Creneau_libre>  creneauList = recupererObjetFichier(pseudo+"_creneau.ser") ;


        simpleTache =  tacheList.get( tacheList.size()-1 ) ; // je recupere la derniere tache introduite
        creneauLibre = creneauList.get( creneauList.size()-1 ) ; // je recupere le dernier creneau introduit


        planning.tache = simpleTache ;
        planning.creneau = creneauLibre ;


        List<HistoriquePlanning> historiquePlanList= recupererObjetFichier(pseudo+"_historiquePlanning.ser");
        HistoriquePlanning history = new HistoriquePlanning();

        // pour recuperer le dernier planning
        HistoriquePlanning historiquePlanning = new HistoriquePlanning();

        if((historiquePlanList.size()) >0){
            // pour recuperer le dernier planning

            historiquePlanning = history.recupererHistorique(historiquePlanList, historiquePlanList.size()-1);
            historiquePlanning.listPlanning.add(planning);

        }
        else {
            // pour le premier element
            historiquePlanning.listPlanning.add(planning);
            historiquePlanList.add(historiquePlanning);

        }

        sauvegarderObjetFichier(pseudo+"_historiquePlanning.ser",historiquePlanList);
        navigateTo(refuseBut,"/app/zaidiboussebata/SchedulePage.fxml","Schedule" , true) ;

    }

    /**
     * si il veut pas fixer une periode
     * @param event
     */
    public void refusePeriodeClick(ActionEvent event) {
        Planning planning = new Planning();
        List<SimpleTache>  tacheList = recupererObjetFichier(pseudo+"_taches.ser") ;
        List<Creneau_libre>  creneauList = recupererObjetFichier(pseudo+"_creneau.ser") ;
        List<Planning> planningList = new ArrayList<>();

        planning.plannifier(LocalDate.now(),LocalDate.of(2200 , 11 ,24) ,tacheList,creneauList,planningList );

        sauvegarderObjetFichier(pseudo+"_taches.ser", tacheList);
        sauvegarderObjetFichier(pseudo+"_creneau.ser",creneauList );
        HistoriquePlanning history = new HistoriquePlanning();
        history.listPlanning.add(planning);
        List<HistoriquePlanning> historiquePlanList= recupererObjetFichier(pseudo+"_historiquePlanning.ser");
        historiquePlanList.add(history);

        history.ajouterHistoriquePlanning(historiquePlanList ,planningList );
        sauvegarderObjetFichier(pseudo+"_historiquePlanning.ser",historiquePlanList);





       //on ferme la fenetre
        Stage currentStage = (Stage) refuseBut.getScene().getWindow();
        currentStage.close();
    }

    public void acceptPeriodeClick(ActionEvent event) {

        // on ajoute la periode


        navigateTo(acceptBut,"/app/zaidiboussebata/periodePlanning.fxml","Schedule" , true) ;

    }


    public void periodAdd(){

           List<SimpleTache>  tacheList = recupererObjetFichier(pseudo+"_taches.ser") ;
           List<Creneau_libre>  creneauList = recupererObjetFichier(pseudo+"_creneau.ser") ;
           List<Planning> planningList = new ArrayList<>();
           Planning planning = new Planning();

        if (!(planning.plannifier(startPeriod.getValue(), endPeriod.getValue(),tacheList,creneauList,planningList ))) {
            sauvegarderObjetFichier(pseudo+"_taches.ser", tacheList);
            sauvegarderObjetFichier(pseudo+"_creneau.ser",creneauList );
            HistoriquePlanning history = new HistoriquePlanning();
            history.listPlanning.add(planning);
            List<HistoriquePlanning> historiquePlanList= recupererObjetFichier(pseudo+"_historiquePlanning.ser");
            historiquePlanList.add(history);

            history.ajouterHistoriquePlanning(historiquePlanList ,planningList );
            sauvegarderObjetFichier(pseudo+"_historiquePlanning.ser",historiquePlanList);


         // elle ne sera pas reportee et donc on pourra la planifier
            Stage currentStage = (Stage) addPeriod.getScene().getWindow();
            currentStage.close();
        }
        else {
            navigateTo(addPeriod,"/app/zaidiboussebata/etaleePage.fxml","Schedule" , true) ;
        }


    }

    public void refuseExtendClick(ActionEvent event) {
        navigateTo(refuseBut,"/app/zaidiboussebata/SchedulePage.fxml","Schedule" , true) ;

    }

    public void acceptExtendClick(ActionEvent event) {
        navigateTo(acceptBut,"/app/zaidiboussebata/etaleePeriode.fxml","Schedule" , true) ;


    }

    public void periodExtend(ActionEvent event){
        List<SimpleTache>  tacheList = recupererObjetFichier(pseudo+"_taches.ser") ;
        List<Creneau_libre>  creneauList = recupererObjetFichier(pseudo+"_creneau.ser") ;
        List<Planning> planningList = new ArrayList<>();
        Planning planning = new Planning();

        if (!(planning.plannifier(startPeriod.getValue(), endPeriod.getValue(),tacheList,creneauList,planningList ))) {

            sauvegarderObjetFichier(pseudo+"_taches.ser", tacheList);
            sauvegarderObjetFichier(pseudo+"_creneau.ser",creneauList );
            HistoriquePlanning history = new HistoriquePlanning();
            history.listPlanning.add(planning);
            List<HistoriquePlanning> historiquePlanList= recupererObjetFichier(pseudo+"_historiquePlanning.ser");
            historiquePlanList.add(history);

            history.ajouterHistoriquePlanning(historiquePlanList ,planningList );
            sauvegarderObjetFichier(pseudo+"_historiquePlanning.ser",historiquePlanList);

            // elle ne sera pas reportee et donc on pourra la planifier
            Stage currentStage = (Stage) addPeriod.getScene().getWindow();
            currentStage.close();
        }
        else {
            navigateTo(addPeriod,"/app/zaidiboussebata/etaleePage.fxml","Schedule" , true) ;
        }

    }

}
