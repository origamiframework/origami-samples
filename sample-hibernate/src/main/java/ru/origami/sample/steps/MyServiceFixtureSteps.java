package ru.origami.sample.steps;

import ru.origami.common.OrigamiHelper;
import ru.origami.common.environment.Environment;
import ru.origami.hibernate.CommonFixtureSteps;
import ru.origami.hibernate.models.DataBaseSessionProperties;
import ru.origami.hibernate.models.EHibernateResource;
import ru.origami.sample.entity.Request;
import ru.origami.testit_allure.annotations.Step;

import java.util.List;
import java.util.UUID;

public class MyServiceFixtureSteps extends CommonFixtureSteps {

    public MyServiceFixtureSteps() {
        sessionProperties = new DataBaseSessionProperties.Builder()
                .setHibernateResource(EHibernateResource.POSTGRES)
                .setDbHost(Environment.get("db.host"))
                .setDbName(Environment.get("db.name"))
                .setDbPort(Environment.get("db.port"))
                .setDbUserName(Environment.get("db.username"))
                .setDbPassword(Environment.get("db.password"))
                .setSchema(Environment.get("db.schema"))
                .build();

        initSession();
    }

    @Step("Получаем все заявки")
    public List<Request> getAllRequests() {
        final String hql = "SELECT r FROM Request r";

        return session.createQuery(hql, Request.class)
                .getResultList();
    }

    @Step("Получаем заявку по id: {id}")
    public Request getRequestById(UUID id) {
        final String hql = """
                SELECT r FROM Request r
                WHERE r.id=:id
                """;

        return session.createQuery(hql, Request.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Step("Очищаем таблицу {table}")
    public void deleteFromTableByName(String table) {
        final String sql = """
                DELETE FROM :schemaName.:table
                """;

        session.createNativeQuery(sql)
                .changeParameter("schemaName", this.sessionProperties.getSchema())
                .changeParameter("table", table)
                .executeUpdate();
    }

    @Step("Добавление заявки")
    public void addRequest(UUID id) {
        final String sql = """
                INSERT INTO :schemaName.requests(id, created_at, comment)
                VALUES (:id, NOW(), :comment)
                """;

        session.createNativeQuery(sql)
                .changeParameter("schemaName", this.sessionProperties.getSchema())
                .setParameter("id", id)
                .setParameter("comment", OrigamiHelper.getRandomCyrillicString(10))
                .executeUpdate();
    }

    @Step("Обновляем комментарий заявки \"{id}\" на \"{comment}\"")
    public void updateCommentById(UUID id, String comment) {
        final String sql = """
                UPDATE :schemaName.requests
                SET comment = :comment
                WHERE id = :id
                """;

        session.createNativeQuery(sql)
                .changeParameter("schemaName", this.sessionProperties.getSchema())
                .setParameter("comment", comment)
                .setParameter("id", id)
                .executeUpdate();
    }

    // Создание таблиц, последовательностей и тд обычно осуществляется при подъеме тестируемого сервиса
    public void createRequestTable() {
        final String sql = """
                CREATE TABLE public.requests (
                    id UUID PRIMARY KEY,
                    created_at TIMESTAMPTZ NOT NULL,
                    comment TEXT
                );
                """;

        session.createNativeQuery(sql)
                .executeUpdate();
    }
}
