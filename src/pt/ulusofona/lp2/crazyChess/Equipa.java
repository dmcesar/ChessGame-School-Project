package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

public class Equipa {

    int id;
    ArrayList<CrazyPiece> crazyPieces;
    ArrayList<CrazyPiece> inGameCrazyPieces;
    int cntValidPlays;
    int cntInvalidPlays;

    Equipa(int id){

        this.id = id;
        this.cntValidPlays = 0;
        this.cntInvalidPlays = 0;
        crazyPieces = new ArrayList<>();
        inGameCrazyPieces = new ArrayList<>();
    }

    public int getId(){return this.id;}

    public int getCntValidPlays(){
        return this.cntValidPlays;
    }

    public int getCntInvalidPlays(){
        return this.cntInvalidPlays;
    }

}
