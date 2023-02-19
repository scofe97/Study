package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;


    public void save(String itemID) {

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                if (itemID.equals("ex")) {
                    throw new IllegalArgumentException("예외 발생");
                }

                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepositoryV4.save()");

    }

    private void sleep(int millis) {
        try {
            Thread.sleep((millis));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}