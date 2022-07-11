import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //exemploClassExecutorService();
        //exemploClassCompletableFuture();
        //exemplo1();
        //completableFutureSemValor();
        //exemploApply();
        exemploListener();

        System.out.println("Fim do método main");

        TimeUnit.MINUTES.sleep(1);
    }

    private static void exemploListener() throws ExecutionException, InterruptedException {
        var t = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return "Future sem valor";
        }).thenAccept(Application::updateDatabase);
    }

    private static void updateDatabase(String message) {
        System.out.println(message);
    }

    private static void exemploApply() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> soma(1000))
                .thenApply(v -> "Soma total: " + v);

        try {
            var result = completableFuture.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void completableFutureSemValor() throws ExecutionException, InterruptedException {
        var ret = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Future sem valor");
        });

    }

    private static void exemplo1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("Olá");
        System.out.println(future.get());
    }

    private static void exemploClassCompletableFuture() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return soma(1000);
        });

        try {
            var result = completableFuture.get();
            System.out.println("Valor: " + result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    private static void exemploClassExecutorService() throws InterruptedException, ExecutionException {
        ExecutorService threadpool = Executors.newCachedThreadPool();
        Future<Integer> futureTask = threadpool.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return soma(1000);
        });

        var result = futureTask.get();
        System.out.println("Valor: " + result);
        System.out.println("Fim");

        threadpool.shutdown();
    }
    public static Integer soma(Integer numero) {

        return IntStream
                .range(0, numero)
                .sum();
    }
}
