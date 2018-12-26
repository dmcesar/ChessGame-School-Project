package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

public class Equipa {

    int id;
    ArrayList<CrazyPiece> crazyPieces;
    ArrayList<CrazyPiece> inGameCrazyPieces;
    int cntValidPlays;
    int cntInvalidPlays;
    int cntCaptures;
    ArrayList<Joker> jokers;

    Equipa(int id){

        this.id = id;
        this.cntValidPlays = 0;
        this.cntInvalidPlays = 0;
        this.cntCaptures = 0;
        this.crazyPieces = new ArrayList<>();
        this.inGameCrazyPieces = new ArrayList<>();
        this.jokers = new ArrayList<>();
    }

    public int getId(){return this.id;}
}
