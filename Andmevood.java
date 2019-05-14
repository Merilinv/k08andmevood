import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;
import static java.util.stream.Collectors.counting;
import static java.util.Comparator.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Andmevood{
  public static void main(String[] arv)throws Exception{
    String inputf = "inputf.txt";
    String outputf = "outputf.txt";
    ArrayList <String> data = new ArrayList <>();
    //tekstifaili lugemine
    File file = new File(inputf);
    Scanner scan = new Scanner(file);

    while(scan.hasNext()){
      data.add(scan.next().replaceAll("[^a-zA-Z-]", "").toLowerCase());
    }
    scan.close();
    //tekstifaili filteerimine ja ümberarvutamine (map)
    //data.filter(str -> str.startsWith("h")).forEach(System.out::println);

    List<String> result =
            data.stream()
                 .collect(groupingBy(identity(), counting()))
                 .entrySet().stream()
                 .sorted(Map.Entry.<String, Long> comparingByValue(reverseOrder()).
                 thenComparing(Map.Entry.comparingByKey()))
                 .limit(10)
                 .map(Map.Entry::getKey)
                 .collect(toList());

    //tekstifaili järjestamine
    //teise tekstifaili salvestamine
    PrintWriter writer = new PrintWriter(new FileWriter(outputf));
    result.stream().collect(Collectors.toList()).forEach(writer::println);
    writer.close();

  }
}
