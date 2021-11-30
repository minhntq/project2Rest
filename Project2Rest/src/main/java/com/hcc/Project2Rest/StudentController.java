package com.hcc.Project2Rest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	public List<Student> readData() throws IOException {
		FileReader fileReader = new FileReader("D:\\eclipse -workspace\\Project2\\src\\main\\resources\\student.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		List<Student> studentList = new ArrayList();
		// List<Author> authors = new ArrayList();

		String header = bufferedReader.readLine(); // read the header
		String line = bufferedReader.readLine(); // read the first line

		while (line != null) {
			String[] data = line.split(",");// split each read line by comma

			Student student = new Student(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]), data[3],
					data[4]);
			studentList.add(student);
			line = bufferedReader.readLine();
			// authors = new ArrayList();
		}
		return studentList;
	}

	/***
	 * http://localhost:8080/book/Algorithms Illuminated - Part 3
	 * 
	 * @param title
	 * @return Book
	 * @throws IOException
	 */
	@GetMapping("/name/{name}")
	public Student book(@PathVariable String name) throws IOException {
		System.out.println("search student by name : " + name);
		List<Student> stu = readData();
		for (Student student : stu) {
			if (student.getFirstName().equalsIgnoreCase(name)) {
				System.out.println("found name " + student);
				return student;
			}
		}
		System.out.println(" No name found for this name: " + name);
		return null;
	}

	@GetMapping("/book")
	public Student book(@RequestParam double gpa, @RequestParam String gender) throws IOException {
		System.out.println("search gpa by title : " + gpa + " author : " + gender);
		List<Student> stu = readData();
		for (Student student : stu) {
			if (student.getGpa() == gpa & student.getGender().equalsIgnoreCase(gender)) {
				System.out.println("found book " + student);
				return student;
			}
			
		}
		System.out.println(" No book found for title " + gpa + "or " + gender);
		return null;
	}

	@GetMapping("/gpa")
	public double average() throws IOException {
		double avg = 0.0;

		List<Student> stu = readData();
		for (Student student : stu) {
			avg += student.getGpa();
		}
		avg = avg / stu.size();
		return avg;
	}
}
