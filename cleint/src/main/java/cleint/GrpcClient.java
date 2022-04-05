package cleint;

import java.util.Scanner;

import com.abid.grpc.User.APIResponse;
import com.abid.grpc.User.LoginRequest;
import com.abid.grpc.User.Empty;
import com.abid.grpc.User.Reg;
import com.abid.grpc.userGrpc;
import com.abid.grpc.userGrpc.userBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
  public static void main(String[] args)
  {
	  ManagedChannel cha= ManagedChannelBuilder.forAddress("localhost", 9001).usePlaintext().build();
	  
	 userBlockingStub us= userGrpc.newBlockingStub(cha) ;
	 APIResponse ap;
	 System.out.println("Hey, Welcome to the GRPC World");
	 System.out.println("Want to Register? Type 1\n Want to SignIn? Type 2\n Want to log out? Type 3");
	 
	 Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
	 int what= sc.nextInt();
	 if(what==1)
	 {
		 System.out.println("Enter Your Name");
		 String hello=sc.nextLine();
		 String name=sc.nextLine();
		 System.out.println("Enter Your Username");
		 
		 String usern=sc.nextLine();
		 System.out.println("Enter Your Phone");
		 
		 String phone=sc.nextLine();
		 
 System.out.println("Enter Your Password");
		 
		 String pass=sc.nextLine();
		 
		 System.out.println(name+usern+phone+pass);
		 
		 
		 Reg res=Reg.newBuilder().setPassword(pass).setPhone(phone).setUsername(usern).setName(name).build();

		  us.registration(res);
		 ap=us.registration(res);
	 }
	 else if(what==2) {
		 System.out.println("Enter Your Username");
		 String hello=sc.nextLine();
		 String name=sc.nextLine();
		 System.out.println("Enter Your Password");
		 
		 String usern=sc.nextLine();
	  LoginRequest lo=LoginRequest.newBuilder().setPassword(usern).setUsername(name).build();
	  
	  us.login(lo);
	   ap=us.login(lo);
	 }
	 else
	 {
		 Empty lg=Empty.newBuilder().build();
		 us.logout(lg);
		 ap=us.logout(lg);
	 }
	  
	  System.out.println(ap.getResponsemessage());
	  
	  
	  
	  
	  
	  
	  
	  
  }
}
