package pt.ulusofona.lp2.crazyChess;
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
    public boolean validaMovimento(int xO, int yO, int xD, int yD) {
        if(xO == xD || yO == yD) {
            return false;
        }

        //Caso o turno seja de numeração ímpar
        if((Simulador.blackTeam.getCntValidPlays() + Simulador.whiteTeam.getCntValidPlays()) % 2 != 0 ){
            return false;
        }

        if(abs(xD-xO) <= 1 && abs(yD-yO) <= 1){
            return true;
        } else {
            return false;
        }
    }
}
