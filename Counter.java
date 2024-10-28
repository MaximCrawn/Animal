public class Counter implements AutoCloseable {
    private int count = 0;
    private boolean closed = false;

    public void add() {
        if (closed) {
            throw new IllegalStateException("Counter is closed");
        }
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() {
        closed = true;
        System.out.println("Counter closed.");
    }
}

