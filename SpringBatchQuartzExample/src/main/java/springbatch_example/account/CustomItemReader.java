package springbatch_example.account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;



public class CustomItemReader implements ItemReader<Object>{

	@Override
	public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		AccountVO accountVO = null;
		try {			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			File folder = new File(Constant.PATH_PENDING);
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {	
				 accountVO = new AccountVO();
				//if(file.getName().toLowerCase().endsWith(".txt")){
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			Map<String, Integer> wordCounts = new HashMap<String, Integer>();
			String line;
			int totalAccounts= 0;
			int skippedCount = 0;
			int creditedAmount = 0;
			int debitedAmount= 0;
			while ((line = bufferedReader.readLine()) != null) {			
				String	[] array = line.split(",");				
				if(array[0].matches("[0-9]+")){
			            if (!wordCounts.containsKey(array[0])) {
			                wordCounts.put(array[0], Integer.parseInt(array[1]));
			                totalAccounts = totalAccounts+1;
			            } else {
			                wordCounts.put(array[0],  wordCounts.get(array[0]) + Integer.parseInt(array[1]));
			               
			            }
			            if(Integer.parseInt(array[1]) > 0)
			            creditedAmount = creditedAmount+Integer.parseInt(array[1]);
			            else
			            	debitedAmount = debitedAmount+Integer.parseInt(array[1]);
				}else{
					skippedCount = skippedCount+1;
				}
			        
				
			}
				
			fileReader.close();
			//System.out.println("Output is: skippedCount"+skippedCount+ " creditedAmount "+  creditedAmount +" debitedAmount "+debitedAmount+" totalAccounts "+totalAccounts);
			
			accountVO.setSkippedTransaction(skippedCount);
			accountVO.setCreditedAmount(creditedAmount);
			accountVO.setDebitedAmount(debitedAmount);
			accountVO.setTotalAccountNumber(totalAccounts);
			accountVO.setFileName(file.getName());
			
			File f1 = new File(Constant.PATH_PROCESSED+"\\"+Constant.FILE_NAME+timestamp.getTime()+".csv");				
			 file.renameTo(f1);
			
				}
			//}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return accountVO;
		//return null;
	}

	


	

}
