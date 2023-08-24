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
        addEnv("MYSQL_ROOT_USERNAME", "root");
        addEnv("MYSQL_ROOT_PASSWORD", "111111");
        setCommand("mysqld");
        setStartupAttempts(3);
    }

    @Override
    public String getDriverClassName() {
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public String getJdbcUrl() {
        return "jdbc:mysql://" + getHost() + ":" + getMappedPort(MYSQL_PORT);
    }

    @Override
    public String getUsername() {
        return "root";
    }

    @Override
    public String getPassword() {
        return "111111";
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