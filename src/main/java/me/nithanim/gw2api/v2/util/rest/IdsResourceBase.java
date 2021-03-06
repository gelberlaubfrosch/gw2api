package me.nithanim.gw2api.v2.util.rest;

import com.sun.jersey.api.client.WebResource;
import me.nithanim.gw2api.v2.util.reflect.ReflectUtil;

/**
 * This class aims to provide a base class that can handle various combinations
 * with ids with language and the apikey parameters. The resource that is given
 * to the client must only expose the methods that are really provided by the
 * official api. This will most likely be only a subset of this methods.
 *
 * @param <DATA_CLASS> The class of the object that is returned when the api is
 * queried with an id
 * @param <OVERVIEW_CLASS> The class of the object that is returned when
 * querying all possible ids
 */
public class IdsResourceBase<DATA_CLASS, OVERVIEW_CLASS> {
    protected final WebResource webResource;
    private final Class<DATA_CLASS> dataClass;
    private final Class<DATA_CLASS[]> dataClassArray;
    private final Class<OVERVIEW_CLASS> overviewClass;

    public IdsResourceBase(WebResource webResource, Class<DATA_CLASS> dataClass, Class<OVERVIEW_CLASS> overviewClass) {
        this.webResource = webResource;
        this.dataClass = dataClass;
        this.dataClassArray = ReflectUtil.getArrayClass(dataClass);
        this.overviewClass = overviewClass;
    }

    public OVERVIEW_CLASS getOverview() {
        return RequestHelper.getRequest(webResource, overviewClass);
    }
    

    public DATA_CLASS get(int id) {
        return RequestHelper.getRequest(webResource.path(String.valueOf(id)), dataClass);
    }

    public DATA_CLASS get(int id, String language) {
        return RequestHelper.getRequest(webResource.path(String.valueOf(id)), dataClass, "lang", language);
    }

    public DATA_CLASS[] get(int[] ids) {
        return RequestHelper.getRequest(webResource, dataClassArray, "ids", DataUtil.intsToCommaSeparatedString(ids));
    }

    public DATA_CLASS[] get(int[] ids, String language) {
        return RequestHelper.getRequest(webResource, dataClassArray, "ids", DataUtil.intsToCommaSeparatedString(ids), "lang", language);
    }
    
    public DATA_CLASS[] getAll() {
        return RequestHelper.getRequest(webResource, dataClassArray, "ids", "all");
    }
    
    public DATA_CLASS[] getAll(String language) {
        return RequestHelper.getRequest(webResource, dataClassArray, "ids", "all", "lang", language);
    }
}
