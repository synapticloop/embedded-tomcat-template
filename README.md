# Embedded Tomcat Server

An embedded tomcat example which gets around the annoying intellij IDEA 
'feature' of not recompiling JSPs on the fly - _**Which works both in your 
IDE AND from the distributable**_

 - JSPs
 - EL
 - JSTL
 - Annotations
   - `@WebServlet`
   - `@WebFilter`
   - `@WebListener`

If you don't want to use Annotations, then there is an example ContextListener 
registered through the included web.xml (in the `src/main/webapp/WEB-INF/` 
directory) which will register the listeners  before any annotations in the 
order that they are configured.

What's more, it is faster to start-up and redeploy than running it through the 
IDE.

# Features

Going through the in-built code and what they do

## Listeners

Listeners are invoked when contexts are created or destroyed, they can be 
registered with either annotations (i.e. `@WebListener`), or through the 
deployment descriptor (i.e. `web.xml`).

### `web.xml` Registered Listener 

`src/main/java/com/embedde/romcat/listener/WebXMLContextListener.java`

Registered through the `src/main/webapp/WEB-INF/web.xml` deployment descriptor.

> **NOTE:** This will be invoked first, so if you require ordering for the 
> listener registration, you can use the `web.xml` file. 

### Annotated Listener

`src/main/java/com/embedde/romcat/listener/ContextListener.java`

Registered with `@WebListener`

Will simply print to the console when the context is either initialised, or 
destroyed.

> **NOTE:** The listeners registered with annotations cannot be guaranteed 
> to be registered in any order.  Should you require that a certain listener 
> be registered in order (e.g. Database connection initialiser) then use the
> `web.xml` file


## Filters

Filters are 

## To Build/Run

`./gradlew assemble`

untar/unzip the `build/distibutions/` file

cd to the directory

`/bin/embedded-tomcat-template`

and away you go...

# What you 