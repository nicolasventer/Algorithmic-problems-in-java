import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReadmeMaker {

	public static boolean overwrite = false;
	
	public static boolean containsExt(File[] l, String extention) {
		for (File f : l)
			if (f.getName().endsWith("." + extention))
				return true;
		return false;
	}

	public static void browse(File current) {
		File[] l = current.listFiles();
		if (containsExt(l, "pdf")) {
			if (overwrite || !containsExt(l, "md")) {
				FileWriter fw;
				try {
					File readmeFile = new File(current, "README.md");
					fw = new FileWriter(readmeFile);
					for (File f : l)
						if (f.getName().endsWith(".jpg"))
							fw.write("![" + current.getName() + "](" + f.getName() + ")\n");
					fw.close();
					System.out.println(readmeFile+" created");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else
			for (File f : l)
				if (f.isDirectory())
					browse(f);
	}

	public static void main(String[] args) {
		overwrite = args.length>0 && args[0].equals("-f");
		browse(new File("."));
	}

}
