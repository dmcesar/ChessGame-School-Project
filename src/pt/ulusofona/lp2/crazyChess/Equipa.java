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
<<<<<<< HEAD
}
=======

    public int getCntValidPlays(){
        return this.cntValidPlays;
    }

    public int getCntInvalidPlays(){
        return this.cntInvalidPlays;
    }

    public void setCntValidPlays(int validPlays){
        this.cntValidPlays = validPlays;
    }

    public void setCntInvalidPlays(int invalidPlays){
        this.cntInvalidPlays = invalidPlays;
    }

}
>>>>>>> origin/master
