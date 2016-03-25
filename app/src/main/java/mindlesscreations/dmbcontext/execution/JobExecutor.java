package mindlesscreations.dmbcontext.execution;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JobExecutor implements ThreadExecutor {

    // How long to keep threads alive
    private static final int KEEP_ALIVE_TIME = 10;

    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private static final int INITIAL_POOL_SIZE = 3;
    private static final int MAXIMUM_POOL_SIZE = 5;

    private final ThreadPoolExecutor threadPoolExecutor;

    public JobExecutor() {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
        //final int NUM_CORES = Runtime.getRuntime().availableProcessors();

        // Create the actual thread pool managers
        this.threadPoolExecutor = new ThreadPoolExecutor(
                INITIAL_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                workQueue
        );
    }

    @Override
    public void execute(@NonNull Runnable command) {
        this.threadPoolExecutor.execute(command);
    }
}