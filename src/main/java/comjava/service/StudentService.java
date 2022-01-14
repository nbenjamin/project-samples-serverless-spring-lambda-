package comjava.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rdsdata.AWSRDSData;
import com.amazonaws.services.rdsdata.AWSRDSDataClient;
import com.amazonaws.services.rdsdata.model.BatchExecuteStatementRequest;
import com.amazonaws.services.rdsdata.model.ExecuteStatementRequest;
import com.amazonaws.services.rdsdata.model.ExecuteStatementResult;
import com.amazonaws.services.rdsdata.model.Field;
import com.amazonaws.services.rdsdata.model.SqlParameter;

@Repository
public class StudentService {

	public static final String RESOURCE_ARN = "arn:aws:rds:ap-southeast-1:909126894583:cluster:mydatabase";
	public static final String SECRET_ARN = "arn:aws:secretsmanager:ap-southeast-1:909126894583:secret:my-secret-7qSHqt";

	public List<Student> getList() {

		AWSRDSData rdsData = AWSRDSDataClient.builder().withRegion(Regions.AP_SOUTHEAST_1)
				// .withCredentials(new AWSStaticCredentialsProvider(credentials))
				.build();

		ExecuteStatementRequest request = new ExecuteStatementRequest().withResourceArn(RESOURCE_ARN)
				.withSecretArn(SECRET_ARN).withDatabase("mydatabase").withSql("select * from mytable");

		ExecuteStatementResult result = rdsData.executeStatement(request);

		List<Student> students = new ArrayList<>();

		for (List<Field> fields : result.getRecords()) {

			System.out.println("fileds: " + fields);
			long idField = fields.get(0).getLongValue();
			String nameField = fields.get(1).getStringValue();

			System.out.println(String.format("Fetched row: id = %d, number = %s", idField, nameField));

			students.add(new Student(idField, nameField));
		}

		return students;
	}

	public void addStudent(Student student) {

		AWSRDSData rdsData = AWSRDSDataClient.builder().withRegion(Regions.AP_SOUTHEAST_1).build();

		BatchExecuteStatementRequest request = new BatchExecuteStatementRequest().withDatabase("mydatabase")
				.withResourceArn(RESOURCE_ARN).withSecretArn(SECRET_ARN)
				.withSql("INSERT INTO mytable VALUES (:id, :name)")
				.withParameterSets(Arrays.asList(Arrays.asList(
						new SqlParameter().withName("id").withValue(new Field().withLongValue(student.getId())),
						new SqlParameter().withName("name")
								.withValue(new Field().withStringValue(student.getName())))));

		rdsData.batchExecuteStatement(request);
	}

	public void deleteStudent(long studentId) {

		AWSRDSData rdsData = AWSRDSDataClient.builder().withRegion(Regions.AP_SOUTHEAST_1).build();

		ExecuteStatementRequest request = new ExecuteStatementRequest().withResourceArn(RESOURCE_ARN)
				.withSecretArn(SECRET_ARN).withDatabase("mydatabase").withSql("delete from mytable where id = :id")
				.withParameters(new SqlParameter().withName("id").withValue(new Field().withLongValue(studentId)));

		rdsData.executeStatement(request);
	}
}
