# Payara Starter Project

This is a sample application generated by the Payara Starter.

## Getting Started

### Prerequisites

- [Java SE 21+](https://adoptium.net/?variant=openjdk21)
- [Maven](https://maven.apache.org/download.cgi)

## Running the Application

To run the application locally, follow these steps:

1. Open a terminal and navigate to the project's root directory.

2. Make sure you have the appropriate Java version installed. We have tested with Java SE 8, Java SE 11, Java SE 17, and Java SE 21.

3. Execute the following command:

```
./mvn clean package payara-micro:dev
```

4. Once the runtime starts, you can access the project at http://localhost:8080/


## Building a Docker Image
To build a Docker image for this application follow these steps:

Open a terminal and navigate to the project's root directory. Make sure you have Docker installed and running on your system.
Execute the following Maven command to build the Docker image:

```
mvn docker:build
```

This command will build a Docker image for your application.

Once the image is built, you can run a Docker container from the image using the following command:

```
docker run -p 8080:8080 planos:${project.version}
```
Replace planos:${project.version} with the actual image name and tag.

That's it! You have successfully built and run the application in a Docker container.


## Leer archivo resources MAven

```java
myFeatures.properties property file inside src/main/resources
ou can try this.getClass.getResource("/myProperties/myFeatures.properties")
this.getClass().getClassLoader().getResourceAsStream("myFeatures.properties");
``

## Multiples profiles Maven

# maven-jar-plugin
mvn clean package

# maven-assembly-plugin
mvn clean package -Passembly

# maven-shade-plugin
mvn clean package -Pshade

# onejar-maven-plugin
mvn clean package -Ponejar
And to run executable JAR we may do it this way:

# maven-assembly-plugin
java -jar fatjar-example-1.0.0-jar-with-dependencies.jar
# maven-shade-plugin
java -jar fatjar-example-1.0.0.jar
# onejar-maven-plugin
java -jar fatjar-example-1.0.0.one-jar.jar


---
## Java generador HTML libreria

integrar vue.js

# enpoint

http://localhost:8080/api/employees

http://localhost:8080/api/country
# MVC

Template
http://localhost:8080/api/view/template



Estudiar un proyecto generado con payara started

* usar j2tml

http://localhost:8080/api/j2html


Employees
http://localhost:8080/api/view/employee#

http://localhost:8080/api/view/jmoordbj2html



Clase HtmlController genera una pagina hmtl en el metodo get

La idea es crear un framework MVC

que use los metodos get/put/delete/update e invoque las opciones del controller Employee


Guia

* Las interfaces de usuario son endpoint con el metodo @GET que devuelven html
* En el paquete faces cree las vistas
* TemplateView.java contiene la plantilla base
 en la seccion se invocan los endpoint, obsere las clases HomeView y AboutView definen el home y el about



``
<!-- Custom script to load content -->
    <script>
    $(document).ready(function () {
            function loadContent(page) {
                $('#content').load(page);
            }
            
            $('#employeeLink').on('click', function () {
                                   loadContent('http://localhost:8080/api/view/employee');
            });
            $('#departmentLink').on('click', function () {
                loadContent('department.html');
            });
            $('#managerLink').on('click', function () {
                loadContent('manager.html');
            });
            $('#homeLink').on('click', function () {
                loadContent('http://localhost:8080/api/view/home');
            });
            $('#aboutUsLink').on('click', function () {
                loadContent('http://localhost:8080/api/view/about');
            });
            // Load default page
            loadContent('http://localhost:8080/api/view/home');
        });
    </script>


```


* EmployeeView.java

visualiza los datos de los empleados

```html



```


---

# Leer archivo html de manera directa

Pasos:
0.  Pruebe el endpoint

http://localhost:8080/api/view/welcome


1. Cree el directorio com.web.pages dentro de src/main/resources
   quedaria
   /src/main/resources/com/web/pages

2. Alli coloque las paginas html correspondientes a los view que desea importar

3. Cree la clase WelcomeView.java en el paquete view
   Observe que se llama al archivo welcome.html para cargar todo el contenido.

```java

package com.avbravo.jettraserverhelloworld.view;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avbravo
 */
@Path("/view/welcome")
public class WelcomeView {

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.TEXT_HTML)
    public Response get() {
 
         String text = "";
        URL resource = getClass().getClassLoader().getResource("welcome.html");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {

         

            try {
                var file1 = new File(resource.toURI());
                Scanner myReader = new Scanner(file1);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    text += data;
                    System.out.println(data);
                }

            } catch (URISyntaxException ex) {
                Logger.getLogger(WelcomeView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WelcomeView.class.getName()).log(Level.SEVERE, null, ex);
            }

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());
            //return new File(resource.toURI());
        }
       
        var html = """
                   
                       """;
        html = text;
        return Response.ok(html).build();
    }
}

```

---


# Cambiar en el jmoordbcore

## En CountryRepositoryImpl.java

```java

 @Inject
    Config config;
    @Inject
    @ConfigProperty(name = "mongodb.database")
    String mongodbDatabase;

```


por


```java
 Config config = ConfigProvider.getConfig();

        String mongodbDatabase= config.getValue("mongodb.database", String.class);



```


## Iconos

[https://icons.getbootstrap.com/](https://icons.getbootstrap.com/)



---

# Leer Microprofile-config.properties

 Para leer las propiedades del archivo src/main/resources/META-INF/microprofile-config.properties, usamos la siguiente guia.

* No usaremos @InjectPara evitar el uso a nivel de Java SE

```java

//    @Inject
//    private Config config;
//    @Inject
//    @ConfigProperty(name = "mongodb.uri")
//    private String mongodburi;

```

* En su lugar las clases deben  implementar JettraConfig

````java

import com.jettraserver.config.JettraConfig;
@ApplicationScoped
@DateSupport(jakartaSource = JakartaSource.JAKARTA)
public class MongoDBProducer implements Serializable, JettraConfig {
}

```

* Para leer las propiedades utilice el metodo getMicroprofileConfig("propiedad");

```java   
import com.jettraserver.config.JettraConfig;
import com.jmoordb.core.annotation.DateSupport;
import com.jmoordb.core.annotation.enumerations.JakartaSource;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import java.io.Serializable;

@ApplicationScoped
@DateSupport(jakartaSource = JakartaSource.JAKARTA)
public class MongoDBProducer implements Serializable, JettraConfig {

//    @Inject
//    private Config config;
//    @Inject
//    @ConfigProperty(name = "mongodb.uri")
//    private String mongodburi;
private String mongodburi =getMicroprofileConfig("mongodb.uri");


    @Produces
    @ApplicationScoped
    public MongoClient mongoClient() {      
        MongoClient mongoClient = MongoClients.create(mongodburi);
       return mongoClient;

    }

    public void close(@Disposes final MongoClient mongoClient) {
        mongoClient.close();
    }

}


```

Cambios a Realizar 

- [ ] MongoDBProducer debe llevar JettraConfig

- [ ] Modificar el framework y agregar nueva anotacion JettraConfig para indicar que debe generar JettraConfig en lugar de MicroprofileConfig.

```java
@Repository(entity = Country.class, JettraConfig = Boolean.TRUE)

``

- [ ] Todos los controller deben llevar JettraConfig

- [ ] En todos los repositorios generados **RepositoryImpl** que se generan deben implementar JettraConfig

      * Agregar import com.jettraserver.config.JettraConfig;
      * Cambiar
```java

/**
* Microprofile Config
*/
//    @Inject
//    Config config;
//    @Inject
//    @ConfigProperty(name = "mongodb.database")
//    String mongodbDatabase;

```

por

```java

String mongodbDatabase =getMicroprofileConfig("mongodb.database");

```
---
# Componentes CSS

* [Picocss](https://picocss.com/docs/forms/select)


---
# Templates

[Tailwind Toolbox - Admin Dashboard Template](https://github.com/tailwindtoolbox/Admin-Template)




mvn clean compile exec:java -Dexec.mainClass="com.avbravo.jettraserverhelloworld.start.Start" --no-transfer-progress process-classes org.codehaus.mojo:exec-maven-plugin:3.1.0:exec

