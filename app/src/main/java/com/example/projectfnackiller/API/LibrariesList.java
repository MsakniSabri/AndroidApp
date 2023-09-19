package com.example.projectfnackiller.API;

import java.util.ArrayList;
import java.util.Arrays;

public class LibrariesList {
    private static LibrariesList instance = null;

    public static LibrariesList getInstance() {
        if (instance == null)
            instance = new LibrariesList();
        return instance;
    }

    private LibrariesList() {
        addNewLibraryDatabase("Aimé Césaire" ,"", 48.831602018711315, 2.3118240480876024);
        addNewLibraryDatabase("Amélie" ,"", 48.85807636746606, 2.308974225646011);
        addNewLibraryDatabase("Andrée Chedid" ,"", 48.85004349997959, 2.2864194561772733);
        addNewLibraryDatabase("André Malraux" ,"", 48.84796364166889, 2.3276102715207654);
        addNewLibraryDatabase("Arthur Rimbaud" ,"", 48.85586259928033, 2.3563184966665256);
        addNewLibraryDatabase("Assia Djebar" ,"", 48.849235095458006, 2.4120357934701633);
        addNewLibraryDatabase("Batignolles" ,"", 48.88438136197555, 2.3220800560927755);
        addNewLibraryDatabase("Benjamin Rabier" ,"", 48.892572854392505, 2.378965679460559);
        addNewLibraryDatabase("Buffon" ,"", 48.84244349140649, 2.3617380850130187);
        addNewLibraryDatabase("Canopée - La Fontaine" ,"", 48.862196988283394, 2.3470995101469168);
        addNewLibraryDatabase("Chaptal" ,"", 48.881108679292, 2.3323570273421446);
        addNewLibraryDatabase("Charlotte Delbo" ,"", 48.86643052327152, 2.3404581407095417);
        addNewLibraryDatabase("Colette Vivier" ,"", 48.889757662035855, 2.31961824083468);
        addNewLibraryDatabase("Courcelles" ,"", 48.87782219129373, 2.3031119408343868);
        addNewLibraryDatabase("Couronnes - Naguib Mahfouz" ,"", 48.86997190797343, 2.3852668696700783);
        addNewLibraryDatabase("Crimée" ,"", 48.88484675926401, 2.3830577426857884);
        addNewLibraryDatabase("Diderot" ,"", 48.84573120453588, 2.3777324442333203);
        addNewLibraryDatabase("Druout" ,"", 48.87342649664834, 2.3404401055350674);
        addNewLibraryDatabase("Edmond Rostand" ,"", 48.88853367221396, 2.30345760071631);
        addNewLibraryDatabase("Europe" ,"", 48.877757324568535, 2.3175286799922628);
        addNewLibraryDatabase("Faidherbe" ,"", 48.85156049869874, 2.3839542966543292);
        addNewLibraryDatabase("Fessart" ,"", 48.876263146969535, 2.388150807918637);
        addNewLibraryDatabase("Claude Lévi-Strauss" ,"", 48.886820362973, 2.3715415970565594);
        addNewLibraryDatabase("François Villon" ,"", 48.87721197371857, 2.370774782722623);
        addNewLibraryDatabase("Françoise Sagan" ,"", 48.875536589104414, 2.353746426829476);
        addNewLibraryDatabase("Georges Brassens" ,"", 48.83371513874065, 2.3257863561903487);
        addNewLibraryDatabase("Germaine Tillion" ,"", 48.861866964529604, 2.284520332083397);
        addNewLibraryDatabase("Glacière" ,"", 48.82736765482467, 2.341924381327543);
        addNewLibraryDatabase("Goutte d'Or" ,"", 48.8842868424418, 2.3542040711590317);
        addNewLibraryDatabase("Gutenberg" ,"", 48.840169368740604, 2.2788121438612317);
        addNewLibraryDatabase("Hélène Berr" ,"", 48.842554520323674, 2.3974286156735216);
        addNewLibraryDatabase("Hergé" ,"", 48.88498161092282, 2.367450297038188);
        addNewLibraryDatabase("Heure Joyeuse" ,"", 48.852025362775265, 2.3450617963753255);
        addNewLibraryDatabase("Italie" ,"", 48.83088182640379, 2.35703069145368);
        addNewLibraryDatabase("Jacqueline de Romilly" ,"", 48.899404979996525, 2.336481771149557);
        addNewLibraryDatabase("Lancry" ,"", 48.869435494736145, 2.3601467976516166);
        addNewLibraryDatabase("Louise Michel" ,"", 48.85323169480669, 2.4011304606991333);
        addNewLibraryDatabase("Jean-Pierre Melville" ,"", 48.82670456282405, 2.3664785020507604);
        addNewLibraryDatabase("Marguerite Audoux" ,"", 48.86371529730786, 2.360205821708474);
        addNewLibraryDatabase("Marguerite Duras" ,"", 48.85984015305796, 2.4030723631868436);
        addNewLibraryDatabase("Marguerite Yourcenar" ,"", 48.836916040367484, 2.303575086250888);
        addNewLibraryDatabase("Maurice Genevoix " ,"", 48.89497153508843, 2.363777280921956);
        addNewLibraryDatabase("MMP" ,"", 48.86267040147105, 2.345787526381723);
        addNewLibraryDatabase("Mohammed Arkoun" ,"", 48.842475777779214, 2.349678768977657);
        addNewLibraryDatabase("Mortier" ,"", 48.87197309067665, 2.4083925672133057);
        addNewLibraryDatabase("Musset" ,"", 48.842142205049356, 2.263284442011253);
        addNewLibraryDatabase("Oscar Wilde" ,"", 48.872099628775345, 2.399920276989963);
        addNewLibraryDatabase("Parmentier" ,"", 48.86006830745384, 2.3790555193397918);
        addNewLibraryDatabase("Place des Fêtes" ,"", 48.87937367846061, 2.3975195172849575);
        addNewLibraryDatabase("Réserve Centrale" ,"", 48.843294432636036, 2.3551758173206885);
        addNewLibraryDatabase("Rainer Maria Rilke" ,"", 48.83919691784136, 2.339322156177039);
        addNewLibraryDatabase("Robert Sabatier" ,"", 48.89162044933531, 2.3444203587591828);
        addNewLibraryDatabase("Saint-Eloi" ,"", 48.84554271932989, 2.387061950083776);
        addNewLibraryDatabase("Saint-Simon" ,"", 48.856813789730644, 2.320202509419208);
        addNewLibraryDatabase("Sorbier" ,"", 48.8659376098043, 2.3928811825960454);
        addNewLibraryDatabase("Vaclav Havel" ,"", 48.889007423977304, 2.363026390319828);
        addNewLibraryDatabase("Valeyre" ,"", 48.877947092358234, 2.345021498537557);
        addNewLibraryDatabase("Vandamme" ,"", 48.83878957833462, 2.321747011758039);
        addNewLibraryDatabase("Vaugirard" ,"", 48.84176186302975, 2.2993146971359946);
    }

