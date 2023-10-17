package files.chatbot;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DynamicJavaCompiler {
	public static void compileAndRun(String code) {
		try {
			// Save the code to a .java file
			String filename = "DynamicCode.java";
			FileWriter fileWriter = new FileWriter(filename);
			fileWriter.write(code);
			fileWriter.close();

			// Get the system compiler
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

			// Compile the .java file
			int compilationResult = compiler.run(null, null, null, filename);

			if (compilationResult == 0) {
				System.out.println("Compilation is successful");
				// Load and execute the compiled class
				Process process = Runtime.getRuntime().exec("java DynamicCode");
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			} else {
				System.out.println("Compilation Failed");
			}

			// Clean up: Delete the .java and .class files
			new File(filename).delete();
			new File("DynamicCode.class").delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String javaCode = "public class DynamicCode { public static void main(String[] args) { System.out.println(\"Hello from Dynamic Code!\"); } }";
		compileAndRun(javaCode);
	}
}
