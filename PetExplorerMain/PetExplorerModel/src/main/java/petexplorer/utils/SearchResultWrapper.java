package petexplorer.utils;

import org.hibernate.Session;
import petexplorer.domain.*;

public class SearchResultWrapper {
    private String name;
    private String category;
    private Integer id;
    private Object entity;

    public SearchResultWrapper(String name, String category, Integer id) {
        this.name = name;
        this.category = category;
        this.id = id;
    }


    public Object getEntity(Session session) {
        if (entity == null) {
            entity = loadEntity(session);
        }

        return entity;
    }


    private Object loadEntity(Session session) {
        switch (category) {
            case "Salon":
                return session.get(Salon.class, id);
            case "Pensiune Canina":
                return session.get(PensiuneCanina.class, id);
            case "Parc":
                return session.get(Parc.class, id);
            case "Cabinet":
                return session.get(CabinetVeterinar.class, id);
            case "Magazin":
                return session.get(Magazin.class, id);
            case "Farmacie":
                return session.get(Farmacie.class, id);
            default:
                throw new IllegalArgumentException("Tip necunoscut: " + category);
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "SearchResultWrapper{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", entity=" + entity +
                '}';
    }
}
