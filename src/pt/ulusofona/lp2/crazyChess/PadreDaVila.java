package pt.ulusofona.lp2.crazyChess;

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

            if (!checkPieceBlockingMove(xO, yO, xD, yD)) {

                for(int y = yD - 2; y <= yD + 2; y++){

                    for(int x = xD - 2; x <= xD + 2; x++){

                        if(x >= 0 && x < Simulador.tabuleiro.length && y >= 0 && y < Simulador.tabuleiro.length){

                            if(Simulador.tabuleiro[y][x].getType().equals("Rainha")){

                                return false;
                            }
                        }
                    }
                }

                return true;
            }
        }

        return false;
    }
}
