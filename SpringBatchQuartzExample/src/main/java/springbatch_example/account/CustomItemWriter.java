package springbatch_example.account;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.batch.item.ItemWriter;



public class CustomItemWriter implements ItemWriter<Object> {
	
	
	@Override
	public void write(List<? extends Object> arg0) throws Exception {
		
		
		
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			List<AccountVO> accountVO = (List<AccountVO>) arg0;
			for(AccountVO accountVO2 : accountVO){
				if(null != accountVO2 && accountVO2.getTotalAccountNumber()>0){
			fw = new FileWriter(Constant.PATH_REPORT+"\\"+Constant.FILE_NAME+timestamp.getTime()+".csv");
			bw = new BufferedWriter(fw);
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("File Processed :"+Constant.FILE_NAME+timestamp.getTime()+".csv");
			stringBuffer.append("\n");
			stringBuffer.append("Total Accounts :"+accountVO2.getTotalAccountNumber());
			stringBuffer.append("\n");
			stringBuffer.append("Total Credits :"+accountVO2.getCreditedAmount());
			stringBuffer.append("\n");
			stringBuffer.append("Total Debits  :"+accountVO2.getDebitedAmount());
			stringBuffer.append("\n");
			stringBuffer.append("Skipped Transaction :"+accountVO2.getSkippedTransaction());
			bw.write(stringBuffer.toString());
			}
			
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		
	}

	}

}
