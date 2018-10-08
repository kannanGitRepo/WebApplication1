package com.webapplication1;

import java.sql.*;
public class LoginCheck{
	public static int validate(String name,String pass,String role){
		int status=0;//Success
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","common_db","knn");
		CallableStatement cstmt = con.prepareCall(
		"{call common_auth.login(?, ?, ?, ?, ?)}");

		cstmt.setString(1,name);
		cstmt.setString(2,pass);
                cstmt.setString(3,role);
		cstmt.registerOutParameter(4,java.sql.Types.INTEGER);
		cstmt.registerOutParameter(5,java.sql.Types.VARCHAR);

		cstmt.executeUpdate();

		int err_code=cstmt.getInt(4);
                String err_msg=cstmt.getString(5);
		if (err_code==0){
			status=0;//Success
		}else if (err_code==101){
                   status=1;//Role is not valid 
                }else{
                    status=2;//Invalid username or password
                }
		if (err_msg!=null){
                    System.out.println(err_msg);
                }

	}catch(Exception e){
		System.out.println(e);
	}
	return status;
	}

}
