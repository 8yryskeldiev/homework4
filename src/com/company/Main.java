package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 900;
    public static int bossDamage = 70;
    public static String bossDefenceType = "";
    public static int[] heroesHealths = {250, 250, 250, 250, 500};
    public static int[] heroesDamages = {20, 20, 20, 10};
    public static String[] heroesAttackTypes = {"Physical", "Magical", "Kinetic", "Medical", "Protective"};
    public static int assistance = 30;
    public static String luckyHero = "";
    public static int tankAbility = 20;

    public static void doctorAbility() {
        for (int i = 0; i < heroesHealths.length; i++) {
            if (heroesAttackTypes[i].equals(luckyHero)) {
                heroesHealths[i] = heroesHealths[i] + assistance;
                System.out.println("Med.assistance to  " + heroesAttackTypes[i] + " " + "Tipe:" + "+" + assistance);
            } else {
                heroesHealths[i] = heroesHealths[i];
                System.out.println("Doctor cannot help  " + heroesAttackTypes[i] + " " + "Tipe");
            }

        }

    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (!heroesAttackTypes[i].equals(bossDefenceType)) {
                Random r = new Random();
                int coefficient = r.nextInt(3) + 2;
                bossHealth = bossHealth - heroesDamages[i] * coefficient;
                System.out.println(heroesAttackTypes[i] + " critical attack "
                        + heroesDamages[i] * coefficient);
            } else {
                bossHealth = bossHealth - heroesDamages[i];
            }
        }

    }

    public static void tankAbility() {
        ;
        System.out.println("The Ability of the tank has worked out: " + tankAbility);
        heroesHealths[4] = heroesHealths[4] - tankAbility * 4;
    }


    public static void bossHit() {

        for (int i = 0; i < heroesHealths.length; i++) {
            heroesHealths[i] = heroesHealths[i] - (bossDamage - tankAbility);

        }
    }


    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }
    }

    public static boolean isFinished() {

        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealths[0] <= 0 && heroesHealths[1] <= 0 && heroesHealths[2] <= 0 && heroesHealths[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void round() {
        changeluckyHero();
        changeBossDefence();

        heroesHit();
        if (heroesHealths[4] > 0) {
            tankAbility();
        }
        if (bossHealth > 0) {
            bossHit();

        }
        if (heroesHealths[3] > 0) {
            doctorAbility();
        } else {
            System.out.println(" The Doctor has dead");
        }

        printStatistics();
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackTypes.length);
        bossDefenceType = heroesAttackTypes[randomIndex];
    }

    public static void changeluckyHero() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackTypes.length);
        luckyHero = heroesAttackTypes[randomIndex];
    }

    public static void printStatistics() {
        System.out.println("_______________");
        System.out.println("Boss health: " + bossHealth);

        for (int i = 0; i < heroesAttackTypes.length; i++) {
            System.out.println(heroesAttackTypes[i] + " health: " + heroesHealths[i]);
        }
        System.out.println("_______________");
    }

}
