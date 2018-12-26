package pt.ulusofona.lp2.crazyChess;

import static java.lang.Math.abs;

public class TorreH extends CrazyPiece {

    TorreH(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "torre_h_black.png";

        }else { //ID equipa branca

            return "torre_h_white.png";
        }
    }

    @Override
    public String getType() {
        return "TorreH";
    }

    @Override
    public String getRelativeValue() {
        return "3";
    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD){

        if(abs(yD - yO) == 0){

            return checkPieceBlockingMove(xO, yO, xD, yD);
        }

        return false;
    }
}


