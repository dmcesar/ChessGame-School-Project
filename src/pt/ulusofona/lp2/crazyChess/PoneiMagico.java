package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class PoneiMagico extends CrazyPiece {

    PoneiMagico(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "ponei_magico_black.png";

        }else { //ID equipa branca

            return "ponei_magico_white.png";
        }
    }

    @Override
    public String getType() {
        return "Ponei Mágico";
    }


    @Override
    public String getRelativeValue() {
        return "5";
    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD) {

        if (abs(xD - xO) == 2 && abs(yD - yO) == 2) {


        }

        return false;
    }

    @Override
    public ArrayList<String> getJogadasPossiveis (int xO, int yO, ArrayList<String> jogadas){
        return jogadas;
    }

}
