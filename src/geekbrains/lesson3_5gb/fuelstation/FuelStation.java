package geekbrains.lesson3_5gb.fuelstation;


import geekbrains.lesson3_5gb.machines.Machine;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FuelStation {
    private static final int stationLine = 3;
    private static final int refuelingTime = 5000;
    private GasPool gasTank;
    private Semaphore semaphore;
    private LinkedList<Machine> queue;
    private ReadWriteLock lock;

    public FuelStation() {
        semaphore = new Semaphore(stationLine);
        gasTank = new GasPool();
        queue = new LinkedList<>();
        lock = new ReentrantReadWriteLock();
    }

    public void arrivalToTheFuelStation (Machine machine) {
        if (semaphore.tryAcquire()) {
            boolean isRefuel;
            try {
                System.out.printf("\n %s: попытка дозаправки...", machine.getId());
                isRefuel = doRefuel(machine);
            } finally {
                semaphore.release();
            }
            new Thread(this::moveQueue).start();
            if (isRefuel) {
                System.out.printf("\n %s заправлен(а).", machine.getId());
                machine.go();
            } else {
                System.out.printf("\n %s не заправлен(а) и прекращает движение.", machine.getId());
            }

        } else {
            lock.writeLock().lock();
            queue.add(machine);
            System.out.printf("\n %s встал(а) в очередь под номером %s.", machine.getId(), queue.indexOf(machine));
            lock.writeLock().unlock();
        }
    }

    private void moveQueue() {
        if (queue.size() > 0) {
            Machine machine;
            lock.writeLock().lock();
            try {
                machine = queue.getFirst();
                queue.removeFirst();
            } finally {
                lock.writeLock().unlock();
            }
            if (machine != null) {
                System.out.printf("\n %s подъехал(а) на дозаправку...", machine.getId());
                arrivalToTheFuelStation(machine);
            }
        }
    }

    private boolean doRefuel(Machine machine) {
        if (gasTank.request(machine.getVolume() - machine.getLevel())) {
            System.out.printf("\n %s : процесс дозоправки...", machine.getId());
            try {
                Thread.sleep(refuelingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            machine.setLevel(machine.getVolume());
            return true;
        }
        return false;
    }

}