package com.gcaceres.mongoDB.blog;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSpark {

	public static void main(String []args){
		Spark.get("/hello", new Route() {
			public Object handle(Request arg0, Response arg1) throws Exception {
				return "hello spark";
			}
		});
		
	}
}
