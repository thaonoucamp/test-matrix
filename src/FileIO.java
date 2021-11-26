import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIO {
    final String FILE_PATH = "/Users/thaodangxuan/Documents/Test/Test/javacore/input.txt";
    final String ROWS_SIZE = "rows have size not as same as!";
    final String SAY_YES = " ------------>It is the Matrix!";
    String SAY_NO = " ------------>It isn't the Matrix because whose ";
    String REGEX_NUMBER = "^[0-9]*$";
    String resultString="";

    // Check index have to number && not null;
    public boolean matches(String regex, String index) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(index);
        matcher.matches();
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean checkCharInLine(List<List<String>> list, String str_regex) {
        for (List<String> str : list) {
            for (String s : str) {
                if (!this.matches(str_regex, s)) {
                    this.resultString = s;
                    return true;
                }
            }
        }
        return false;
    }

    public void display(List<List<String>> list) {
        for (List<String> l : list) {
            for (int i = 0; i < l.size(); i++) {
                System.out.print(l.get(i) + " ");
                if (i == (l.size() - 1)) {
                    System.out.println("\t");
                }
            }
        }
    }

    public boolean checkLengthOfLine(List<List<String>> list) {
        for (int i = 0; i < list.size(); i++) {
            int size = list.get(0).size();
            int sizeCompare = list.get(i + 1).size();
            if (size != sizeCompare) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        FileIO fileIO = new FileIO();
        List<List<String>> list = new ArrayList<>();
        int count = 1;

        for (String line : Files.readAllLines(Paths.get(fileIO.FILE_PATH))) {
//            System.out.println(line);
            if (!line.equals("")) {
                String[] arr = line.split("\\s");
                List<String> str = new ArrayList<>();
                for (String s : arr) {
                    if (s.equals("")) {
                        continue;
                    } else {
                        str.add(s);
                    }
                }
                list.add(str);
            } else {
                fileIO.display(list);
                if (!list.isEmpty()) {
                    if (fileIO.checkLengthOfLine(list)) {
                        if (!fileIO.checkCharInLine(list, fileIO.REGEX_NUMBER)) {
                            System.out.println(count + fileIO.SAY_YES + "\n");
                        } else {
                            System.out.println(count + fileIO.SAY_NO + fileIO.resultString + "\n");
                        }
                    } else {
                        System.out.println(count + fileIO.SAY_NO + fileIO.ROWS_SIZE + "\n");
                    }
                    list.removeAll(list);
                    count++;
                }
            }
        }
    }
}
