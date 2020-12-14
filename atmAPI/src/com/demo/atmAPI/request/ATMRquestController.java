package com.demo.atmAPI.request;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.demo.atmAPI.model.Account;
import com.demo.atmAPI.processing.AddAccountRequest;
import com.demo.atmAPI.service.AuthenticationServiceImpl;
import com.demo.atmAPI.service.OperationsImp;


@Path("/")
public class ATMRquestController {

	 /*@GET
	 @Path("ping")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getMessage() {
	    System.out.println("testing api");
	    return "pong";
	  }
	 */
	 @POST
	 @Path("CREATE/account")
	 @Produces("application/json")
	 @Consumes("application/json")
	 public String addAccounttoDB(Account account) {
		 System.out.println("oooooookkk");
		 AddAccountRequest accountRequest = new AddAccountRequest();
		 Boolean result = accountRequest.addAccountReq(account);
		 System.out.println(result);
		 if(result)
		 {
			 return "User acoount has been added";
		 }
		 else	
			 return "User account already exist";
		}
	 
	 @POST
	 @Path("gettoken")
	 @Produces("application/json")
	 @Consumes("application/json")
	 public String authorize(Account account) {
		 AuthenticationServiceImpl authservice = new AuthenticationServiceImpl();
		 String s = authservice.generateToken(account);
		 return s;
	 }
	 
	 @POST
	 @Path("withdraw/{token}/{accountnumber}/{amount}")
	 @Produces("application/json")
	 @Consumes("application/json")
	public String withdraw(@PathParam(value = "token") String token, @PathParam(value = "accountnumber") String accountNumber, @PathParam(value = "amount") Double amount) {
	try {
		AuthenticationServiceImpl authservice = new AuthenticationServiceImpl();
		OperationsImp operationsimp = new OperationsImp();
	    if (authservice.verifyToken(accountNumber, token)) {
	        if (operationsimp.withdraw(accountNumber, amount)) {
	            return "Success";
	        } else {
	            return "Error occured while updating your account";
	        }
	    } else {
	        return "Invalid Token";
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return "Error occured while updating your account";
	    }
	}
	 
	 @POST
	 @Path("deposit/{token}/{accountnumber}/{amount}")
	 @Produces("application/json")
	 @Consumes("application/json")
	public String deposit(@PathParam(value = "token") String token, @PathParam(value = "accountnumber") String accountNumber, @PathParam(value = "amount") Double amount) {
	try {
		AuthenticationServiceImpl authservice = new AuthenticationServiceImpl();
		OperationsImp operationsimp = new OperationsImp();
	    if (authservice.verifyToken(accountNumber, token)) {
	        if (operationsimp.deposit(accountNumber, amount)) {
	            return "Success";
	        } else {
	            return "Error occured while updating your account";
	        }
	    } else {
	        return "Invalid Token";
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return "Error occured while updating your account";
	    }
	}
	 
	 @GET
	 @Path("checkBalance/{token}/{accountnumber}")
	 @Produces(MediaType.TEXT_PLAIN)
	public Double checkbalance(@PathParam(value = "token") String token, @PathParam(value = "accountnumber") String accountNumber) {
		 try {
			 System.out.println("check balance");
			 AuthenticationServiceImpl authservice = new AuthenticationServiceImpl();
			 OperationsImp operationsimp = new OperationsImp();
	            if (authservice.verifyToken(accountNumber, token)) {
	                return operationsimp.checkBalance(accountNumber);
	            } else {
	                return 0.0;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0.0;
        }
	}

	 
	
}
