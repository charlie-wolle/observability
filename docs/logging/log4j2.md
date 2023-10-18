# Apache Log4j2 - Backend Logging

## Advantages over Log4j, Slf4j and Logback

https://logging.apache.org/log4j/log4j-2.2/manual/index.html

- **Performance** -> next-generation lock-free Asynchronous Loggers
- **Customizable** configuration with new Appenders, Filters, Layouts, Lookups, and Pattern
  Converters
- **Automatically reload** its configuration
- Supported APIs: SLF4J, Commons Logging, Log4j-1.x and java.util.logging
- Support for Message objects
- Support Filters that can be configured to process events before they are handled by a Logger

## Configuration

Source: /tigerteam-observability/observability/src/main/resources/log4j2.xml

```xml

<Configuration status="WARN" monitorInterval="30">
  <Properties>
    <Property name="LOG_PATTERN">
      %d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${hostName} --- [%15.15t] %-40.40c{1.} %-4.4L : %X - %m%n%ex
    </Property>
    <Property name="CEF_PATTERN">
      %m%n
    </Property>
    <Property name="LOG_DIR">/logs</Property>
  </Properties>
  <Appenders>
    <RollingRandomAccessFile
      name="RollingCEFFile"
      fileName="logs/cef.log"
      filePattern="logs/$${date:yyyy-MM}/cef-%d{MM-dd-yyyy}-%i.log.gz">
      <PatternLayout pattern="${CEF_PATTERN}"/>
      <Policies>
        <TimeBasedTriggeringPolicy/>
        <SizeBasedTriggeringPolicy size="10 MB"/>
      </Policies>
      <DefaultRolloverStrategy max="20"/>
    </RollingRandomAccessFile>
    <RollingRandomAccessFile
      name="RollingDevelopmentFile"
      fileName="logs/develop/dev.log"
      filePattern="logs/develop/$${date:yyyy-MM}/dev-%d{MM-dd-yyyy}-%i.log.gz">
      <PatternLayout pattern="${LOG_PATTERN}"/>
      <Policies>
        <TimeBasedTriggeringPolicy/>
        <SizeBasedTriggeringPolicy size="10 MB"/>
      </Policies>
      <DefaultRolloverStrategy max="20"/>
    </RollingRandomAccessFile>
    <RollingRandomAccessFile
      name="RollingApplicationFile"
      fileName="logs/app/application.log"
      filePattern="logs/app/$${date:yyyy-MM}/application-%d{MM-dd-yyyy}-%i.log.gz">
      <PatternLayout pattern="${LOG_PATTERN}"/>
      <Policies>
        <TimeBasedTriggeringPolicy/>
        <SizeBasedTriggeringPolicy size="10 MB"/>
      </Policies>
      <DefaultRolloverStrategy max="20"/>
    </RollingRandomAccessFile>
    <RollingRandomAccessFile
      name="RollingApplicationJsonFile"
      fileName="logs/json/app.json"
      filePattern="logs/json/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.json.gz">
      <JSONLayout eventEol="true" properties="true" stacktraceAsString="true"
        includeTimeMillis="true">
      </JSONLayout>
      <Policies>
        <TimeBasedTriggeringPolicy/>
        <SizeBasedTriggeringPolicy size="10 MB"/>
      </Policies>
      <DefaultRolloverStrategy max="20"/>
    </RollingRandomAccessFile>
    <Console name="ConsoleAppender" target="SYSTEM_OUT" follow="true">
      <PatternLayout pattern="${LOG_PATTERN}"/>
    </Console>
  </Appenders>
  <Loggers>
    <Logger name="tt.logging" level="debug" additivity="false">
      <AppenderRef ref="RollingCEFFile"/>
    </Logger>
    <Logger name="tt.observability" level="info" additivity="false">
      <AppenderRef ref="RollingApplicationFile"/>
      <AppenderRef ref="RollingDevelopmentFile"/>
      <AppenderRef ref="ConsoleAppender"/>
      <AppenderRef ref="RollingApplicationJsonFile"/>
    </Logger>
    <Logger name="tt.observability" level="trace" additivity="false">
      <AppenderRef ref="RollingDevelopmentFile"/>
      <AppenderRef ref="ConsoleAppender"/>
      <AppenderRef ref="RollingApplicationJsonFile"/>
    </Logger>
    <Root level="info">
      <AppenderRef ref="ConsoleAppender"/>
      <AppenderRef ref="RollingApplicationJsonFile"/>
    </Root>
  </Loggers>
</Configuration>
```

### Properties

1. Logging pattern for application logs

```
%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${hostName} --- [%15.15t] %-40.40c{1.} %-4.4L : %X - %m%n%ex
2023-08-17 17:22:48.763 DEBUG SWOPF2MNJFL --- [nio-8080-exec-2] t.o.s.j.JwtUtils 45 : {username=matze} - JWT Token is generated.
```

2. Logging pattern for CEF logs - output of the message from CEFlogService

```
%m%n
```

### Appenders

RollingRandomAccessFile similar to the standard RollingFileAppender except it is always buffered. We
saw a 20-200% performance improvement.

Essentials:

- name: Name of the appender
- fileName: Name of the log file
- filePattern: Name of the log file pattern
- PatternLayout: Pattern of the log file

Definition of four RollingRandomAccessFile:

- **RollingCEFFile: CEF logs**

    * CEF logs are used for SIEM integration
    * (debug level an higher)

- **RollingDevelopmentFile: Development logs**
    * Development logs are used for debugging
    * (trace level and higher)

- **RollingApplicationFile: Application logs**

    * Application logs are used for monitoring
    * (info level and higher)

- **RollingApplicationJsonFile: Application logs in JSON format**

    * Application logs are used to better analyze the logs within Grafana
    * (trace level and higher)

And one default ConsoleAppender (trace level and higher).

### Loggers

Loggers are used to differentiate between different log levels and appenders.
