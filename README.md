# Embedded Tomcat Server

An embedded tomcat example which gets around the annoying intellij IDEA 
'feature' of not recompiling JSPs on the fly. 

 - JSPs
 - EL
 - JSTL
 - Annotations
   - `@WebServlet`
   - `@WebFilter`
   - `@WebListener`

If you don't want to use Annotations, then there is an example 
ContextListener registered through the included web.xml (in the 
`src/main/webapp/WEB-INF/` directory) which will register the listeners 
before any annotations in the order that they are configured.

What's more, it is faster to start-up and redeploy than running it through 
the IDE.

**Which works both in your IDE and from the distributable**

## To Build/Run

`./gradlew assemble`

untar/unzip the `build/distibutions/` file

cd to the directory

`/bin/embedded-tomcat-template`

and away you go...