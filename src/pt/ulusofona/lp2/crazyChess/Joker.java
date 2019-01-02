package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

public class Joker extends CrazyPiece {

    CrazyPiece mask;
    String designacao = "";

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

        return "Joker/"+ this.designacao;
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

    public void switchJokerType(){

        /*String[] pieceData = {Integer.toString(this.idPiece), Integer.toString(this.getTeam().cntValidPlays % 6), Integer.toString(this.idTeam), this.nickname};*/
        int playNumber = (Simulador.blackTeam.getCntValidPlays() + Simulador.whiteTeam.getCntValidPlays()) + 6;

       switch (playNumber % 6){
           case 0:
               this.mask = new Rainha(idPiece, idType, idTeam, nickname);
               designacao = "Rainha";
               break;

           case 1:
               this.mask = new PoneiMagico(idPiece, idType, idTeam, nickname);
               designacao = "Ponei Mágico";
               break;

           case 2:
               this.mask = new PadreDaVila(idPiece, idType, idTeam, nickname);
               designacao = "Padre da Vila";
               break;

           case 3:
               this.mask = new TorreH(idPiece, idType, idTeam, nickname);
               designacao = "TorreH";
               break;

           case 4:
               this.mask = new TorreV(idPiece, idType, idTeam, nickname);
               designacao = "TorreV";
               break;

           default:
               this.mask = new Lebre(idPiece, idType, idTeam, nickname);
               designacao = "Lebre";
               break;

       }
    }
}
