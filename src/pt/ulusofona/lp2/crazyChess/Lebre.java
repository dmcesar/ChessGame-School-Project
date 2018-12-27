package pt.ulusofona.lp2.crazyChess;
import java.util.ArrayList;
import java.util.List;

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
    public boolean checkValidMovement(int xO, int yO, int xD, int yD) {
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

    public boolean checkPosition (int xO, int yO){
        if (Simulador.tabuleiro[yO][xO] != null){
            return true;
        }

        return false;
    }

    @Override
    public ArrayList<String> getJogadasPossiveis (int xO, int yO, ArrayList<String> jogadas){

        /*
        if (xO == 0 && yO == 0){
            CrazyPiece peca;
            if (Simulador.tabuleiro[yO+1][xO+1] != null ) {
                peca = Simulador.tabuleiro[yO+1][xO+1];
                if (peca.getIdEquipa() != Simulador.tabuleiro[yO][xO].getIdEquipa()){
                    jogadas.add((xO + 1) + ", " + (yO + 1));
                }
                return jogadas;
            } else {
                jogadas.add((xO + 1) + ", " + (yO + 1));
            }
        }

        if (xO == (Simulador.tabuleiro.length-1) && yO == 0){
            CrazyPiece peca;
            if (Simulador.tabuleiro[yO+1][xO-1] != null ) {
                peca = Simulador.tabuleiro[yO+1][xO-1];
                if (peca.getIdEquipa() != Simulador.tabuleiro[yO][xO].getIdEquipa()){
                    jogadas.add((xO - 1) + ", " + (yO + 1));
                }
                return jogadas;
            } else {
                jogadas.add((xO - 1) + ", " + (yO + 1));
            }
        }

        if (xO == 0 && yO == (Simulador.tabuleiro.length-1)){
            CrazyPiece peca;
            if (Simulador.tabuleiro[yO-1][xO+1] != null ) {
                peca = Simulador.tabuleiro[yO-1][xO+1];
                if (peca.getIdEquipa() != Simulador.tabuleiro[yO][xO].getIdEquipa()){
                    jogadas.add((xO + 1) + ", " + (yO - 1));
                }
                return jogadas;
            } else {
                jogadas.add((xO + 1) + ", " + (yO - 1));
            }
        }

        if (xO == (Simulador.tabuleiro.length-1)  && yO == (Simulador.tabuleiro.length-1)){
            CrazyPiece peca;
            if (Simulador.tabuleiro[yO-1][xO-1] != null ) {
                peca = Simulador.tabuleiro[yO-1][xO-1];
                if (peca.getIdEquipa() != Simulador.tabuleiro[yO][xO].getIdEquipa()){
                    jogadas.add((xO - 1) + ", " + (yO - 1));
                }
                return jogadas;
            } else {
                jogadas.add((xO - 1) + ", " + (yO - 1));
            }
        }

        if (checkPosition((xO - 1), (yO - 1))){
            if (Simulador.tabuleiro[yO - 1][xO - 1].getIdEquipa() != Simulador.tabuleiro[yO][xO].getIdEquipa()){
                jogadas.add((xO - 1) + ", " + (yO - 1));
            }
        }else {
            jogadas.add((xO - 1) + ", " + (yO - 1));
        }

        if (checkPosition((xO - 1), (yO + 1))){
            if (Simulador.tabuleiro[yO + 1][xO - 1].getIdEquipa() != Simulador.tabuleiro[yO][xO].getIdEquipa()){
                jogadas.add((xO - 1) + ", " + (yO + 1));
            }
        }else {
            jogadas.add((xO - 1) + ", " + (yO + 1));
        }

        if (checkPosition((xO + 1), (yO + 1))){
            if (Simulador.tabuleiro[yO + 1][xO + 1].getIdEquipa() != Simulador.tabuleiro[yO][xO].getIdEquipa()){
                jogadas.add((xO + 1) + ", " + (yO + 1));
            }
        }else {
            jogadas.add((xO + 1) + ", " + (yO + 1));
        }

        if (checkPosition((xO + 1), (yO - 1))){
            if (Simulador.tabuleiro[yO + 1][xO - 1].getIdEquipa() != Simulador.tabuleiro[yO][xO].getIdEquipa()){
                jogadas.add((xO + 1) + ", " + (yO - 1));
            }
        }else {
            jogadas.add((xO + 1) + ", " + (yO - 1));
        }
        */
        return jogadas;

    }

}
