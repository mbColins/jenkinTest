//package dfoodbackend.router;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import static jdk.internal.org.objectweb.asm.tree.AnnotationNode.accept;
//
//@Configuration
//public class RouterConfig {
//
//    public RouterFunction<ServerResponse> route(SampleHandlerFunction sampleHandlerFunction){
//        return RouterFunction
//                .route(GET('/functional/flux').and(accept(MediaType.APPLICATION_JSON)), sampleHandlerFunction::flux);
//    }
//}
