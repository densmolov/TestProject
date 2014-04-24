TestProject
===========

Сопроводительная записка к тестовому заданию

1.	Для корректной работы приложения необходимо положить файл catalog.xml в корень диска D. В программе путь к файлу прописан, как “D:/catalog.xml”.
2.	Была создана полноценная модель Controller – Service – DAO, как это было бы при работе с базой данных.
3.	Это одностраничное приложение на AJAX.
4.	Страница для аплоада данных (XML файл) в программу – HTML-форма для добавления записи в существующий XML файл. Есть валидация и на клиенте, и на сервере.
5.	Страница для отображения данных (таблица данных должна быть разбита по страницам) – AJAX-запрос тянет максимум 5 записей из XML файла, как это было бы, например, при наличии ORM Hibernate и базы данных.
6.	Страница со ссылкой для доунлоада объединенного XML файла – у пользователя есть возможность сохранить объединенный XML файл на свой компьютер.
7.	Технологии:
JAVA EE7
Spring MVC
Tomcat 7
Maven
JQuery + AJAX
Toastr.js
jquery.validationEngine
HTML5
CSS
Tweeter Bootstrap
Git


Файл catalog.xml имеет вид:

<?xml version="1.0" encoding="UTF-8"?>
<CATALOG>
    <CD>
        <TITLE>Empire Burlesque</TITLE>
        <ARTIST>Bob Dylan</ARTIST>
        <COUNTRY>USA</COUNTRY>
        <COMPANY>Columbia</COMPANY>
        <PRICE>10.90</PRICE>
        <YEAR>1985</YEAR>
    </CD>
    <CD>
        <TITLE>Hide your heart</TITLE>
        <ARTIST>Bonnie Tyler</ARTIST>
        <COUNTRY>UK</COUNTRY>
        <COMPANY>CBS Records</COMPANY>
        <PRICE>9.90</PRICE>
        <YEAR>1988</YEAR>
    </CD>
    <CD>
        <TITLE>Greatest Hits</TITLE>
        <ARTIST>Dolly Parton</ARTIST>
        <COUNTRY>USA</COUNTRY>
        <COMPANY>RCA</COMPANY>
        <PRICE>9.90</PRICE>
        <YEAR>1982</YEAR>
    </CD>
    <CD>
        <TITLE>Still got the blues</TITLE>
        <ARTIST>Gary Moore</ARTIST>
        <COUNTRY>UK</COUNTRY>
        <COMPANY>Virgin records</COMPANY>
        <PRICE>10.20</PRICE>
        <YEAR>1990</YEAR>
    </CD>
    <CD>
        <TITLE>Eros</TITLE>
        <ARTIST>Eros Ramazzotti</ARTIST>
        <COUNTRY>EU</COUNTRY>
        <COMPANY>BMG</COMPANY>
        <PRICE>9.90</PRICE>
        <YEAR>1997</YEAR>
    </CD>
    <CD>
        <TITLE>One night only</TITLE>
        <ARTIST>Bee Gees</ARTIST>
        <COUNTRY>UK</COUNTRY>
        <COMPANY>Polydor</COMPANY>
        <PRICE>10.90</PRICE>
        <YEAR>1998</YEAR>
    </CD>
    <CD>
        <TITLE>Sylvias Mother</TITLE>
        <ARTIST>Dr.Hook</ARTIST>
        <COUNTRY>UK</COUNTRY>
        <COMPANY>CBS</COMPANY>
        <PRICE>8.10</PRICE>
        <YEAR>1973</YEAR>
    </CD>
    <CD>
        <TITLE>Maggie May</TITLE>
        <ARTIST>Rod Stewart</ARTIST>
        <COUNTRY>UK</COUNTRY>
        <COMPANY>Pickwick</COMPANY>
        <PRICE>8.50</PRICE>
        <YEAR>1990</YEAR>
    </CD>
    <CD>
        <TITLE>Romanza</TITLE>
        <ARTIST>Andrea Bocelli</ARTIST>
        <COUNTRY>EU</COUNTRY>
        <COMPANY>Polydor</COMPANY>
        <PRICE>10.80</PRICE>
        <YEAR>1996</YEAR>
    </CD>
    <CD>
        <TITLE>When a man loves a woman</TITLE>
        <ARTIST>Percy Sledge</ARTIST>
        <COUNTRY>USA</COUNTRY>
        <COMPANY>Atlantic</COMPANY>
        <PRICE>8.70</PRICE>
        <YEAR>1987</YEAR>
    </CD>
    <CD>
        <TITLE>Black angel</TITLE>
        <ARTIST>Savage Rose</ARTIST>
        <COUNTRY>EU</COUNTRY>
        <COMPANY>Mega</COMPANY>
        <PRICE>10.90</PRICE>
        <YEAR>1995</YEAR>
    </CD>
    <CD>
        <TITLE>1999 Grammy Nominees</TITLE>
        <ARTIST>Many</ARTIST>
        <COUNTRY>USA</COUNTRY>
        <COMPANY>Grammy</COMPANY>
        <PRICE>10.20</PRICE>
        <YEAR>1999</YEAR>
    </CD>
</CATALOG>
