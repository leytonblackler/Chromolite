package com.leytonblackler.chromolite.main;

import com.leytonblackler.chromolite.razerchroma.RazerChromaService;

public class Chromolite {

    public static void main(String[] args) {
        //Create a new instance of the Razer Chroma SDK Service.
        RazerChromaService razerChromaService = new RazerChromaService();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Stop running the Razer Chroma SDK Service.
        razerChromaService.stop();
    }
}
