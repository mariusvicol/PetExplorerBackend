package petexplorer;


import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres")
                .username("postgres.lugkzbamwteflagftckw")
                .password("petexplorer.2004")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}