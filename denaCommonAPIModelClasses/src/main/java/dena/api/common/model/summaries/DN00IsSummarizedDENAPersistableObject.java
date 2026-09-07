package dena.api.common.model.summaries;

import dena.api.common.model.DN00IsDENAPersistableObject;
import dena.api.common.model.oids.DN00CommonOIDs.DN00IsDENAPersistableObjectOID;
import r01f.facets.HasLangInDependentName;
import r01f.facets.HasOID;
import r01f.model.SummarizedPersistableObject;

/**
 * Interface for a model object summary
 * @param <O>
 * @param <M>
 */
public interface DN00IsSummarizedDENAPersistableObject<O extends DN00IsDENAPersistableObjectOID,M extends DN00IsDENAPersistableObject<O>>
	     extends SummarizedPersistableObject<O,M>,
	     		 DN00IsSummarizedDENAObject<M>,
	     		 HasOID<O>,
	     		 HasLangInDependentName {
	// a marker interface
}
