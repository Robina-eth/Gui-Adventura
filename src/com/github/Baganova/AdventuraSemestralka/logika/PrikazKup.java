/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Baganova.AdventuraSemestralka.logika;



/*******************************************************************************
 *
 * @author    Robina Bangová
 * @version   0.00.000
 */
public class PrikazKup implements IPrikaz{
    private static final String NAZEV = "kup";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazKup(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *                       
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) {
            
            return "Co mám koupit? Musíš zadat název věci";
        }

        String nazev = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Batoh pomocny = plan.getBatoh();
        
        if(aktualniProstor.getNazev().equals("Osada"))
        {
           if(nazev.equals("mapa") || nazev.equals("mapu"))
           {
               if( pomocny.obsahujeVec("červený_diamand") && pomocny.obsahujeVec("zelený_diamand"))
               {
                   pomocny.vyhodVec("červený_diamand");
                   pomocny.vyhodVec("zelený_diamand");
                   Vec mapy = new Vec("mapa",true,"mapa.jpg");
                   pomocny.pridejVec(mapy);
                   return "Úspěšně jsi koupil od domorodce mapu !";
               }
               else
               {
                   return "Nemáš všechny věci potřebné ke koupi";
               }
           }
           else
           {
               return "Zadanou věc se tu koupit nedá.";
           }
        }
        else
        {
            return "Nakupovat můžeš jen v Osadě";
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
