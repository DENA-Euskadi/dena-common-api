package dena.api.common.model.summaries;

import dena.api.common.model.DN00IsDENAModelObject;
import r01f.debug.Debuggable;
import r01f.model.SummarizedModelObject;

/**
 * Interface for a model object summary
 * @param <O>
 * @param <M>
 */
public interface DN00IsSummarizedDENAObject<M extends DN00IsDENAModelObject>
	     extends SummarizedModelObject<M>,
	     		 Debuggable {
	// just a marker interface
}
