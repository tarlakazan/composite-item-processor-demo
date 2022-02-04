package com.example.springbatchdemo;

import com.example.springbatchdemo.batchmodel.ConsoleItemWriter;
import com.example.springbatchdemo.batchmodel.MemberIdValidator;
import com.example.springbatchdemo.batchmodel.MemberNameMerger;
import com.example.springbatchdemo.batchmodel.MembershipDateFilter;
import com.example.springbatchdemo.domain.Member;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Value("${file.input}")
    private String fileInput;


    @Bean
    public FlatFileItemReader<Member> reader() {

        FlatFileItemReader<Member> reader = new FlatFileItemReader<Member>();
        reader.setResource(new ClassPathResource(fileInput));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "id", "firstName", "lastName", "membershipDate" });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Member>() {
                    {
                        setTargetType(Member.class);
                    }
                });
            }
        });
        return reader;

    }


    @Bean
    public Job importMemberJob(Step mainStep) {
        return jobBuilderFactory.get("validateMemberJob")
                .incrementer(new RunIdIncrementer())
                .flow(mainStep)
                .end()
                .build();
    }

    @Bean
    public Step mainStep() throws Exception {
        return stepBuilderFactory.get("mainStep")
                .<Member, Member> chunk(10)
                .reader(reader())
                .processor(compositeItemProcessor())
                .writer(consoleItemWriter())
                .build();
    }

    @Bean
    public MemberIdValidator memberIdValidator() {
        return new MemberIdValidator();
    }

    @Bean
    public MemberNameMerger memberNameMerger() {
        return new MemberNameMerger();
    }

    @Bean
    public MembershipDateFilter membershipDateFilter() {
        return new MembershipDateFilter();
    }

    @Bean
    public CompositeItemProcessor<Member, Member> compositeItemProcessor() throws Exception {
        CompositeItemProcessor<Member, Member> processor = new CompositeItemProcessor<>();

        List<ItemProcessor<Member, Member>> processors = Arrays.asList(memberIdValidator(),membershipDateFilter(),
                memberNameMerger());
        processor.setDelegates(processors);
        processor.afterPropertiesSet();

        return processor;
    }

    @Bean
    public ConsoleItemWriter<Member> consoleItemWriter()
    {
        return new ConsoleItemWriter<Member>();
    }






}
