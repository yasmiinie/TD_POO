package app.zaidiboussebata.Noyau;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Utilisateur implements Serializable{
    private String pseudo;
    static int nbr_tache = 0;

    public Utilisateur(String pseudo) {
        this.setPseudo(pseudo);
    }

    /**
     * Ajouter un nouveau utilisateur
     * @param pseudo
     * @return
     */
public void authentification(int pseudo) {
        //corps
        }

public String getPseudo() {
        return pseudo;
        }

public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
        }


        }
