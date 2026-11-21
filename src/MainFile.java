import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("C:/Users/homyk/IdeaProjects/basejava/src");
        printAllFilesInDirectory(dir.getAbsolutePath());
    }

    static void printAllFilesInDirectory(String pathName) {
        File dir = new File(pathName);
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
                printAllFilesInDirectory(dir.getAbsolutePath() + "/" + name);
            }
        }
    }
}
