package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.incrementExact;

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
        if (abs(xD - xO) == 2 && abs(yD - yO) == 2) {

            boolean kingBlockingPathX = false;
            boolean kingBlockingPathY = false;

            int x = xO;
            int y = yO;

            while (x != xD && y != yD) {

                if ((Simulador.tabuleiro[yO][x] != null && Simulador.tabuleiro[yO][x].getIdType() == 0) || (Simulador.tabuleiro[yD][x] != null && Simulador.tabuleiro[yD][x].getIdType() == 0)) {

                    kingBlockingPathX = true;
                }

                if ((Simulador.tabuleiro[y][xO] != null && Simulador.tabuleiro[y][xO].getIdType() == 0) || (Simulador.tabuleiro[y][xD] != null && Simulador.tabuleiro[y][xD].getIdType() == 0)) {

                    kingBlockingPathY = true;
                }

                if (x < xD) {

                    x++;

                } else {

                    x--;
                }

                if (y < yD) {

                    y++;

                } else {

                    y--;
                }
            }

            return !kingBlockingPathX || !kingBlockingPathY;
        }
        return false;
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
