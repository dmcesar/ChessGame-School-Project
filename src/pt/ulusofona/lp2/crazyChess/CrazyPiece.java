package pt.ulusofona.lp2.crazyChess;


import java.util.ArrayList;
import java.util.List;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

abstract public class CrazyPiece {

    protected int idPiece;
    protected int idType;
    protected int idTeam;
    protected String nickname;
    String previousCoords;
    boolean captured;

    CrazyPiece(){}

    CrazyPiece(int idPiece, int idType, int idTeam, String nickname){

        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
        this.previousCoords = "";
        this.captured  =false;
    }

    public int getId(){return this.idPiece;}

    public int getIdType(){return this.idType;}

    public int getIdEquipa(){return this.idTeam;}

    public String getNickname(){return this.nickname;}

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
}