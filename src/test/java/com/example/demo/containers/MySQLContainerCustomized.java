package com.example.demo.containers;

import org.testcontainers.containers.MySQLContainer;

public class MySQLContainerCustomized<SELF extends MySQLContainerCustomized<SELF>> extends MySQLContainer<SELF> {

    private static final String MY_CNF_CONFIG_OVERRIDE_PARAM_NAME = "TC_MY_CNF";
    private static final Integer MYSQL_PORT = 3306;

    public MySQLContainerCustomized(String dockerImageName) {
        super(dockerImageName);
    }


    @Override
    protected void configure() {
        optionallyMapResourceParameterAsVolume(MY_CNF_CONFIG_OVERRIDE_PARAM_NAME, "/etc/mysql/conf.d", "mysql-default-conf");

        addExposedPort(3306);
        addEnv("MYSQL_DATABASE", "eis");
        addEnv("MYSQL_USER", "admin");
        addEnv("MYSQL_PASSWORD", "admin");
        addEnv("MYSQL_ROOT_PASSWORD", "admin");
        setCommand("mysqld");
        setStartupAttempts(3);
    }

    @Override
    public String getDriverClassName() {
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public String getJdbcUrl() {
        return "jdbc:mysql://" + getHost() + ":" + getMappedPort(MYSQL_PORT) + "/" + "eis";
    }

    @Override
    public String getUsername() {
        return "admin";
    }

    @Override
    public String getPassword() {
        return "admin";
    }

    @Override
    public String getTestQueryString() {
        return "SELECT 1";
    }

    public SELF withConfigurationOverride(String s) {
        parameters.put(MY_CNF_CONFIG_OVERRIDE_PARAM_NAME, s);
        return self();
    }
}