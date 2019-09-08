package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    //private static final String URL_FORMAT = "https://kiev.hh.ua/search/vacancy?text=java+kiev&page=1";
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%s";
    //private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data.html";
   // private static final String URL_FORMAT = "C://1//MyHtml.html";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        int pageNum = 0;
        Document doc = null;
        List<Vacancy> Vacancies = new ArrayList<>();
        while(true) {
            try {
                doc = getDocument(searchString, pageNum);

            } catch (IOException e) {
                e.printStackTrace();
            }
            //Elements vacancies = doc.getElementsByClass("vacancy-serp-item");
            Elements vacancies = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (vacancies.size() == 0) break;
            for(Element element:vacancies){
                if (element!=null){
                    Vacancy vac = new Vacancy();
                    vac.setTitle(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                    vac.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vac.setSiteName("http://hh.ua");
                    vac.setUrl(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                    String salary = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text();
                    String city = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text();
                    vac.setSalary(salary.length()==0 ? "" : salary);
                    vac.setCity(city.length()==0 ? "" : city);
                    Vacancies.add(vac);
                }
            }
            pageNum++;
        }
        return  Vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        String url = String.format(URL_FORMAT, searchString, page);
        Document doc = Jsoup.connect(url).userAgent("Chrome/57.0.2987.133 (jsoup)").referrer("?").get();
        return doc;
    }
}
