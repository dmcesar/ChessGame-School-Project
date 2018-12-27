package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Rei extends CrazyPiece {

    Rei(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "crazy_emoji_black.png";

        }else { //ID equipa branca

            return "crazy_emoji_white.png";
        }
    }

    @Override
    public String getType() {
        return "Rei";
    }

    @Override
    public String getRelativeValue() {
        return "(infinito)";
    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD) {
        if (abs(xD-xO) <= 1 && abs(yD-yO) <= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<String> getJogadasPossiveis (int xO, int yO, ArrayList<String> jogadas){
        //Falta verificar se existe alguma peça a bloquear o caminho

        //Caso a peça esteja no ponto de origem
        if (xO == 0 && yO == 0){
            jogadas.add((xO+1) + ", " + (yO+1));
            jogadas.add((xO+1) + ", " + yO);
            jogadas.add((xO) + ", " + (yO + 1));
            return jogadas;
        }

        if (xO == 0){
            if (yO == (Simulador.tabuleiro.length - 1)){
                jogadas.add((xO + 1) + ", " + ( yO - 1));
                jogadas.add((xO + 1) + ", " + (yO));
                jogadas.add((xO) + ", " + ( yO - 1));

            } else {
                jogadas.add((xO + 1) + ", " + ( yO - 1));
                jogadas.add((xO + 1) + ", " + (yO));
                jogadas.add((xO) + ", " + ( yO - 1));
                jogadas.add((xO) + ", " + ( yO + 1));
                jogadas.add((xO + 1) + ", " + ( yO + 1));
            }

            return jogadas;
        }

        if (yO == 0){
            if (xO == (Simulador.tabuleiro.length - 1)){
                jogadas.add((xO - 1) + ", " + ( yO ));
                jogadas.add((xO - 1) + ", " + (yO + 1));
                jogadas.add((xO) + ", " + ( yO + 1));

            } else {
                jogadas.add((xO + 1) + ", " + ( yO));
                jogadas.add((xO) + ", " + ( yO + 1));
                jogadas.add((xO + 1) + ", " + ( yO + 1));
                jogadas.add((xO - 1) + ", " + ( yO + 1));
                jogadas.add((xO - 1) + ", " + ( yO));
            }

            return jogadas;
        }

        if (xO == (Simulador.tabuleiro.length - 1) && yO == (Simulador.tabuleiro.length - 1)){
            jogadas.add((xO-1) + ", " + (yO-1));
            jogadas.add((xO-1) + ", " + yO);
            jogadas.add((xO) + ", " + (yO - 1));
            return jogadas;
        }

        //Movimento vertical para cima
        jogadas.add((xO) + ", " + ( yO - 1 ));
        //Movimento vertical para baixo
        jogadas.add((xO) + ", " + (yO + 1));
        //Movimento horizontal para a direita
        jogadas.add((xO + 1) + ", " + (yO));
        //Movimento horizontal para a esquerda
        jogadas.add((xO - 1) + ", " + (yO));

        jogadas.add((xO - 1) + ", " + (yO - 1));
        jogadas.add((xO - 1) + ", " + (yO + 1));
        jogadas.add((xO + 1) + ", " + (yO + 1));
        jogadas.add((xO + 1) + ", " + (yO - 1));


        return jogadas;
    }

}
