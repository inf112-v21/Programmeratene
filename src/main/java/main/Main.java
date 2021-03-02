package main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import game.GUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");
        cfg.setWindowedMode(500, 500);

        Scanner sc = new Scanner(System.in);
        System.out.print("Start game as host? (y/n): ");

        if (sc.nextLine().equals("y")){

        }

        new Lwjgl3Application(new GUI(), cfg);
    }
}