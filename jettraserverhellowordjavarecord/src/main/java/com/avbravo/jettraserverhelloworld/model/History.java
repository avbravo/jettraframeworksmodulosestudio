/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Embedded;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.enumerations.GenerationType;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@Entity
public class History {

    @Id(strategy = GenerationType.AUTO)
    private Long idhistory;
    @Column
    private String database;
    @Column
    private String collection;
    @Column
    private String idcollection;
    @Embedded
    private ActionHistory actionHistory;

    @Column
    private String data;

    public History() {
    }

    public History(Long idhistory, String database, String collection, String idcollection, ActionHistory actionHistory, String data) {
        this.idhistory = idhistory;
        this.database = database;
        this.collection = collection;
        this.idcollection = idcollection;
        this.actionHistory = actionHistory;
        this.data = data;
    }

    public Long getIdhistory() {
        return idhistory;
    }

    public void setIdhistory(Long idhistory) {
        this.idhistory = idhistory;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getIdcollection() {
        return idcollection;
    }

    public void setIdcollection(String idcollection) {
        this.idcollection = idcollection;
    }

    public ActionHistory getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(ActionHistory actionHistory) {
        this.actionHistory = actionHistory;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("History{");
        sb.append("idhistory=").append(idhistory);
        sb.append(", database=").append(database);
        sb.append(", collection=").append(collection);
        sb.append(", idcollection=").append(idcollection);
        sb.append(", actionHistory=").append(actionHistory);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idhistory);
        hash = 59 * hash + Objects.hashCode(this.database);
        hash = 59 * hash + Objects.hashCode(this.collection);
        hash = 59 * hash + Objects.hashCode(this.idcollection);
        hash = 59 * hash + Objects.hashCode(this.actionHistory);
        hash = 59 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.database, other.database)) {
            return false;
        }
        if (!Objects.equals(this.collection, other.collection)) {
            return false;
        }
        if (!Objects.equals(this.idcollection, other.idcollection)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.idhistory, other.idhistory)) {
            return false;
        }
        return Objects.equals(this.actionHistory, other.actionHistory);
    }

    public static class Builder {

        private Long idhistory;

        private String database;

        private String collection;

        private String idcollection;

        private ActionHistory actionHistory;

        private String data;

        public Builder idhistory(Long idhistory) {
            this.idhistory = idhistory;
            return this;
        }

        public Builder database(String database) {
            this.database = database;
            return this;
        }

        public Builder collection(String collection) {
            this.collection = collection;
            return this;
        }

        public Builder idcollection(String idcollection) {
            this.idcollection = idcollection;
            return this;
        }
        public Builder data(String data) {
            this.data= data;
            return this;
        }

        public Builder actionHistory(ActionHistory actionHistory) {
            this.actionHistory = actionHistory;
            return this;
        }

        public History build() {
            return new History(idhistory, database, collection, idcollection, actionHistory, data);
        }

    }

}
