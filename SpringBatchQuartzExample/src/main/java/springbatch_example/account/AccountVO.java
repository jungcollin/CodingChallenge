package springbatch_example.account;

public class AccountVO {
	private String fileName;
	private long totalAccountNumber;
	private long creditedAmount;
	private long debitedAmount;
	private int skippedTransaction;
	
	
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the totalAccountNumber
	 */
	public long getTotalAccountNumber() {
		return totalAccountNumber;
	}
	/**
	 * @param totalAccountNumber the totalAccountNumber to set
	 */
	public void setTotalAccountNumber(long totalAccountNumber) {
		this.totalAccountNumber = totalAccountNumber;
	}
	/**
	 * @return the creditedAmount
	 */
	public long getCreditedAmount() {
		return creditedAmount;
	}
	/**
	 * @param creditedAmount the creditedAmount to set
	 */
	public void setCreditedAmount(long creditedAmount) {
		this.creditedAmount = creditedAmount;
	}
	/**
	 * @return the debitedAmount
	 */
	public long getDebitedAmount() {
		return debitedAmount;
	}
	/**
	 * @param debitedAmount the debitedAmount to set
	 */
	public void setDebitedAmount(long debitedAmount) {
		this.debitedAmount = debitedAmount;
	}
	/**
	 * @return the skippedTransaction
	 */
	public int getSkippedTransaction() {
		return skippedTransaction;
	}
	/**
	 * @param skippedTransaction the skippedTransaction to set
	 */
	public void setSkippedTransaction(int skippedTransaction) {
		this.skippedTransaction = skippedTransaction;
	}
	
	
	
	
	

}
