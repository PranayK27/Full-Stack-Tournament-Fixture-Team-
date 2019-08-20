package com.dsp.springboot.rest;

import java.util.*;

public class Sport {

    public static void main(String[] args) {

       Map<Integer,Integer> fixturemap = new HashMap<>();
       Map<Integer,Map<Integer,Integer>> fixturemaps = new HashMap<>();
        //obtain the number of teams from user input
        Scanner input = new Scanner(System.in);
        System.out.print("How many teams should the fixture table have?");

        int teams;
        teams = input.nextInt();


        // Generate the schedule using round robin algorithm.
        int totalRounds = (teams - 1)*2;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];

        int halfRoundMark = (totalRounds/2);


        for (int round = 0; round < totalRounds; round++) {
        for (int match = 0; match < matchesPerRound; match++) {
            int home = (round + match) % (teams - 1);
            int away = (teams - 1 - match + round) % (teams - 1);

            // Last team stays in the same place while the others
            // rotate around it.
            if (match == 0) {
                away = teams - 1;
            }
            String roundString;
            if (round < halfRoundMark) {
//                roundString = ("Home team " + (home + 1)
//                        + " plays against Away team " + (away + 1));
                roundString = "("+(home + 1)+","+(away + 1)+")";
//                fixturemap.put(home + 1,away + 1);
            } else {
//                roundString = ("Away team " + (away + 1)
//                        + " plays against Home team " + (home + 1));
//                fixturemap.put(home + 1,away + 1);
                roundString = "("+(away + 1)+","+(home + 1)+")";
            }
            rounds[round][match] = roundString;

        }
            fixturemaps.put(round+1,fixturemap);
    }

    // Display the rounds
    for (int i = 0; i < rounds.length; i++) {
        System.out.println("Week " + (i + 1));
        System.out.println(Arrays.asList(rounds[i]));
        System.out.println();
    }



}
}
