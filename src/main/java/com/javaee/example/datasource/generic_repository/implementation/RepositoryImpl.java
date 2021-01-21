package com.javaee.example.datasource.generic_repository.implementation;

import com.javaee.example.datasource.db_connector.ClassBuilder;
import com.javaee.example.datasource.db_connector.OrientDbConnector;
import com.javaee.example.datasource.query_builder.QueryBuilder;
import com.javaee.example.datasource.generic_repository.Repository;

import javax.ejb.EJB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.javaee.example.datasource.query_builder.Keywords.SPACE;


public class RepositoryImpl<T extends ClassBuilder<T>> implements Repository<T> {

    @EJB
    OrientDbConnector connector;

    @EJB
    QueryBuilder builder;

    private T instance;

    protected void createInstance(Class<T> clazz){
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        try {
            String[] arr = instance.getClass().getName().toLowerCase(Locale.ROOT).split("\\.");
            result = connector.select(builder
                    .select(arr[arr.length - 1]), instance);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addOne(T obj) {
        boolean result = false;
        Map<String, Object> fields = new HashMap<>(obj.getFieldMap());
        obj.getFieldMap().forEach((k, v) -> {
            if (v == null) {
                fields.remove(k);
            }
        });
        try {
            String[] arr = obj.getClass().getName().toLowerCase(Locale.ROOT).split("\\.");
            result = connector.insert(builder.insert(arr[arr.length - 1], SPACE, getContentQuery(fields)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getContentQuery(Map<String, Object> fields) {
        Map<String, Object> notNullFields = new HashMap<>(fields);
        StringBuilder keysBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        notNullFields.forEach((k, v) -> {
            if (v == null) {
                notNullFields.remove(k);
            }
        });
        Set<String> keySet = notNullFields.keySet();
        int count = 0;
        for (String key : keySet) {
            if (count == (keySet.size() - 1)) {
                keysBuilder.append(key).append(", ");
                keysBuilder.delete(keysBuilder.length() - 2, keysBuilder.length());
                keysBuilder.append(")");
            } else {
                keysBuilder.append(key).append(", ");
            }
            count++;
        }

        Collection<Object> values = notNullFields.values();
        count = 0;
        for (Object obj : values) {
            if (count == (values.size() - 1)) {
                valuesBuilder
                        .append("'")
                        .append(obj)
                        .append("'")
                        .append(", ");
                valuesBuilder.delete(valuesBuilder.length() - 2, valuesBuilder.length());
                valuesBuilder.append(")");
            } else {
                valuesBuilder
                        .append("'")
                        .append(obj)
                        .append("'")
                        .append(", ");
            }
            count++;
        }
        return keysBuilder.toString() + " VALUES " + valuesBuilder.toString();
    }
}
