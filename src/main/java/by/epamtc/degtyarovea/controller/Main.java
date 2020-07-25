package by.epamtc.degtyarovea.controller;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.impl.FileBookDAO;
import by.epamtc.degtyarovea.entity.TextComponent;

public class Main {

    public static void main(String[] args) {
        BookDAO dao = new FileBookDAO();
        TextComponent book = dao.createBook();

        System.out.println(book.text());
    }
}
