package org.example.entity;

import org.example.features.Overable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class BattingPlay extends TeamPlay implements Overable {
    int totalScore;
    int totalWickets;
    List<Batsman> batsmen;

    public BattingPlay(List<Batsman> batsmen){
        this.totalScore = 0;
        this.totalWickets = 0;
        this.batsmen = batsmen;
    }

    @Override
    public boolean isOver() {
        if(totalWickets >= batsmen.size()-1){
            return true;
        }
        return false;
    }

    public void out() {
        Batsman currentBatsman = current();
        currentBatsman.out();
        totalWickets += 1;

        if(!isOver()){
            Batsman nextBatsman = next();
            if(Objects.isNull(nextBatsman)){

            }
            nextBatsman.come();
        }
    }

    public void addARun(Ball ball) {
        Batsman currentBatsman = current();
        Batsman nonStrikeBatsman = findNonStrikeBatsman(currentBatsman);
        totalScore += ball.run;
        currentBatsman.bat(ball);
        if(ball.run % 2 == 1){
            rotate(currentBatsman, nonStrikeBatsman);
        }
    }

    public void getNewBatsmen(){
        if(onCreaseBatsmen().size() != 2){
            Batsman onStrike = next();
            onStrike.come();
            Batsman nonStrike = next();
            nonStrike.come();
            nonStrike.onStrike = false;
            System.out.println(onStrike);
            System.out.println(nonStrike);
        }
    }

    private List<Batsman> onCreaseBatsmen() {
        return batsmen.stream().filter(e -> e.onCrease && !e.isOut).collect(Collectors.toList());
    }

    public void rotate(Batsman currentBatsman, Batsman nonStrikeBatsman) {
        currentBatsman.onStrike = false;
        nonStrikeBatsman.onStrike = true;
    }

    public void rotate(){
        Batsman currentBatsman =  current();
        Batsman nonStrikeBatsman = findNonStrikeBatsman(currentBatsman);
        rotate(currentBatsman, nonStrikeBatsman);
    }

    private Batsman findNonStrikeBatsman(Batsman currentBatsman) {
        return batsmen.stream().filter(e -> e.onCrease)
                .filter(e -> !e.equals(currentBatsman)).findFirst().orElse(null);
    }

    public void addExtraRun(int run) {
        totalScore += run;
    }

    public Batsman current(){
        return batsmen.stream().filter(e -> e.onStrike && !e.isOut).findFirst().orElse(null);
    }

    public Batsman next(){
        return batsmen.stream().filter(e -> !e.onCrease && !e.isOut).findFirst().orElse(null);
    }

    public void print() {
        System.out.printf("%-15s %6s %4s %4s %4s %4s %4s %6s%n",
                "Player Name", "Score", "1s", "2s", "3s", "4s", "6s", "Balls");
        for(Batsman player: batsmen){
            System.out.printf("%-15s %6d %4d %4d %4d %4d %4d %6d%n",
                    player.name, player.runs, player.ones, player.twos, player.threes,
                    player.fours, player.sixes, player.balls);
        }
        System.out.printf("Total: %s/%s%n", totalScore, totalWickets);
    }
}
