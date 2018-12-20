package br.edu.ifpb;

// Classe BufferExemplo que cria o Buffer e os métodos set e get

public class BufferExemplo implements Buffer {

    private int buffer = -1;

    public void set(int valor) {

        System.out.printf("Produtor grava: \t%2d\n", valor);

        buffer = valor;

    }

    public int get() {

        System.out.printf("Consumidor lê: \t%2d\n", buffer);

        return buffer;
    }
}
