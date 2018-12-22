package pt.ulusofona.lp2.crazyChess;

public class PoneiMagico extends CrazyPiece {

    PoneiMagico(int idPiece, int idType, int idTeam, String nickname){
        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "ponei_magico_black.png";

        }else { //ID equipa branca

            return "ponei_magico_white.png";
        }
    }

    @Override
    public String getDesignacao() {
        return "Ponei Mágico";
    }


    @Override
    public String getValorRelativo() {
        return "5";
    }

    @Override
    public boolean validaMovimento(int xO, int yO, int xD, int yD) {
        return false;
    }
}
