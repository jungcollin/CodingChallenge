package springbatch_example;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springbatch_example.account.CustomItemReader;
import springbatch_example.account.CustomItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfigurations {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public CustomItemReader reader(){
		return new CustomItemReader();
	}
	
	@Bean
	public CustomItemWriter writer(){
		return new CustomItemWriter();
	}

	

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Object, Object>chunk(10).reader(reader()).writer(writer()).build();

	}

	


	@Bean
	public Job testjob() {
		return jobBuilderFactory.get("testjob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();

	}
	

}
