package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class TorreV extends CrazyPiece {

    TorreV(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "torre_v_black.png";

        }else { //ID equipa branca

            return "torre_v_white.png";
        }
    }

    @Override
    public String getType() {
        return "TorreV";
    }

    @Override
    public String getRelativeValue() {
        return "3";
    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD){

        if(abs(xD - xO) == 0){

            boolean naoHouveBloqueio = this.checkPieceBlockingMove(xO, yO, xD, yD);
            if (naoHouveBloqueio){
                return true;
            }

        }

        return false;
    }

    @Override
    public ArrayList<String> getValidPlays(int xO, int yO){

        ArrayList<String> validPlays = new ArrayList<>();

        for(int x = 1; x < Simulador.tabuleiro.length; x++) {

            validPlays.add(xO + "," + (yO + x));
            validPlays.add(xO + "," + (yO - x));
        }

        return validPlays;
    }
}
