package com.javaee.example.query_builder;


public interface QueryBuilder {
    /**
     * @param strings query
     * @return String
     */
    String select(String... strings);

    /**
     * @param strings query
     * @return String
     */
    String insert(String... strings);

    /**
     * @param strings query
     * @return String
     */
    String update(String... strings);

    /**
     * @param strings query
     * @return String
     */
    String delete(String... strings);

    /**
     * @param strings query
     * @return String
     */
    String deleteClass(String... strings);

    /**
     * @param strings query
     * @return String
     */
    String createClass(String... strings);

    /**
     * @param strings query
     * @return String
     */
    String createLink(String... strings);

    /**
     * @param strings query
     * @return String
     */
    String alterClass(String... strings);
}
