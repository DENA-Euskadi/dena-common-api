package dena.api.common.model.security;

import dena.api.common.model.DN00IsDENAModelObject;
import r01f.objectstreamer.annotations.MarshallPolymorphicTypeInfo;

@MarshallPolymorphicTypeInfo(typeIdPropertyName = "type")
public interface DN00IsDENASecurityConfig
	     extends DN00IsDENAModelObject {
	// just a marker interface to be implemented by all model objects of the consent domain
}
