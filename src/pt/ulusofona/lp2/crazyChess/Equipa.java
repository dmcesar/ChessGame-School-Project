package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

public class Equipa {

    int id;
    ArrayList<Integer> crazyPiecesIds;
    ArrayList<Integer> inGameCrazyPiecesIds;
    int cntValidPlays;
    int cntInvalidPlays;

    Equipa(int id){

        this.id = id;
        this.cntValidPlays = 0;
        this.cntInvalidPlays = 0;
        crazyPiecesIds = new ArrayList<>();
        inGameCrazyPiecesIds = new ArrayList<>();
    }

    public int getId(){return this.id;}
}
