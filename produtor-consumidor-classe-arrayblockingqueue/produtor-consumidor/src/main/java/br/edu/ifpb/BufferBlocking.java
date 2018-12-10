package br.edu.ifpb;

// Classe BufferExemplo que implementa os métodos set e get com ArrayBlockingQueue

import java.util.concurrent.ArrayBlockingQueue;

public class BufferBlocking implements Buffer {

    private ArrayBlockingQueue<Integer> buffer;

    public BufferBlocking() {

        buffer = new ArrayBlockingQueue<Integer>(3);
    }

    public void set(int value) {

        try {
           buffer.put(value);

            System.out.printf("%s%2d\t%s%d\n", "Produtor grava: ", value,
                    "Buffers ocupados: ", buffer.size());

        } catch (Exception exception) {

            exception.printStackTrace();
        }
    }

    public int get() {

        int readValue = 0;

        try {
            readValue = buffer.take();

            System.out.printf("%s %2d\t%s%d\n", "Consumidor lê: ", readValue,
                    "Buffers ocupados: ", buffer.size());

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return readValue;

    }
}
