package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Lebre extends CrazyPiece {

    Lebre(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "lebre_black.png";

        }else { //ID equipa branca

            return "lebre_white.png";
        }
    }

    @Override
    public String getType() {
        return "Lebre";
    }

    @Override
    public String getRelativeValue() {
        return "2";
    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD){

        if(abs(xD - xO) == 1 && abs(yD - yO) == 1){

            if((Simulador.blackTeam.getCntValidPlays() + Simulador.whiteTeam.cntValidPlays) % 2 == 0){

                return Simulador.tabuleiro[yD][xD] == null || (Simulador.tabuleiro[yD][xD] != null && Simulador.tabuleiro[yD][xD].getIdEquipa() != this.getIdEquipa());
            }
        }

        return false;
    }

    @Override
    public ArrayList<String> getValidPlays(int xO, int yO){

        ArrayList<String> validPlays = new ArrayList<>();

        validPlays.add((xO + 1) + ", " + (yO + 1));
        validPlays.add((xO + 1) + ", " + (yO - 1));
        validPlays.add((xO - 1) + ", " + (yO + 1));
        validPlays.add((xO - 1) + ", " + (yO - 1));

        return validPlays;
    }
}
