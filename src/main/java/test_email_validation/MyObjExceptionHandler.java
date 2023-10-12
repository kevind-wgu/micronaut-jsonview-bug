package test_email_validation;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@Requires(classes = {MyObjException.class, ExceptionHandler.class})
@RequiredArgsConstructor
public class MyObjExceptionHandler implements ExceptionHandler<MyObjException, HttpResponse> {
    private final ErrorResponseProcessor<?> errorResponseProcessor;

    @Override
    public HttpResponse handle(HttpRequest request, MyObjException e) {
        return this.errorResponseProcessor.processResponse(ErrorContext.builder(request).cause(e).errorMessage(e.getMessage()).build(), HttpResponse.notFound());
    }
}
