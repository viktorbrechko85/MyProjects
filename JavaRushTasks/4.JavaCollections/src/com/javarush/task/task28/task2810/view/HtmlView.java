package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
/*
Пора заняться отображением вакансий.
1. В методе update класса HtmlView реализуй следующую логику:
1.1. сформируй новое тело файла vacancies.html, которое будет содержать вакансии,
1.2. запиши в файл vacancies.html его обновленное тело,
1.3. Все исключения должны обрабатываться в этом методе - выведи стек-трейс, если возникнет исключение.

2. Для реализации п.1 создай два пустых private метода:
String getUpdatedFileContent(List<Vacancy>), void updateFile(String),
Реализовывать их логику будем в следующих заданиях.

3. Чтобы добраться до файла vacancies.html, сформируй относительный путь к нему.
В классе HtmlView создай приватное строковое final поле filePath, присвой ему относительный путь к vacancies.html.
Путь должен быть относительно корня проекта.
Формируй путь динамически используя this.getClass().getPackage() и разделитель "/".

Подсказка: путь должен начинаться с "./".
 */
public class HtmlView implements View {
    private final String filePath = "./4.JavaCollections/src/"+this.getClass().getPackage().getName().replaceAll("\\.","/")+"/vacancies.html";
    //private final String filePath = "G:\\Dev\\Java\\ProjectJavaRushTask\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task28\\task2810\\view\\vacancies.html";
    Controller controller;
    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(vacancies.size());
        System.out.println(filePath);
        try {
            String newFile = getUpdatedFileContent(vacancies);
            updateFile(newFile);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies){
        Document doc = null;
        try {
            doc = getDocument();
            Element templateEl = doc.getElementsByClass( "template").first();
            Element tempEl = templateEl.clone();
            tempEl.removeClass("template").removeAttr("style");
            doc.getElementsByAttributeValue("class","vacancy").remove();

            Element template;
            Element temp;
            for(Vacancy vac: vacancies){
                template = tempEl.clone();
                template.getElementsByClass("city").first().text(vac.getCity());
                template.getElementsByClass("companyName").first().text(vac.getCompanyName());
                template.getElementsByClass("salary").first().text(vac.getSalary());
                temp = template.getElementsByTag("a").first();
                temp.text(vac.getTitle());
                temp.attr("href",vac.getUrl());
                templateEl.before(template.outerHtml());
            }
            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

    }
    private void updateFile(String body)  {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))){
            fos.write(body.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath),"UTF-8");
    }
}
