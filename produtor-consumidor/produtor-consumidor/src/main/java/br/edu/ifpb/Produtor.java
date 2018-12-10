package br.edu.ifpb;

import java.util.Random;

// Classe Produtor que compartilha o buffer e executa o método set

public class Produtor implements Runnable {

    private static Random gerador = new Random();

    private Buffer localizacaoCompartilhada;

    public Produtor(Buffer compartilhado) {

        localizacaoCompartilhada = compartilhado;

    }

    public void run() {

        for(int contador=1; contador<=10; contador++) {

            try {
                Thread.sleep(gerador.nextInt(3000));

                localizacaoCompartilhada.set(contador);

            } catch (InterruptedException exception) {

                exception.printStackTrace();

            }
        }

        System.out.printf("\n%s\n%s\n", "Produtor produz!!!!!",
        "Fim do Produtor.");

    }

}
