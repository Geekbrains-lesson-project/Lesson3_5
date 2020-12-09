package geekbrains.lesson3_5gb.fuelstation;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GasPool {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final float volume = 200f;
    private float level;

    public GasPool() {
        level = volume;
    }

    protected boolean request(float amount) {
        try {
            lock.writeLock().lock();

            System.out.print("\n Уроень топлива в резервуаре (на заправочной станции): " + info());

            if (amount <= level) {
                level -= amount;
                return true;
            }
            return false;
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    private float info() {
        try {
            lock.readLock().lock();
            return level;
        }
        finally {
            lock.readLock().unlock();
        }
    }
}