package org.example.entity;

import org.example.features.Overable;
import org.example.features.Printable;

import java.util.List;
import java.util.Random;

public class Play implements Overable, Printable {
    Innings[] innings;
    int toss;
    int activeInnings;

    public Play(int numOver, int numPlayer, String[] team1Order, String[] team2Order, String teamName1, String teamName2){
        toss = tossCoin();
        innings = new Innings[2];
        Team[] teams = new Team[2];
        teams[0] = new Team(numPlayer, numOver, teamName1, team1Order);
        teams[1] = new Team(numPlayer, numOver, teamName2, team2Order);

        System.out.printf("%s won the toss, selected to bat, %s will bowl %n", teams[toss%2].name, teams[(toss+1)%2].name);
        innings[0] = new Innings(teams[toss%2], teams[(toss+1)%2]);
        innings[1] = new Innings(teams[(toss+1)%2], teams[toss%2]);
        activeInnings = 0;
    }

    public int tossCoin(){
        Random rand = new Random();
        int max=1,min=0;
        System.out.println("Generated numbers are within "+min+" to "+max);
        toss = rand.nextInt(max-min+1)-min;
        System.out.println(toss);
        return toss;
    }

    public void nextPlay(String baller){
        innings[activeInnings].nextBall(baller);
    }


    @Override
    public boolean isOver() {
        if(activeInnings == 0){
            return innings[activeInnings].isOver();
        }
        return innings[activeInnings].isOver()
                        || (innings[activeInnings].batting.battingPlay.totalScore >
                                innings[activeInnings-1].batting.battingPlay.totalScore);
    }

    private Team whoWonTheMatch(){
        if(!isOver()){
            return null;
        }
        if(activeInnings == 1){
            if(innings[activeInnings].batting.battingPlay.totalScore >
                    innings[activeInnings-1].batting.battingPlay.totalScore){
                return innings[activeInnings].batting;
            }
            else return innings[activeInnings].bowling;
        }
        return null;
    }

    public void showResult() {
        Team winner = whoWonTheMatch();
        Team loser = whoLostTheMatch();
        if(winner == null || loser == null){
            System.out.println("null error");
        }
        else System.out.println(winner.name + " won the match & " + loser.name + " lost the match");
    }

    private Team whoLostTheMatch() {
        if(!isOver()){
            return null;
        }
        if(activeInnings == 1){
            if(innings[activeInnings].batting.battingPlay.totalScore >
                    innings[activeInnings-1].batting.battingPlay.totalScore){
                return innings[activeInnings].bowling;
            }
            else return innings[activeInnings].batting;
        }
        return null;
    }

    public void startNextInnings() {
        activeInnings = 1;
    }

    public void start(){
        innings[activeInnings].start();
    }

    @Override
    public void print() {
        System.out.printf("Scorecard of %s innings \n", activeInnings+1);
        innings[activeInnings].print();
    }
}
