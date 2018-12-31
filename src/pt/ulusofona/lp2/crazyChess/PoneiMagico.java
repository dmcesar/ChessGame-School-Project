package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

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
        //Verifica se as coordenadas de destino são válidas
        if (abs(xD - xO) != 2 || abs(yD - yO) != 2) {
            return false;
        }

            //Se não existir um Rei no caminho do Pónei Mágico a jogada é válida
        if (Simulador.tabuleiro[abs(xD - xO) / 2][abs(yD - yO) / 2] != null && Simulador.tabuleiro[abs(xD - xO) / 2][abs(yD - yO) / 2].getType().equals("Rei")){
                return false;
        }

        return true;
    }

    @Override
    public ArrayList<String> getValidPlays(int xO, int yO){

        ArrayList<String> validPlays = new ArrayList<>();

        validPlays.add((xO + 2) + ", " + (yO + 2));
        validPlays.add((xO + 2) + ", " + (yO - 2));
        validPlays.add((xO - 2) + ", " + (yO + 2));
        validPlays.add((xO - 2) + ", " + (yO - 2));

        return validPlays;
    }
}
