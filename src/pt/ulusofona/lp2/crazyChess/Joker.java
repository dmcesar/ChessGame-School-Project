package pt.ulusofona.lp2.crazyChess;

public class Joker extends CrazyPiece {

    Joker(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

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
        return "Joker";
    }

    @Override
    public String getRelativeValue() {
        return "4";
    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD) {
        return false;
    }

    public void switchJokerType(){

        for(int y = 0; y < Simulador.tabuleiro.length; y++){

            for(int x = 0; y < Simulador.tabuleiro.length; x++){

                if(Simulador.tabuleiro[y][x] != null && Simulador.tabuleiro[y][x].getId() == this.idPiece){

                    String[] pieceData = {Integer.toString(this.idPiece), Integer.toString(this.getTeam().cntValidPlays % 6), Integer.toString(this.idTeam), this.nickname};

                    Simulador.getPeca(pieceData);
                }
            }
        }
    }
}
