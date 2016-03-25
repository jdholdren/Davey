package mindlesscreations.dmbcontext.execution;

import rx.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