    private void addNewLibraryDatabase(String name, String address, double latitude, double longitude) {
        Library l = new Library(name, address, latitude, longitude);
        allLibraries.add(l);

        categoriesException.add("LROP+Roman+après+1945");
        categoriesException.add("LPOL+Roman+policier,+roman+d'espionnage");
        categoriesException.add("AUTP+Auteurs+a+partir+de+1945");
        categoriesException.add("DBIO+Biographie,+monographie+sur+auteur");
        categoriesException.add("CSTV+Serie+televisee");
        categoriesException.add("LCON+Contes");
        categoriesException.add("CANI+Film+d'animation");
        categoriesException.add("LSFI+Science-fiction");
        categoriesException.add("LFAY+Fantasy,+Heroic+fantasy");
        categoriesException.add("AUTM+Auteurs+avant+1945");
        categoriesException.add("LHIS+Roman+historique");
        categoriesException.add("LFAN+Roman+fantastique,+horreur");
        categoriesException.add("LTHP+Theatre+a+partir+de+1945");
        categoriesException.add("LPOP+Poesie+a+partir+de+1945");
        categoriesException.add("LROM+Roman+avant+1945");
        categoriesException.add("CSFI+Science-fiction,+film+fantastique");
        categoriesException.add("LVOY+Recits+de+voyage");
        categoriesException.add("LTHM+Theatre+avant+1945");
        categoriesException.add("LPOM+Poesie+avant+1945");
        categoriesException.add("CPOL+Film+policier,+film+d'espionnage");
        categoriesException.add("CACT+Film+d'action,+film+de+guerre");
        categoriesException.add("CMUS+Film+musical");
        categoriesException.add("CHIS+Film+historique,+peplum");


    }

    private ArrayList<Library> allLibraries = new ArrayList<>();
    private ArrayList<String> categoriesException = new ArrayList<>();

    public ArrayList<Library> getLibraries() {
        return allLibraries;
    }

    public void addLibraries(Library lib) {
        this.allLibraries.add((lib));
    }

    public ArrayList<String> getCategoriesException() {
        return categoriesException;
    }
}
