# Capitulo CDI

Ejemplo se puede encontrar en el [https://github.com/avbravo/jettraframeworksmodulosestudio](https://github.com/avbravo/jettraframeworksmodulosestudio)

Revise el proyecto cdihttp.

Pasos:


1. Crear anotaciones

* Inject

```java

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Inject {
}

```


* Named

```java

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Named {
    
}

```

* Prototype

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Prototype {
    
}
```


* Singleton

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Singleton {
    
}


```


2. Cree el paquete cdi.container y defina las clases

Se realizara un  escaneo de las clases que implementen CDI. El usuario indicara los paquetes donde las clases se encuentren al container.

* JettraContainer.java

```java
import com.avbravo.jettraframework.cdi.Inject;
import com.avbravo.jettraframework.cdi.Prototype;
import com.avbravo.jettraframework.cdi.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JettraContainer {
    private final Map<Class<?>, Object> singletonBeans = new HashMap<>();
    private final Map<Class<?>, Class<?>> beanDefinitions = new HashMap<>();

    public void scanAndRegister(String packageName) {
        List<Class<?>> classes = JettraClassScanner.scanPackage(packageName);

        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Singleton.class) || clazz.isAnnotationPresent(Prototype.class)) {
                registerBean(clazz);
            }
        }
    }

    public void registerBean(Class<?> beanClass) {
        if (beanClass.isInterface()) {
            throw new IllegalArgumentException("Cannot register an interface directly: " + beanClass.getName());
        }
        beanDefinitions.put(beanClass, beanClass);

        // Si la clase implementa una interfaz, registramos la relación
        for (Class<?> iface : beanClass.getInterfaces()) {
            beanDefinitions.put(iface, beanClass);
        }
    }

    public <T> T getBean(Class<T> beanClass) {
        Class<?> implementationClass = beanDefinitions.get(beanClass);
        if (implementationClass == null) {
            throw new RuntimeException("No implementation found for: " + beanClass.getName());
        }

        if (implementationClass.isAnnotationPresent(Singleton.class)) {
            return getSingletonBean(implementationClass);
        } else {
            return createNewInstance(implementationClass);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T getSingletonBean(Class<?> beanClass) {
        return (T) singletonBeans.computeIfAbsent(beanClass, this::createNewInstance);
    }

    @SuppressWarnings("unchecked")
    private <T> T createNewInstance(Class<?> beanClass) {
        try {
            T instance = (T) beanClass.getDeclaredConstructor().newInstance();
            injectDependencies(instance);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create bean: " + beanClass.getName(), e);
        }
    }

    private <T> void injectDependencies(T instance) {
        for (var field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                Object dependency = getBean(fieldType);
                try {
                    field.set(instance, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject dependency: " + field.getName(), e);
                }
            }
        }
    }
}

```

JettraClassScanner.java

```java
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JettraClassScanner {

    public static List<Class<?>> scanPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String packagePath = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            Enumeration<URL> resources = classLoader.getResources(packagePath);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File directory = new File(resource.getFile());

                if (directory.exists()) {
                    findClassesInDirectory(directory, packageName, classes);
                } else {
//                    System.out.println("JAR files are not supported in this example.");
                     String jarFilePath = resource.getPath().substring(5, resource.getPath().indexOf("!"));
                    findClassesInJar(jarFilePath, packagePath, packageName, classes);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to scan package: " + packageName, e);
        }

        return classes;
    }

    private static void findClassesInDirectory(File directory, String packageName, List<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                findClassesInDirectory(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                try {
                    Class<?> clazz = Class.forName(className);
                    classes.add(clazz);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Failed to load class: " + className, e);
                }
            }
        }
    }
      private static void findClassesInJar(String jarFilePath, String packagePath, String packageName, List<Class<?>> classes) {
        try (JarFile jarFile = new JarFile(jarFilePath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();

                if (entryName.startsWith(packagePath) && entryName.endsWith(".class")) {
                    String className = entryName.substring(0, entryName.length() - 6).replace('/', '.');
                    try {
                        Class<?> clazz = Class.forName(className);
                        classes.add(clazz);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Failed to load class: " + className, e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JAR file: " + jarFilePath, e);
        }
    }
}

```

3. Cree la carpeta cdi.application

* Esta clase permitira la inicializacion de las clases dentro del paquete especificado y agregado al container.

```java

public class JettraConfigApplication {
     private static final JettraContainer container = new JettraContainer();

    public static void initialize(String packageName) {
        container.scanAndRegister(packageName);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return container.getBean(beanClass);
    }
}


```


---

## Crear un modelo

```java

public class User {
     private String id;
    private String name;
    private String email;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}

```

## Cree el repositorio

```java
public interface UserRepository {
    void save(String user);
    String find(String id);
}



```

## Cree la implementacion del repositorio

```java
import com.avbravo.cdihttp.db.DB;
import com.avbravo.cdihttp.model.User;
import com.avbravo.cdihttp.repository.UserRepository;
import com.avbravo.jettraframework.cdi.Singleton;

/**
 *
 * @author avbravo
 */
@Singleton
public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save(String user) {
        System.out.println("Saving user: " + user);
        DB.user.add(new User("1-2",user,""));
    }

    @Override
    public String find(String id) {
        System.out.println("Finding user with ID: " + id);
        return DB.user.getLast().getId()+ " "+DB.user.getLast().getName();
//        return "User-" + id;
    }
    
}



```



## Crear Services

*  Observe que se inyecta el repositorio

```java
import com.avbravo.cdihttp.repository.UserRepository;
import com.avbravo.jettraframework.cdi.Inject;
import com.avbravo.jettraframework.cdi.Prototype;

/**
 *
 * @author avbravo
 */
@Prototype
public class UserService {
    @Inject
    private UserRepository userRepository;

    public void addUser(String user) {
        userRepository.save(user);
    }

    public String getUser(String id) {
        return userRepository.find(id);
    }
}

```

## Crear una clase DB para simular base de datos

```java
import com.avbravo.cdihttp.model.Country;
import com.avbravo.cdihttp.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class DB {
    public static List<User> user = new ArrayList<>();
    public static List<Country> country = new ArrayList<>();
}


```


## Crear la clase HttpServerApp

```java
import com.avbravo.cdihttp.service.CountryService;
import com.avbravo.cdihttp.service.UserService;
import com.avbravo.jettraframework.cdi.container.JettraConfigApplication;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpServerApp {

    public static void startServer() {
        try {
               long start = System.currentTimeMillis();

                System.out.println("\n___________________________________________________________________________");
                System.out.println("                          Htttp configuration....");
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/users/add", exchange -> handleAddUser(exchange));
            server.createContext("/users/get", exchange -> handleGetUser(exchange));
            server.createContext("/countries/add", exchange -> handleAddCountry(exchange));
            server.createContext("/countries/get", exchange -> handleGetCountry(exchange));

        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
            server.start();
            System.out.println("HTTP Server started on port 8080");
            
               long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                   System.out.println("\tServer started in WITH TEST: " + timeElapsed + "ms");
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to start HTTP server", e);
        }
    }

    private static void handleAddUser(HttpExchange exchange) throws IOException {
        UserService userService = JettraConfigApplication.getBean(UserService.class);

        if ("POST".equals(exchange.getRequestMethod())) {
            String user = new String(exchange.getRequestBody().readAllBytes());
            userService.addUser(user);

            sendResponse(exchange, "User added: " + user);
        } else {
            sendErrorResponse(exchange, "Invalid request method. Use POST.");
        }
    }

    private static void handleGetUser(HttpExchange exchange) throws IOException {
        UserService userService = JettraConfigApplication.getBean(UserService.class);

        if ("GET".equals(exchange.getRequestMethod())) {
            String id = exchange.getRequestURI().getQuery().split("=")[1];
            String user = userService.getUser(id);

            sendResponse(exchange, "Retrieved user: " + user);
        } else {
            sendErrorResponse(exchange, "Invalid request method. Use GET.");
        }
    }
   
    private static void handleAddCountry(HttpExchange exchange) throws IOException {
        CountryService countryService = JettraConfigApplication.getBean(CountryService.class);

        if ("POST".equals(exchange.getRequestMethod())) {
            String country = new String(exchange.getRequestBody().readAllBytes());
            countryService.addCountry(country);

            sendResponse(exchange, "Country added: " + country);
        } else {
            sendErrorResponse(exchange, "Invalid request method. Use POST.");
        }
    }

    private static void handleGetCountry(HttpExchange exchange) throws IOException {
        CountryService countryService = JettraConfigApplication.getBean(CountryService.class);

        if ("GET".equals(exchange.getRequestMethod())) {
            String id = exchange.getRequestURI().getQuery().split("=")[1];
            String country = countryService.getCountry(id);

            sendResponse(exchange, "Retrieved country: " + country);
        } else {
            sendErrorResponse(exchange, "Invalid request method. Use GET.");
        }
    }

    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void sendErrorResponse(HttpExchange exchange, String errorMessage) throws IOException {
        exchange.sendResponseHeaders(400, errorMessage.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(errorMessage.getBytes());
        os.close();
    }
}
```

## La clase Main

*  Agregar al container los paquetes de las clases que continenen inyeccion de dependdencias.
```java

import com.avbravo.cdihttp.db.DB;
import com.avbravo.jettraframework.cdi.container.JettraConfigApplication;
import java.util.ArrayList;

/**
 *
 * @author avbravo
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        DB.country = new ArrayList<>();
        DB.user = new ArrayList<>();
        JettraConfigApplication.initialize("com.avbravo.cdihttp.repository");
        JettraConfigApplication.initialize("com.avbravo.cdihttp.repository.implementation");
        JettraConfigApplication.initialize("com.avbravo.cdihttp.service");

        // Iniciar el servidor HTTP
        HttpServerApp.startServer();

        System.out.println("Application is running...");
    }
}


```

### Paso 3: Probar los Endpoints
Una vez que el servidor esté en ejecución, puedes probar los endpoints utilizando herramientas como `curl` o Postman.

#### Ejemplo de Solicitudes

1. **Agregar un Usuario**
   - Método: `POST`
   - URL: `http://localhost:8080/users/add`
   - Body: `"Alice"`

   ```bash
   curl -X POST http://localhost:8080/users/add -d "Alice"
   ```

2. **Obtener un Usuario**
   - Método: `GET`
   - URL: `http://localhost:8080/users/get?id=123`

   ```bash
   curl -X GET "http://localhost:8080/users/get?id=123"
   ```

3. **Agregar un País**
   - Método: `POST`
   - URL: `http://localhost:8080/countries/add`
   - Body: `"Mexico"`

   ```bash
   curl -X POST http://localhost:8080/countries/add -d "Mexico"
   ```

4. **Obtener un País**
   - Método: `GET`
   - URL: `http://localhost:8080/countries/get?id=MX`

   ```bash
   curl -X GET "http://localhost:8080/countries/get?id=MX"
   ```

---

### Salida Esperada
Cuando pruebas los endpoints, el servidor generará salidas como las siguientes:

#### Agregar un Usuario
```
Saving user: Alice
```

#### Obtener un Usuario
```
Finding user with ID: 123
Retrieved user: User-123
```

#### Agregar un País
```
Saving country: Mexico
```

#### Obtener un País
```
Finding country with ID: MX
Retrieved country: Country-MX
```

---

### Explicación del Funcionamiento
1. **Endpoints HTTP**:
   - Los endpoints están asociados con rutas específicas (`/users/add`, `/users/get`, `/countries/add`, `/countries/get`).
   - Utilizan métodos HTTP estándar (`POST` para agregar y `GET` para recuperar).

2. **Inyección de Dependencias**:
   - Los endpoints obtienen los servicios (`UserService` y `CountryService`) a través del contenedor centralizado (`Application.getBean()`).
   - Las dependencias se inyectan automáticamente gracias al sistema de registro automático.

3. **Registro Automático**:
   - Todas las clases anotadas con `@Singleton` o `@Prototype` se registran automáticamente en el contenedor durante la inicialización.

4. **Escalabilidad**:
   - Es fácil agregar nuevos endpoints para otros repositorios o servicios sin modificar el código existente.

---