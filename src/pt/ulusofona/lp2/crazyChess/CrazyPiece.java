package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

abstract public class CrazyPiece {

    protected int idPiece;
    protected int idType;
    protected int idTeam;
    protected String nickname;

    CrazyPiece(){}

    CrazyPiece(int idPiece, int idType, int idTeam, String nickname){

        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }

    public int getId(){return this.idPiece;}

    public int getIdEquipa(){return this.idTeam;}

    public Equipa getTeam(){

        if(this.idTeam == 10){

            return blackTeam;

        } else {

            return whiteTeam;
        }
    }

    public String toString(){

        String output = this.idPiece + " | " + this.getType() + " | " + this.idTeam + " | " + this.nickname + " @ (n/a)";

        if(this.getTeam().inGameCrazyPieces.contains(this)){

            for(int x = 0; x < tabuleiro.length; x++){

                for(int y = 0; y < tabuleiro.length; y++){

                    if(tabuleiro[y][x] != null && tabuleiro[y][x].idPiece == this.idPiece){

                        output =  this.idPiece + " | " + this.getType() + " | " + this.idTeam + " | " + this.nickname + " @ " + "(" + x + ", " + y + ")";
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

    public boolean checkPieceBlockingMove(int xO, int yO, int xD, int yD){

        while(xO != xD && yO != yD){

            if(xO < xD){

                xO++;

            } else {

                xO--;
            }

            if(yO < yD){

                yO++;

            } else {

                yO--;
            }

            if ((xO != xD && yO != yD) && Simulador.tabuleiro[xO][yO] != null) {

                return true;
            }
        }

        return false;
    }
}