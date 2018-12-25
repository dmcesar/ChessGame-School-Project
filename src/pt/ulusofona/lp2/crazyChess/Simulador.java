package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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


    //Contém o número de jogadas sem capturas
    private static int cntPlaysNoCaptures;

    //Contém o resultado final do jogo
    private String result;


    public boolean iniciaJogo(File ficheiroInicial){

        try {

            //Inicializa equipas e variáveis do jogo.
            blackTeam = new Equipa(10);
            whiteTeam = new Equipa(20);

            cntPlaysNoCaptures = 0;

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

                //Vê-se o tipo da peça, e consoante o id do tipo de peça instancia-se os objetos referaente a cada tipo de peça
                switch (lineData[1]){
                    case "0":
                        Rei rei = new Rei(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]),lineData[3]);
                        crazyPieces.add(rei);
                        break;

                    case "1":
                        Rainha rainha = new Rainha(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]),lineData[3]);
                        crazyPieces.add(rainha);
                        break;

                    case "2":
                        PoneiMagico ponei = new PoneiMagico(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]),lineData[3]);
                        crazyPieces.add(ponei);
                        break;

                    case "3":
                        PadreDaVila padre = new PadreDaVila(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]),lineData[3]);
                        crazyPieces.add(padre);
                        break;

                    case "4":
                        TorreH torreH = new TorreH(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]),lineData[3]);
                        crazyPieces.add(torreH);
                        break;

                    case "5":
                        TorreV torreV = new TorreV(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]),lineData[3]);
                        crazyPieces.add(torreV);
                        break;

                    case "6":
                        Lebre lebre = new Lebre(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]),lineData[3]);
                        crazyPieces.add(lebre);
                        break;

                    case "7":
                        Joker joker = new Joker(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]),lineData[3]);
                        crazyPieces.add(joker);
                        break;

                    default:
                        //Id inválido
                        break;

                }

                //Cria a nova peça a ser adicionada
                /*CrazyPiece crazyPiece = new CrazyPiece(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]), lineData[3]);*/

                //Adiciona a peça ao conjunto de peças do jogo
                /*crazyPieces.add(crazyPiece);*/
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
                            if(crazyPiece.getId() == positionID){

                                //Insere a peça no conjunto de peças em jogo da equipa
                                crazyPiece.getTeam().inGameCrazyPieces.add(crazyPiece);
                                crazyPiece.getTeam().crazyPieces.add(crazyPiece);

                                //Insere a nova peça no tabuleiro
                                tabuleiro[y][x] = crazyPiece;
                            }
                        }
                    }
                }
            }

            //Secção 5 (Depende do ficheiro)
            if(fileReader.hasNextLine()){
                line = fileReader.nextLine();
                String[] lineData = line.split(":");
                //Atualiza as jogadas válidas da equipa preta de acordo com o ficheiro
                blackTeam.setCntValidPlays(Integer.parseInt(lineData[1]));
                //Atualiza as jogadas inválidas da equipa preta de acordo com o ficheiro
                blackTeam.setCntInvalidPlays(Integer.parseInt(lineData[3]));

                //Atualiza as jogadas válidas da equipa branca de acordo com o ficheiro
                whiteTeam.setCntValidPlays(Integer.parseInt(lineData[4]));
                //Atualiza as jogadas inválidas da equipa branca de acordo com o ficheiro
                whiteTeam.setCntInvalidPlays(Integer.parseInt(lineData[6]));
            }

        } catch (FileNotFoundException e) {

            return false;
        }


        return true;
    }

    public int getTamanhoTabuleiro(){return tabuleiro.length;}

    public  boolean processaJogada(int xO, int yO, int xD, int yD) {

        //Valida posição inicial
        if ((0 <= xO && xO < tabuleiro.length) && (0 <= yO && yO < tabuleiro.length)) {

            //Verifica se existe uma peça na posição inicial
            if (tabuleiro[yO][xO] != null) {

                //Retorna a peça que se pretende mover
                CrazyPiece crazyPiece = tabuleiro[yO][xO];

                //Verifica se é a vez da equipa da peça em questão jogar
                if ((tabuleiro[yO][xO].getIdEquipa() == getIDEquipaAJogar())) {

                    //Valida posição final
                    if ((0 <= xD && xD < tabuleiro.length) && (0 <= yD && yD < tabuleiro.length)) {

                        //Verifica se a peça pode se movmentar
                       // if (abs(xD-xO) <= 1 && abs(yD-yO) <= 1) {
                        if(crazyPiece.validaMovimento(xO,yO,xD,yD)){

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
                                }
                                //Caso contrário a jogada é inválida
                                else {

                                    //É incrementado o contador de jogadas inválidas da equipa
                                    crazyPiece.getTeam().cntInvalidPlays++;

                                    //Jogada falhada
                                    return false;
                                }
                            }
                            //Caso contrário não existe uma captura e a mudança de posição é direta
                             else {

                                tabuleiro[yD][xD] = crazyPiece;

                                tabuleiro[yO][xO] = null;

                                crazyPiece.getTeam().cntValidPlays++;

                                //Se já tiver ocorrido uma captura préviamente e se for efuetuada uma jogada sem captura (número de capturas de uma equipa = diferença entre número de peças da outra e o seu número de peças em jogo)
                                if((whiteTeam.crazyPieces.size() - whiteTeam.inGameCrazyPieces.size()) + (blackTeam.crazyPieces.size() - blackTeam.inGameCrazyPieces.size()) != 0) {

                                    //O contador de jogadas sem captura é incrementado
                                    cntPlaysNoCaptures++;
                                }

                                //Jogada realizada com sucesso
                                return true;
                            }
                        }
                    }
                }
            }
        }

        //Como a jogada falhou, incrementa o número de jogadas inválidas da equipa em questão
        if(getIDEquipaAJogar() == blackTeam.getId()){

            blackTeam.cntInvalidPlays++;

        } else {

            whiteTeam.cntInvalidPlays++;
        }

        //Jogada falhada, falhou algum dos parametros acima logo não foram efetuadas alterações ao tabuleiro
        return false;
    }

    public List<CrazyPiece> getPecasMalucas(){ return crazyPieces;}

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

    public  int getIDEquipaAJogar(){

        if((blackTeam.cntValidPlays + whiteTeam.cntValidPlays) % 2 == 0){

            return blackTeam.getId();

        } else {

            return whiteTeam.getId();
        }
    }

    public boolean gravarJogo(File ficheiroDestino){
        try{
            FileWriter writer = new FileWriter(ficheiroDestino);
            //Escrita da primeira linha do ficheiro, que é o tamanho do tabuleiro
            writer.write(getTamanhoTabuleiro() + "\n");

            //Escrita do número de peças existentes no jogo
            writer.write(numberOfPieces + "\n");

            //Percorre-se a lista onde estão todas as CrazyPieces
            for (CrazyPiece piece : crazyPieces){
                //Escrita do id da peça no ficheiro
                writer.write(piece.getId() + ":");
                //Escrita do tipo da peça no ficheiro
                writer.write(piece.getIdType() + ":");
                //Escrita do id da equipa da peça no ficheiro
                writer.write(piece.getIdEquipa() + ":");
                //Escrita da alcunha da peça no ficheiro
                writer.write(piece.getNickname());
                writer.write("\n");
            }

            //Percorre-se o tabuleiro
            for (int y = 0; y < tabuleiro.length; y++) {
                for (int x = 0; x < tabuleiro.length; x++){
                    //Verifica-se se numa determinada posição do tabuleiro existe uma peça
                    if(tabuleiro[y][x] != null){
                        //Esta condição é necessária para o escritor não escrever um ":" a mais em cada linha
                        if(x == tabuleiro.length - 1){
                            writer.write(tabuleiro[y][x].getId() + "");
                        } else {
                            writer.write(tabuleiro[y][x].getId() + ":");
                        }

                    } else {
                        //Se não houver nenhuma peça na posição atual do tabuleiro escreve-se 0, em vez do id da peça
                        //Esta condição é necessária para o escritor não escrever um ":" a mais em cada linha
                        if (x == tabuleiro.length-1) {
                            writer.write("0");
                        } else {
                            writer.write("0:");
                        }
                    }
                }
                writer.write("\n");
            }

            //Escrita do id da equipa a jogar
            writer.write(getIDEquipaAJogar()+":");

            //Escrita do nº de jogadas válidas da equipa preta
            writer.write(blackTeam.getCntValidPlays()+":");
            //Escrita do nr capturas da equipa preta
            writer.write(Integer.toString(whiteTeam.crazyPieces.size() - whiteTeam.inGameCrazyPieces.size()) + ":");
            //Escrita do nº de jogadas inválidas da equipa preta
            writer.write(blackTeam.getCntInvalidPlays() + ":");

            //Escrita do nº de jogadas válidas da equipa branca
            writer.write(whiteTeam.getCntValidPlays() + ":");
            //Escrita do nr capturas da equipa branca
            writer.write(Integer.toString(blackTeam.crazyPieces.size() - blackTeam.inGameCrazyPieces.size())+":");
            //Escrita do nº de jogadas inválidas da equipa preta
            writer.write(whiteTeam.getCntInvalidPlays() + "");

            //Fecho do escritor
            writer.close();

            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
