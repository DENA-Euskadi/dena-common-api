package dena.api.common.model.types.url;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Predicate;

import r01f.facets.HasID;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.url.HasUrl;
import r01f.types.url.Url;
import r01f.util.types.collections.CollectionUtils;


@MarshallType(as="endpointCollection")
public abstract  class DN00EndpointUrlCollectionBase<U extends HasUrl & HasID<?>>
        extends LinkedHashSet<U> {
	
	private static final long serialVersionUID = 7084501431723537076L;


	@SuppressWarnings("unchecked")
	public DN00EndpointUrlCollectionBase<U> addUrls(final U... urls) {
        if (CollectionUtils.hasData(urls)) return this.addUrls(Arrays.asList(urls));
        return this;
    }
    public DN00EndpointUrlCollectionBase<U> addUrls(final Collection<U> urls) {
        if (CollectionUtils.hasData(urls)) this.addAll(urls);
        return this;
    }
    public boolean removeUrl(final Url url) {
        if (this.size() == 0) return false;

        Collection<U> itemsToBeRemoved = new ArrayList<>();
        // find the items to be removed
        for (U item : this) {
            if (url.equals(item.getUrl())) itemsToBeRemoved.add(item);
        }
        // Effectively remove the items
        boolean removed = this.removeAll(itemsToBeRemoved);
        return removed;
    }

    public U getUrlWithId(final DN00EndpointUrlID id) {
        Predicate<U> filterById = item -> id.equals(item.getId());
        return this.stream()
	                .filter(filterById)
	                .findFirst()
	                .orElse(null);
    }
}
