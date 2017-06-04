package springbatch_example.quartz;



import org.assertj.core.internal.cglib.transform.impl.AddStaticInitTransformer;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import springbatch_example.account.Constant;

public class QuartzJobLauncher extends QuartzJobBean {
	
	private String jobName;
	private JobLauncher jobLauncher;
	private JobLocator jobLocator;
	
	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}



	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}



	/**
	 * @return the jobLauncher
	 */
	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}



	/**
	 * @param jobLauncher the jobLauncher to set
	 */
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}



	/**
	 * @return the jobLocator
	 */
	public JobLocator getJobLocator() {
		return jobLocator;
	}



	/**
	 * @param jobLocator the jobLocator to set
	 */
	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}



	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobParameters jobParameters  = new JobParametersBuilder().addString("file", Constant.PATH_PENDING).addLong("time", System.currentTimeMillis()).toJobParameters();
		
		try {
			Job job = jobLocator.getJob(jobName);
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			System.out.println("The job status is ###############"+jobExecution.getStatus());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException | NoSuchJobException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
