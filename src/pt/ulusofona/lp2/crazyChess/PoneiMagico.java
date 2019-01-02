package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.incrementExact;

public class PoneiMagico extends CrazyPiece {

    PoneiMagico(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "ponei_magico_black.png";

        }else { //ID equipa branca

            return "ponei_magico_white.png";
        }
    }

    @Override
    public String getType() {
        return "Ponei Mágico";
    }

    @Override
    public String getRelativeValue() {
        return "5";
    }

    public boolean checkKingX(int xO,int y, int xD){
        if (xO < xD) {
            int x = xO;
            //Enquanto a peça nao chegar ao seu destino
            while (x != xD) {
                x++;
                //Caso a peça chegue ao seu destino interrompe-se o ciclo
                if (Simulador.tabuleiro[y][x] != null && Simulador.tabuleiro[y][x].getType().equals("Rei")) {
                    return false;
                }

                if (x == xD) {
                    break;
                }
            }
        } else if (xO > xD){
            int x=xO;
            //Enquanto a peça nao chegar ao seu destino
            while (x != xD){
                x--;

                //Vê se existe alguma peça no seu caminho
                if(Simulador.tabuleiro[y][x] != null && Simulador.tabuleiro[y][x].getType().equals("Rei")){
                    return false;
                }

                if(x == xD){
                    break;
                }
            }
        }

        return true;
    }

    public boolean checkKingY (int x, int yO, int yD){
        if (yO < yD){
            int y=yO;
            //Enquanto a peça nao chegar ao seu destino
            while (y != yD){
                y++;
                if(Simulador.tabuleiro[y][x] != null && Simulador.tabuleiro[y][x].getType().equals("Rei")) {
                    return false;
                }

                if(y == yD){
                    break;
                }
            }

        }

        else if (yO > yD){
            //Se for indicado que a peça se mova para cima
            int y=yO;
            //Enquanto a peça nao chegar ao seu destino
            while (y != yD){
                y--;
                //Vê se existe alguma peça no seu caminho
                if(Simulador.tabuleiro[y][x] != null && Simulador.tabuleiro[y][x].getType().equals("Rei")){
                    return false;
                }

                if(y == yD){
                    break;
                }

            }

        }

        return true;

    }

    @Override
    public boolean checkValidMovement(int xO, int yO, int xD, int yD) {
        //Verifica se as coordenadas de destino são válidas
        boolean resultX = true;
        boolean resultY = true;

        boolean resultX2 = true;
        boolean resultY2 = true;

        if (abs(xD - xO) != 2 || abs(yD - yO) != 2) {
            return false;
        }

        if (!checkKingX(xO, yO, xD)){
            resultX = false;
        }

        if (!checkKingY(xD, yO, yD)){
            resultY = false;
        }


        if (!checkKingY(xO, yO, yD)) {
            resultY2 = false;
        }

        if (!checkKingX(xO, yD, xD)) {
                resultX2 = false;
        }

        if ((resultX && resultY) || (resultX2 && resultY2) ) {
                return true;
        }


        /*
            //Se não existir um Rei no caminho do Pónei Mágico a jogada é válida
        if (Simulador.tabuleiro[abs(yD - yO) / 2][abs(xD - xO) / 2] != null && Simulador.tabuleiro[abs(yD - yO) / 2][abs(xD - xO) /2].getType().equals("Rei")){
                return false;
        }

        if (Simulador.tabuleiro[abs(xD - xO) / 2][abs(yD - yO) / 2] != null && Simulador.tabuleiro[abs(xD - xO) / 2][abs(yD - yO) /2].getType().equals("Rei")){
            return false;
        }
        */

        return false;
    }

    @Override
    public ArrayList<String> getValidPlays(int xO, int yO){

        ArrayList<String> validPlays = new ArrayList<>();

        validPlays.add((xO + 2) + ", " + (yO + 2));
        validPlays.add((xO + 2) + ", " + (yO - 2));
        validPlays.add((xO - 2) + ", " + (yO + 2));
        validPlays.add((xO - 2) + ", " + (yO - 2));

        return validPlays;
    }
}
