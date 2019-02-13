
package jgb.dashboard.io;

import java.io.*;
import java.util.*;
import jgb.dashboard.domain.*;

public class CSVReader{
    private String commonPath = "C:\\Users\\jaegeun\\Documents\\git\\data";
    private String totalTrafficFile = "total_traffic_2018_12.csv";
    private String[] searcheFiles = {"search_avg_2018_12.csv", "search_qm_2018_12.csv"};
    
    public List<Search> readCSVSearch(int index)
    {
        BufferedReader br = null;
        List<Search> search_list = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(commonPath + "\\" + searcheFiles[index])) ;
            String line = "";
            String csvSplitBy = ",";
            while(( line = br.readLine()) != null) {
                String[] csv_object = line.split(csvSplitBy) ;
                
                Search search = new Search() ;
                search.setDailyWeeklyMonthly(csv_object[0]);
                search.setDevice(csv_object[1]);
                search.setDate(csv_object[2]);
                search.setValueType(csv_object[3]);
                search.setValue(Double.valueOf(csv_object[4]));
                search.setSearchType(index);
                search_list.add(search) ;
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace() ;
        }catch(IOException e) {
            e.printStackTrace() ;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return search_list ;
    }
    public List<TotalTraffic> readCSVTotalTraffic()
    {
        BufferedReader br = null;
        List<TotalTraffic> totalTraffic_list = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(commonPath + "\\" + totalTrafficFile)) ;
            String line = "";
            String csvSplitBy = ",";
            while(( line = br.readLine()) != null) {
                String[] csv_object = line.split(csvSplitBy) ;
                
                TotalTraffic totalTraffic = new TotalTraffic() ;
                totalTraffic.setDailyWeeklyMonthly(csv_object[0]);
                totalTraffic.setDevice(csv_object[1]);
                totalTraffic.setDate(csv_object[2]);
                totalTraffic.setDomain(csv_object[3]);
                totalTraffic.setPvuv(csv_object[4]);
                totalTraffic.setValue(Integer.valueOf(csv_object[5]));
                totalTraffic_list.add(totalTraffic) ;
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace() ;
        }catch(IOException e) {
            e.printStackTrace() ;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return totalTraffic_list ;
    }
}