package pt.ulusofona.lp2.crazyChess;


import java.util.ArrayList;

import static java.lang.Math.abs;

public class Rainha extends CrazyPiece {

    Rainha(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "rainha_black.png";

        } else { //ID equipa branca

            return "rainha_white.png";
        }
    }

    @Override
    public int getIdType(){return 1;}

    @Override
    public String getType() {
        return "Rainha";
    }

    @Override
    public String getRelativeValue() {
        return "8";
    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD){

        //Verifica se o movimento é horizontal/vertical/obliquo
        if((abs(xD - xO) <= 5 && abs(yD - yO) == 0 || abs(xD - xO) == 0 && abs(yD - yO) <= 5) || (abs(xD - xO) == abs(yD - yO) && abs(xD - xO) <= 5)){

            if (Simulador.tabuleiro[yD][xD] != null && Simulador.tabuleiro[yD][xD].getType().equals("Joker/Rainha")){
                return false;
            }

            //Verifica se na posição destino não existe uma rainha (rainhas não capturam rainhas) e se não existe uma peça da mesma equipa na posição destino
            if(Simulador.tabuleiro[yD][xD] == null || (Simulador.tabuleiro[yD][xD] != null && (!Simulador.tabuleiro[yD][xD].getType().equals("Rainha") && Simulador.tabuleiro[yD][xD].getIdEquipa() != this.getIdEquipa()))){

                //Verifica se não existem outras peças a bloquear o movimento
                return checkPieceBlockingMove(xO, yO, xD, yD);
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getValidPlays(int xO, int yO){

        ArrayList<String> validPlays = new ArrayList<>();

        for(int x = 1; x <= 5; x++) {

            validPlays.add((xO + x) + ", " + yO);
            validPlays.add((xO - x) + ", " + yO);
            validPlays.add(xO + ", " + (yO + x));
            validPlays.add(xO + ", " + (yO - x));
            validPlays.add((xO + x) + ", " + (yO + x));
            validPlays.add((xO + x) + ", " + (yO - x));
            validPlays.add((xO - x) + ", " + (yO + x));
            validPlays.add((xO - x) + ", " + (yO - x));
        }

        return validPlays;
    }
}
