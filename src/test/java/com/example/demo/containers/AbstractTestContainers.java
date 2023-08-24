package com.example.demo.containers;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@SuppressWarnings("ALL")
@SpringBootTest
public class AbstractTestContainers extends ParentTestContainer {

    private static final boolean reuseContainers = true;

    static {
        if (isNotPopulated()) {

            addContainer(new MySQLContainerCustomized("mysql:8")
                    .withInitScript("sitedb.sql")
                    .withReuse(reuseContainers),
                    container -> Map.of(
                            "spring.datasource.url", ((MySQLContainerCustomized) container).getJdbcUrl(),
                            "spring.datasource.username", ((MySQLContainerCustomized) container).getUsername(),
                            "spring.datasource.password", ((MySQLContainerCustomized) container).getPassword(),
                            "spring.datasource.jdbcUrl", ((MySQLContainerCustomized) container).getJdbcUrl()));
            addContainer(new MySQLContainerCustomized("mysql:8")
                            .withInitScript("coredb.sql")
                            .withReuse(reuseContainers),
                    container -> Map.of(
                            "spring.second-datasource.url", ((MySQLContainerCustomized) container).getJdbcUrl(),
                            "spring.second-datasource.username", ((MySQLContainerCustomized) container).getUsername(),
                            "spring.second-datasource.password", ((MySQLContainerCustomized) container).getPassword(),
                            "spring.second-datasource.jdbcUrl", ((MySQLContainerCustomized) container).getJdbcUrl()));
        }
        setupConnectivityProperties();
        Map<String, String> connectivityProperties = getConnectivityProperties();
        for (Map.Entry<String, String> entry : connectivityProperties.entrySet()) {
            System.setProperty(entry.getKey(), entry.getValue());
        }
    }

    @SneakyThrows
    public static void blockUntil(int timeoutSeconds, Predicate<?> predicate) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeToStopPolling = now.plusSeconds(timeoutSeconds);
        while (LocalDateTime.now().isBefore(timeToStopPolling)) {
            if (predicate.test(null)) {
                return;
            }
            TimeUnit.SECONDS.sleep(1);
            System.err.println("sleep");
        }
        Assert.fail("Waited " + timeoutSeconds + " and didn't get the answer");
    }

    public static void blockUntil(Predicate<?> predicate) {
        blockUntil(10, predicate);
    }
}
