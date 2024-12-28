# ActionDispatcher
An annotation-based request dispatcher for building clean and efficient HTTP routing logic in Java applications.

## Description
ActionDispatcher is a lightweight Java library for processing and dispatching HTTP requests to predefined handlers using annotations. 
Designed for simplicity and scalability, it helps organize routing logic in a clean and efficient manner.

## Features
- Annotation-based configuration (`@Dispatcher`, `@Action`, etc.).
- Support for primitive and complex request parameters.
- Custom exception handlers with `@ExceptionHandler`.
- Built-in support for interceptors to extend request processing.
- Easy integration with existing Java web applications.


## Installation

### From Source
To install the library by compiling the source code:
1. Clone the repository:
    ```bash
    git clone https://github.com/ShirobokovValentin/action_dispatcher.git
    ```
2. Navigate to the project directory:
    ```bash
    cd action_dispatcher
    ```
3. Compile and install the library locally:
    ```bash
    mvn install
    ```

### Maven
Add the following dependency to your `pom.xml`:
```xml
<dependency>
    <groupId>com.github.shirobokovvalentin</groupId>
    <artifactId>action_dispatcher</artifactId>
    <version>1.2</version>
</dependency>
```

### Gradle
Add the following to your `build.gradle`:
```gradle
implementation 'com.github.shirobokovvalentin:action_dispatcher:1.2'
```

### JitPack
If the library is not in Maven Central, you can use JitPack. Add the following configuration:

##### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
   ...
</repositories>

<dependency>
    <groupId>com.github.shirobokovvalentin</groupId>
    <artifactId>action_dispatcher</artifactId>
    <version>1.2</version>
</dependency>
```

#### Gradle
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.shirobokovvalentin:action_dispatcher:1.0.0'
}
```

## Usage

### Defining a Dispatcher

```java {title="DispatcherClass.java"}
package com.example;

import com.github.shirobokovvalentin.action_dispatcher.annotations.*;

@Dispatcher("action")
public class DispatcherClass {

    @Action("greet")
    public String greet(@RequestParam("name") String name) {
        return "Hello, " + name + "!";
    }
}
```

### Integrating with a Web Application

```jsp title="index.jsp"
<%@ page import="com.example.DispatcherClass" %>
<%@ page import="com.github.shirobokovvalentin.action_dispatcher.ActionDispatcher" %>
<%= ActionDispatcher.newInstance(new DispatcherClass()).process(request)%>

```

## Detailed Usage

### Annotation Overview

#### `@Dispatcher`
Marks a class as a dispatcher for handling HTTP actions and specifies a name parameter for the dispatch selector. 
Example:
```java
@Dispatcher("action")
public class MyDispatcher {

}
```
#### `@DefaultAction`
Marks a method as the default action if no dispatch selector is specified. 
Example:
```java
@DefaultAction
public String greet(@RequestParam("name") String name) {
   return "Hello, " + name + "!";
}
```

#### `@Action`
Defines a specific action that is triggered based on a value of the dispatch parameter. 
Example:
```java
@Action("greet")
public String greet(@RequestParam("name") String name) {
    return "Hello, " + name + "!";
}
```

#### `@RequestParam`
Maps request parameters to method arguments. Example:
```java
@Action("sum")
public int sum(@RequestParam("a") int a, @RequestParam("b") int b) {
    return a + b;
}
```

#### `Additional readers`
- `@HeaderParam("param_name")` - Reads the header `param_name` from request.
- `@HttpMethod` - Retrieves the HTTP method (e.g., GET, POST).
- `@IpParam` - Retrieves the client IP address.
- `@Request` - Injects the raw `HttpServletRequest`.

```java
@Action("additional")
public String additional(
        @RequestParam("a") Integer a,
        @HeaderParam("Accept") String header,
        @HttpMethod String httpMethod,
        @IpParam String ip,
        @Request HttpServletRequest request)
{
   return " @RequestParam(\"a\")" + a +
           " @HeaderParam: " + header +
           " @HttpMethod: " + httpMethod +
           " @IpParam: " + ip +
           " @Request: " + request.getClass();
}
```
### Constraints
Applies validation constraints to method arguments. If violated, an `ArgumentValidationException` is thrown.
- `@NotNull` — Argument must not be null.
- `@MinValue(X)` — Argument must be greater than or equal to X.
- `@NotEmpty` — String argument must not be empty.
- `@Pattern(regex)"` — String argument must match the specified `regex`.
- `@Email` — String argument must be a valid email address.
- `@Uniqueidentifier` — String argument must be a valid GUID.


#### `@ExceptionHandler`
Marks a method as a handler for exceptions thrown during request processing. 
Example:
```java
@ExceptionHandler
public String handleException(Exception e) {
    return "Error: " + e.getMessage();
}
```

#### @DispatcherInterceptor
Allows transformation of `HttpServletRequest` before parameter extraction and post-processing after action execution.
```java
@Dispatcher(ACTION_PARAM_NAME)
@DispatcherInterceptor(MyCustomDispatcherInterceptor.class)
public class DispatcherClass {
   
}
```

#### @ActionInterceptor
Adds custom logic before and after the execution of an action or the handling of an exception. 
Example:
```java
@Action("secureAction")
@ActionInterceptor(MyCustomActionInterceptor.class)
public String secureAction() {
    return "Secure Data";
}
```

## Requirements
- Java 6 or higher
- Servlet API (javax.servlet, compatible with Java EE 6/7/8)
- If using Jakarta EE 9 or higher, the library may require adaptation for `jakarta.servlet` package usage.

## License
ActionDispatcher is licensed under the Apache License Version 2.0. See [LICENSE](LICENSE) for details.

## Contact
For questions or suggestions, please contact or open an issue on GitHub.