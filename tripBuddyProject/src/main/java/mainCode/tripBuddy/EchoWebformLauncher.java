package mainCode.tripBuddy;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
public class EchoWebformLauncher {

	public static String restBaseUri; 
	
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(String webBaseUri) {
        final ResourceConfig rc = new ResourceConfig().packages("mainCode.tripBuddy");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        System.out.println("Trying to starting service at: " + webBaseUri);
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(webBaseUri), rc);
    }

    /**
     * Main method to start web form server. Pass REST web service URL, public IP/hostname of local machine/VM and optional port to start server on.
     * e.g. EchoWebformLauncher http://host1:8081/project0 host2
     * e.g. EchoWebformLauncher http://host1:8081/project0 host2 1950
     * 
     * @param args  URL of the REST service, the public IP/hostname of the local VM, and optional port number to start web form server on (default 8080) 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	if(args.length != 2 && args.length !=3){
    		System.out.println("Please pass the URL of the REST service, and the public IP/hostname of the local VM as parameters. e.g. http://ec2-54-254-184-72.ap-southeast-1.compute.amazonaws.com:8080/project0/  ec2-54-254-184-72.ap-southeast-1.compute.amazonaws.com");
    		System.out.println("You can also optionally pass a port number for the web form server. 8080 is used by default.");
    		return;
    	}

    	int port = args.length == 3 ? Integer.parseInt(args[2]) : 8080;
    	String webBaseUri = "http://" + args[1] + ":" + port + "/project0";
    	restBaseUri = args[0];
    	
        final HttpServer server = startServer(webBaseUri);
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%s/application.wadl\nHit enter to stop it...", webBaseUri));
        System.in.read();
        server.shutdownNow();
    }
}

