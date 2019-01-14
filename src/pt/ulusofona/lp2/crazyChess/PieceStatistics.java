package pt.ulusofona.lp2.crazyChess;

public class PieceStatistics {

    int cntCaptures;
    int cntPoints;
    int cntValidPlays;
    int cntInvalidPlays;

    public PieceStatistics(){}

    public PieceStatistics(int cntCaptures, int cntPoints, int cntValidPlays, int cntInvalidPlays){

        this.cntCaptures = cntCaptures;
        this.cntPoints = cntPoints;
        this.cntValidPlays = cntValidPlays;
        this.cntInvalidPlays = cntInvalidPlays;
    }
}
