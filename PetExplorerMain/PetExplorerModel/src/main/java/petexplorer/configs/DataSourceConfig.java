package petexplorer.configs;


import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://aws-1-eu-north-1.pooler.supabase.com:6543/postgres")
                .username("postgres.dcduwmhhemvowdwtkfeq")
                .password("Petexplorer2025")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}