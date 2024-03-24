package cmc.sp.webprac.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.SessionFactoryUtils;

import javax.sql.DataSource;

public class DatabaseInit {
    private final SessionFactory sessionFactory;

    @Autowired
    public DatabaseInit(LocalSessionFactoryBean sessionFactoryBean) {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

    private void executeSQLScript(String scriptName) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(new ClassPathResource(scriptName));
        DataSource source = SessionFactoryUtils.getDataSource(sessionFactory);
        if (source != null) {
            populator.execute(source);
        } else {
            throw new ExecutionException("Couldn't retrieve DataSource");
        }
    }

    public void create() {
        executeSQLScript("sql/schema.sql");
    }

    public void populate() {
        executeSQLScript("sql/data.sql");
    }

    public void clear() {
        executeSQLScript("sql/clear.sql");
    }

    public void drop() {
        executeSQLScript("sql/drop.sql");
    }

}
