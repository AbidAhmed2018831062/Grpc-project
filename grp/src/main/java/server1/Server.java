package server1;

import java.io.IOException;

import io.grpc.ServerBuilder;
import user.UserService;

public class Server {
public static void main(String[] args) throws IOException, InterruptedException
{
	io.grpc.Server ser=ServerBuilder.forPort(9001).addService(new UserService()).build();
	
	ser.start();
	System.out.println("Hey, Server started at 9001 port");
	
	ser.awaitTermination();
	
	
	
}
}
