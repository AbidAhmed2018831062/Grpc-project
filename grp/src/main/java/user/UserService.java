package user;

import java.sql.*;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import com.abid.grpc.User.APIResponse;
import com.abid.grpc.User.APIResponse.Builder;
import com.abid.grpc.userGrpc.userImplBase;

public class UserService extends userImplBase{
	
	

	 public void login(com.abid.grpc.User.LoginRequest request,
		        io.grpc.stub.StreamObserver<com.abid.grpc.User.APIResponse> reso)  {
		 try {
		
		     String username=request.getUsername();
		     String pass=request.getPassword();
		     
		     APIResponse.Builder  res=APIResponse.newBuilder();
		     Class.forName("com.mysql.cj.jdbc.Driver");
			 
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Users","root","");

			   String s1="Select * from Users";
			   Statement st=con.createStatement();
			   ResultSet rs=st.executeQuery(s1);
			   String name="";
			  String phone="";
			   int p=0;
			   while(rs.next())
			   {
				   String username1=rs.getString("username");
				   String p1=rs.getString("password");
				   if(username1.equals(username)&&p1.equals(pass))
				   {
					   p=1;
					   name=rs.getString("name");
					   phone=rs.getString("phone");
					   break;
				   }
				   
			   }
			if(p==0)
			   {
				   st.close();
				   con.close();
				     res.setResponseCode(2).setResponsemessage("Username or Phone number is wrong");
				     reso.onNext(res.build());
					    reso.onCompleted();
			   }
		     
			else
		     {
		    	 res.setResponseCode(0).setResponsemessage("Successfully LoggedIn\nYour name is: "+name+"\nYour username is: "+username+"\nYour phone number is: "+phone);
		     }
			reso.onNext(res.build());
		    reso.onCompleted();
		 }
			catch (Exception e){
				 
			 }
		    
		    }

		    /**
		     */
		    public void logout(com.abid.grpc.User.Empty request,
		        io.grpc.stub.StreamObserver<com.abid.grpc.User.APIResponse> reso) {
		    	
		    	 APIResponse.Builder  res=APIResponse.newBuilder();
			     res.setResponseCode(1).setResponsemessage("Logout Successful");
			     reso.onNext(res.build());
				    reso.onCompleted();
		    	
		    }
		    public void registration(com.abid.grpc.User.Reg request,
		            io.grpc.stub.StreamObserver<com.abid.grpc.User.APIResponse> reso) {
		       
		        try {
		    	String name=request.getName();
			     String pass=request.getPassword();
			     String phone=request.getPhone();
			     String username=request.getUsername();
			     
			   
			   
			     APIResponse.Builder  res=APIResponse.newBuilder();
			     Class.forName("com.mysql.cj.jdbc.Driver");
				 
				 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Users","root","");

				   String s1="Select * from Users";
				   Statement st=con.createStatement();
				   ResultSet rs=st.executeQuery(s1);
				   int p=0;
				   while(rs.next())
				   {
					   String username1=rs.getString("username");
					   String p1=rs.getString("phone");
					   if(username1.equals(username)||p1.equals(phone))
					   {
						   p=1;
						   break;
					   }
					   
				   }
				   if(p==1) {
				 PreparedStatement s = con.prepareStatement("INSERT INTO `Users`(name,username,phone,password) VALUES ( ?, ?, ?, ?)");
			     s.setString(1, name);
			     s.setString(2,username);
			     s.setString(3,phone);
			     s.setString(4,pass);
			     s.executeUpdate();
			     s.close();
			     con.close();
			     res.setResponseCode(2).setResponsemessage("Successful");
			     reso.onNext(res.build());
				    reso.onCompleted();
				    System.out.println(name+pass+phone+username);
				   }
				   else
				   {
					   st.close();
					   con.close();
					     res.setResponseCode(2).setResponsemessage("Username or Phone number already exists");
					     reso.onNext(res.build());
						    reso.onCompleted();
				   }
		        }
		        catch(Exception e)
		        {
		        	System.out.println(e.getMessage());
		        }
			    	
			    }
		    
		    
	
}
