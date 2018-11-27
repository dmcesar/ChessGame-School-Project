package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

public class Equipa {

    int id;
    ArrayList<Integer> crazyPiecesIds;
    ArrayList<Integer> inGameCrazyPiecesIds;
    int cntValidPlays;
    int cntInvalidPlays;

    Equipa(int id, int cntValidPlays, int cntInvalidPlays){

        this.id = id;
        this.cntValidPlays = cntValidPlays;
        this.cntInvalidPlays = cntInvalidPlays;
        crazyPiecesIds = new ArrayList<>();
        inGameCrazyPiecesIds = new ArrayList<>();
    }

    public int getId(){return this.id;}
}
