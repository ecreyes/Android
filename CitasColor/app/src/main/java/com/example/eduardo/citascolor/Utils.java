package com.example.eduardo.citascolor;

import java.util.Random;

public class Utils {
    public static int getNumeroAleatorio(int max){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return random.nextInt(max);
    }
}
