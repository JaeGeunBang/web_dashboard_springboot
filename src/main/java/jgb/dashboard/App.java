package jgb.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jgb.dashboard.domain.*;
import jgb.dashboard.repository.*;
import jgb.dashboard.io.CSVReader;
import java.util.*;

@SpringBootApplication
public class App implements CommandLineRunner{

	@Autowired
	TotalTrafficRepository TotalTrafficRepository;
	@Autowired
	SearchRepository searchRepository;

	// csv 파일을 db로 입력 여부
	private boolean isInsertCSVToDB = false;

	@Override
	public void run(String... string) throws Exception {
		
		if(isInsertCSVToDB) {
			CSVReader csvReader = new CSVReader() ;
			/*
			List<TotalTraffic> totalTraffic_list = csvReader.readCSVTotalTraffic() ;
			for(TotalTraffic t_object : totalTraffic_list)
				TotalTrafficRepository.save(t_object) ;
			*/
			// 0: avg, 1: qm
			List<Search> searchAvg_list = csvReader.readCSVSearch(0) ;
			for(Search s_object : searchAvg_list)
				searchRepository.save(s_object) ;

			List<Search> searchQm_list = csvReader.readCSVSearch(1) ;
			for(Search s_object : searchQm_list)
				searchRepository.save(s_object) ;
		}	
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

