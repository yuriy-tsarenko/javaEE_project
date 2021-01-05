package com.javaee.example.query_builder;

import javax.ejb.Stateless;

@Stateless
public class QueryBuilderBean implements QueryBuilder {

    private StringBuilder select = new StringBuilder("SELECT * FROM ");
    private StringBuilder insert = new StringBuilder("INSERT INTO ");
    private StringBuilder update = new StringBuilder("UPDATE ");
    private StringBuilder delete = new StringBuilder("DELETE VERTEX ");
    private StringBuilder deleteClass = new StringBuilder("DELETE CLASS ");
    private StringBuilder createClass = new StringBuilder("CREATE CLASS ");
    private StringBuilder createLink = new StringBuilder("CREATE LINK ");
    private StringBuilder alterClass = new StringBuilder("ALTER CLASS ");

    @Override
    public String select(String... strings) {
        for (String string : strings) {
            select.append(string);
        }
        String query = select.toString();
        select = new StringBuilder("SELECT * FROM ");
        return query;
    }

    @Override
    public String insert(String... strings) {
        for (String string : strings) {
            insert.append(string);
        }
        String query = insert.toString();
        insert = new StringBuilder("INSERT INTO ");
        return query;
    }

    @Override
    public String update(String... strings) {
        for (String string : strings) {
            update.append(string);
        }
        String query = update.toString();
        update = new StringBuilder("UPDATE ");
        return query;
    }

    @Override
    public String delete(String... strings) {
        for (String string : strings) {
            delete.append(string);
        }
        String query = delete.toString();
        delete = new StringBuilder("DELETE VERTEX ");
        return query;
    }

    @Override
    public String deleteClass(String... strings) {
        for (String string : strings) {
            deleteClass.append(string);
        }
        String query = deleteClass.toString();
        deleteClass = new StringBuilder("DELETE CLASS ");
        return query;
    }

    @Override
    public String createClass(String... strings) {
        for (String string : strings) {
            createClass.append(string);
        }
        String query = createClass.toString();
        createClass = new StringBuilder("CREATE CLASS ");
        return query;
    }

    @Override
    public String createLink(String... strings) {
        for (String string : strings) {
            createLink.append(string);
        }
        String query = createLink.toString();
        createLink = new StringBuilder("CREATE LINK");
        return query;
    }

    @Override
    public String alterClass(String... strings) {
        for (String string : strings) {
            alterClass.append(string);
        }
        String query = alterClass.toString();
        alterClass = new StringBuilder("ALTER CLASS ");
        return query;
    }
}