package com.github.Baganova.AdventuraSemestralka.logika;

import java.util.Observable;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@author     Robina Bangová
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan extends Observable{
    private Prostor aktualniProstor;
    private Batoh batuzek;
    private Hra hra;
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        batuzek = new Batoh();
        this.hra = hra;
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor lod = new Prostor("Loď","Loď, která odplouvá domů",350,130);
        Prostor plaz = new Prostor("Pláž","Písečná pláž plná ježků",250,180);
        Prostor louka = new Prostor("Louka","Zelená travnatá louka",250,100);
        Prostor studna = new Prostor("Studna","Bezedná studna",150,100);
        Prostor osada = new Prostor("Osada","Osada plná domorodců",250,20);
        Prostor prales = new Prostor("Prales","Starobylý prales plný všemožných nástrah",100,0);
        Prostor jeskyne = new Prostor("Jeskyně","Loď, která odplouvá domů",0,80);





        // přiřazují se průchody mezi prostory (sousedící prostory)

        plaz.setVychod(lod);
        plaz.setVychod(louka);
        louka.setVychod(studna);
        louka.setVychod(plaz);
        louka.setVychod(osada);
        louka.setVychod(jeskyne);
        louka.setVychod(prales);
        osada.setVychod(louka);
        osada.setVychod(prales);
        osada.setVychod(jeskyne);
        jeskyne.setVychod(osada);
        jeskyne.setVychod(louka);
        
                
        aktualniProstor = plaz;  // hra začíná v prostoru pláž
        
        Vec boty = new Vec ("boty",true,"boty.jpg");
        Vec maceta = new Vec ("mačeta",true,"maceta.jpg");
        Vec strasak = new Vec ("strašák",false,"strasak.jpg");
        Vec kameny = new Vec ("kameny",true,"kameny.jpg");
        Vec koste = new Vec("koště",true,"koste.jpg");
        Vec zdiamand = new Vec("zelený_diamand",true,"zeleny_diamand.jpg");
        Vec cdiamand = new Vec("červený_diamand",true,"cerveny_diamand.jpg");
        
        plaz.vlozVec(boty);
        plaz.vlozVec(maceta);
        louka.vlozVec(strasak);
        louka.vlozVec(zdiamand);
        osada.vlozVec(koste);
        jeskyne.vlozVec(kameny);
        jeskyne.vlozVec(cdiamand);
        
        
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;

    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    // Metoda vrací batoh
    public Batoh getBatoh() 
    {
       return this.batuzek;
    }
    // Metoda vrací hru kterou hrajeme
    public Hra getHra()
    {
       return this.hra;
    }
    
    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }
    
    
    
    }

