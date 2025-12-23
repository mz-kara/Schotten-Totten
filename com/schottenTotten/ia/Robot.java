package com.schottenTotten.ia;

import java.util.Random;

public class Robot{

    private static Random rand = new Random();
    
    public static int choisir_carte(){
        return rand.nextInt(6);
    }

    public static int choisir_borne(){
        return rand.nextInt(9);
    }
}