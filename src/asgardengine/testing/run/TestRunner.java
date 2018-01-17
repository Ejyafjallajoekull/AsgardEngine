package asgardengine.testing.run;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import asgardengine.testing.utility.binary.ByteUtilitiesTester;

public class TestRunner {

	public static void main(String[] args) {
//		TestRunner.runAllTests();
		try {
			ArrayList<Path> list = new ArrayList<Path>();
			Files.find((new File("")).toPath(), Integer.MAX_VALUE, (filePath, fileAttr) -> 
			filePath.toString().toLowerCase().endsWith(".class")).forEach(list::add);
			System.out.println(list.get(0).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void runAllTests() {
		System.out.println("Start testing.");
		ByteUtilitiesTester a = new ByteUtilitiesTester();
		Asserter.assertTestSubject(a);
		System.out.println("All tests passed.");
	}
	
}
