package org.example.entity;

import org.example.features.Overable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team implements Overable {
    String name;
    int numberOfPlayers;
    List<Player> players;
    BattingPlay battingPlay;
    BowlingPlay bowlingPlay;

    public Team(int numberOfPlayers, int numberOfOvers, String name, String[] order){
        this.numberOfPlayers = numberOfPlayers;
        this.players = new ArrayList<>();
        addPlayers(order);
        this.battingPlay = new BattingPlay(this.players.stream()
                .filter(e -> e instanceof Batsman).map(e -> (Batsman)e).collect(Collectors.toList()));
        this.bowlingPlay = new BowlingPlay(numberOfOvers, this.players.stream()
                .filter(e -> e instanceof Bowler).map(e -> (Bowler)e).collect(Collectors.toList()));
        this.name = name;
    }

    @Override
    public boolean isOver() {
        return bowlingPlay.isOver() || battingPlay.isOver();
    }

    public void addPlayer(String name, int jersey){
        Player batterVersion = new Batsman(name, jersey);
        Player bowlerVersion = new Bowler(name, jersey);
        players.add(batterVersion);
        players.add(bowlerVersion);
    }

    public void addPlayers(String[] order){
        for (String s : order) {
            String name = s.split(" ")[0];
            int number = Integer.parseInt(s.split(" ")[1]);
            addPlayer(name, number);
        }
    }

    public void out() {
        battingPlay.out();
        battingPlay.batsmen.stream().filter(e -> e.onCrease).forEach(System.out::println);
    }

    public boolean wicketTaken(Ball ball) {
        return bowlingPlay.wicketTaken(ball);
    }

    public void addARun(Ball ball) {
        battingPlay.addARun(ball);
        battingPlay.batsmen.stream().filter(e -> e.onCrease).forEach(System.out::println);
    }

    public boolean addABall(Ball ball) {
        return (bowlingPlay.addABall(ball));
    }

    public void addExtraRun(int run) {
        battingPlay.addExtraRun(run);
        battingPlay.batsmen.stream().filter(e -> e.onCrease).forEach(System.out::println);
    }

    public void rotate(){
        battingPlay.rotate();
        battingPlay.batsmen.stream().filter(e -> e.onCrease).forEach(System.out::println);
    }
}
