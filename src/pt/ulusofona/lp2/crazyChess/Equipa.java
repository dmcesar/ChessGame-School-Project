package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

public class Equipa {

    int id;
    ArrayList<CrazyPiece> crazyPieces;
    ArrayList<CrazyPiece> inGameCrazyPieces;
    int cntValidPlays;
    int cntInvalidPlays;
    int cntCaptures;

    Equipa(int id){

        this.id = id;
        this.cntValidPlays = 0;
        this.cntInvalidPlays = 0;
        this.cntCaptures = 0;
        crazyPieces = new ArrayList<>();
        inGameCrazyPieces = new ArrayList<>();
    }

    public int getId(){return this.id;}
}