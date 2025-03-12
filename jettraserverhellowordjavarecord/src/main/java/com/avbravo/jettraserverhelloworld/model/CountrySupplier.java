package com.avbravo.jettraserverhelloworld.model;
// <editor-fold defaultstate="collapsed" desc="imports">

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
/**
 * Java
 */
import java.time.LocalDateTime;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.UUID;
/**
 * Jmoordb
 */
import com.jmoordb.core.util.MessagesUtil;
import com.jmoordb.core.util.JmoordbCoreDateUtil;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.enumerations.TypePK;
import com.jmoordb.core.annotation.enumerations.TypeReferenced;
/**
 * MongoDB
 */
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import com.avbravo.jettraserverhelloworld.model.Country;
import com.avbravo.jettraserverhelloworld.model.*;

// </editor-fold>
@RequestScoped
public class CountrySupplier implements Serializable {
// <editor-fold defaultstate="collapsed" desc="inject">

    @Inject
    ActionHistorySupplier actionHistorySupplier;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Country get(Supplier<? extendsCountry> s, Document document, Boolean... showError) ">

    public Country get(Supplier<? extends Country> s, Document document_, Boolean... showError) {
        Country country = s.get();
        Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }

//	 country.idcountry(document_.getString("idcountry"));
//	country.setCountry(document_.getString("country"));
            // Embedded List<actionHistory>
            List<ActionHistory> actionHistoryList = new ArrayList<>();
            List<Document> actionHistoryDoc = (List) document_.get("actionHistory");
            if (actionHistoryDoc == null || actionHistoryDoc.isEmpty()) {

            } else {
                for (Document docActionHistory : actionHistoryDoc) {
                    ActionHistory actionHistory = actionHistorySupplier.get(ActionHistory::new, docActionHistory);
                    actionHistoryList.add(actionHistory);
                }
            }

            return new Country(document_.getString("idcountry"), document_.getString("country"), actionHistoryList);

        } catch (Exception e) {
            if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
            }
        }
        return country;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Country getId(Supplier<? extendsCountry> s, Document document, Boolean... showError) ">

    public Country getId(Supplier<? extends Country> s, Document document_, Boolean... showError) {
        Country country = s.get();
        Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }

            return new Country(document_.getString("idcountry"), country.country(), country.actionHistory());

        } catch (Exception e) {
            if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
            }
        }
        return country;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Country putObjectId(Country country String _id, Boolean... showError) ">

    public Country putObjectId(Country country, String _id, Boolean... showError) {
        Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }

        } catch (Exception e) {
            if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
            }
        }
        return country;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toDocument (Country country) ">

    public Document toDocument(Country country) {
        Document document_ = new Document();
        try {

            document_.put("idcountry", country.idcountry());
            document_.put("country", country.country());

            // Embedded List<actionHistory>
            document_.put("actionHistory", actionHistorySupplier.toDocument(country.actionHistory()));

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
        }
        return document_;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toDocument (List<Country> countryList) ">

    public List<Document> toDocument(List<Country> countryList) {
        List<Document> documentList_ = new ArrayList<>();
        try {

            for (Country country : countryList) {
                Document document_ = new Document();
                document_.put("idcountry", country.idcountry());
                document_.put("country", country.country());

                // Embedded List<actionHistory>
                document_.put("actionHistory", actionHistorySupplier.toDocument(country.actionHistory()));
                documentList_.add(document_);

            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
        }
        return documentList_;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Bson toUpdate (Country country) ">

    public Bson toUpdate(Country country) {
        Bson update_ = Filters.empty();
        try {
            update_ = Updates.combine(
                    Updates.set("idcountry", country.idcountry()),
                    Updates.set("country", country.country()),
                    // Embedded List<actionHistory>
                    Updates.set("actionHistory", actionHistorySupplier.toDocument(country.actionHistory()))
            );
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
        }
        return update_;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Bson> toUpdate (List<Country> countryList) ">

    public List<Bson> toUpdate(List<Country> countryList) {
        List<Bson> bsonList_ = new ArrayList<>();
        try {
            for (Country country : countryList) {
                Bson update_ = Filters.empty();
                update_ = Updates.combine(
                        Updates.set("idcountry", country.idcountry()),
                        Updates.set("country", country.country()),
                        // Embedded List<actionHistory>
                        Updates.set("actionHistory", actionHistorySupplier.toDocument(country.actionHistory()))
                );
                bsonList_.add(update_);

            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
        }
        return bsonList_;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toReferenced (Country country) ">

    public Document toReferenced(Country country) {
        Document document_ = new Document();
        try {

            document_.put("idcountry", country.idcountry());

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
        }
        return document_;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toReferenced(List<Country> countryList) ">

    public List<Document> toReferenced(List<Country> countryList) {
        List<Document> documentList_ = new ArrayList<>();
        try {

            for (Country country : countryList) {
                Document document_ = new Document();
                document_.put("idcountry", country.idcountry());
                documentList_.add(document_);

            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
        }
        return documentList_;
    }
// </editor-fold>

}
