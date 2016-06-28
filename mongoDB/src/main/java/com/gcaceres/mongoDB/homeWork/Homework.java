package com.gcaceres.mongoDB.homeWork;

import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Aggregates.unwind;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Updates.pull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;

public class Homework {

	private MongoDatabase db;
	private MongoClient client;

	public Homework() {
		client = new MongoClient();
	}

	public void ejercicio_2_1() {
		db = client.getDatabase("students");
		MongoCollection<Document> grades = db.getCollection("grades");

		Bson sort = new Document("student_id", 1).append("score", 1);
		List<Document> homeworkByStudent = grades.find(eq("type", "homework")).sort(sort)
				.into(new ArrayList<Document>());
		Document delete = new Document();
		int studentId;
		int currentStudentId = -1;
		for (Document doc : homeworkByStudent) {
			studentId = doc.getInteger("student_id");
			if (currentStudentId != studentId) {
				currentStudentId = studentId;
				delete.clear();
				delete.append("_id", doc.get("_id"));
				grades.deleteOne(delete);
			}
		}
	}

	public void ejercicio_3_1() {
		db = client.getDatabase("school");
		MongoCollection<Document> students = db.getCollection("students");

		List<Document> homeWork = students.aggregate(
				Arrays.asList(unwind("$scores"), match(eq("scores.type", "homework")), sort(ascending("scores.score")),
						group("$_id", Accumulators.min("score", "$scores.score")), sort(ascending("_id"))))
				.into(new ArrayList<Document>());

		for (Document doc : homeWork) {
			students.updateOne(eq("_id", doc.get("_id")),
					pull("scores", new Document("type", "homework").append("score", doc.getDouble("score"))));
		}

	}
}
