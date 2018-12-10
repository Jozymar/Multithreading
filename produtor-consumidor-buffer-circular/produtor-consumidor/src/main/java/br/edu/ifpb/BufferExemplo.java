package br.edu.ifpb;

// Classe BufferExemplo que implementa os métodos set e get usando Buffer circular

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferExemplo implements Buffer {

    private Lock accessLock = new ReentrantLock();

    private Condition podeGravar = accessLock.newCondition();

    private Condition podeLer = accessLock.newCondition();

    private int[] buffer = {-1, -1, -1};

    private int buffersOcupados = 0;

    private int gravarIndice = 0;

    private int lerIndice = 0;

    public void set(int valor) {

        accessLock.lock();

        try {
            while (buffersOcupados == buffer.length) {

                System.out.printf("Todos os buffers cheios. Produtor aguarda.\n");

                podeGravar.await();

            }

            buffer[gravarIndice] = valor;

            gravarIndice = (gravarIndice + 1) % buffer.length;

            buffersOcupados++;

            mostrarEstado("Produtor grava: " + valor);

            podeLer.signal();

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            accessLock.unlock();
        }

    }

    public int get() {

        int valorLido = 0;

        accessLock.lock();

        try {
            while (buffersOcupados == 0) {

                System.out.printf("Todos os buffers estão vazios. " +
                "Consumidor aguarda.\n");

                podeLer.await();
            }

            valorLido = buffer[lerIndice];

            lerIndice = (lerIndice + 1) % buffer.length;

            buffersOcupados--;

            mostrarEstado("Consumidor lê: " + valorLido);

            podeGravar.signal();

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            accessLock.unlock();
        }

        return valorLido;

    }

    public void mostrarEstado(String operacao) {
        System.out.printf("%s%s%d\n", operacao, " Buffers ocupados: ",
                buffersOcupados);
    }

}
