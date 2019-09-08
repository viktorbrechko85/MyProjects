<%@ page import="com.test.parts.dao.PartsDAO" %>
<%@ page import="com.test.parts.dao.PartsDAOImpl" %>
<%@ page import="com.test.parts.service.PartsServiceImpl" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.test.parts.model.Parts" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.parts.service.PartsService" %>
<%@ page import="com.test.parts.controller.PartsController" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>PartsList App</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/imtech_pager.js"></script>
    <script src="<c:url value="/resources/js/simplePagination.js" />"></script>

    <style type="text/css">
        <!--
        #content {
            text-indent:20px;
            text-align:justify;
        }
        #pagingControls {
            padding-top:15px;
            font-weight:bold;
        }
        #pagingControls ul {
            display:inline;
            padding-left:0.5em;
        }
        #pagingControls li {
            display:inline;
            padding:0 0.5em
        }
        -->
    </style>
    <script type="text/javascript">
        var Imtech = {};
        Imtech.Pager = function() {
            this.paragraphsPerPage = 3;
            this.currentPage = 1;
            this.pagingControlsContainer = '#pagingControls';
            this.pagingContainerPath = '#tableofparts';
            this.numPages = function() {
                var numPages = 0;
                if (this.paragraphs != null && this.paragraphsPerPage != null) {
                    numPages = Math.ceil(this.paragraphs.length / this.paragraphsPerPage);
                }
                return numPages;
            };
            this.showPage = function(page) {
                this.currentPage = page;
                var html = '';
                this.paragraphs.slice((page-1) * this.paragraphsPerPage,
                        ((page-1)*this.paragraphsPerPage) + this.paragraphsPerPage).each(function() {
                    html += '<tr>' + $(this).html() + '</tr>';
                });
                $(this.pagingContainerPath).html(html);
                renderControls(this.pagingControlsContainer, this.currentPage, this.numPages());
            }
            var renderControls = function(container, currentPage, numPages) {
                var pagingControls = 'Страницы: <ul>';
                for (var i = 1; i <= numPages; i++) {
                    if (i != currentPage) {
                        pagingControls += '<li><a href="#" onclick="pager.showPage(' + i + '); return false;">' + i + '</a></li>';
                    } else {
                        pagingControls += '<li>' + i + '</li>';
                    }
                }

                pagingControls += '</ul>';

                $(container).html(pagingControls);
            }
        }
        var pager = new Imtech.Pager();
        $(document).ready(function() {
            pager.paragraphsPerPage = 10;
            pager.pagingContainer = $('#tableofparts');
            pager.paragraphs = $('tr.z', pager.pagingContainer);
            pager.showPage(1);
        });
    </script>
</head>
<body>
<div class="header">
    <div class="header-panel add-part">
        <form action="createPart">
            <input type='submit' value='Новая деталь'/>
        </form>
    </div>
    <div class="header-panel filter-part">
        <form action="filterPart">
            <input type="text" name="filterName" placeholder="Type a partname here...">
            <input type='submit' value='Найти по имени'/>
        </form>
    </div>
    <div class="header-panel sort-part">
        <form action="sortNeedParts">
            <input name="sortNeedPart" type="radio" value="2"> Все детали
            <input name="sortNeedPart" type="radio" value="1"> Необходимые детали
            <input name="sortNeedPart" type="radio" value="0"> Опциональные детали
            <input type='submit' value='Сортировать'/>
        </form>
    </div>
</div>
<div class="content">

    <div class="table-of-parts" >
        <table >
            <thead>
            <tr>
                <th>ИД</th>
                <th>Наименование</th>
                <th>Главная деталь</th>
                <th>Кол-во</th>
                <th>Тип детали</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody id = "tableofparts">
            <c:forEach items="${partsList}" var="part">
                <tr class="z">
                    <td><c:out value="${part.id}" /></td>
                    <td><c:out value="${part.partname}" /></td>
                    <td><c:out value="${part.partbase}" /></td>
                    <td><c:out value="${part.partqty}" /></td>
                     <c:choose>
                        <c:when test="${part.parttype == 1}">
                            <td><c:out value="1-Материнская плата" /></td>
                        </c:when>
                        <c:when test="${part.parttype == 2}">
                            <td><c:out value="2-Процессор" /></td>
                        </c:when>
                        <c:when test="${part.parttype == 3}">
                            <td><c:out value="3-Память" /></td>
                        </c:when>
                        <c:when test="${part.parttype == 4}">
                            <td><c:out value="4-Жесткий диск" /></td>
                        </c:when>
                        <c:when test="${part.parttype == 5}">
                            <td><c:out value="5-Блок питания" /></td>
                        </c:when>
                         <c:when test="${part.parttype == 6}">
                             <td><c:out value="6-Корпус" /></td>
                         </c:when>
                        <c:when test="${part.parttype == 7}">
                            <td><c:out value="7-Опции" /></td>
                        </c:when>
                    </c:choose>
                    <th><a href="editPart?id=<c:out value='${part.id}'/>">Редакт</a></th>
                    <th><a href="deletePart?id=<c:out value='${part.id}'/>">Удалить</a></th>
                </tr>
            </c:forEach>
            </tbody>

          </table>
        <div class="pagination" id="pagingControls"></div>
    </div>
</div>
<%
    int cComp=0;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        PreparedStatement ps=con.prepareStatement("select parttype,sum(partqty) as qty from parts where partbase=1 and parttype <> 7 group by parttype");
        ResultSet rs=ps.executeQuery();
        int cParts = 1;

        rs.next();
        cComp = rs.getInt(2);
        while(rs.next()){
            cParts++;
            if (cComp>rs.getInt(2)){
                cComp = rs.getInt(2);
            }
        }
        if (cParts!=6)
        {
            cComp = 0;
        }
        con.close();
    }catch(Exception e){e.printStackTrace();}

%>
<p></p>
<p></p>
<p></p>
<div>
  <div class="content2">
    <table class="table-of-parts">
        <tbody>
        <tr>
        <td><c:out value="Можно собрать, компьютеров" /></td>
        <td><c:out value = "<%=cComp%>" /></td>
         <td><c:out value="шт" /></td>
        </tr>
        </tbody>
    </table>
    <p></p>
    <label>Для расчета кол-ва примем по умолчанию, что компьютер состоит из 6 необходимых типов деталей:</label>
      <ul>
          <li>1- Матер-я плата</li>
          <li>2- Процессор</li>
          <li>3- Память</li>
          <li>4- Жесткий диск</li>
          <li>5- БП</li>
          <li>6- Корпус</li>
      </ul>
      <label>Для сбора 1 компьютера требуется по одной единицы каждого типа и все типы должны быть в наличии. Ассортимент деталей может быть разным!</label>
  </div>
</div>


</body>
</html>
