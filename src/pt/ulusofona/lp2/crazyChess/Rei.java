package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

import static java.lang.Math.abs;

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
    public boolean checkValidMovement(int xO, int yO, int xD, int yD){
        if (abs(xD-xO) <= 1 && abs(yD-yO) <= 1) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> getValidPlays(int xO, int yO){

        ArrayList<String> validPlays = new ArrayList<>();

        validPlays.add((xO + 1) + "," + yO);
        validPlays.add((xO - 1) + "," + yO);
        validPlays.add(xO + "," + (yO + 1));
        validPlays.add(xO + "," + (yO - 1));
        validPlays.add((xO + 1) + "," + (yO + 1));
        validPlays.add((xO + 1) + "," + (yO - 1));
        validPlays.add((xO - 1) + "," + (yO + 1));
        validPlays.add((xO - 1) + "," + (yO - 1));

        return validPlays;
    }
}
