package pt.ulusofona.lp2.crazyChess;

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
    public boolean validaMovimento(int xO, int yO, int xD, int yD) {
        return false;
    }
}
