package dfoodbackend.Utils.exceptions;

import dfoodbackend.Utils.errors.Details;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static javax.swing.JComponent.getDefaultLocale;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ServerResponse> handleResourceNotFoundException(ResourceNotFoundException ex, ServerRequest request) {
        Locale locale = Locale.getDefault();
        String message = getResponseMessage(ex.getMessage(), null, locale);
        Details errorDetails = new Details(new Date(),message ,request.path());
        return ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(errorDetails);
    }


    private String getResponseMessage(String errorCode, Map<String, Object> args, Locale locale) {
        // Retrieve the message based on errorCode, using default errorCode as fallback
        String message = messageSource.getMessage(errorCode, null, errorCode, locale);
        // Format the message using the provided arguments
        return String.format(message, args.values().toArray());
    }
}


