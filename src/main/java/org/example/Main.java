package org.example;

import org.example.entity.Play;
import org.example.io.InReader;
import org.example.io.OutPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        InReader reader = new InReader();
        OutPrinter printer = new OutPrinter();

        printer.print("Enter number of overs: ");
        int numOvers = reader.read();

        printer.print("Enter number of players: ");
        int numPlayers = reader.read();

        printer.print("Enter name of 1st team: ");
        String teamName1 = reader.readLine();

        printer.print("Enter name of 2nd team: ");
        String teamName2 = reader.readLine();

        printer.print(String.format("Enter %s players of 1st team in {name, jersey} format", numPlayers));
        String[] team1Order = reader.readNextLines(numPlayers);

        printer.print(String.format("Enter %s players of 2nd team in {name, jersey} format", numPlayers));
        String[] team2Order = reader.readNextLines(numPlayers);
        Play play = new Play(numOvers, numPlayers, team1Order, team2Order, teamName1, teamName2);

        play.start();
        while(!play.isOver()){
            String str = reader.readLine();
            play.nextPlay(str);
        }
        System.out.println("First Innings is Over");
        play.print();
        play.startNextInnings();
        play.start();
        while(!play.isOver()){
            String str = reader.readLine();
            play.nextPlay(str);
        }
        play.print();
        play.showResult();
    }
}