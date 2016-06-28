//homework 5.2
db.zips.aggregate([ 
{ $group:{ "_id":{ "state":"$state", "city":"$city" }, 
					"pop":{ $sum:"$pop" } } 
},
{ $match:{ "_id.state":{ $in:[ "CA", "NY" ] }, "pop":{ $gt:25000 } } },
{ $group:{ "_id":null, "pop":{ $avg:"$pop" } } } 

])

//homework 5.3
db.students.aggregate([
{$unwind:"$scores"},
{$match:{"scores.type":{$not:{$eq:"quiz"}}}},
{$group:{_id:{"student":"$student_id","class":"$class_id"},
				student_avg:{$avg:"$scores.score"}
				}
},
{$group:{_id:{"class_id":"$_id.class"},class_avg:{$avg:"$student_avg"}}},
{$sort:{"class_avg":1}}
])

//homework 5.4
db.zips.aggregate([
{$match:{"city":{$regex:/^[0-9]/}}},
{$group:{_id:null,popT:{$sum:"$pop"}}},
{$project:{_id:0,popT:1}}
]).pretty()

