package com.util.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InterruptStream extends OutputStream {

    private Function<Integer, Boolean> interrupt;

    private List<Integer> log = new ArrayList<>();

    public InterruptStream(Function<Integer, Boolean> interrupt) {

        initInterrupt(interrupt);
    }

    private void initInterrupt(Function<Integer, Boolean> interrupt) {

        if (interrupt == null) {

            throw new IllegalArgumentException("ERROR: The interrupt to be called must not be null");
        }
        this.interrupt = interrupt;
    }

    /**
     * Writes the specified byte to this output stream. The general contract for <code>write</code> is that one byte is
     * written to the output stream. The byte to be written is the eight low-order bits of the argument <code>b</code>.
     * The 24 high-order bits of <code>b</code> are ignored.
     * <p>
     * Subclasses of <code>OutputStream</code> must provide an implementation for this method.
     *
     * @param b the <code>byte</code>.
     * @throws java.io.IOException if an I/O error occurs. In particular, an <code>IOException</code> may be thrown if
     *                             the output stream has been closed.
     */
    @Override
    public void write(int b) throws IOException {

        if (!interrupt.apply(b)) {

            return;
        }

        log.add(b);
    }

    public Object[] getLog() {

        return log.toArray();
    }

    @Override
    public void flush () {

        log.clear();
    }
}
