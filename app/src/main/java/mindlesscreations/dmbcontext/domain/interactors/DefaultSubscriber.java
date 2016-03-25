package mindlesscreations.dmbcontext.domain.interactors;

public class DefaultSubscriber<T> extends rx.Subscriber<T> {
    @Override
    public void onCompleted() {
        // no-op
    }

    @Override
    public void onError(Throwable e) {
        // no-op
    }

    @Override
    public void onNext(T t) {
        // no-op
    }
}
