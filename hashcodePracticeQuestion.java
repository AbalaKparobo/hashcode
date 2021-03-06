import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HashcodePracticeQuestion {

 

    public static void main(String[] args) throws IOException {

 

	String fileLocation = ".//c_medium.in";
 

        BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
        StringBuilder builder = new StringBuilder();
        String currentLine = reader.readLine();
        String secondLine = reader.readLine();
		reader.close();
 

        String[] firstLineSplit = currentLine.split(" ");
        int maxPizza = Integer.parseInt(firstLineSplit[0]);
        int totalPizzaType = Integer.parseInt(firstLineSplit[1]);

 


        String[] secondLineSplit = secondLine.split(" ");
        //corverting the array to a list for sorting
        List<Integer> sliceList = new ArrayList();
		List<Integer> positions = new ArrayList();
		int sum = 0;
        for (int i=0; i < secondLineSplit.length; i++){
            sliceList.add(Integer.parseInt(secondLineSplit[i]));
			sum = sum + Integer.parseInt(secondLineSplit[i]);
			positions.add(i);
        }
 
//		  EDGE CASE: when total pizza is less than required

        if(sum <= maxPizza){
            String firstLineOutput = String.valueOf(sum);
			String secondLineOutput = String.valueOf(positions.stream().map(String::valueOf).collect(Collectors.joining(" ")));

			System.out.println(firstLineOutput);
			System.out.println(secondLineOutput);
			
			String writeFilePath = ".\\";
			BufferedWriter writer = new BufferedWriter(new FileWriter(writeFilePath + "output.in"));
			
			writer.append(firstLineOutput);
			writer.newLine();
			writer.append(secondLineOutput);
			writer.close();
			return;
        }

 

        //creating a copy of the list before sorting.
        List<Integer> sliceListOriginal = new ArrayList<>();
        sliceListOriginal.addAll(sliceList);
        Collections.sort(sliceList, Collections.reverseOrder());

 


        //finding combination to add up to a value not more than the maximunm pizza
        int totalPizaSlice = 0;
        List<Integer> typePizza = new ArrayList<>();
        for (int i=0; i < sliceList.size(); i++){
            if (totalPizaSlice + sliceList.get(i) <= maxPizza ){
                totalPizaSlice = totalPizaSlice + sliceList.get(i);
                typePizza.add(sliceList.get(i));
            }
        }

 

        //trying to get the position of the pizza type.
        List<Integer> posPizza = new ArrayList<>(); 
 
		for(int a : typePizza){
            for (int i = 0; i < sliceListOriginal.size(); i++){
                if (a == sliceListOriginal.get(i)){
                    posPizza.add(i);
					sliceListOriginal.set(i, 0);
					break;
                }
            }
        }
        Collections.sort(posPizza);

 

        //writing the totalpizza and pizza type to a file
        String firstLineOutput = String.valueOf(posPizza.size());
        String secondLineOutput = String.valueOf(posPizza.stream().map(String::valueOf)
                .collect(Collectors.joining(" ")));
        System.out.println(totalPizaSlice);
        System.out.println(firstLineOutput);
        System.out.println(secondLineOutput);
        String writeFilePath = "./";
        BufferedWriter writer = new BufferedWriter(new FileWriter(writeFilePath + "\\output.in"));

        writer.append(firstLineOutput);
		writer.newLine();
        writer.append(secondLineOutput);

         writer.close();

 

    }
}