package dena.api.common.delegates;

import jakarta.inject.Provider;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.api.delegates.ClientAPIServiceDelegateBase;
import r01f.services.interfaces.ServiceInterface;

public abstract class DN00ClientAPIDelegateForDENAServiceBase<S extends ServiceInterface>
	          extends ClientAPIServiceDelegateBase<S> {
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	public DN00ClientAPIDelegateForDENAServiceBase(final Provider<SecurityContext> securityContextProvider,
			  									   final Marshaller modelObjectsMarshaller, final S serviceProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  serviceProxy);
    }
}
