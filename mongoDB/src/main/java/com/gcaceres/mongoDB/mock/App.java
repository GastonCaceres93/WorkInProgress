package com.gcaceres.mongoDB.mock;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	MongoClient client = new MongoClient();
    	MongoDatabase database = client.getDatabase("school");
    	MongoCollection<Document> people = database.getCollection("people");

    	people.drop();
    	
    	Document doc = new Document("name", "Andrew Erlichson").append("company", "10gen");

    	 people.insertOne(doc);      // first insert
    	 doc.remove("_id");             // remove the _id key
    	 people.insertOne(doc);      // second insert
    }
}
