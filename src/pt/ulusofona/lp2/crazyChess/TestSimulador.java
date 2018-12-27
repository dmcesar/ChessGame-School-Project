/*package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class TestSimulador {

    Simulador simulador = new Simulador();
    @Test
    public void test01ProcessaJogada(){

        int size = 4;


        Simulador.tabuleiro = new CrazyPiece[size][size];

        Simulador.blackTeam = new Equipa(0);
        Simulador.whiteTeam = new Equipa(1);

        CrazyPiece testPiece01 = new CrazyPiece(1, 0, 0, "testPiece01");
        CrazyPiece testPiece02 = new CrazyPiece(2, 0, 1, "testPiece02");

        blackTeam.crazyPieces.add(testPiece01);
        blackTeam.inGameCrazyPieces.add(testPiece01);

        whiteTeam.crazyPieces.add(testPiece02);
        whiteTeam.inGameCrazyPieces.add(testPiece02);


        //Movimento horizontal para a direita
        int xO = 0, yO = 0;
        int xD = 1, yD = 0;

        tabuleiro[yO][xO] = testPiece01;
        tabuleiro[yD][xD] = testPiece02;


        //Teste: Mover a peça 'testPiece01' para as coordenadas da peça 'testPiece02'
        boolean resultadoEsperado = true;

        //Deve retornar 'true', pois existem peças em ambas as posições de equipas opostas e dentro do tabuleiro e a distância entre as duas é 1
        boolean resultadoReal = simulador.processaJogada(xO, yO, xD, yD);

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void test02ProcessaJogada(){

        int size = 4;

        Simulador.tabuleiro = new CrazyPiece[size][size];

        Simulador.blackTeam = new Equipa(0);
        Simulador.whiteTeam = new Equipa(1);

        CrazyPiece testPiece01 = new CrazyPiece(1, 0, 0, "testPiece01");
        CrazyPiece testPiece02 = new CrazyPiece(2, 0, 1, "testPiece02");

        blackTeam.crazyPieces.add(testPiece01);
        blackTeam.inGameCrazyPieces.add(testPiece01);

        whiteTeam.crazyPieces.add(testPiece02);
        whiteTeam.inGameCrazyPieces.add(testPiece02);


        //Movimento horizontal para a direita
        int xO = 0, yO = 0;
        int xD = 0, yD = 1;

        tabuleiro[yO][xO] = testPiece01;
        tabuleiro[yD][xD] = testPiece02;


        //Teste: Mover a peça 'testPiece01' para as coordenadas da peça 'testPiece02'
        boolean resultadoEsperado = true;

        //Deve retornar 'true', pois existem peças em ambas as posições de equipas opostas e dentro do tabuleiro e a distância entre as duas é 1
        boolean resultadoReal = simulador.processaJogada(xO, yO, xD, yD);

        assertEquals(resultadoEsperado, resultadoReal);
    }
}
*/