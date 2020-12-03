package com.javaee.example.datasource;

import com.javaee.example.dto.Product;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataSource extends Session {

    public void createSchema() {
        ODatabaseSession session = openSession();
        OClass product = session.createVertexClass("Product");

        if (product.getProperty("id") == null) {
            product.createProperty("id", OType.LONG);
            product.createIndex("product_id", OClass.INDEX_TYPE.UNIQUE, "id");
            product.createProperty("name", OType.STRING);
            product.createProperty("description", OType.STRING);
            product.createProperty("price", OType.DECIMAL);
            product.createProperty("amount", OType.LONG);
        }
        closeSession();
    }

    public void createProduct(String name, String description,
                              BigDecimal price, Long amount) {
        Long generatedId = generateId();
        ODatabaseSession session = openSession();
        OClass product = session.getClass("Product");
        if (product == null) {
            createSchema();
        }

        OVertex result = session.newVertex("Product");
        result.setProperty("id", generatedId, OType.LONG);
        result.setProperty("name", name, OType.STRING);
        result.setProperty("description", description, OType.STRING);
        result.setProperty("price", price, OType.DECIMAL);
        result.setProperty("amount", amount, OType.LONG);
        result.save();
        closeSession();
    }

    public List<Product> findAllProducts() {
        List<Product> list = new ArrayList<>();
        ODatabaseSession session = openSession();
        OClass product = session.getClass("Product");
        if (product == null) {
            createSchema();
        }
        String query = "SELECT FROM Product";
        OResultSet rs = session.command(query);
        while (rs.hasNext()) {
            list.add(mapOResultToProduct(rs.next()));
        }
        rs.close();
        closeSession();
        return list;
    }

    public void deleteOneProduct(Long id) {
        ODatabaseSession session = openSession();
        OClass product = session.getClass("Product");
        if (product == null) {
            createSchema();
        }
        String query = "DELETE VERTEX Product WHERE id=" + id;
        session.command(query);
        closeSession();
    }

    public void updateExitingProduct(Long id, String name, String description,
                                     BigDecimal price, Long amount) {
        ODatabaseSession session = openSession();
        OClass product = session.getClass("Product");
        if (product == null) {
            createSchema();
        }
        String query = "UPDATE Product SET name ='" + name + "',description ='" + description + "'"
                + ",price=" + price + ",amount=" + amount + " WHERE id=" + id;
        session.command(query);
        closeSession();
    }

    private Product mapOResultToProduct(OResult item) {
        Product fromDb = new Product();
        fromDb.setId(item.getProperty("id"));
        fromDb.setName(item.getProperty("name"));
        fromDb.setDescription(item.getProperty("description"));
        fromDb.setPrice(item.getProperty("price"));
        fromDb.setAmount(item.getProperty("amount"));
        return fromDb;
    }

    private Long generateId() {
        List<Product> list = findAllProducts();
        long id;
        if (list != null && list.size() > 0) {
            id = list.get(list.size() - 1).getId() + 1L;
        } else {
            id = 1L;
        }
        return id;
    }
}
