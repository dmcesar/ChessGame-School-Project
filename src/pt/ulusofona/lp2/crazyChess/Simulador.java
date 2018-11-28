package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Simulador{

    //Informações sobre o tabuleiro
    static CrazyPiece[][] tabuleiro;


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

            result = "";


            //Secçao 1
            Scanner fileReader = new Scanner(ficheiroInicial);

            String line = fileReader.nextLine();

            tabuleiro = new CrazyPiece[Integer.parseInt(line)][Integer.parseInt(line)];


            //Secçao 2
            line = fileReader.nextLine();

            numberOfPieces = Integer.parseInt(line);

            crazyPieces = new ArrayList<>();


            //Secçao 3
            for (int cntLine = 0; cntLine < numberOfPieces; cntLine++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                //Cria a nova peça a ser adicionada
                CrazyPiece crazyPiece = new CrazyPiece(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]), lineData[3]);

                //Adiciona a peça aos conjuntos de peças da sua equipa
                if(crazyPiece.idTeam == blackTeam.id){

                    blackTeam.crazyPieces.add(crazyPiece);

                }else{

                    whiteTeam.crazyPieces.add(crazyPiece);
                }

                //Adiciona a peça ao conjunto de peças do jogo
                crazyPieces.add(crazyPiece);
            }


            //Secçao 4
            for (int y = 0; y < tabuleiro.length; y++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                for (int x = 0; x < lineData.length; x++) {

                    //Devolve o valor de id contido nessa posição da matriz
                    int positionID = Integer.parseInt(lineData[x]);

                    //Se esse valor for diferente de 0 significa que se encontra lá uma peça
                    if (positionID != 0) {

                        //Itera todas as peças do jogo
                        for (CrazyPiece crazyPiece : crazyPieces) {

                            //Á procura de uma cujo id corresponda áquele lido do ficheiro
                            if(crazyPiece.idPiece == positionID){

                                //Insere a peça no conjunto de peças em jogo da equipa
                                crazyPiece.getTeam().inGameCrazyPieces.add(crazyPiece);

                                //Insere a nova peça no tabuleiro
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

    public int getTamanhoTabuleiro(){return tabuleiro.length;}

    public boolean processaJogada(int xO, int yO, int xD, int yD) {

        //Valida posição inicial
        if ((0 <= xO && xO < tabuleiro.length) && (0 <= yO && yO < tabuleiro.length)) {

            //Verifica se existe uma peça na posição inicial
            if (tabuleiro[yO][xO] != null) {

                //Retorna a peça que se pretende mover
                CrazyPiece crazyPiece = tabuleiro[yO][xO];

                //Verifica se é a vez da equipa da peça em questão jogar
                if ((tabuleiro[yO][xO].getIdEquipa() == blackTeam.getId() && cntPlays % 2 == 0) || (tabuleiro[yO][xO].getIdEquipa() == whiteTeam.getId() && cntPlays % 2 != 0)) {

                    //Valida posição final
                    if ((0 <= xD && xD < tabuleiro.length) && (0 <= yD && yD < tabuleiro.length)) {

                        //Verifica se a distância a ser percorrida é válida
                        if (abs(xD-xO) <= 1 && abs(yD-yO) <= 1) {

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

                                    //É incrementado o contador de jogadas
                                    cntPlays++;

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

                                //Se já tiver ocorrido uma captura préviamente e se for efuetuada uma jogada sem captura
                                if((whiteTeam.crazyPieces.size() - whiteTeam.inGameCrazyPieces.size()) + (blackTeam.crazyPieces.size() - blackTeam.inGameCrazyPieces.size()) != 0) {

                                    //O contador de jogadas sem captura é incrementado
                                    cntPlaysNoCaptures++;
                                }

                                //No entanto o contador de jogadas é incrementado
                                cntPlays++;

                                //Jogada realizada com sucesso
                                return true;
                            }
                        }
                    }
                }
            }
        }

        //Como a jogada falhou, incrementa o número de jogadas inválidas da equipa em questão
        if(cntPlays % 2 == blackTeam.getId()){

            blackTeam.cntInvalidPlays++;

        } else {

            whiteTeam.cntInvalidPlays++;
        }

        //Jogada falhada, falhou algum dos parametros acima logo não foram efetuadas alterações
        return false;
    }

    public List<CrazyPiece> getPecasMalucas(){ return crazyPieces; }

    public boolean jogoTerminado(){

        //Vitória das brancas por falta de peças pretas em jogo
        if(blackTeam.inGameCrazyPieces.size() == 0){

            result = "VENCERAM AS BRANCAS";

            return true;
        }

        //Vitória das Pretas por falta de peças brancas em jogo
        if(whiteTeam.inGameCrazyPieces.size() == 0){

            result = "VENCERAM AS PRETAS";

            return true;
        }

        //Empate por número igual de peças em jogo (1 peça por equipa é considerado empate)
        if(blackTeam.inGameCrazyPieces.size() == 1 && whiteTeam.inGameCrazyPieces.size() == 1){

            result = "EMPATE";

            return true;
        }

        //Empate por falta de capturas
        if (cntPlaysNoCaptures == 10){

            result = "EMPATE";

            return true;
        }

        return false;
    }

    public List<String> getAutores(){

        ArrayList<String> authors = new ArrayList<>();

        authors.add("Diogo César : 21804304");
        authors.add("Rafael Horta : 21705375");

        return authors;
    }

    public List<String> getResultados(){

        ArrayList<String> resultados = new ArrayList<>();

        resultados.add("JOGO DE CRAZY CHESS");
        resultados.add("Resultado: " + result);
        resultados.add("---");

        resultados.add("Equipa das Pretas");
        resultados.add(Integer.toString((whiteTeam.crazyPieces.size() - whiteTeam.inGameCrazyPieces.size())));
        resultados.add(Integer.toString(blackTeam.cntValidPlays));
        resultados.add(Integer.toString(blackTeam.cntInvalidPlays));

        resultados.add("Equipa das Brancas");
        resultados.add(Integer.toString((blackTeam.crazyPieces.size() - blackTeam.inGameCrazyPieces.size())));
        resultados.add(Integer.toString(whiteTeam.cntValidPlays));
        resultados.add(Integer.toString(whiteTeam.cntInvalidPlays));

        return resultados;
    }

    public int getIDPeca(int x, int y){

        //Verifica se as coordenadas passadas são válidas
        if ((0 <= x && x < tabuleiro.length) && (0 <= y && y < tabuleiro.length)) {

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