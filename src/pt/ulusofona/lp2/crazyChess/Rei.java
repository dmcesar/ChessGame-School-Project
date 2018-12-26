package pt.ulusofona.lp2.crazyChess;

import static java.lang.Math.abs;
<<<<<<< HEAD
=======

public class Rei extends CrazyPiece {
>>>>>>> origin/master

public class Rei extends CrazyPiece {

    Rei(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "crazy_emoji_black.png";

        }else { //ID equipa branca

            return "crazy_emoji_white.png";
        }
    }

    @Override
    public String getType() {
        return "Rei";
    }

    @Override
    public String getRelativeValue() {
        return "(infinito)";
    }

    @Override
<<<<<<< HEAD
    public boolean checkValidMovement(int xO, int yO, int xD, int yD){ return (abs(xD - xO) <= 1 && abs(yD - yO) <= 1); }
=======
    public boolean validaMovimento(int xO, int yO, int xD, int yD) {
        if (abs(xD-xO) <= 1 && abs(yD-yO) <= 1) {
            return true;
        } else {
            return false;
        }
    }
>>>>>>> origin/master
}
