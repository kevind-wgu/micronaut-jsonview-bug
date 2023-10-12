## Micronaut 4.1.2

## Bug
This code is demonstrating a bug with using @JsonView and handling exceptions.

To see this bug in action run the test "JsonViewBugTest".

To repeat the bug
1. Create a JsonView class (Views.class)
2. Create a rest GET endpoint.
   1. Declaration should return an object.
   2. Body of method should throw a custom exception.
   3. Annotate the endpoint with @JsonView(Views.Public.class)
3. Create an exception handler for the custom exception.
   1. It should handle the response with setting a status code.
   2. It also needs to set something in the body. (See MyObjExceptionHandler)
4. Annotate a given rest endpoint with JsonView(Views.class)

That should be enough to repeat the bug.

It apepars that io.micronaut.jackson.databind.JacksonDatabindMapper was changed such that
it now registeres the expected type. Unforunately the type that is being converted is not
the expected type and is instead of type JsonError. So the ObjectMapper throws an exception.