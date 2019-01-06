package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class PadreDaVila extends CrazyPiece {

    PadreDaVila(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "padre_vila_black.png";

        }else { //ID equipa branca

            return "padre_vila_white.png";
        }
    }

    @Override
    public String getType() {
        return "Padre da Vila";
    }

    @Override
    public String getRelativeValue() {
        return "3";
    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD) {

        if (abs(xD - xO) == abs(yD - yO) && abs(xD - xO) <= 3) {

            if (checkPieceBlockingMove(xO, yO, xD, yD)) {

                for(int y = yD - 1; y < yD + 2; y++){

                    for(int x = xD - 1; x < xD + 2; x++){

                        if(x >= 0 && x < Simulador.tabuleiro.length && y >= 0 && y < Simulador.tabuleiro.length){

                            if(Simulador.tabuleiro[y][x] != null && (Simulador.tabuleiro[y][x].getType().equals("Rainha") && Simulador.tabuleiro[y][x].getIdEquipa() != this.getIdEquipa())){

                                return x == xD && y == yD;

                            }
                        }
                    }
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public ArrayList<String> getValidPlays(int xO, int yO){

        ArrayList<String> validPlays = new ArrayList<>();

        for(int x = 1; x <= 3; x++) {

            validPlays.add((xO + x) + ", " + (yO + x));
            validPlays.add((xO + x) + ", " + (yO - x));
            validPlays.add((xO - x) + ", " + (yO + x));
            validPlays.add((xO - x) + ", " + (yO - x));
        }

        return validPlays;
    }
}
