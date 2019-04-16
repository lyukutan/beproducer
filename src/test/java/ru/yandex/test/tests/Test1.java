package ru.yandex.test.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

public class Test1 extends TestBase  {

    @Epic("Первая группа")
    @Feature("Работа с поиском")
    @Test(description = "Первый тест")
    @Description("•\tОткрыть браузер Chrome и развернуть на весь экран. (Автотест должен работать с разными браузерами IE, Chrome, firefox   - сделать параметром)\n" +
            "•\tПерейти в яндекс маркет\n" +
            "•\tВыбрать раздел Компьютеры\n" +
            "•\tВыбрать раздел Ноутбуки\n" +
            "•\tЗадать параметр поиска до 30000 рублей.\n" +
            "•\tВыбрать производителя HP и Lenovo\n" +
            "•\tВыбрать черный, белый цвет\n" +
            "•\tНажать кнопку Применить.\n" +
            "•\tВывести самый дешевый ноут. (полное название + цена) \n" +
            "•\tВывести самый дорогой  ноут. (полное название + цена) \n" +
            "•\tВ терминал вывести разницу в цене\n" +
            "•\tЗанести все выведенные ноуты в список. Список отсортировать по имени. Вывести в терминал\n" +
            "•\tЗанести все выведенные ноуты в мапу. Ключ имя, цена – значение. Вывести в терминал\t\t\n")
    @Issue("Что-то сломалось")
    public void test1() throws Exception{
        applicationManager.navigationHelper.gotoMainPage();
        applicationManager.navigationHelper.gotoNotebooks();

    }


    @Epic("Первая группа")
    @Feature("Работа с товарами")
    @Test(description = "Второй тест")
    @Description("•\tЗайти внутрь любоко ноута –> Все характеристики. Найти  блок “Питание” “Питание” и занести все характеристики в отдельные поля. Создать несколько конструкторов  с инициализацией полей.\n" +
            "•\tС помощью equals сравнить пару  таких блоков ”Питание”\n" +
            "•\tВыбрать любое поле с вопросом  и вывести текст подсказки\n")
    @Issue("Что-то сломалось")
    public void test2() throws Exception{
        applicationManager.navigationHelper.openSummary(applicationManager.resultHelper.getAllResults().get(0));
        applicationManager.navigationHelper.gotoPreferences();
        applicationManager.compare2powers();
        applicationManager.printDescriotion();
    }


    @Epic("Вторая группа")
    @Feature("Работа с поиском")
    @Story("Настройка параметров из JSON")
    @Test(description = "Третий тест")
    @Description("Вручную создать файл json  (внутри характеристика какого нибудь товара) должно быть больше 3 товаров (параметры мониторов)\n" +
            "\n" +
            "Распарсить json. Найти каждый товар и сделать скрины экрана. Скрины хранить в отдельной дериктории в ресурсах.\n")
    @Issue("Что-то сломалось")
    public void test3() throws Exception{
        applicationManager.findFromJSON();
    }

}
