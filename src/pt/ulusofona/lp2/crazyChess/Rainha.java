package pt.ulusofona.lp2.crazyChess;

public class Rainha extends CrazyPiece {
    Rainha(int idPiece, int idType, int idTeam, String nickname){
        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }

    @Override
    public String getImagePNG() {
        if(this.getIdEquipa() == 10) { //ID equipa preta
            return "rainha_black.png";
        } else { //ID equipa branca
            return "rainha_white.png";
        }
    }

    @Override
    public String getDesignacao() {
        return "Rainha";
    }

    @Override
    public String getValorRelativo() {
        return "8";
    }

}
