package com.javaee.example.datasource;

import com.javaee.example.dto.Product;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public void createSchema() {
        OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
        ODatabaseSession db = orient.open("orient_db", "admin", "admin");
        OClass product = db.createVertexClass("Product");

        if (product.getProperty("id") == null) {
            product.createProperty("id", OType.LONG);
            product.createIndex("product_id", OClass.INDEX_TYPE.UNIQUE, "id");
        }
        db.close();
        orient.close();
    }

    public void createProduct(String name, String description,
                              BigDecimal price, Long amount) {
        Long generatedId = generateId();
        OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
        ODatabaseSession db = orient.open("orient_db", "admin", "admin");
        OClass product = db.getClass("Product");
        if (product == null) {
            createSchema();
        }
        OVertex result = db.newVertex("Product");
        result.setProperty("id", generatedId);
        result.setProperty("name", name);
        result.setProperty("description", description);
        result.setProperty("price", price);
        result.setProperty("amount", amount);
        result.save();
        db.close();
        orient.close();
    }

    public List<Product> findAllProducts() {
        List<Product> list = new ArrayList<>();
        OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
        ODatabaseSession db = orient.open("orient_db", "admin", "admin");
        OClass product = db.getClass("Product");
        if (product == null) {
            createSchema();
        }
        String query = "SELECT FROM Product";
        OResultSet rs = db.query(query);
        while (rs.hasNext()) {
            list.add(mapOResultToProduct(rs.next()));
        }
        rs.close();
        db.close();
        orient.close();
        return list;
    }

    public void deleteOneProduct(Long id) {
        OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
        ODatabaseSession db = orient.open("orient_db", "admin", "admin");
        OClass product = db.getClass("Product");
        if (product == null) {
            createSchema();
        }
        String query = "DELETE VERTEX Product WHERE id=" + "'" + id + "'";
        db.command(query);
        db.close();
        orient.close();
    }

    private Product mapOResultToProduct(OResult item) {
        Product fromDb = new Product();
        fromDb.setId((Long) item.getProperty("id"));
        fromDb.setName((String) item.getProperty("name"));
        fromDb.setDescription((String) item.getProperty("description"));
        fromDb.setPrice((BigDecimal) item.getProperty("price"));
        fromDb.setAmount((Long) item.getProperty("amount"));
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
