package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Simulador{

    //Informações sobre o tabuleiro
    static CrazyPiece[][] tabuleiro;
    static int boardSize;

    //Contém todas as peças em jogo
    static ArrayList<CrazyPiece> crazyPieces;
    static int numberOfPieces;

    //Tipos complexos que contêm a informação respetivamente a cada uma das equipas
    static Equipa blackTeam;
    static Equipa whiteTeam;

    //Conta o número de jogadas efetuadas ao longo do jogo. Usada também para verificar qual a equipa a jogar.
    static int cntPlays;
    //Contém o número de jogadas sem capturas
    static int cntPlaysNoCaptures;

    //Contém o resultado final do jogo
    static String result;

    public boolean iniciaJogo(File ficheiroInicial){

        try {

            //Inicializa equipas e variáveis do jogo.
            blackTeam = new Equipa(0);
            whiteTeam = new Equipa(1);

            cntPlays = 0;
            cntPlaysNoCaptures = 0;

            result = "";


            //Secçao 1
            Scanner fileReader = new Scanner(ficheiroInicial);

            String line = fileReader.nextLine();

            boardSize = Integer.parseInt(line);

            tabuleiro = new CrazyPiece[boardSize][boardSize];


            //Secçao 2
            line = fileReader.nextLine();

            numberOfPieces = Integer.parseInt(line);

            crazyPieces = new ArrayList<>();


            //Secçao 3
            for (int cntLine = 0; cntLine < numberOfPieces; cntLine++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                CrazyPiece crazyPiece = new CrazyPiece(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]), lineData[3]);

                //Adiciona o id da peça á sua equipa
                if(crazyPiece.idTeam == blackTeam.id){

                    blackTeam.crazyPieces.add(crazyPiece);
                    blackTeam.inGameCrazyPieces.add(crazyPiece);

                }else{

                    whiteTeam.crazyPieces.add(crazyPiece);
                    whiteTeam.inGameCrazyPieces.add(crazyPiece);
                }

                //Adiciona a peça ao conjunto de peças do jogo
                crazyPieces.add(crazyPiece);
            }


            //Secçao 4
            for (int y = 0; y < boardSize; y++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                for (int x = 0; x < lineData.length; x++) {

                    int positionID = Integer.parseInt(lineData[x]);

                    if (positionID != 0) {

                        for (CrazyPiece crazyPiece : crazyPieces) {

                            if(crazyPiece.idPiece == positionID){

                                //Se já existir uma peça anteriormente nessa posição da equipa adversária
                                if(tabuleiro[y][x] != null) {

                                    tabuleiro[y][x].getTeam().inGameCrazyPieces.remove(tabuleiro[y][x]);
                                }

                                tabuleiro[y][x] = crazyPiece;
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {

            return false;
        }

        return true;
    }

    public int getTamanhoTabuleiro(){return boardSize;}

    public boolean processaJogada(int xO, int yO, int xD, int yD) {

        //Valida posição inicial
        if ((0 <= xO && xO < boardSize) && (0 <= yO && yO < boardSize)) {

            //Verifica se existe uma peça na posição inicial
            if (tabuleiro[yO][xO] != null) {

                //Retorna a peça que se pretende mover
                CrazyPiece crazyPiece = tabuleiro[yO][xO];

                //Verifica se é a vez da equipa da peça em questão jogar
                if ((tabuleiro[yO][xO].getIdEquipa() == blackTeam.getId() && cntPlays % 2 == 0) || (tabuleiro[yO][xO].getIdEquipa() == whiteTeam.getId() && cntPlays % 2 != 0)) {

                    //Valida posição final
                    if ((0 <= xD && xD < boardSize) && (0 <= yD && yD < boardSize)) {

                        //Verifica se a distância a ser percorrida é válida
                        if (sqrt(Math.pow((xD - xO), 2) + Math.pow((yD - yO), 2)) == 1) {

                            //Verifica se existe uma peça na posição final
                            if (tabuleiro[yD][xD] != null) {

                                //Caso as peças sejam de equipas diferentes ocorre uma captura
                                if (tabuleiro[yD][xD].getIdEquipa() != tabuleiro[yO][xO].getIdEquipa()) {

                                    //Remove a peça da equipa oposta da lista de peças em jogo dessa equipa
                                    tabuleiro[yD][xD].getTeam().inGameCrazyPieces.remove(tabuleiro[yD][xD]);

                                    //Mexe a peça
                                    tabuleiro[yD][xD] = crazyPiece;

                                    //Limpa a posição anterior
                                    tabuleiro[yO][xO] = null;

                                    //É incrementado o contador de jogadas válidas da equipa
                                    crazyPiece.getTeam().cntValidPlays++;

                                    //Como foi efetuada uma captura, o número de jogadas sem capturas foi reposto
                                    cntPlaysNoCaptures = 0;

                                    //Jogada realizada com sucesso
                                    return true;

                                 //Caso contrário a jogada é inválida
                                } else {

                                    //É incrementado o contador de jogadas inválidas da equipa
                                    crazyPiece.getTeam().cntInvalidPlays++;

                                    //Jogada falhada
                                    return false;
                                }

                                //Caso contrário não existe uma captura e a mudança de posição é direta.
                            } else {

                                tabuleiro[yD][xD] = crazyPiece;

                                tabuleiro[yO][xO] = null;

                                crazyPiece.getTeam().cntValidPlays++;

                                //Foi efuetuada uma jogada sem captura, logo o contador de jogadas sem captura é incrementado
                                cntPlaysNoCaptures++;

                                //Jogada realizada com sucesso
                                return true;
                            }
                        }
                    }
                }
            }
        }

        //Jogada falhada, falhou algum dos parametros acima logo não foram efetuadas alterações
        return false;
    }

    public List<CrazyPiece> getPecasMalucas(){ return crazyPieces; }

    public boolean jogoTerminado(){

        //Vitória das brancas
        if(blackTeam.inGameCrazyPieces.size() == 0){

            result = "VENCERAM AS BRANCAS";

            return true;
        }

        //Vitória das Pretas
        if(whiteTeam.inGameCrazyPieces.size() == 0){

            result = "VENCERAM AS PRETAS";

            return true;
        }

        //Empate por número igual de peças
        if(blackTeam.inGameCrazyPieces.size() == 1 && whiteTeam.inGameCrazyPieces.size() == 1){

            result = "EMPATE";

            return true;
        }

        //Empate por falta de capturas;
        if (cntPlaysNoCaptures == 10){

            result = "EMPATE";

            return true;
        }

        return false;
    }

    public List<String> getAutores(){

        ArrayList<String> authors = new ArrayList<>();

        authors.add("Diogo César : 21804304");
        authors.add("Rafael Horta : <numero_aluno>");

        return authors;
    }

    public List<String> getResultados(){

        ArrayList<String> resultados = new ArrayList<>();

        resultados.add("JOGO DE CRAZY CHESS");
        resultados.add("Resultado: " + result);
        resultados.add("---");

        resultados.add("Equipa das Pretas");
        resultados.add(Integer.toString(whiteTeam.crazyPieces.size() - whiteTeam.inGameCrazyPieces.size()));
        resultados.add(Integer.toString(blackTeam.cntValidPlays));
        resultados.add(Integer.toString(blackTeam.cntInvalidPlays));

        resultados.add("Equipa das Brancas");
        resultados.add(Integer.toString(blackTeam.crazyPieces.size() - blackTeam.inGameCrazyPieces.size()));
        resultados.add(Integer.toString(whiteTeam.cntValidPlays));
        resultados.add(Integer.toString(whiteTeam.cntInvalidPlays));

        return resultados;
    }

    public int getIDPeca(int x, int y){

        //Verifica se as coordenadas passadas são válidas
        if ((0 <= x && x < boardSize) && (0 <= y && y < boardSize)) {

            //Verifica se existe uma peça na posição passada
            if (tabuleiro[y][x] != null) {

                return tabuleiro[y][x].idPiece;

            }
        }
        return 0;
    }

    public int getIDEquipaAJogar(){

        if(cntPlays % 2 == 0){

            return blackTeam.id;

        } else {

            return whiteTeam.id;
        }
    }
}