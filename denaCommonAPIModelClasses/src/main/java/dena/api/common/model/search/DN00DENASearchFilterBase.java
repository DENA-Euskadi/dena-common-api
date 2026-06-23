package dena.api.common.model.search;

import java.util.Collection;

import lombok.experimental.Accessors;
import r01f.model.ModelObject;
import r01f.model.search.SearchFilterForModelObjectBase;

@Accessors(prefix="_")
public abstract class DN00DENASearchFilterBase<SELF_TYPE extends DN00DENASearchFilterBase<SELF_TYPE>>
              extends SearchFilterForModelObjectBase<SELF_TYPE>
		   implements DN00IsDENASearchFilter {

	private static final long serialVersionUID = -7328506874819631272L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00DENASearchFilterBase() {
		super();	// default no-args constructor
	}
	public DN00DENASearchFilterBase(final Class<? extends ModelObject> modelObjectTypes) {
		super(modelObjectTypes);
	}
	public DN00DENASearchFilterBase(@SuppressWarnings("unchecked") final Class<? extends ModelObject>... modelObjectTypes) {
		super(modelObjectTypes);
	}
	public DN00DENASearchFilterBase(final Collection<Class<? extends ModelObject>> modelObjectTypes) {
		super(modelObjectTypes);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
