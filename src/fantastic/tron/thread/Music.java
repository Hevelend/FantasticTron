package fantastic.tron.thread;

import java.io.*;
import javazoom.jl.player.*;

public class Music extends Thread {

    private String fileLocation;
    private boolean loop;
    private Player prehravac;

    public Music(String fileLocation, boolean loop) {
        this.fileLocation = fileLocation;
        this.loop = loop;
    }

    // On démarre la musique
    public void run() {
        try {
            do {
                FileInputStream buff = new FileInputStream(fileLocation);
                prehravac = new Player(buff);
                prehravac.play();
            } while (loop);
        } catch (Exception ioe) {
            // TODO error handling
        }
    }

    // On stop la musique
    public void close(){
        loop = false;
        prehravac.close();
        this.interrupt();
    }
}