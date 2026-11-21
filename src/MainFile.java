import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        printAllFilesInDirectory(new File("C:/Users/homyk/IdeaProjects/basejava/src"));
    }

    static void printAllFilesInDirectory(File dir) {
        File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    System.out.println("    " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(file.getName());
                    printAllFilesInDirectory(file);
                }
            }
        }
    }
}
