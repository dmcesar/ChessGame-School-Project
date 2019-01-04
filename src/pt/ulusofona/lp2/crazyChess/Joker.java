package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

public class Joker extends CrazyPiece {

    CrazyPiece mask;

    Joker(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname);}

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "joker_black.png";

        }else { //ID equipa branca

            return "joker_white.png";
        }
    }

    @Override
    public String getType() {

        if(this.mask != null) {

            return "Joker | " + this.mask.getType();

        } else {

            return "Joker";
        }
    }

    @Override
    public String getRelativeValue() {
        return "4";
    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD) {

        return this.mask.checkValidMovement(xO, yO, xD, yD);

    }

    @Override
    public ArrayList<String> getValidPlays(int xO, int yO){

        return this.mask.getValidPlays(xO, yO);
    }

    void switchJokerType(){

        switch ((Simulador.blackTeam.getCntValidPlays() + Simulador.whiteTeam.getCntValidPlays()) % 6){

            case 0: {

                this.mask = new Rainha(idPiece, 1, idTeam, nickname);

                break;
            }

            case 1: {

                this.mask = new PoneiMagico(idPiece, 2, idTeam, nickname);

                break;
            }

            case 2: {

                this.mask = new PadreDaVila(idPiece, 3, idTeam, nickname);

                break;
            }

            case 3: {

                this.mask = new TorreH(idPiece, 4, idTeam, nickname);

                break;
            }

            case 4: {

                this.mask = new TorreV(idPiece, 5, idTeam, nickname);

                break;
            }

            default: {

                this.mask = new Lebre(idPiece, 6, idTeam, nickname);

                break;
            }
        }
    }
}
