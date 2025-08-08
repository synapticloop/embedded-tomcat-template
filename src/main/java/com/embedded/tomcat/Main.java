package com.embedded.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

	public static void main(String[] args) throws IOException, LifecycleException {
		// REQUIRED - set the temp directory and cleanup on exit
		Path tempDir = Files.createTempDirectory("tomcat-temp");
		tempDir.toFile().deleteOnExit();

		// Set up tomcat
		Tomcat tomcat =  new Tomcat();
		tomcat.setBaseDir(tempDir.toAbsolutePath().toString());
		tomcat.setPort(8080);

		// Add a connector to enable HTTP requests
		tomcat.getConnector();

		Context context = tomcat.addWebapp("", getWebAppDirectory());
		context.addWelcomeFile("index.jsp");

		registerScannerForAnnotations(context);

		tomcat.start();
		// Keep the server running until explicitly shut down
		tomcat.getServer().await();

	}

	/**
	 * <p>Find the webapp directory - this checks for locations whether we are
	 * running in an IDE, or from a distribution release.</p>
	 *
	 * @return The location of the webapp directory
	 */
	private static String getWebAppDirectory() {
		File webappDir = new File("webapp/");
		if (!webappDir.exists()) {
			System.err.println("Webapp 'webapp/' directory not found: " + webappDir.getAbsolutePath());
			webappDir = new File("src/main/webapp/");
			if(!webappDir.exists()) {
				System.err.println("Webapp 'src/main/webapp/' directory not found: " + webappDir.getAbsolutePath());
				System.exit(1);
			}
		}
		return webappDir.getAbsolutePath();
	}

	/**
	 * <p>Register for scanning of jar files so that TLDs and annotations are
	 * picked up automatically.</p>
	 *
	 * @param context The context to start
	 */
	private static void registerScannerForAnnotations(Context context) {
		StandardRoot resources = new StandardRoot(context);
		try {
			URL classesUrl = Main.class.getProtectionDomain().getCodeSource().getLocation();
			File classesSource = new File(classesUrl.toURI());

			if (classesSource.isFile() && classesSource.getName().endsWith(".jar")) {
				resources.addJarResources(new JarResourceSet(resources, "/WEB-INF/classes", classesSource.getAbsolutePath(), "/"));
			} else if (classesSource.isDirectory()) {
				resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", classesSource.getAbsolutePath(), "/"));
				File metaInfResourcesDir = new File(classesSource, "META-INF/resources");
				if (metaInfResourcesDir.exists() && metaInfResourcesDir.isDirectory()) {
					resources.addPreResources(new DirResourceSet(resources, "/", metaInfResourcesDir.getAbsolutePath(), "/"));
				}
			}

		} catch (URISyntaxException e) {
			System.err.println("Error getting classes directory for annotation scanning/resource loading: " + e.getMessage());
			throw new RuntimeException("Failed to configure annotation scanning/resource loading due to URI syntax error.", e);
		}
		context.setResources(resources);
	}
}