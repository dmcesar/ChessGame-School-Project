package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

abstract public class CrazyPiece implements Comparable<CrazyPiece>{

    protected int idPiece;
    protected int idType;
    protected int idTeam;
    protected String nickname;
    PieceCoords previousCoords;
    PieceCoords presentCoords;
    PieceStatistics statistics;

    CrazyPiece(){}

    CrazyPiece(int idPiece, int idType, int idTeam, String nickname){

        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
        this.previousCoords = new PieceCoords();
        this.presentCoords = new PieceCoords();
        this.statistics = new PieceStatistics(0, 0, 0, 0);
    }

    public PieceStatistics getStatistics() {
        return statistics;
    }

    public int getId(){return this.idPiece;}

    public int getIdType(){return this.idType;}

    public int getIdEquipa(){return this.idTeam;}

    public String getNickname(){return this.nickname;}

    public void setPreviousCoords(PieceCoords pieceCoords){

        this.previousCoords = pieceCoords;
    }

    public void setPresentCoords(PieceCoords pieceCoords){

        this.presentCoords = pieceCoords;
    }

    Equipa getTeam(){

        if(this.idTeam == 10){

            return blackTeam;

        } else {

            return whiteTeam;
        }
    }

    public String toString(){

        String output = this.idPiece + " | " + this.getType() + " | " + this.getRelativeValue() + " | " + this.idTeam + " | " + this.nickname + " @ (n/a)";

        if(this.getTeam().inGameCrazyPieces.contains(this)){

            for(int x = 0; x < tabuleiro.length; x++){

                for(int y = 0; y < tabuleiro.length; y++){

                    if(tabuleiro[y][x] != null && tabuleiro[y][x].idPiece == this.idPiece){

                        output =  this.idPiece + " | " + this.getType() + " | " + this.getRelativeValue() + " | " + this.idTeam + " | " + this.nickname + " @ " + "(" + x + ", " + y + ")";
                    }
                }
            }
        }

        return output;
    }

    abstract public String getImagePNG();

    abstract public String getType();

    abstract public String getRelativeValue();

    abstract public int getPointsOnCapture();

    abstract public boolean checkValidMovement(int xO, int yO, int xD, int yD);

    abstract public ArrayList<String> getValidPlays(int xO, int yO);

    boolean checkPieceBlockingMove(int xO, int yO, int xD, int yD){

        while(xO != xD || yO != yD){

            if(xO < xD){

                xO++;

            } else if(xO > xD){

                xO--;
            }

            if(yO < yD){

                yO++;

            } else if(yO > yD){

                yO--;
            }

            if ((xO != xD || yO != yD) && Simulador.tabuleiro[yO][xO] != null) {

                return false;
            }
        }

        return true;
    }

    public int compareByPoints(CrazyPiece crazyPiece){

        if(this.statistics.cntPoints > crazyPiece.statistics.cntPoints){

            return 1;
        }

        if(this.statistics.cntPoints < crazyPiece.statistics.cntPoints){

            return -1;
        }

        return this.compareTo(crazyPiece);
    }

    public int compareByCaptures(CrazyPiece crazyPiece){

        if(this.statistics.cntCaptures > crazyPiece.statistics.cntCaptures){

            return 1;
        }

        if(this.statistics.cntCaptures < crazyPiece.statistics.cntCaptures){

            return -1;
        }

        return this.compareTo(crazyPiece);
    }

    public int compareByRacio(CrazyPiece crazyPiece){

        if(this.statistics.getInvalidValidPlaysRacio() > crazyPiece.statistics.getInvalidValidPlaysRacio()){

            return 1;
        }

        if(this.statistics.getInvalidValidPlaysRacio() < crazyPiece.statistics.getInvalidValidPlaysRacio()){

            return -1;
        }

        return this.compareTo(crazyPiece);
    }

    public int compareTo(CrazyPiece crazyPiece){

        return crazyPiece.nickname.compareTo(this.nickname);
    }
}