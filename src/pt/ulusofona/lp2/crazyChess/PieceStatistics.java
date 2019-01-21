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

    public int getCntCaptures(){return this.cntCaptures;}

    public int getCntPoints(){return this.cntPoints;}

    public int getCntValidPlays(){return this.cntValidPlays;}

    public int getCntInvalidPlays(){return this.cntInvalidPlays;}

    public float getInvalidValidPlaysRacio(){

        if(this.getCntInvalidPlays() == 0){

            return 0;
        }

        return (float)(this.cntInvalidPlays / (this.cntValidPlays + this.cntInvalidPlays));
    }
}