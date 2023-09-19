package com.example.projectfnackiller.API.Facets;

import com.example.projectfnackiller.API.Library;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Facet {
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacetName() {
        return facetName;
    }

    public void setFacetName(String facetName) {
        this.facetName = facetName;
    }

    int icon = 0;
    String name = "";
    String facetName = "";
    HashMap<String, ArrayList<String>> values = new HashMap<String, ArrayList<String>>();


    public Facet(String Name, int Icon, String FacetName) {
        this.name = Name;
        this.icon = Icon;
        this.facetName = FacetName;
    }

    public void addValue(String valueName, ArrayList<String> valueFacetsName){
        values.put(valueName, valueFacetsName);
    }

    public HashMap<String, ArrayList<String>>getAllValues(){
        return values;
    }

    public void setUpValues()
    {
        if (this.name == "Language") {
            this.addValue("English", new ArrayList<>(Collections.singletonList("anglais")));
            ArrayList<String> s1 = new ArrayList<>();
            s1.addAll(Arrays.asList("français","français+moyen"));
            this.addValue("French", s1);
            this.addValue("Arabic", new ArrayList<>(Collections.singletonList("arabe")));
            this.addValue("Chinese", new ArrayList<>(Collections.singletonList("chinois")));
            this.addValue("Spanish", new ArrayList<>(Collections.singletonList("espagnol")));
            this.addValue("German", new ArrayList<>(Collections.singletonList("allemand")));
            this.addValue("Italian", new ArrayList<>(Collections.singletonList("italien")));
            this.addValue("Russian", new ArrayList<>(Collections.singletonList("russe")));
            this.addValue("Japanese", new ArrayList<>(Collections.singletonList("japonais")));
            this.addValue("Vietnamese", new ArrayList<>(Collections.singletonList("vietnamien")));
            ArrayList<String> s2 = new ArrayList<>();
            s2.addAll(Arrays.asList("akan","latin","tchèque", "français+ancien", "grec+moderne+(après+1453)", "danois", "hindi", "hebreu", "polonais", "hongrois", "suédois", "turc", "persan", "basque", "norvégien", "berbère", "arménien", "tibétain", "mandingue", "indonésien", "wolof", "tsiguane", "bengali"));
            this.addValue("Other", s2);
        } /*else if (this.name == "Library") {
            this.addValue("Library a", new ArrayList<>(Collections.singletonList("autre")));
            this.addValue("Library b", new ArrayList<>(Collections.singletonList("autre")));
        } else if (this.name == "Author") {
            this.addValue("Author a", new ArrayList<>(Collections.singletonList("autre")));
            this.addValue("Author b", new ArrayList<>(Collections.singletonList("autre")));
        }*/ else if (this.name == "Genre") { //TODO: All
            ArrayList<String> s3 = new ArrayList<>();
            s3.addAll(Arrays.asList("LMAN+Mangas", "LBDE+Bandes+dessinees"));
            this.addValue("Mangas & Comics", s3);
            ArrayList<String> s4 = new ArrayList<>();
            s4.addAll(Arrays.asList("LSFI+Science-fiction", "CSFI+Science-fiction,+film+fantastique", "LFAY+Fantasy,+Heroic+fantasy", "LFAN+Roman+fantastique,+horreur"));
            this.addValue("Sci-Fi & Fantasy", s4);
            ArrayList<String> s5 = new ArrayList<>();
            s5.addAll(Arrays.asList("CACT+Film+d'action,+film+de+guerre"));
            this.addValue("Action", s5);
            ArrayList<String> s7 = new ArrayList<>();
            s7.addAll(Arrays.asList("LHIS+Roman+historique", "CHIS+Film+historique,+peplum", "D904+Histoire+occidentale+(Europe+et+Amerique+du+Nord)"));
            this.addValue("History", s7);
            ArrayList<String> s8 = new ArrayList<>();
            s8.addAll(Arrays.asList("LPOP+Poesie+a+partir+de+1945", "D701+Arts+generalites", "LTHP+Theatre+a+partir+de+1945", "LTHM+Theatre+avant+1945", "LPOM+Poesie+avant+1945", "D705+Peinture,+arts+graphiques"));
            this.addValue("Poetry, Art & Theater", s8);
            ArrayList<String> s9 = new ArrayList<>();
            s9.addAll(Arrays.asList("LROP+Roman+après+1945", "LPOL+Roman+policier,+roman+d'espionnage", "LROM+Roman+avant+1945"));
            this.addValue("Novel", s9);
            ArrayList<String> s10 = new ArrayList<>();
            s10.addAll(Arrays.asList("CSTV+Serie+televisee", "M401+Musiques+electroniques", "M307+Musique+classique:+epoque+baroque+(jusqu'a+1750)", "CFRA+Cinema+francais", "CANI+Film+d'animation", "CPOL+Film+policier,+film+d'espionnage", "CMUS+Film+musical", "LALB+Albums", "CNAM+Cinema+nord-americain", "M310+Musique+classique:+20eme+et+21eme+siecles", "M309+Musique+classique:+epoque+romantique+et+post-romantique", "D707+Musique"));
            this.addValue("Cinema & Music", s10);
            ArrayList<String> s11 = new ArrayList<>();
            s11.addAll(Arrays.asList("DBIO+Biographie,+monographie+sur+auteur", "D302+Politique","Littérature+française+(autres)", "D103+Psychologie", "LCON+Contes", "LVOY+Recits+de+voyage", "LFRA+Litterature+francaise", "LNAM+Litterature+nord-americaine", "LBRI+Litterature+britannique", "D911+Guides+de+voyage,+tourisme"));
            this.addValue("Other", s11);
        } else if (this.name == "Type") {
            ArrayList<String> s1 = new ArrayList<>();
            s1.addAll(Arrays.asList("Livre+pour+adulte", "Livre+jeunesse", "Usuels", "Méthode+de+langue", "D602 Medecine et sexualite", "Revue+jeunesse","Revue+pour+adulte", "Livre+en+langue+étrangère", "Livre+en+gros+caractères", "Livre+sonore+pour+adulte", "Livre+de+section+jeunesse+>+12+ans"));
            this.addValue("Book", s1);
            ArrayList<String> s2 = new ArrayList<>();
            s2.addAll(Arrays.asList("Bande+dessinée+jeunesse+>12+ans", "Bande+dessinée+pour+adulte"));
            this.addValue("Comic", s2);
            ArrayList<String> s3 = new ArrayList<>();
            s3.addAll(Arrays.asList("DVD-vidéo+tous+publics", "DVD+jeunesse", "DVD-vidéo+>+16+ans", "DVD-+vidéo+>+12+ans", "DVD-+vidéo+>+18 ans"));
            this.addValue("DVD", s3);
            ArrayList<String> s4 = new ArrayList<>();
            s4.addAll(Arrays.asList("Jeux vidéos+>+18+ans", "Jeux Vidéos+tous+publics"));
            this.addValue("Video game", s4);
            this.addValue("Board game", new ArrayList<>(Collections.singletonList("Jeux+de+société")));
            ArrayList<String> s5 = new ArrayList<>();
            s5.addAll(Arrays.asList("Enregistrement musical+pour+la+jeunesse", "Méthode+musicale", "Livre+sonore+jeunesse", "Vinyle"));
            this.addValue("Music recording", s5);
            this.addValue("Compact disk", new ArrayList<>(Collections.singletonList("Disque+compact")));
            this.addValue("Map", new ArrayList<>(Collections.singletonList("Carte+ou+plan")));
        }
    }

    //TODO: use https://opendata.paris.fr/api/v2/catalog/datasets/tous-les-documents-des-bibliotheques-de-pret/facets
}
