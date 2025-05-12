package dfoodbackend.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


public class FluxTest {

    @Test
    public void fluxText() {
        Flux<String> stringFlux = Flux.just("a", "b", "c")
                .concatWith(Flux.error(new RuntimeException("error")))
                .concatWith(Flux.just("AFTER ERROR OCCURED"))
                .log();

        stringFlux.subscribe(value -> System.out.println(value),
                (e) -> System.err.println(e));
          }

          @Test
          public void flux_elements(){
              Flux<String> stringFlux = Flux.just("a", "b", "c")
                      .log();
              StepVerifier.create(stringFlux)
                      .expectNext("a")
                      .expectNext("b")
                      .expectNext("c")
                      .verifyComplete();
          }
}
