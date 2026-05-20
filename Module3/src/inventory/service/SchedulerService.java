package inventory.service;

public class SchedulerService {

    public void startThreads() {

        Thread pumaThread =
                new Thread(
                        new FileProcessorService(
                                "puma"));

        Thread nikeThread =
                new Thread(
                        new FileProcessorService(
                                "nike"));

        pumaThread.setDaemon(true);
        nikeThread.setDaemon(true);

        pumaThread.start();
        nikeThread.start();

        System.out.println(
                "Threads Started Successfully");
    }
}