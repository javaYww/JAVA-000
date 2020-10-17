import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends ClassLoader {

	private static String filePath = "Hello.xlass";

	private static String fileName = "Hello";

	private static String methodName = "hello";

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		Class<?> clazz = main.loadClass(fileName);
		Object hello = clazz.newInstance();
		Method method = clazz.getDeclaredMethod(methodName);
		method.invoke(hello);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			Path path = Paths.get(filePath);
			byte[] bytes = Files.readAllBytes(path);
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = (byte) (255 - bytes[i]);
			}
			return defineClass(fileName, bytes, 0, bytes.length);
		} catch (IOException e) {
			throw new ClassNotFoundException();
		}
	}
}
